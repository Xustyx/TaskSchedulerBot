package com.xsty.telegram.config;

import com.xsty.telegram.alert.AlertAction;
import com.xsty.telegram.alert.AlertService;
import com.xsty.telegram.bot.DummyBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.text.SimpleDateFormat;


@Configuration
public class TelegramBotConfiguration {

    private AlertService alertService;
    private SimpleDateFormat simpleDateFormat;

    @Value("${telegram.bot.token:invalid_token}")
    public String botToken;
    @Value("${telegram.bot.name:DummyBot}")
    public String botUsername;
    @Value("${telegram.bot.admin:1}")
    public int adminId;

    public TelegramBotConfiguration(AlertService alertService,@Value("${spring.jackson.date-format:'dd/MM/yyyy hh:mm:ss'}") String datePattern){
        this.alertService = alertService;
        this.simpleDateFormat = new SimpleDateFormat(datePattern);
    }

    @Bean
    public TelegramBotsApi telegramBotsApi(){
        TelegramBotsApi api = new TelegramBotsApi();
        registerBot(api);
        return api;
    }

    private void registerBot(TelegramBotsApi api)  {
        try {
            api.registerBot(dummyBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public DummyBot dummyBot(){
        return new DummyBot(botToken,botUsername,adminId);
    }

    @Bean
    public AlertAction alertAction(){
        return new AlertAction(alertService,simpleDateFormat);
    }
}
