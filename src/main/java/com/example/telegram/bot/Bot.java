package com.example.telegram.bot;

import com.example.telegram.models.CityInfo;
import com.example.telegram.repositories.CityInfoRepository;
import com.example.telegram.services.CityInfoService.CityInfoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    private CityInfoService service;
    static final Logger LOGGER = LogManager.getLogger(Bot.class);

    /**
     * Method that return bot's token
     * @return token
     */
    @Override
    public String getBotToken() {
        return "1203448089:AAH8eWSTO6CT6iw_p_qEt6kcCJPzFLijxqA";
    }

    /**
     * Method that processes telegram bot.
     * @param update input information
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            //check that message exist and contain text
            if (update.hasMessage() && update.getMessage().hasText()) {
                String answer;
                Message inMessage = update.getMessage();
                String inputText = inMessage.getText().toLowerCase();
                CityInfo cityInfo = service.getByName(inputText);
                if(cityInfo != null){
                    answer = cityInfo.getCityDescription();
                }else {
                    List<String> listOfCities  = service.getAllCityNames();
                    answer = "Неверный ввод, попробуйте ввести город из предложенных\n" +
                            listOfCities;
                }
                //Create output message
                SendMessage outMessage = new SendMessage();
                outMessage.setChatId(inMessage.getChatId());
                outMessage.setText(answer);
                //Send message
                execute(outMessage);
            }
        } catch (TelegramApiException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method that return bot's name.
     * @return name
     */
    @Override
    public String getBotUsername() {
        return "RomanovTravelbot";
    }
    @PostConstruct
    public void start() {
        LOGGER.info("username: {}, token: {}", getBotUsername(), getBotToken());
    }
}
