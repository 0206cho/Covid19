package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jdbc.DB;

public class AdminLogin extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel p2;
	private JLabel lbl1;
	private JLabel lbl2;
	private JTextField tx1;
	private JPasswordField tx2;
	private JButton b1;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public AdminLogin(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		setResizable(false); // 실행후 화면크기 변경 불가
		
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.white);
		lbl1 = new JLabel("ID : ");
		lbl2 = new JLabel("PW : ");
		tx1 = new JTextField(10);
		tx2 = new JPasswordField(10);
		tx2.setEchoChar('*'); // 텍스트필드에 입력한 값 안보이고 *로 표시됨
		lbl1.setBounds(200, 0, 300, 300);
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		tx1.setBounds(240, 140, 200, 30);
		lbl2.setBounds(193, 40, 300, 300);
		tx2.setBounds(240, 180, 200, 30);
		tx2.addActionListener(this);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b1 = new JButton("로그인");
		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		b1.setBounds(450, 141, 60, 70);
		b1.setBackground(Color.white);
		b1.setFocusPainted(false);
		b1.addActionListener(this);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		p2.add(lbl1);
		p2.add(tx1);
		p2.add(lbl2);
		p2.add(tx2);
		p2.add(b1);
		
		
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(p1,BorderLayout.NORTH);
		last.add(p2);
		add(last);
		setResizable(false);
		setVisible(true);
		
		
	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
		g.drawLine(458, 238, 517, 238);
		g.drawLine(458, 309, 517, 309);
		g.drawLine(457, 238, 457, 309);
		g.drawLine(518, 238, 518, 309);
	}
	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new AdminLogin("관리자 로그인", 710, 520);
	}
	private boolean checkIDPW(String id, String pw) {
		boolean check = false;
		String sql = "SELECT * FROM admin WHERE id = '" + id +"' AND pw = '" + pw +"' ";
		try {
			ResultSet rs = DB.getResultSet(sql);
			
			if(rs.next()) {
				check = true;
			}else {
				check = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1 || obj == tx2) {
			//로그인 버튼클릭시
			String id = tx1.getText();
			String pw = tx2.getText();
			
			boolean check = checkIDPW(id, pw);
			if(id.equals("")) {
				JOptionPane.showMessageDialog(this, "ID를 입력하지 않았습니다.", "로그인", JOptionPane.ERROR_MESSAGE);
				tx1.requestFocus();
			}else if(pw.equals("")) {
				JOptionPane.showMessageDialog(this, "PW를 입력하지 않았습니다.", "비밀번호 찾기", JOptionPane.ERROR_MESSAGE);
				tx2.requestFocus();
			}else {
				if(check) {
					new AdminMain("관리자", 1020, 420);
					tx1.setText("");
					tx2.setText("");
//					this.dispose();
//					this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(this, "로그인 실패", "로그인", JOptionPane.ERROR_MESSAGE);
					tx1.setText("");
					tx2.setText("");
					tx1.requestFocus();
				}
			}
		}
	}

}
