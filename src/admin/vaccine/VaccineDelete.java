package admin.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class VaccineDelete extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel p2;
	private JPanel pNor;
	private JPanel pCen;
	private JPasswordField tf1;
	private JButton btnDelete;
	private JButton btnCancel;
	private JPanel last;
	private VaccineAdmin vaccineAdmin;
	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public VaccineDelete(String title, int width, int height, VaccineAdmin vaccineAdmin) {
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
		// lblicon.setPreferredSize(new Dimension(150, 150));
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
		pCen.setLayout(null);
		pCen.setBackground(Color.white);

		JLabel lbl1 = new JLabel("주민등록번호 뒷자리 :");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lbl1.setBounds(10, 50, 130, 20);

		tf1 = new JPasswordField();
		tf1.setBounds(150, 50, 100, 25);
		tf1.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		pCen.add(lbl1);
		pCen.add(tf1);

		// 하단 패널
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.WHITE);

		btnDelete = new JButton("삭제");
		btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnDelete.setBackground(Color.WHITE);
		btnDelete.setFocusPainted(false);
		btnDelete.addActionListener(this);
		pSou.add(btnDelete);

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
		
		setVisible(true);

	}

//	public static void main(String[] args) {
//		new VaccineDelete("백신 예약 취소", 300, 300);
//	}
	
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 94, 1050, 94);

	}
	private void deleteDB(String sql) {
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
		if(obj == btnDelete) {
			if(tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "주민등록번호 뒷자리를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf1.requestFocus();
			}else {
				String sql = "DELETE FROM vaccine WHERE residentBackID = '" + tf1.getText() + "' ";
				JOptionPane.showMessageDialog(this,"삭제가 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				deleteDB(sql);
				tf1.setText("");
				vaccineAdmin.getuseradd(vaccineAdmin.getModel());
			}
			
		}else if(obj == btnCancel) {
			this.dispose();
		}
	}

}
