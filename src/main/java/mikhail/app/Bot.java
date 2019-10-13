package mikhail.app;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends TelegramLongPollingBot {

    /**
     * Метод для приема сообщений
     * @param update Содержиь сообщения от пользователя
     */
    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);

    }

    /**
     * Метод для настройки сообщения отправки.
     * @param chatId id чато
     * @param s Строка, которую необходимо отправить в качестве сообщения
     */
    public synchronized void sendMsg(String chatId, String s){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(s);

        try {
            sendMessage(sendMessage);
        }catch (TelegramApiException e){
            Logger log = null;
            log.log(Level.SEVERE, "Exception: ", e.toString());
        }
    }

    /**
     * Метод возвращает имя бота, указанное при регистрации.
     * @return имя бота
     */
    @Override
    public String getBotUsername() {
        //return null; //”BotName”
        return "Bear";
    }

    /**
     * Метод возвращает token бота для связи с сервером Telegram
     * @return token для бота
     */
    @Override
    public String getBotToken() {
        //return null; //”BotToken”
        String token = "123456:ABC-DEF1234ghIkl-zyx57W2v1u123ew11";
        return token;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(Bot.getBot());
        }catch (TelegramApiRequestException e){
            e.printStackTrace();
        }
    }


}
