package thread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) throws IOException {
  
    ServerSocket server = new ServerSocket(7777);
    System.out.println("서버 준비 완료...");

    Socket socket = server.accept();

    System.out.println("서버의 소켓 : " + socket); // toString() 오버라이드 되어있음

    Sender sender = new Sender(socket);
    Receiver receiver = new Receiver(socket);

    sender.start();
    receiver.start();
    // 여기까지하면 이제 3개(main+sender+receiver)의 스레드가 작동하는 멀티스레드 프로그램
  } // main
} // class