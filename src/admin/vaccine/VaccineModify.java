package admin.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import jdbc.DB;

public class VaccineModify extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p2;
	private JPanel pNor;
	private JPanel pCen;
	private JPasswordField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JTextField tf6;
	private JButton btnSearch;
	private JButton btnModify;
	private JButton btnCancel;
	private JPanel last;
	private VaccineAdmin vaccineAdmin;
	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineModify(String title, int width, int height, VaccineAdmin vaccineAdmin) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		this.vaccineAdmin = vaccineAdmin;
		// 상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p1.setBackground(Color.white);
		p1.add(lblicon, BorderLayout.WEST);

		// 상단 버튼 패널
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

		pNor = new JPanel();
		pNor.setLayout(new BorderLayout());
		pNor.add(p1, BorderLayout.WEST);
		pNor.add(p2, BorderLayout.EAST);
		pNor.setBackground(Color.WHITE);

		// 중앙패널
		pCen = new JPanel();
		pCen.setLayout(new GridLayout(6, 2));
		pCen.setBackground(Color.white);

		JLabel lbl1 = new JLabel("  주민번호 뒷자리 :");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl2 = new JLabel("  이름 :");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl3 = new JLabel("  주민번호 앞자리 :");
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl4 = new JLabel("  휴대폰번호 :");
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl5 = new JLabel("  접종날짜 :");
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl6 = new JLabel("  의료기관 :");
		lbl6.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		tf1 = new JPasswordField();
		tf1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf2 = new JTextField();
		tf2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf3 = new JTextField();
		tf3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf4 = new JTextField();
		tf4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf5 = new JTextField();
		tf5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf6 = new JTextField();
		tf6.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		pCen.add(lbl1);
		pCen.add(tf1);
		pCen.add(lbl2);
		pCen.add(tf2);
		pCen.add(lbl3);
		pCen.add(tf3);
		pCen.add(lbl4);
		pCen.add(tf4);
		pCen.add(lbl5);
		pCen.add(tf5);
		pCen.add(lbl6);
		pCen.add(tf6);

		// 하단 패널
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.WHITE);

		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFocusPainted(false);
		btnSearch.addActionListener(this);
		pSou.add(btnSearch);

		btnModify = new JButton("수정");
		btnModify.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnModify.setBackground(Color.WHITE);
		btnModify.setFocusPainted(false);
		btnModify.addActionListener(this);
		pSou.add(btnModify);

		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(this);
		pSou.add(btnCancel);

		// 모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(pNor, BorderLayout.NORTH);
		last.add(pCen);
		last.add(pSou, BorderLayout.SOUTH);
		add(last);
		setResizable(false);
		setVisible(true);

	}

//	public static void main(String[] args) {
//		new VaccineModify("백신 예약 수정", 500, 420);
//	}
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 94, 1050, 94);
		

	}
	
	public void UserUpdate(String sql) {
		try {
			DB.stmt.executeUpdate(sql);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JOptionPane.showMessageDialog(null, "수정이 완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void IDSearchDB(String sql) {
		try {
			ResultSet rs = DB.getResultSet(sql);
			
			while(rs.next()) {
				tf2.setText(rs.getString(2));
				tf3.setText(rs.getString(3));
				tf4.setText(rs.getString(4));
				tf5.setText(rs.getString(5));
				tf6.setText(rs.getString(6));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void updateDB(String sql) {
		try {
			DB.stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == btnSearch) {
			if(tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "주민번호 뒷자리를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			}else {
				String sql = "SELECT * FROM vaccine WHERE residentBackID = '" + tf1.getText() + "' ";
				IDSearchDB(sql);
				JOptionPane.showMessageDialog(this,"검색이 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(obj == btnModify) {
			if(tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "이름을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf2.requestFocus();
			}else if(tf3.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "시군구를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf3.requestFocus();
			}else if(tf4.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "의료기관명을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf4.requestFocus();
			}else if(tf5.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "주소를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf5.requestFocus();
			}else if(tf6.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "평일운영시간을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf6.requestFocus();
			}else {
				String sql = "UPDATE vaccine SET name='" + tf2.getText() + "', " + "residentFrontID='" + tf3.getText() + "', "
						+ "phoneNum='" + tf4.getText() + "', " + "vaccineDate='" + tf5.getText() + "', " + "medicalinstitution='" + tf6.getText() 
						+"' WHERE residentBackID = '" + tf1.getText() + "'";
				updateDB(sql);
				JOptionPane.showMessageDialog(this,"수정이 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				vaccineAdmin.getuseradd(vaccineAdmin.getModel());
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
			}
			
			
		}
		else if(obj == btnCancel) {
			dispose();
		}
	}

}
