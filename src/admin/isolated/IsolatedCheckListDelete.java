package admin.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;

public class IsolatedCheckListDelete extends JFrame implements ActionListener {

	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel pCen;
	private JPanel pNor;
	private JPanel p2;
	private JTextField tf1;
	private JTextField tf2;
	private JButton btnDelete;
	private JButton btnCancel;
	private IsolatedCheckListAdmin isolatedCheckListAdmin;
	private JButton btnSearch;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public IsolatedCheckListDelete(String title, int width, int height, IsolatedCheckListAdmin isolatedCheckListAdmin) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐
		setResizable(false); // 실행후 화면크기 변경 불가
		this.isolatedCheckListAdmin = isolatedCheckListAdmin;

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

		JLabel lbl1 = new JLabel("   연번  ");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lbl1.setBounds(50, 50, 70, 20);

		JLabel lbl2 = new JLabel("   ID ");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		lbl2.setBounds(50, 80, 70, 20);

		tf1 = new JTextField();
		tf1.setBounds(120, 50, 100, 25);
		tf1.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		tf2 = new JTextField();
		tf2.setBounds(120, 80, 100, 25);
		tf2.setFont(new Font("맑은 고딕", Font.BOLD, 13));

		pCen.add(lbl1);
		pCen.add(tf1);
		pCen.add(lbl2);
		pCen.add(tf2);

		// 하단 패널
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.WHITE);

		btnSearch = new JButton("검색");
		btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFocusPainted(false);
		btnSearch.addActionListener(this);
		pSou.add(btnSearch);

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
		setResizable(false);
		setVisible(true);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.drawLine(0, 94, 1050, 94);

	}

	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == btnSearch) {
			if (tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "연번을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
			} else {
				String sql = "SELECT * FROM isolatedCheckList WHERE isolatedCheckListID= '" + tf1.getText() + "' ";
				IDSearchDB(sql);
				JOptionPane.showMessageDialog(this, "검색이 완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (obj == btnDelete) {
			if (tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "연번을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf1.requestFocus();
			} else if (tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "ID를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf2.requestFocus();
			} else {
				String sql = "DELETE FROM isolatedCheckList WHERE isolatedCheckListID = '" + tf1.getText()
						+ "' AND  isolatedID = '" + tf2.getText() + "' ";
				deleteDB(sql);

				tf1.setText("");
				tf2.setText("");
				isolatedCheckListAdmin.SelectAll(isolatedCheckListAdmin.getModel());
			}

		} else if (obj == btnCancel) {
			this.dispose();
		}
	}
	
	private void IDSearchDB(String sql) {
		try {
			ResultSet rs = DB.getResultSet(sql);
			
			while(rs.next()) {
				tf2.setText(rs.getString(2));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void deleteDB(String sql) {
		DB.executeQuery(sql);
		JOptionPane.showMessageDialog(this, "삭제가 완료되었습니다.", "메시지", JOptionPane.INFORMATION_MESSAGE);
	}

}
