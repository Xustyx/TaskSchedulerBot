package com.xsty.scheduler.config;

import com.xsty.scheduler.command.CommandHandler;
import com.xsty.scheduler.command.CommandMediator;
import com.xsty.scheduler.command.CommandMediatorHandler;
import com.xsty.scheduler.task.TaskCommandHandler;
import com.xsty.scheduler.task.TaskService;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.*;

public abstract class CommandMediatorConfigAdapter {

    @Bean
    public CommandMediator commandMediator(){
        CommandMediator commandMediator = new CommandMediator();
        addDelegations(commandMediator);
        return commandMediator;
    }
    
    private void addDelegations(CommandMediator commandMediator){
        Map<Observable,List<CommandHandler>> delegations = new HashMap<>();
        delegations.put(commandMediator, commandMediatorHandlers());
        delegations.put(taskService(), taskServiceHandlers());

        Map<Observable,List<CommandHandler>> customDelegations = new HashMap<>();
        configureDelegations(customDelegations);
        delegations.putAll(customDelegations);

        for (Map.Entry<Observable,List<CommandHandler>> entry: delegations.entrySet()) {
            commandMediator.addObservableHandlerList(entry.getKey(),entry.getValue());
        }
    }

    protected abstract void configureDelegations( Map<Observable,List<CommandHandler>> customDelegations);


    private List<CommandHandler> commandMediatorHandlers(){
        List<CommandHandler> commandMediatorHandlers = new ArrayList<>();
        commandMediatorHandlers.add(commandMediatorHandler());
        return commandMediatorHandlers;
    }

    private CommandHandler commandMediatorHandler(){
        return new CommandMediatorHandler(taskService());
    }

    public abstract ThreadPoolTaskScheduler threadPoolTaskScheduler();


    @Bean
    public TaskService taskService() {
        return new TaskService();
    }

    private CommandHandler taskCommandHandler(){
        return new TaskCommandHandler(threadPoolTaskScheduler(),taskService());
    }

    private List<CommandHandler> taskServiceHandlers(){
        List<CommandHandler> taskServiceHandlers = new ArrayList<>();
        taskServiceHandlers.add(taskCommandHandler());
        return taskServiceHandlers;
    }
}
