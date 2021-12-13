package user.isolated;

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
import javax.swing.JTextField;

import jdbc.DB;

public class IsolatedFindIDPW extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel p2;
	private JLabel lbl1;
	private JLabel lbl2;
	private JTextField tx1;
	private JTextField tx2;
	private JButton b1;
	private String pw;
	private String id;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedFindIDPW(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐

		// 상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		// lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon, BorderLayout.WEST);

		// 중앙패널
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(Color.white);

		lbl1 = new JLabel("이름 : ");
		lbl2 = new JLabel("휴대폰 : ");
		tx1 = new JTextField(10);
		tx2 = new JTextField(10);

		lbl1.setBounds(90, 50, 60, 30);
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		tx1.setBounds(150, 50, 200, 30);

		lbl2.setBounds(90, 90, 90, 30);
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 15));

		tx2.setBounds(150, 90, 200, 30);
		tx2.addActionListener(this);

		// 찾기 버튼
		b1 = new JButton("ID/PW 찾기");
//		b1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		b1.setBounds(90, 130, 260, 40);
		b1.setBackground(Color.white);
		b1.setFocusPainted(false);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		b1.addActionListener(this);

		p2.add(lbl1);
		p2.add(tx1);
		p2.add(lbl2);
		p2.add(tx2);
		p2.add(b1);

		// 모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(p1, BorderLayout.NORTH);
		last.add(p2);
		add(last);
		setResizable(false);
		setVisible(true);
	}

	public JLabel getLbl1() {
		return lbl1;
	}

	public JLabel getLbl2() {
		return lbl2;
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
//		g.drawLine(458, 238, 517, 238);
//		g.drawLine(458, 309, 517, 309);
//		g.drawLine(457, 238, 457, 309);
//		g.drawLine(518, 238, 518, 309);
	}

	// main
	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new IsolatedFindIDPW("ID/PW 찾기", 450, 350);
	}

	// event
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1 || obj == tx2) {
			String name = tx1.getText();
			String phone = tx2.getText();

			boolean find = findPW(name, phone);
			if (name.equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력하지 않았습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				tx1.requestFocus();
			} else if (phone.equals("")) {
				JOptionPane.showMessageDialog(this, "휴대폰번호를 입력하지 않았습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				tx2.requestFocus();
			} else {
				if (find) {
					JOptionPane.showMessageDialog(this, "ID: " + id + " PW: " + pw + "입니다.", "ID/PW 찾기",
							JOptionPane.INFORMATION_MESSAGE);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this, "ID/PW가 존재하지 않습니다.", "오류", JOptionPane.ERROR_MESSAGE);
					tx1.setText("");
					tx2.setText("");
					tx1.requestFocus();
				}
			}

		}

	}

	private boolean findPW(String name, String phone) {
		boolean find = false;
		String sql = "SELECT isolatedID, isolatedPW FROM isolated WHERE name = '" + name + "' AND phone = '" + phone
				+ "' ";
		try {
			ResultSet rs = DB.getResultSet(sql);

			if (rs.next()) {
				find = true;
				id = rs.getString(1);
				pw = rs.getString(2);
			} else {
				find = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return find;
	}

}
