package com.xsty.telegram.config;

import com.xsty.scheduler.command.CommandHandler;
import com.xsty.scheduler.config.CommandMediatorConfigAdapter;
import com.xsty.telegram.alert.AlertCommandHandler;
import com.xsty.telegram.alert.AlertService;
import com.xsty.telegram.bot.DummyBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.*;

@Configuration
public class CommandMediatorConfiguration extends CommandMediatorConfigAdapter {

    public static final String THREAD_POOL_TASK_SCHEDULER_NAME = "MediatorThreadPoolTaskScheduler";

    @Autowired
    private AlertService alertService;

    @Autowired
    private DummyBot dummyBot;

    @Override
    protected void configureDelegations(Map<Observable, List<CommandHandler>> customDelegations) {
        customDelegations.put(alertService, alertServiceCommandHandlers());
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(4);
        threadPoolTaskScheduler.setThreadNamePrefix(THREAD_POOL_TASK_SCHEDULER_NAME);

        return threadPoolTaskScheduler;
    }

    private List<CommandHandler> alertServiceCommandHandlers() {
        List<CommandHandler> commandHandlers = new ArrayList<>();
        commandHandlers.add(alertCommandHandler());
        return commandHandlers;
    }

    private CommandHandler alertCommandHandler() {
        return new AlertCommandHandler(
                dummyBot.getMessageSender()
        );
    }

}
