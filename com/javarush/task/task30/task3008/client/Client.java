package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected;

    protected String getServerAddress() {
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        return ConsoleHelper.readInt();
    }

    protected String getUserName() {
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    protected void sendTextMessage(String text) {
        try {
            connection.send(new Message(MessageType.TEXT, text));
        }
        catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при отправке сообщения!");
            clientConnected = false;
        }
    }

    public void run() {
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Произошла ошибка!");
                try {
                    connection.close();
                }
                catch (IOException ex) {
                    ex.getMessage();
                }
            }
            if (clientConnected == true) {
                ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
            }
            else {
                ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
            }
            while(clientConnected) {
                String line = ConsoleHelper.readString();
                if (line.equals("exit")) break;
                if (shouldSendTextFromConsole()) sendTextMessage(line);
            }
        }
    }

    public class SocketThread extends Thread {

        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " присоединился к чату.");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " покинул чат.");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                MessageType messageType = connection.receive().getType();
                if (messageType == MessageType.NAME_REQUEST) {
                    String userName = getUserName();
                    connection.send(new Message(MessageType.USER_NAME, userName));
                } else {
                    if (messageType == MessageType.NAME_ACCEPTED) {
                        notifyConnectionStatusChanged(true);
                        return;
                    } else {
                        throw  new IOException("Unexpected MessageType");
                    }
                }
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                MessageType messageType = message.getType();
                if (messageType == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else {
                    if (messageType == MessageType.USER_ADDED) {
                        informAboutAddingNewUser(message.getData());
                    }
                    else {
                        if (messageType == MessageType.USER_REMOVED) {
                            informAboutDeletingNewUser(message.getData());
                        }
                        else {
                            throw new IOException("Unexpected MessageType");
                        }
                    }
                }
            }
        }

        @Override
        public void run() {
            String serverAddress = getServerAddress();
            int serverPort = getServerPort();
            try (Socket socket = new Socket(serverAddress,serverPort)) {
                Client.this.connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            }
            catch (IOException | ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
