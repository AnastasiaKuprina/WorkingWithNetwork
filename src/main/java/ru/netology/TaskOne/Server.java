package ru.netology.TaskOne;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8001; //8080

    public static void main(String[] args) {
        System.out.println("\nЗапускаем сервер, ждём клиента...\n");
        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            try (Socket clientSocket = serverSocket.accept();
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                out.println("Введите имя:");
                System.out.println("New connection accepted");

                final String name = in.readLine();
                out.println(String.format("Hi %s, your port is %d", name, clientSocket.getPort()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}