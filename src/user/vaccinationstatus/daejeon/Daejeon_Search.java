package user.vaccinationstatus.daejeon;

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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;

public class Daejeon_Search extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JTextField tx1;
	private JPanel p2;
	private JLabel lblicon;
	private JPanel last;
	private JButton b1;
	private JComboBox<String> cb;
	private JPanel p3;
	private JButton b2;
	private JLabel lb1;
	private  Daejeon_Comfirmed Daejeon_Comfirmed;
	private JLabel lbl1;
	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Daejeon_Search(String title, int width, int height, Daejeon_Comfirmed Daegu_Comfirmed) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		String [] hi = {"보건소"};
		this.Daejeon_Comfirmed = Daegu_Comfirmed;
		p2 = new JPanel();
		p2.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		p2.setBackground(Color.white);
		p2.add(lblicon,BorderLayout.WEST);
		
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(new FlowLayout());
		cb = new JComboBox<String>(hi);
		cb.setBackground(Color.white);
		cb.addActionListener(this);
		tx1 = new JTextField("",10);
		p1.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		lbl1 = new JLabel("검색어를 입력해주세요. EX) 보건소 - 강서구");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		p1.add(lbl1);
		p1.add(cb);
		p1.add(tx1);
		
		p3 = new JPanel();
		p3.setBackground(Color.white);
		b1 = new JButton("확인");
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b1.setBackground(Color.white);
		b1.addActionListener(this);
		b2 = new JButton("취소");
		b2.addActionListener(this);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		b2.setBackground(Color.white);
		
		p3.add(b1);
		p3.add(b2);
		
		
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
	

	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			String getCombo = cb.getSelectedItem().toString();
			if(getCombo.equals("거주지")) {
				getCombo = "LOCAL";
			}
//			else if(getCombo.equals("확진자")) {
//				getCombo = "PERSON";
//			}
//			else if(getCombo.equals("확진일")) {
//				getCombo = "DATE";
//			}
//			else if(getCombo.equals("감염경로")) {
//				getCombo = "ROUTE";
//			}
//			else if(getCombo.equals("증상유무")) {
//				getCombo = "SYMPTOM";
//			}
			try {
				Daejeon_Comfirmed.getSearch(Daejeon_Comfirmed.getModel(), getCombo, tx1.getText());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if (obj == cb) {
				int getIndex = cb.getSelectedIndex();
				if(getIndex == 0) {
					tx1.setText("");
				}
				else {
					tx1.setText("");
				}
			}
		else if (obj == b2) {
			dispose();
		}
	}

}
