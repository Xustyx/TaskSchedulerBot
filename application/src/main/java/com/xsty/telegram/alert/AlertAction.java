package com.xsty.telegram.alert;

import org.telegram.abilitybots.api.objects.MessageContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Stack;


public class AlertAction {

    private static SimpleDateFormat simpleDateFormat;
    private static AlertService alertService;

    public AlertAction(AlertService alertService, SimpleDateFormat simpleDateFormat){
        this.alertService = alertService;
        this.simpleDateFormat = simpleDateFormat;

    }

    public static void action(MessageContext ctx) {
        alertService.save(doCreateAlertCtx(ctx));
    }

    private static Alert doCreateAlertCtx(MessageContext ctx){
        Alert alert = new Alert();
        Stack<String> stackArgs = stackArgs(ctx.arguments());

        alert.setAuthor(String.valueOf(ctx.user().id()));
        alert.setChatId(ctx.chatId());
        alert.setDate(doCreateDateCtx(stackArgs));
        alert.setMessage(doCreateMessageCtx(stackArgs));

        return alert;
    }

    private static String doCreateMessageCtx(Stack<String> stackArgs) {
        String msg = "";
        if(stackArgs.size() <= 0)
            stackArgs.push("No message was send");

        while (!stackArgs.empty())
            msg += stackArgs.pop() + " ";

        return msg;
    }

    private static Stack<String> stackArgs(String[] args){
        Stack<String> stackArgs = new Stack<>();
        for(int i = args.length-1; i >=0; i--){
            stackArgs.push(args[i]);
        }
        return stackArgs;
    }

    private static Date doCreateDateCtx(Stack<String> args) {
        Date date = new Date();

        if (args.size() >= 2) {
            String year = args.pop();
            String hour = args.pop();

            try {
                date = simpleDateFormat.parse(year + " " + hour);
            } catch (ParseException e) {
                args.push(hour);
                args.push(year);
            }
        }

        return date;
    }
}
