package user.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;
import user.comfirmed_case.seoul.Seoul_Search;

public class VaccineSearch extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JTextField tx1;
	private JPanel p2;
	private JLabel lblicon;
	private JPanel last;
	private JButton b1;
	private JPanel p3;
	private JButton b2;
	private JLabel lb1;
	private JLabel lbl1;
	private JLabel lb2;
	private JLabel lbl2;
	private VaccineInquiry vaccineInquiry;
	private JButton b3;
	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineSearch(String title, int width, int height, VaccineInquiry vaccineInquiry) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		String [] hi = {"확진자","확진일","거주지","감염경로","증상유무"};
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p2.setBackground(Color.white);
		p2.add(lblicon,BorderLayout.WEST);
		this.vaccineInquiry = vaccineInquiry;
		
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(new FlowLayout());
		tx1 = new JPasswordField(10);
		p1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
//		lbl1 = new JLabel("탭을 선택후 검색어를 입력해주세요. EX) 감염경로 - 접촉");
//		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lbl2 = new JLabel("주민등록번호 뒷자리 : ");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
//		p1.add(lbl1);
		p1.add(lbl2);
		p1.add(tx1);
		
		p3 = new JPanel();
		p3.setBackground(Color.white);
		b1 = new JButton("검색");
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		b2 = new JButton("취소");
		b2.addActionListener(this);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b2.setBackground(Color.white);
		b3 = new JButton("예약취소");
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b3.setBackground(Color.white);
		b3.addActionListener(this);
		p3.add(b1);
		p3.add(b2);
		p3.add(b3);
		
		
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(p2,BorderLayout.NORTH);
		last.add(p1,BorderLayout.CENTER);
		last.add(p3,BorderLayout.SOUTH);
		
		
		add(last);
		setResizable(false);
		setVisible(true);
		
		
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
	}
	public void BackDelete(String sql) {

		try {
			DB.stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
//	public static void main(String[] args) {
//		new VaccineSearch("백신 예약자 검색", 400, 250);
//	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			vaccineInquiry.getBackidSearch(vaccineInquiry.getModel(), tx1.getText());
		}
		else if (obj == b2) {
			dispose();
		}
		else if(obj == b3) {
			String sql = "delete from vaccine where residentBackID='" + tx1.getText() + "'";
			BackDelete(sql);
			vaccineInquiry.getBackidSearch(vaccineInquiry.getModel(), tx1.getText());
		}
	}

}
