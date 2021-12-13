package admin.pcap;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import org.jnetpcap.Pcap;
import org.jnetpcap.PcapHeader;
import org.jnetpcap.PcapIf;
import org.jnetpcap.nio.JBuffer;
import org.jnetpcap.nio.JMemory;
import org.jnetpcap.packet.JRegistry;
import org.jnetpcap.packet.Payload;
import org.jnetpcap.packet.PcapPacket;
import org.jnetpcap.packet.format.FormatUtils;
import org.jnetpcap.protocol.lan.Ethernet;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;


public class pcapGUI extends JFrame implements ActionListener {

	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon;
	private Color color;
	JLabel packet_Lb;
	JScrollPane scrollPane;
	public static JTextArea logTa;
	public JButton start_btn;
	private JButton cancel_btn;
	
	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public pcapGUI() {

		this.setTitle("비인가 패킷 분석");
		setSize(525, 800);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐
		setResizable(false); // 실행후 화면크기 변경 불가

		// 상단 패널
		pUp = new JPanel();
		pUp.setLayout(new BorderLayout());
		pUp.setBackground(Color.white);

		// 로고
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");

		pUp.add(lblicon, BorderLayout.WEST);

		// 중앙패널
		color = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(null);
		pCenter.setBackground(color);
		logTa = new JTextArea();
		logTa.setEditable(false);	// 글 사용 금지
		scrollPane = new JScrollPane(logTa);
		scrollPane.setViewportView(logTa);	//텍스트가 아래로 내려갈 경우 스크롤바도 같이 내려감
		scrollPane.setBounds(5, 390, 500, 300);
		
		JLabel packet_Lb = new JLabel("패킷 캡쳐");
		packet_Lb.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		packet_Lb.setBounds(5, 325, 100, 100);
		pCenter.add(scrollPane);
		pCenter.add(packet_Lb);
		
		start_btn = new JButton("Start");
		start_btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		start_btn.setBounds(5, 150, 150, 150);
		start_btn.addActionListener(this);
		pCenter.add(start_btn);
		
		cancel_btn = new JButton("Cancel");
		cancel_btn.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		cancel_btn.setBounds(5, 300, 100, 100);
		cancel_btn.addActionListener(this);
		pCenter.add(cancel_btn);
		
		// 모든 패널 붙이기
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);
		
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 98, 1050, 98);
	}



	public JTextArea getLogTa() {
		return logTa;
	}

	public static void main(String[] args) {
		new pcapGUI();
	}

	public void pcap() {
		//logTa.append("test1");
		ArrayList<PcapIf> allDevs = new ArrayList<PcapIf>(); 
		// 디바이스를 담을 변수를 ArrayList로 생성
		StringBuilder errbuf = new StringBuilder();	
		// 에러처리
		
		int r = Pcap.findAllDevs(allDevs, errbuf);	
		// 접근가능한 디바이스를 첫 번째인자에 담는다, 두 번째인자는 에러처리
		
		if (r==Pcap.NOT_OK || allDevs.isEmpty()) {
			System.out.println("네트워크 장치 찾기 실패." + errbuf.toString());
			//logTa.append("네트워크 장치 찾기 실패." + errbuf.toString());
			return;
		}	// 예외처리
		System.out.println("< 탐색된 네트워크 Device >");
		//logTa.append("< 탐색된 네트워크 Device >");
		int i = 0;	// Device Numbering 용도
		
		for(PcapIf device : allDevs) 
		{	// 탐색한 장비를 출력
			String description = (device.getDescription() != null) ? device.getDescription() : "장비에 대한 설명이 없습니다.";
			System.out.printf("[%d번]: %s [%s]\n", ++i, device.getName(), description);
			//logTa.append("[%d번]: %s [%s]\n" + ++i + device.getName() + description);
		}
		
		PcapIf device = allDevs.get(5);
		System.out.printf("선택된 장치: %s\n", (device.getDescription() != null) ?
				device.getDescription() : device.getName());


		int snaplen = 64 * 1024; //65536Byte만큼 패킷을 캡쳐
		int flags = Pcap.MODE_NON_PROMISCUOUS; // 무차별모드
		int timeout = 10 *1000; // timeout 10second
		Pcap pcap = Pcap.openLive(device.getName(), snaplen, flags, timeout, errbuf);
		
		if (pcap == null) {
			System.out.printf("Network Device Access Failed. Error: " + errbuf.toString());
			return;
		}
		
		// 여기서 부터 계층별 객체 생성
		Ethernet eth = new Ethernet();
		Ip4 ip = new Ip4();
		Tcp tcp = new Tcp();
		Payload payload = new Payload();
		PcapHeader header = new PcapHeader(JMemory.POINTER);
		JBuffer buf = new JBuffer(JMemory.POINTER);
		int id = JRegistry.mapDLTToId(pcap.datalink());	// pcap의 datalink 유형을 jNetPcap의 프로토콜 ID에 맵핑
		while(pcap.nextEx(header, buf) == Pcap.NEXT_EX_OK) // 헤더와 버퍼를 피어링
		{
			PcapPacket packet = new PcapPacket(header, buf);
			packet.scan(id); //새로운 패킷을 스캔하여 포함 된 헤더를 찾습니다
			System.out.printf("[ #%d ]\n", packet.getFrameNumber());
			System.out.println("##################################### Packet #####################################");
			if (packet.hasHeader(eth)) {
				System.out.printf("출발지 MAC 주소 = %s\n도착지 MAC 주소 = %s\n", FormatUtils.mac(eth.source()), FormatUtils.mac(eth.destination()));
				logTa.append("출발지 MAC 주소 = "+FormatUtils.mac(eth.source())+"\n도착지 MAC 주소 = "+FormatUtils.mac(eth.destination()) +"\n");
				logTa.setCaretPosition(logTa.getDocument().getLength());	// 포커스 맨 아래로 
			}
			if (packet.hasHeader(ip)) {
				System.out.printf("출발지 IP 주소 = %s\n도착지 IP 주소 = %s\n", FormatUtils.ip(ip.source()), FormatUtils.ip(ip.destination()));
				logTa.append("출발지 IP 주소 = "+FormatUtils.ip(ip.source())+"\n도착지 IP 주소 = "+FormatUtils.ip(ip.destination())+"\n");
				logTa.setCaretPosition(logTa.getDocument().getLength());
			}
			if (packet.hasHeader(tcp)) {
				System.out.printf("출발지 TCP 주소 = %d\n도착지 TCP 주소 = %d\n", tcp.source(), tcp.destination());
				logTa.append("출발지 TCP 주소 = "+tcp.source()+"\n도착지 TCP 주소 = "+tcp.destination()+"\n");
				logTa.setCaretPosition(logTa.getDocument().getLength());
			}
			if (packet.hasHeader(payload)) {
				System.out.printf("페이로드의 길이 = %d\n", payload.getLength()); System.out.print(payload.toHexdump());	// 와이어샤크에서 보이는 hexdump를 출력
				logTa.append("페이로드의 길이 = "+payload.getLength()+"\n" + payload.getLength() + "\n");
				logTa.append(payload.toHexdump());
				logTa.setCaretPosition(logTa.getDocument().getLength());	// 포커스 맨 아래로 
			}
		}
		pcap.close();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
			if(obj == start_btn ) {
				pcap();
			}
			else if(obj == cancel_btn) {
				logTa.append("왜 안되냐");
			}
		}
	}

