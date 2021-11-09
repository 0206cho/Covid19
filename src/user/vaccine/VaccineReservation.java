package user.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jdbc.DB;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class VaccineReservation extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p4;
	private JLabel lbl1;
	private JPanel p5;
	private JButton b3;
	private JButton b4;
	private JPanel p6;
	private JPanel p3;
	private JPanel last;
	private JPanel p2;
	private JLabel lblicon2;
	private JLabel name;
	private JLabel redStar;
	private JTextField nameTx;
	private JButton topButton;
	private JLabel topLabel1;
	private JLabel topLabel2;
	private JLabel topLabel3;
	private JButton nameButton;
	private JButton NumButton;
	private JLabel num;
	private JLabel redStarNum;
	private JTextField numTx;
	private JLabel num2;
	private JPasswordField numTx2;
	private JLabel phone;
	private JLabel redStarphone;
	private RoundJTextField phoneTx;
	private JLabel phone2;
	private RoundJTextField phoneTx2;
	private JLabel phone3;
	private RoundJTextField phoneTx3;
	private JLabel date;
	private JLabel redStardate;
	private RoundJTextField dateTx;
	private JLabel hostpital;
	private JLabel redStarHsp;
	private JButton btnCal;
	private Component datePicker;
	private String [] local = {"보건소", "대학병원", "개인병원"};
	private JComboBox<String> cb;
	private JButton btnReserve;
	private JButton btnCancle;
	private VaccineIntro vaccineIntro;
	private ButtonGroup bg;
	private JRadioButton hCenter;
	private JRadioButton uHospital;
	private JRadioButton hospital;
	
	
	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineReservation(String title, int width, int height, VaccineIntro vaccineIntro) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐
		this.vaccineIntro = vaccineIntro;
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.setBackground(Color.white);
		// lblicon.setPreferredSize(new Dimension(150, 150));
		p1.add(lblicon, BorderLayout.WEST);

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
		//p5.add(b3);
		p5.add(b4);

		p6 = new JPanel();
		p6.setBackground(Color.white);
		p6.setLayout(new BorderLayout());
		p6.add(p4, BorderLayout.WEST);
		p6.add(p5, BorderLayout.EAST);

		p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.add(p1, BorderLayout.WEST);
		p3.add(p6);

		p2 = new JPanel();
		p2.setBackground(Color.white);
		p2.setLayout(null);

		topButton = new JButton();
		topButton.setBorderPainted(false);
		topButton.setBackground(new Color(240, 240, 240));
		topButton.setBounds(110, 30, 600, 150);
		topButton.setEnabled(false);

		topLabel1 = new JLabel("개인정보 수집 및 이용에 대한 안내");
		topLabel1.setForeground(new Color(75, 137, 220));
		topLabel1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		topLabel1.setBounds(260, 22, 400, 100);

		topLabel2 = new JLabel("「감염병의 예방 및 관리에 관한 법률」 제33조의 4 및 같은 법 시행령 제32조의 3에 따라");
		// TopLabel2.setForeground(new Color(75, 137, 220));
		topLabel2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		topLabel2.setBounds(150, 60, 600, 100);

		topLabel3 = new JLabel("코로나19 예방접종 예약신청을 위하여 다음의 개인정보를 수집합니다.");
		// TopLabel2.setForeground(new Color(75, 137, 220));
		topLabel3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		topLabel3.setBounds(205, 85, 600, 100);

		nameButton = new JButton();
		nameButton.setBorderPainted(false);
		nameButton.setBackground(new Color(240, 240, 240));
		nameButton.setBounds(110, 200, 130, 500);
		nameButton.setEnabled(false);

		name = new JLabel("이름");
		name.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		name.setBounds(160, 175, 100, 100);
		redStar = new JLabel("*");
		redStar.setForeground(Color.red);
		redStar.setBounds(195, 178, 100, 100);
		nameTx = new RoundJTextField(10);
		nameTx.setBounds(255, 210, 130, 30);

		num = new JLabel("주민등록번호");
		num.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		num.setBounds(135, 225, 100, 100);
		redStarNum = new JLabel("*");
		redStarNum.setForeground(Color.red);
		redStarNum.setBounds(220, 228, 100, 100);
		numTx = new RoundJTextField(10);
		numTx.setBounds(255, 260, 130, 30);
		num2 = new JLabel("-");
		num2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		num2.setBounds(395, 222, 100, 100);
		numTx2 = new RoundJPWField(10);
		numTx2.setBounds(412, 260, 130, 30);
		
		
		
		phone = new JLabel("휴대폰번호");
		phone.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		phone.setBounds(142, 275, 100, 100);
		redStarphone = new JLabel("*");
		redStarphone.setForeground(Color.red);
		redStarphone.setBounds(218, 278, 100, 100);
		phoneTx = new RoundJTextField(10);
		phoneTx.setBounds(255, 310, 130, 30);
		phone2 = new JLabel("-");
		phone2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		phone2.setBounds(395, 272, 100, 100);
		phoneTx2 = new RoundJTextField(10);
		phoneTx2.setBounds(412, 310, 130, 30);
		phone3 = new JLabel("-");
		phone3.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		phone3.setBounds(552, 272, 100, 100);
		phoneTx3 = new RoundJTextField(10);
		phoneTx3.setBounds(568, 310, 130, 30);
		
		date = new JLabel("접종날짜");
		date.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		date.setBounds(150, 325, 100, 100);
		redStardate = new JLabel("*");
		redStardate.setForeground(Color.red);
		redStardate.setBounds(210, 328, 100, 100);
		//데이트 픽커로 날짜 입력받기 때문에 비활성화시킴 
		dateTx = new RoundJTextField(10);
		dateTx.setEnabled(false);
		dateTx.setBounds(255, 360, 130, 30);
		ImageIcon icon2 = new ImageIcon("images/Cal.png");
		btnCal = new JButton(icon2);
		btnCal.setBounds(400, 365, 23, 23);
		btnCal.setBorderPainted(false);
		btnCal.setBackground(Color.white);
		btnCal.setFocusPainted(false);
		btnCal.addActionListener(this);
		
		
		hostpital = new JLabel("의료기관");
		hostpital.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		hostpital.setBounds(147, 385, 100, 100);
		redStarHsp = new JLabel("*");
		redStarHsp.setForeground(Color.red);
		redStarHsp.setBounds(207, 388, 100, 100);
		cb = new JComboBox<String>(local);
		cb.setBounds(260,420,80,35);
		btnReserve = new JButton("예약");
		btnReserve.setBounds(500, 485, 100, 40);
		btnReserve.setBackground(new Color(75, 137, 220));
		btnReserve.setForeground(Color.white);
		btnReserve.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		btnReserve.setFocusPainted(false);
		btnReserve.addActionListener(this);
		btnCancle = new JButton("취소");
		btnCancle.setBounds(610, 485, 100, 40);
		btnCancle.setBackground(Color.white);
		btnCancle.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		btnCancle.setFocusPainted(false);
		btnCancle.addActionListener(this);
		
		p2.add(name);
		p2.add(redStar);
		p2.add(nameTx);
		p2.add(topLabel1);
		p2.add(topLabel2);
		p2.add(topLabel3);
		p2.add(num);
		p2.add(redStarNum);
		p2.add(numTx);
		p2.add(num2);
		p2.add(numTx2);
		p2.add(phone);
		p2.add(redStarphone);
		p2.add(phoneTx);
		p2.add(phoneTx2);
		p2.add(phone2);
		p2.add(phone3);
		p2.add(phoneTx3);
		p2.add(date);
		p2.add(redStardate);
		p2.add(dateTx);
		p2.add(hostpital);
		p2.add(redStarHsp);
		p2.add(cb);
		p2.add(btnReserve);
		p2.add(btnCancle);
		p2.add(btnCal);
		
		
		
		p2.add(topButton);
		p2.add(nameButton);

		
		
		


		
		

		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(p3, BorderLayout.NORTH);
		last.add(p2);
		add(last);
		setVisible(true);

	}

	public RoundJTextField getDateTx() {
		return dateTx;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
		g.drawLine(210, 40, 210, 88);
		g.drawLine(120, 300, 715, 300);
		g.drawLine(120, 350, 715, 350);
		g.drawLine(120, 400, 715, 400);
		g.drawLine(120, 450, 715, 450);
		g.drawLine(120, 500, 715, 500);
	}

