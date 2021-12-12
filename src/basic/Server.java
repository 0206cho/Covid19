package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;

import jdbc.DB;

// 서버
public class Server {
	private String id, date, heat, bt, cough, st, displayable, note;
	public Server() {
		try {
			int socketPort = 1234;
			ServerSocket serverSocket = new ServerSocket(socketPort); // 소켓 만들기
			Socket socketUser = null; // 클라이언트 접속시 사용할 Socket
			System.out.println("socket : " + socketPort + "으로 서버가 열렸습니다");

			// 소켓 서버가 종료될 때까지 반복
			while (true) {

				socketUser = serverSocket.accept();
				// 소켓 서버로 접속 시 socketUser에 접속자 정보 할당
				System.out.println("Client가 접속함 : " + socketUser.getLocalAddress());
				// 접속자의 getLocalAddress 가져오기

				// InputStream - 클라이언트에서 서버로
				InputStream input = socketUser.getInputStream();
				// socket의 InputStream 정보를 InputStream in에 넣은 뒤
				BufferedReader reader = new BufferedReader(new InputStreamReader(input));
				// BufferedReader에 위 InputStream을 담아 사용

				// 클라이언트에서 온 메세지 확인
				id = reader.readLine();
				date = reader.readLine();
				heat = reader.readLine();
				bt = reader.readLine();
				cough = reader.readLine();
				st = reader.readLine();
				displayable = reader.readLine();
				note = reader.readLine();
				
				String sql = "INSERT INTO isolatedCheckList (isolatedID, `DateTime`, heat, bodyTemperature, cough, soreThroat, displayable, note) VALUES('" + id +"', '" + date + "', '" + heat + "', '"
						+ bt + "', '" + cough + "', '" + st+ "', '" + displayable+ "', '" + note + "' )";
				setInsertDB(sql);
				System.out.println("처리완료");

				// OutputStream - 서버에서 클라이언트로
				OutputStream out = socketUser.getOutputStream();
				// socket의 OutputStream 정보를 OutputStream out에 넣은 뒤
				PrintWriter writer = new PrintWriter(out, true);
				// BufferedReader에 위 InputStream을 담아 사용

				writer.println("SERVER TO CLIENT");
				// 서버에서 클라이언트로 메세지 보내기

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Server();
		
	}

	private void setInsertDB(String sql) {
		DB.executeQuery(sql);
		
	}
}