package com.xsty.scheduler.task;


import com.xsty.scheduler.command.Command;
import com.xsty.scheduler.common.Identifiable;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by XST on 26/10/2017.
 */
public class Task extends Identifiable {
    private Date scheduledDate;
    private Command taskElement;

    private ScheduledFuture scheduledFuture;

    public Task(Date scheduledDate, Command taskElement) {
        this.scheduledDate = scheduledDate;
        this.taskElement = taskElement;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public Command getTaskElement() {
        return taskElement;
    }

    public boolean isDone(){
        return this.scheduledFuture != null ? this.scheduledFuture.isDone() : false;
    }

    public boolean isCancelled(){
        return this.scheduledFuture != null ? this.scheduledFuture.isCancelled() : false;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setTaskElement(Command taskElement) {
        this.taskElement = taskElement;
    }


    protected ScheduledFuture getScheduledFuture() {
        return scheduledFuture;
    }

    protected void setScheduledFuture(ScheduledFuture scheduledFuture) {
        this.scheduledFuture = scheduledFuture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Task task = (Task) o;

        if (scheduledDate != null ? !scheduledDate.equals(task.scheduledDate) : task.scheduledDate != null)
            return false;
        if (taskElement != null ? !taskElement.equals(task.taskElement) : task.taskElement != null) return false;
        return scheduledFuture != null ? scheduledFuture.equals(task.scheduledFuture) : task.scheduledFuture == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (scheduledDate != null ? scheduledDate.hashCode() : 0);
        result = 31 * result + (taskElement != null ? taskElement.hashCode() : 0);
        result = 31 * result + (scheduledFuture != null ? scheduledFuture.hashCode() : 0);
        return result;
    }
}
