package com.xsty.scheduler.task;

import com.xsty.scheduler.command.Command;
import com.xsty.scheduler.command.CommandHandler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledFuture;

/**
 * Created by XST on 27/10/2017.
 */
public class TaskCommandHandler extends CommandHandler<Task> {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private TaskService taskService;

    public TaskCommandHandler(ThreadPoolTaskScheduler threadPoolTaskScheduler, TaskService taskService) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.taskService = taskService;
    }

    @Override
    public Command handle(Task element) {
        ScheduledFuture future = threadPoolTaskScheduler.schedule(element.getTaskElement(), element.getScheduledDate());
        element.setScheduledFuture(future);
        return null;
    }
}
