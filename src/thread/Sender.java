package thread;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
  private DataOutputStream dos;
  private String name;

  public Sender(Socket socket) {
    name = "[" + socket.getInetAddress() + " : " + socket.getLocalPort() + "]";

    try {
        dos = new DataOutputStream(socket.getOutputStream());
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
  
  @Override
  public void run() {
    Scanner scan = new Scanner(System.in);
    while (dos != null) { // 무한루프
      try {
        dos.writeUTF(name + " >>> " + scan.nextLine()); // scanner: 엔터치기전까지 block
      } catch (Exception e) {
      e.printStackTrace();
      }
    }
    scan.close();
  }
} // class