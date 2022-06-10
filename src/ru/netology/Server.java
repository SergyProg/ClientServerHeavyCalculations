package ru.netology;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
    private static ServerSocket server;
    public static final int port = 23445;

    Server() throws IOException {
        server = new ServerSocket(port);
    }

    private Integer fibonacci(Integer value) {
        if (value == 1) return 0;
        if ((value == 2) || (value == 3)) return 1;
        return fibonacci(value - 1) + fibonacci(value - 2);
    }

    @Override
    public void run() {
        try (Socket socket = server.accept();
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Integer value;
            String line;
            while ((line = in.readLine()) != null) {
                value = Integer.parseInt(line);
                if (value == 0) {
                    break;
                }
                out.println(fibonacci(value));
            }
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
