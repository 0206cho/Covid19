package user.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import jdbc.DB;

public class VaccineIntro extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel p2;
	private JButton b1;
	private JButton b2;
	private JPanel p3;
	private JPanel p4;
	private JLabel lbl1;
	private JPanel p6;
	private JPanel p5;
	private JButton b3;
	private JButton b4;
	private PreparedStatement ps;
	private ResultSet rs;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineIntro(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		
		
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		p4 = new JPanel();
		p4.setBackground(Color.white);
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.setBorder(BorderFactory.createEmptyBorder(15, 25, 0, 0));
		lbl1 = new JLabel("코로나19 예방접종 사전예약 시스템");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 19));
		p4.add(lbl1);
		
		p5 = new JPanel();
		p5.setBackground(Color.white);
		p5.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		p5.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		
		//예약하기 프레임은 주민번호 뒷자리로 중복 불가능하게 설정
		b3 = new JButton("예약하기");
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b3.setBorderPainted(false);
		b3.setBackground(Color.white);
		b3.setFocusPainted(false);
		b3.addActionListener(this);
		
		
		b4 = new JButton("예약 조회/취소");
		b4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b4.setBorderPainted(false);
		b4.setBackground(Color.white);
		b4.setFocusPainted(false);
		b4.addActionListener(this);
		p5.add(b3);
		p5.add(b4);
		
		p6 = new JPanel();
		p6.setBackground(Color.white);
		p6.setLayout(new BorderLayout());
		p6.add(p4,BorderLayout.WEST);
		p6.add(p5,BorderLayout.EAST);
		
		
		
		
		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(p1,BorderLayout.WEST);
		p3.add(p6);
		
		
		
		
		p2 = new JPanel();
		p2.setBackground(Color.white);
		p2.setLayout(null);
		
		
		b1 = new JButton("예약하기");
		b1.setBounds(220, 200, 150, 100);
		b1.addActionListener(this);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		b1.setForeground(new Color(255, 165, 0));
		b1.setBackground(Color.white);
		b1.setFocusPainted(false);
		
		
		b2 = new JButton("조회/취소");
		b2.setBounds(430, 200, 150, 100);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		b2.setForeground(Color.blue);
		b2.setBackground(Color.white);
		b2.setFocusPainted(false);
		b2.addActionListener(this);
	
		
		p2.add(b1);
		p2.add(b2);
		
		
		
		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p3,BorderLayout.NORTH);
		last.add(p2);
		add(last);
		
		
		setVisible(true);
		
		
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
		g.drawLine(210, 40, 210, 88);
	}
	
	//인수로 들어온 num에 해당하는 레코드 검색하여 중복여부 체크하기 return = true 사용가능, false이면 중복임
	public boolean getNumCheck(String num) {
		boolean result = true;
		
		//preparestatemnet 사용하면 ''를 안써도 된다. ?에 데이터 값을 넣기 때문에 sql 사용이 쉬워짐
		// 위에 num 값이 아래 sql ?에 들어감
		String sql = "select * from vaccine where residentBackID = ?";
		try {
			//쿼리를 생성하고 실행할때 statemnet 랑 preparedStatement 방법이 있는데 둘의 차이점은 캐시 사용 유무이다.
			// preparestatement는 객체를 캐시에 담아 재사용하기에 반복적으로 쿼리를 수행한다면 statement에 비해 성능이 좋다
			// statement는 취약점을 갖짐
			ps = DB.conn.prepareStatement(sql);
			
			//1번 인덱스를 스트링 값으로 지정한다.
			ps.setString(1, num.trim());
			//executeQuery는 select문 쓸때 사용 execyteUpdate는 insert나 update 등등
			rs = ps.executeQuery(); 
			if(rs.next()) {
				//레코드가 존재하면 false 즉, 중복
				result = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new VaccineIntro("백신 예약 및 조회", 800, 600);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b3) {
			new VaccineReservation("예약 하기", 840, 900,this);
		}
		else if(obj == b1) {
			new VaccineReservation("예약 하기", 840, 900,this);
		}
		else if(obj == b2) {
			new VaccineInquiry("백신 예약 조회", 750, 650);
		}
		else if(obj == b4) {
			new VaccineInquiry("백신 예약 조회", 750, 650);
		}
	}

}
