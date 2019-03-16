package com.javarush.task.task30.task3008;

import com.javarush.task.task30.task3008.client.Client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    private static class Handler extends Thread {
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            connection.send(new Message(MessageType.NAME_REQUEST));
            Message message = connection.receive();
            String name = message.getData();
            if (message.getType() == MessageType.USER_NAME) {
                if (name != "") {
                    if (!connectionMap.containsKey(name)) {
                        connectionMap.put(name, connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED, name));
                        return name;
                    }
                }
            }
            return serverHandshake(connection);
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                if (!pair.getKey().equals(userName))
                connection.send(new Message(MessageType.USER_ADDED, pair.getKey()));
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    Message newMessage = new Message(MessageType.TEXT, userName + ": " + message.getData());
                    sendBroadcastMessage(newMessage);
                } else ConsoleHelper.writeMessage("Ошибка!");
            }
        }

        @Override
        public void run() {
            System.out.println(socket.getRemoteSocketAddress());
            try(Connection connection = new Connection(socket);) {
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            catch (Exception e) {
                e.getMessage();
            }
        }
    }

    public static void sendBroadcastMessage(Message message) {
        try {
            for (Map.Entry<String, Connection> pair : connectionMap.entrySet()) {
                pair.getValue().send(message);
            }
        }
        catch (IOException e){
            System.out.println("Мы не смогли отправить Ваше сообщение.");
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
            ConsoleHelper.writeMessage("Сервер запущен");
            try {
                while (!serverSocket.isClosed()) {
                    Handler handler = new Handler(serverSocket.accept());
                    handler.start();
                }
            }
            catch (IOException e) {
                e.getMessage();
            }
            finally {
                serverSocket.close();
            }
        }
        catch (Exception e) {
            e.getMessage();
        }


    }
}
