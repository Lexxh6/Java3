package Lesson3.dz;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client implements AutoCloseable{
    Socket socket;
    public Client (int port) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(),port);
        System.out.println("Клиент запущен");

    }
    public void sendObj (Object obj) throws IOException {
        ObjectOutputStream ous = new ObjectOutputStream(socket.getOutputStream());
        ous.writeObject(obj);
    }

    @Override
    public void close() throws IOException {
        socket.close();
    }
}
