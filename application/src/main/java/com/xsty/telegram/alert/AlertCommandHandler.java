package com.xsty.telegram.alert;

import com.xsty.scheduler.command.Command;
import com.xsty.scheduler.command.CommandHandler;
import org.telegram.abilitybots.api.sender.MessageSender;

import java.util.Date;

/**
 * Created by XST on 26/10/2017.
 */
public class AlertCommandHandler extends CommandHandler<Alert> {

    private MessageSender sender;

    public AlertCommandHandler(MessageSender sender){
        this.sender = sender;
    }

    @Override
    protected Command<Alert> handle(Alert element) {
        return new Command<Alert>(Alert.class) {
            @Override
            public Date scheduledDate() {
                return element.getDate();
            }

            @Override
            public String id() {
                return String.valueOf(element.getId());
            }

            @Override
            public void run() {
                sender.send(element.getMessage(),element.getChatId());
            }
        };
    }
}
