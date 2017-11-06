package com.xsty.scheduler.command;

/**
 * Created by XST on 26/10/2017.
 */
public abstract class CommandHandler<T> {
    protected abstract Command<T> handle(T element);
}
