package Lesson3.dz;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements AutoCloseable{
    Socket socket;

    public Server (int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Thread thread = new Thread(() -> {
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        System.out.println("Сервер запущен");
    }
    public Object reciveObj () throws IOException, ClassNotFoundException {
        ObjectInputStream ins = new ObjectInputStream(socket.getInputStream());
        return ins.readObject();
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