//	public static void main(String[] args) {
//		new VaccineReservation("예약 하기", 840, 900);
//	}

	
	// 텍스트 필드를 둥글게 만들기 위해 JtextField 상속받는 RoundTextFiled생성하고 상속받앗기 때문에 기능은 동일하게 동작함
	public class RoundJTextField extends JTextField {
		private Shape shape;

		public RoundJTextField(int size) {
			super(size);
			setOpaque(false); // As suggested by @AVD in comment.
		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());							//아래 10,10 텍스트 필드 둥글게 설정하기 
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(new Color(140, 140, 140));
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}
	}
	
	// 
	public class RoundJPWField extends JPasswordField {
		private Shape shape;

		public RoundJPWField(int size) {
			super(size);
			setOpaque(false); // As suggested by @AVD in comment.
		}

		protected void paintComponent(Graphics g) {
			g.setColor(getBackground());
			g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			super.paintComponent(g);
		}

		protected void paintBorder(Graphics g) {
			g.setColor(new Color(140, 140, 140));                  
			g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
		}

		public boolean contains(int x, int y) {
			if (shape == null || !shape.getBounds().equals(getBounds())) {
				shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
			}
			return shape.contains(x, y);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnCal) {
			new Calendar("접종 날짜", 350, 200, this);
		}
		else if(obj == btnReserve) {
			
			if(vaccineIntro.getNumCheck(numTx2.getText())) {
				String sql = "INSERT INTO vaccine VALUES ('"+ numTx2.getText() +"', '"+ nameTx.getText() +"', '"+ numTx.getText() + "', '" + phoneTx.getText() + "-" + phoneTx2.getText() + "-" + phoneTx3.getText() + "', '"+ dateTx.getText() + "', '" + cb.getSelectedItem() + "')";	
				
				// 텍스트가 비어있을때 팝업다이얼로그 표시
				if(numTx2.getText().equals("") | nameTx.getText().equals("") | numTx.getText().equals("") | phoneTx.getText().equals("") | phoneTx2.getText().equals("") | phoneTx3.getText().equals("") | dateTx.getText().equals("")) {
					JOptionPane.showMessageDialog(null,"다시 입력 해주세요.","메시지",JOptionPane.ERROR_MESSAGE);
				}
				else {
				try {
					DB.stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null,"예약이 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				numTx2.setText("");
				nameTx.setText("");
				numTx.setText("");
				phoneTx.setText("");
				phoneTx2.setText("");
				phoneTx3.setText("");
				dateTx.setText("");
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"이미 예약하셨습니다.","메시지",JOptionPane.WARNING_MESSAGE);
			}
			
			
		}
		else if(obj == btnCancle) {
			dispose();
		}
		else if(obj == b4) {
			new VaccineInquiry("백신 예약 조회", 750, 650);
		}
	}

}
