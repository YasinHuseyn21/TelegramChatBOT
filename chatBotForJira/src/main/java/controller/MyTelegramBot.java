package controller;

import ldap.LDAP;
import model.UserAdmin;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.naming.NamingException;
import java.util.LinkedList;
import java.util.List;

public class MyTelegramBot extends TelegramLongPollingBot {

    private static final String YOUR_PHONE_NUMBER = "0505090713";


    LDAP ldp = new LDAP();
    boolean result;
    UserAdmin userAdmin=new UserAdmin();


    @Override
    public void onUpdateReceived(Update update) {

        String command = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        SendMessage response = new SendMessage();

        Message message = update.getMessage();
        Integer messageId = message.getMessageId();

        String text = message.getText();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId());

//        command.equals("556") || command.equals("/user") || command.equals("/search") || command.equals("/run")

        if (command.equals(userAdmin.getPassword()) || command.equals("/user") || command.equals("/search") || command.equals("/run")) {
            if(command.equals(userAdmin.getPassword())) {

                result = LDAP.authUser("HP", command);
                if (result == false) {
                    result = Boolean.parseBoolean(null);
                    return;
                }
                String messageText = "Salam, Bot aktivdir başlaya bilərik! ✅";

                sendMessage.setText(messageText);
                sendMessage.setChatId(update.getMessage().getChatId());
                try {
                    execute(sendMessage);
                    return;

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }


        if (command.equals("/logout")) {

            String message2 = " Sistemən uğurla çıxış etdiniz \uD83E\uDD16 :   ";
            response.setChatId(chatId);
            response.setText(message2);
            try {
                execute(response);
                result = Boolean.parseBoolean(null);
                return;
            } catch (TelegramApiException exception) {
                exception.printStackTrace();
            }

        }


        if (command.equals("/start")) {

            String message2 = " Botun aktiv olunmasi ucun password daxil edin :   ";
            response.setChatId(chatId);
            response.setText(message2);
            try {
                execute(response);
                result = Boolean.parseBoolean(null);
                return;
            } catch (TelegramApiException exception) {
                exception.printStackTrace();
            }

        }


        if (result == false) {
            String messageTextErr1 = " Xidmət aktiv deyil ❌ ";
            SendMessage sendMessageErr = new SendMessage();

            sendMessageErr.setText(messageTextErr1);
            sendMessageErr.setChatId(update.getMessage().getChatId());
            try {
                execute(sendMessageErr);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }


        }
        if (result == true) {

//            if(!command.equals("/run")){
//                if(!command.equals("/user")){
//                    if(!command.equals("/search")){
//                        String messageTextErr1 = " Göndərdiyiniz əmri başa düşmədim \uD83E\uDD37\uD83C\uDFFB\u200D♂\uFE0F ";
//                        SendMessage sendMessageErr = new SendMessage();
//
//                        sendMessageErr.setText(messageTextErr1);
//                        sendMessageErr.setChatId(update.getMessage().getChatId());
//                        try {
//                            execute(sendMessageErr);
//
//                        } catch (TelegramApiException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }
//            }



            if (text.equals("/Menu")) {
                sendMessage.setText(" Select your command ");
                sendMessage.setParseMode("Markdown");

                InlineKeyboardButton searchButton = new InlineKeyboardButton();
                searchButton.setText("Search");
                searchButton.setCallbackData("search");

                InlineKeyboardButton allUsersButton = new InlineKeyboardButton();
                allUsersButton.setText("All Users");
                allUsersButton.setCallbackData("users");

                List<InlineKeyboardButton> row = new LinkedList<>();
                row.add(searchButton);
                row.add(allUsersButton);

                List<List<InlineKeyboardButton>> rowCollection = new LinkedList<>();
                rowCollection.add(row);

                InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
                inlineKeyboardMarkup.setKeyboard(rowCollection);

                sendMessage.setReplyMarkup(inlineKeyboardMarkup);


                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }


            }
            if (command.equals("/run")) {

                String message2 = " A Telegram Bot is a type of chatbot that is designed to interact with users on the popular messaging platform Telegram. It is a computer program that is designed to simulate human conversation and provide automated responses to user queries. Telegram Bots can be used to automate various tasks, such as providing customer service, sending notifications, and providing information.\n" +
                        "\n" +
                        "Telegram Bots can be used to help businesses in a variety of ways. For example, they can be used to provide customer service, such as answering questions and providing support. They can also be used to send notifications, such as order confirmations or product updates. Additionally, they can be used to provide information, such as product descriptions or pricing information.\n" +
                        "\n" +
                        "Overall, Telegram Bots can be a great way for businesses to automate tasks and provide better customer service. They can help businesses save time and money, while also providing a more efficient and personalized experience for customers.  ";
                response.setChatId(chatId);
                response.setText(message2);
                try {
                    execute(response);
                } catch (TelegramApiException exception) {
                    exception.printStackTrace();
                }

            }


            if (command.equals("/user")) {

                try {
                    List<String> string = ldp.getAllUsers();
                    for (String message3 : string) {

//                    SendMessage response = new SendMessage();
                        response.setChatId(chatId);
                        response.setText(message3);
                        try {
                            execute(response);
                        } catch (TelegramApiException exception) {
                            exception.printStackTrace();
                        }
                    }
                } catch (NamingException e) {
                    throw new RuntimeException(e);
                }
            }
            if (command.equals("/search")) {

                String message4 = "\uD83D\uDD0D Istifadçi adını qeyd edin: ";
//            SendMessage response = new SendMessage();
                response.setChatId(chatId);
                response.setText(message4);
                try {
                    execute(response);
                } catch (TelegramApiException exception) {
                    exception.printStackTrace();
                }

            }

            try {
                String user = update.getMessage().getText();
                List<String> string = ldp.searchUser(user);
                for (String text2 : string) {
//                SendMessage response = new SendMessage();
                    response.setChatId(chatId);
                    response.setText(text2);
                    try {
                        execute(response);
                    } catch (TelegramApiException exception) {
                        exception.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            if (command.equals("/adduser")) {

                String message5 = "\uD83C\uDFC3 İstifadəçi adını qeyd edin: ";

//            SendMessage response = new SendMessage();
                response.setChatId(chatId);
                response.setText(message5);

                try {
                    execute(response);
                    ldp.addUser(command);
                } catch (TelegramApiException exception) {
                    exception.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getBotUsername() {
        return "KapitalJirabot";
    }

    public String getBotToken() {
        // TODO
        return "6370291667:AAFhK8ez5WcUCHERpXVPVQ596EkTNl2vP6c";

    }


}