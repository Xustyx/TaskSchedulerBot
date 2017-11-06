package com.xsty.scheduler.command;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * Created by XST on 27/10/2017.
 */
public abstract class Command<T> implements Runnable {

    private Date date = new Date();
    private final Class<T> type;

    public Command(Class<T> type) {
        this.type = type;
    }

    public Date scheduledDate(){
        return date;
    }

    @JsonProperty("type")
    public Class<T> type() {
        return this.type;
    }

    @JsonProperty("id")
    public abstract String id();
}
