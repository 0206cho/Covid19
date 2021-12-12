package thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receiver extends Thread {
  private Socket socket;
  private DataInputStream dis;

  public Receiver(Socket socket) {
    this.socket = socket;

    try {
      dis = new DataInputStream(socket.getInputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  @Override
  public void run() {
    while(dis != null) {
      try {
        System.out.println(dis.readUTF());
        // -> Sender에서 writeUTF 호출 전까지 block, 
        //    writeUTF 호출되면 그때부터 read하기 시작
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
} // class