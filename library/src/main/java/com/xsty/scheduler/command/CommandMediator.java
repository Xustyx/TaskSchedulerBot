package com.xsty.scheduler.command;


import java.util.*;

/**
 * Created by XST on 26/10/2017.
 */
public class CommandMediator extends Observable implements Observer {
    //TODO: Make delegaton class and builder to easy configure.
    private Map<Observable,List<CommandHandler>> delegations = new HashMap<>();

    @Override
    public void update(Observable o, Object arg) {
        if(arg != null) /// recursive exit.
            for(CommandHandler commandHandler : delegations.get(o)) {
                this.setChanged();
                notifyObservers(commandHandler.handle(arg));
            }
    }

    public void addObservableHandlerList(Observable observable,List<CommandHandler> commandHandlerList){
        observable.addObserver(this);
        this.delegations.put(observable,commandHandlerList);
    }

}
