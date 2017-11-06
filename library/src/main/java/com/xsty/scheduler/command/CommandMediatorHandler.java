package com.xsty.scheduler.command;


import com.xsty.scheduler.task.Task;
import com.xsty.scheduler.task.TaskService;

/**
 * Created by XST on 27/10/2017.
 */
public class CommandMediatorHandler extends CommandHandler<Command> {

    private TaskService taskService;

    public CommandMediatorHandler(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public Command handle(Command command) {
        Task task = new Task(command.scheduledDate(), command);
        taskService.save(task);
        return null; /// recursive exit.
    }
}
