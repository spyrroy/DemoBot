package com.example.demobot.bot.commands;

import com.example.demobot.bot.constants.Actions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
public class StartCommandHandler extends BotCommand {

    public StartCommandHandler(@Value("start") String commandIdentifier, @Value("") String description) {
        super(commandIdentifier, description);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        try {
            var replyMarkup = InlineKeyboardMarkup.builder()
                    .keyboardRow(List.of(
                            InlineKeyboardButton.builder()
                                    .text("I'm link to youtube chat")
                                    .url("https://www.youtube.com/watch?v=lQ-_F2NUAiE&ab_channel=CodeUA")
                                    .build(),
                            InlineKeyboardButton.builder()
                                    .text("I'm callback button")
                                    .callbackData(Actions.SOME_ACTION)
                                    .build()
                    ))
                    .build();
            var sendMessage = SendMessage.builder()
                    .chatId(chat.getId())
                    .text("Some text")
                    .replyMarkup(replyMarkup)
                    .build();
            absSender.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}
