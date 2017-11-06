package com.xsty.telegram.bot;

import com.xsty.telegram.alert.AlertAction;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.sender.MessageSender;

import static org.telegram.abilitybots.api.objects.Locality.ALL;
import static org.telegram.abilitybots.api.objects.Privacy.PUBLIC;

public class DummyBot extends AbilityBot {

    public int adminId;

    public DummyBot(String token, String botName, int adminId) {
        super(token, botName);
        this.adminId = adminId;
    }

    @Override
    public int creatorId() {
        return adminId;
    }

    public Ability alert() {
        return Ability
                .builder()
                .name("alert")
                .info("Create an alert!!!")
                .locality(ALL)
                .privacy(PUBLIC)
                .action(ctx -> AlertAction.action(ctx))
                .build();
    }

    public MessageSender getMessageSender(){
        return this.sender;
    }
}
