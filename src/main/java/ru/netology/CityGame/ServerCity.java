package ru.netology.CityGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCity {
    private static final int PORT = 8080; //8002; //8989

    public static void main(String[] args) {
        System.out.println("\nЗапускаем сервер, ждём клиента...\n");
        String city = null;

        try (ServerSocket serverSocket = new ServerSocket(PORT);) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                ) {
                    if (city == null) {
                        out.println("???");
                        city = in.readLine();
                       System.out.printf("Соединение установлено.\nГород клиента: %s\n", city);
                        out.println("OK");
                    } else {
                        city = getCity(city, out, in);
                       System.out.printf("Город клиента (port is %d): %s\n", clientSocket.getPort(), city);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private static String getCity(String city, PrintWriter out, BufferedReader in) throws IOException {
        out.println(city);
        String newCity = in.readLine();
        if (city.charAt(city.length() - 1) == newCity.charAt(0)) {
            city = newCity;
            out.println("OK");
        } else out.println("NOT OK");
        return city;
    }
}