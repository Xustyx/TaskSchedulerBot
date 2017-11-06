package com.xsty.scheduler.task;

import com.xsty.scheduler.common.ObservableCrudListService;
/**
 * Created by XST on 26/10/2017.
 */
public class TaskService extends ObservableCrudListService<Task> {

    public Task cancelTask(Long id){
        Task task = this.findOne(id);

        if(task != null && task.getScheduledFuture() != null)
            task.getScheduledFuture().cancel(true);

        return task;
    }
}
