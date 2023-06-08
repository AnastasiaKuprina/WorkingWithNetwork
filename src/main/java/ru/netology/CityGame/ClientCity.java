package ru.netology.CityGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientCity {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        System.out.println("\nЗапускаем клиента, ждем сервер\n");
        try (Socket clientSocket = new Socket(HOST, PORT);
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String city = in.readLine();
            System.out.println("Соединение установлено");
            System.out.printf("Город: %s, следующий город: ", city);

            Scanner scanner = new Scanner(System.in);
            String newCity = scanner.nextLine();
            out.println(newCity);

            String result = in.readLine();
            System.out.println(result);

        } catch (IOException e) {
            throw new RuntimeException(e + "Ошибка соединения");
        }
    }
}