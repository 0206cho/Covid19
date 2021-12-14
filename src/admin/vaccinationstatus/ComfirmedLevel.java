package admin.vaccinationstatus;

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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import jdbc.DB;

public class ComfirmedLevel extends JFrame implements ActionListener {
	
	private JPanel p1;
	private JLabel lblicon;
	private JPanel last;
	private JPanel pCen;
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane sp;
	private JPanel pNor;
	private JButton btnModify;
	private JTextField tf1;
	private String [] local = {"서울", "경기", "인천","강원도", "경상남도", "경상북도", "광주광역시",
			"대구", "대전광역시", "부산", "세종자치시", "울산", "전라남도", "전라북도", "제주도", "충청남도", "충청북도"};
	private JComboBox<String> cb;

	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public ComfirmedLevel(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		
		
		//상단 패널
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		

		

		
		pNor = new JPanel();
		pNor.setLayout(new BorderLayout());
		pNor.add(p1, BorderLayout.WEST);
		pNor.setBackground(Color.WHITE);
		
		
		//중앙패널
		pCen = new JPanel();
//		p2.setLayout(new BorderLayout());
		pCen.setBackground(Color.white);
		
		
		String [] column = { "지역", "단계"};
		
		model = new DefaultTableModel(column, 0);
		table = new JTable(model);
		sp = new JScrollPane(table);
		sp.setPreferredSize(new Dimension(280, 250));
		
		SelectAll(model);
		
		pCen.add(sp);
		
		
		
		//하단
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.white);
		
		
		cb = new JComboBox<String>(local);
		cb.setBackground(Color.white);
		pSou.add(cb);
		
		tf1 = new JTextField(5);
		tf1.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		pSou.add(tf1);
		
		btnModify = new JButton("수정");
		btnModify.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		btnModify.setBackground(Color.white);
		btnModify.setFocusPainted(false);
		btnModify.addActionListener(this);
		pSou.add(btnModify);

		
		
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
	public void SelectAll(DefaultTableModel model) {
		String sql = "select * from level";
		
		try {
			ResultSet rs = DB.getResultSet(sql);
			//전체데이터 삭제후 다시 출력
			for(int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}
			
			while(rs.next()) {
				String data[] = { rs.getString(1), rs.getString(2) 	};
				model.addRow(data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public DefaultTableModel getModel() {
		return model;
	}
	
	public void paint (Graphics g) {
		super.paint(g);
		g.drawLine(0, 100, 1050, 100);
		

	}
	public static void main(String[] args) {
		try {
			DB.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new ComfirmedLevel("지역별거리단계괸리", 300, 400);
	}
	
	//이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		if(obj==btnModify) {
			if(tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "단계를 입력해주세요.", "오류", JOptionPane.ERROR_MESSAGE);
				tf1.requestFocus();
			}else {
				int index = cb.getSelectedIndex();
				String ILocal = local[index];
				String sql = "UPDATE level SET levelNum='" + tf1.getText()  + "' WHERE local = '" + ILocal + "' ";
				updateDB(sql);
				JOptionPane.showMessageDialog(this,"수정이 완료되었습니다.","메시지",JOptionPane.INFORMATION_MESSAGE);
				
				this.SelectAll(model);
				tf1.setText("");
			}
		}
		
	}
	
	private void updateDB(String sql) {
		DB.executeQuery(sql);
		
	}

}
