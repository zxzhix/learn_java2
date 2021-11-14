package part2;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpService {


    public static void service(Socket socket)
    {

        try {
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(),true);
            printWriter.println("HTTP/1.1 200 ok");
            printWriter.println("Content-Type:text/html;charset=UTF-8");
            String boy ="hello word";
            printWriter.println("Content-Length:"+boy.getBytes(StandardCharsets.UTF_8).length);
            printWriter.println();
            printWriter.write(boy);
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ExecutorService executorService  = Executors.newFixedThreadPool(1000);
        try {
           final ServerSocket serverSocket   = new ServerSocket(8082);
           while (true)
           {
               final  Socket socket = serverSocket.accept();
               executorService.execute(()->service(socket));
           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
