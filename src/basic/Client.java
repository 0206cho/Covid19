package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

import user.isolated.IsolatedCheckList;

// 클라이언트
public class Client {
	private IsolatedCheckList isolatedCheckList;
	//private IsolatedMain isolatedMain;

	public Client(IsolatedCheckList isolatedCheckList) {
		this.isolatedCheckList = isolatedCheckList;
		
		try {
			Socket socket = new Socket("127.0.0.1", 1234); // 소켓 서버에 접속
			System.out.println("socket 서버에 접속 성공!");

			// OutputStream - 클라이언트에서 Server로 메세지 발송
			OutputStream out = socket.getOutputStream();
			// socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
			PrintWriter writer = new PrintWriter(out, true);
			// PrintWriter에 위 OutputStream을 담아 사용

			//writer.println("CLIENT TO SERVER");
			// 클라이언트에서 서버로 메세지 보내기
			writer.println(isolatedCheckList.getIso_id());  //id
			writer.println(isolatedCheckList.getFormatedNow());  //일시
			writer.println(isolatedCheckList.isHeat());  //발열여부
			writer.println(isolatedCheckList.getTfBT().getText());  //체온
			writer.println(isolatedCheckList.isCough());  //기침여부
			writer.println(isolatedCheckList.isSt());  //인후통 여부
			writer.println(isolatedCheckList.isDisplayable());  //호흡곤란여부
			writer.println(isolatedCheckList.getTaNotee().getText());  //특이사항

			// InputStream - Server에서 보낸 메세지 클라이언트로 가져옴
			InputStream input = socket.getInputStream();
			// socket의 InputStream 정보를 InputStream in에 넣은 뒤
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			// BufferedReader에 위 InputStream을 담아 사용

			System.out.println(reader.readLine());
			// 서버에서 온 메세지 확인
			System.out.println("CLIENT SOCKET CLOSE");
			socket.close(); // 소켓 종료

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		//new Client(IsolatedCheckList isolatedCheckList);
	}

}