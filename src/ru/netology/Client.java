package ru.netology;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client extends Thread {
    private static Socket clientSocket;
    public static final String ipAddr = "localhost";
    public static final int port = 23445;

    Client() throws IOException {
        clientSocket = new Socket(ipAddr, port);
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {
            Integer value;
            while (true) {
                System.out.println("Введите порядковый номер числа ряда Фибоначчи (или 0 - для выхода): ");
                value = scanner.nextInt();
                out.println(value);
                if (value == 0) break;
                System.out.println("Число Фибаначчи =  " + in.readLine());
            }
        } catch (IOException e) {
            System.err.println(e);
        }

    }
}
