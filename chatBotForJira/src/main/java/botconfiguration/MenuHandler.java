package botconfiguration;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MenuHandler {


//    String command = update.getMessage().getText();
//    long chatId = update.getMessage().getChatId();
//    SendMessage response = new SendMessage();
//
//        if (update.hasMessage() && update.getMessage().hasText()) {
//
//
//        if (command.equals("/start")) {
//            response.setChatId(chatId);
//            response.setText("    Menu  ");
//
//            InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
//            List<List<InlineKeyboardButton>> rows = new ArrayList<>();
//
//
//            // First row button list
//            List<InlineKeyboardButton> row1 = new ArrayList<>();
//
//            // First button
//            InlineKeyboardButton button1 = new InlineKeyboardButton("\uD83D\uDD0D Axtarış");
//            button1.setCallbackData("button1");
//            row1.add(button1);
//
//            rows.add(row1);
//
//            List<InlineKeyboardButton> row2 = new ArrayList<>();
//
//            //Second button
//            InlineKeyboardButton button2 = new InlineKeyboardButton("\uD83D\uDCC4 İstifadəçilərin listi");
//            button2.setCallbackData("button2");
//            row2.add(button2);
//
//            rows.add(row2);
//
//            List<InlineKeyboardButton> row3 = new ArrayList<>();
//            InlineKeyboardButton button3 = new InlineKeyboardButton("✅ Run");
//            button3.setCallbackData("button3");
//            row3.add(button3);
//
//            rows.add(row3);
//
//
//            keyboardMarkup.setKeyboard(rows);
//            response.setReplyMarkup(keyboardMarkup);
//
//            try {
//                execute(response);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//
//
//    } else if (update.hasCallbackQuery()) {
//        Message message = update.getCallbackQuery().getMessage();
//        CallbackQuery callbackQuery = update.getCallbackQuery();
//        String data = callbackQuery.getData();
//        response.setChatId(message.getChatId());
//        if (data.equals("button1")) {
//            response.setText("Axtaris secildi");
//        } else if (data.equals("button2")) {
//            response.setText("Button2 secildi");
//        }
//        try {
//            execute(response);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//
//    }


}


