package admin.medicalstation;

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

public class MedicalStationAdd extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel pCen;
	private JButton b1;
	private JButton b2;
	private JPanel p3;
	private JPanel pNor;
	private JPanel p2;
	private JTextField tf1;
	private JTextField tf2;
	private JTextField tf3;
	private JTextField tf4;
	private JTextField tf5;
	private JTextField tf6;
	private JButton btnOK;
	private JButton btnCancel;
	private MedicalStationAdmin medicalstationAdmin;
	private JTextField tf7;
	private JTextField tf8;
	private JTextField tf9;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public MedicalStationAdd(String title, int width, int height, MedicalStationAdmin medicalstationAdmin) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		this.medicalstationAdmin = medicalstationAdmin;
		
		
		//상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		//상단 버튼 패널
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		

		
		
		pNor = new JPanel();
		pNor.setLayout(new BorderLayout());
		pNor.add(p1, BorderLayout.WEST);
		pNor.add(p2, BorderLayout.EAST);
		pNor.setBackground(Color.WHITE);
		
		
		//중앙패널
		pCen = new JPanel();
		pCen.setLayout(new GridLayout(9, 2));
		pCen.setBackground(Color.white);
		
		JLabel lbl1 = new JLabel("  연번 :");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl2 = new JLabel("  시도:");
		lbl2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl3 = new JLabel("  시군구 :");
		lbl3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl4 = new JLabel("  의료기관명 :");
		lbl4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl5 = new JLabel("  주소 :");
		lbl5.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl6 = new JLabel("  평일운영시간 :");
		lbl6.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl7 = new JLabel("  토요일운영시간 :");
		lbl7.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl8 = new JLabel("  공휴일운영시간 :");
		lbl8.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		JLabel lbl9 = new JLabel("  대표 전화번호 :");
		lbl9.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		tf1 = new JTextField();
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
		
		tf7 = new JTextField();
		tf7.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf8 = new JTextField();
		tf8.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		tf9 = new JTextField();
		tf9.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
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
		pCen.add(lbl7);
		pCen.add(tf7);
		pCen.add(lbl8);
		pCen.add(tf8);
		pCen.add(lbl9);
		pCen.add(tf9);
		
		
		//하단 패널
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.WHITE);
		
		btnOK = new JButton("확인");
		btnOK.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnOK.setBackground(Color.WHITE);
		btnOK.setFocusPainted(false);
		btnOK.addActionListener(this);
		pSou.add(btnOK);
		
		btnCancel = new JButton("취소");
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(this);
		pSou.add(btnCancel);
		
		
		//모든 패널 붙이기
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(pNor,BorderLayout.NORTH);
		last.add(pCen);
		last.add(pSou, BorderLayout.SOUTH);
		add(last);
		
		setVisible(true);
		
		
	}

	public void paint (Graphics g) {
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
		
//		new IsolatedAdd("자가격리괸리", 300, 320);
	}
	
	//이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnOK) {
			if(tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "연번을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf1.requestFocus();
			}else if(tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "시도를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
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
			}else if(tf7.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "토요일운영시간을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf7.requestFocus();
			}else if(tf8.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "공휴일운영시간을 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf8.requestFocus();
			}else if(tf9.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "대표전화번호를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf9.requestFocus();
			}else {
				String sql = "INSERT INTO medicalstation VALUES('" + tf1.getText() +"', '" + tf2.getText() + "', '" + tf3.getText() + "', '"
						+ tf4.getText() + "', '" + tf5.getText() + "', '" + tf6.getText() +"', '" + tf7.getText() + "', '" + tf8.getText() + "', '" + tf9.getText() +"' )";
				setInsertDB(sql);
				JOptionPane.showMessageDialog(this,"처리가 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
				tf7.setText("");
				tf8.setText("");
				tf9.setText("");
				medicalstationAdmin.SelectAll(medicalstationAdmin.getModel());
			}
			
		}else if(obj == btnCancel) {
			this.dispose();
		}

		
	}

	private void setInsertDB(String sql) {
		DB.executeQuery(sql);
		
	}

}
