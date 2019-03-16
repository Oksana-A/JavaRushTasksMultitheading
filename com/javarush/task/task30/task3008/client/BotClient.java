package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BotClient extends Client {
    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        int x = (int) (Math.random()*100);
        return "date_bot_" + x;
    }

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message != null && message.contains(":")) {
                String[] str = message.split(":");
                if (str.length == 2) {
                    String userName = str[0].trim();
                    String text = str[1].trim();
                    Map<String, String> map = new HashMap<>();
                    map.put("дата", "d.MM.YYYY");
                    map.put("день", "d");
                    map.put("месяц", "MMMM");
                    map.put("год", "YYYY");
                    map.put("время", "H:mm:ss");
                    map.put("час", "H");
                    map.put("минуты", "m");
                    map.put("секунды", "s");
                    if (map.containsKey(text)) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat(map.get(text));
                        String date = dateFormat.format(Calendar.getInstance().getTime());
                        String sendString = String.format("Информация для %s: %s", userName, date);
                        sendTextMessage(sendString);
                    }
                }
            }

        }
    }



}
