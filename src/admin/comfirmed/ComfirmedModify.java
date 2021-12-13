package admin.comfirmed;

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

public class ComfirmedModify extends JFrame implements ActionListener {
	
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
	private JButton btnSearch;
	private JButton btnCancel;
	private ComfirmedAdmin comfirmedAdmin;
	private JButton btnModify;
	private JTextField tf7;

	//JFrame�� ��� �޾� ����� ��� << �̰� �� ��ȣ��.
	public ComfirmedModify(String title, int width, int height, ComfirmedAdmin comfirmedAdmin) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//ȭ�� ��� ����
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �ִ� Ư�� ������� �־��� ������ ������ �����ư�� Ŭ���ɶ� ���α׷��� ���� ����� 
		this.comfirmedAdmin = comfirmedAdmin;
		
		
		//��� �г�
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("�㳷���� �濪�� ���� ����Ͻô� �����ںе� �����մϴ�!!");
		//lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon,BorderLayout.WEST);
		
		//��� ��ư �г�
		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		

		
		
		pNor = new JPanel();
		pNor.setLayout(new BorderLayout());
		pNor.add(p1, BorderLayout.WEST);
		pNor.add(p2, BorderLayout.EAST);
		pNor.setBackground(Color.WHITE);
		
		
		//�߾��г�
		pCen = new JPanel();
		pCen.setLayout(new GridLayout(7, 2));
		pCen.setBackground(Color.white);
		
		JLabel lbl1 = new JLabel("   ���Ǽ� ");
		lbl1.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl2 = new JLabel("   1��-��������");
		lbl2.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl3 = new JLabel("   1��-���ϴ���");
		lbl3.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl4 = new JLabel("   2��-��������");
		lbl4.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl5 = new JLabel("   2��-���ϴ���");
		lbl5.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl6 = new JLabel("   �ν��ͼ�-��������");
		lbl6.setFont(new Font("���� ���", Font.BOLD, 13));
		JLabel lbl7 = new JLabel("   �ν��ͼ�-���ϴ���");
		lbl7.setFont(new Font("���� ���", Font.BOLD, 13));

		
		
		tf1 = new JTextField();
		tf1.setFont(new Font("���� ���", Font.BOLD, 13));
		tf2 = new JTextField();
		tf2.setFont(new Font("���� ���", Font.BOLD, 13));
		tf3 = new JTextField();
		tf3.setFont(new Font("���� ���", Font.BOLD, 13));
		tf4 = new JTextField();
		tf4.setFont(new Font("���� ���", Font.BOLD, 13));
		tf5 = new JTextField();
		tf5.setFont(new Font("���� ���", Font.BOLD, 13));
		tf6 = new JTextField();
		tf6.setFont(new Font("���� ���", Font.BOLD, 13));
		tf7 = new JTextField();
		tf7.setFont(new Font("���� ���", Font.BOLD, 13));
		
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


		
		
		//�ϴ� �г�
		JPanel pSou = new JPanel();
		pSou.setBackground(Color.WHITE);
		
		btnSearch = new JButton("�˻�");
		btnSearch.setFont(new Font("���� ���", Font.BOLD, 13));
		btnSearch.setBackground(Color.WHITE);
		btnSearch.setFocusPainted(false);
		btnSearch.addActionListener(this);
		pSou.add(btnSearch);
		
		btnModify = new JButton("����");
		btnModify.setFont(new Font("���� ���", Font.BOLD, 13));
		btnModify.setBackground(Color.WHITE);
		btnModify.setFocusPainted(false);
		btnModify.addActionListener(this);
		pSou.add(btnModify);
		
		btnCancel = new JButton("���");
		btnCancel.setFont(new Font("���� ���", Font.BOLD, 13));
		btnCancel.setBackground(Color.WHITE);
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(this);
		pSou.add(btnCancel);
		
		
		//��� �г� ���̱�
		last = new JPanel();
		last.setLayout(new BorderLayout());
		last.setBackground(Color.white);
		last.add(pNor,BorderLayout.NORTH);
		last.add(pCen);
		last.add(pSou, BorderLayout.SOUTH);
		add(last);
		setResizable(false);
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
		
//		new IsolatedAdd("�ڰ��ݸ�����", 300, 320);
	}
	
	//�̺�Ʈ ó��
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		if(obj == btnSearch) {
			if(tf1.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "���ǼҸ� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
			}else {
				String sql = "SELECT * FROM vaccinationstatus WHERE name= '" + tf1.getText() + "' ";
				IDSearchDB(sql);
				JOptionPane.showMessageDialog(this,"�˻��� �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
			}
			
			
		}else if(obj == btnModify) {
			if(tf2.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "1��-���������� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf2.requestFocus();
			}else if(tf3.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "1��-���ϴ��踦 �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf3.requestFocus();
			}else if(tf4.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "2��-���������� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf4.requestFocus();
			}else if(tf5.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "2��-���ϴ��踦 �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf5.requestFocus();
			}else if(tf6.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�ν��ͼ�-���������� �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf6.requestFocus();
			}else if(tf7.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "�ν��ͼ�-���ϴ��踦 �Է����ּ���.", "����", JOptionPane.ERROR_MESSAGE);
				tf7.requestFocus();
			}
			else {
				String sql = "UPDATE vaccinationstatus SET first_today = '"+ tf2.getText() + "', first_total = '" +tf3.getText()+ "', second_today = '"+tf4.getText()+"', second_total = '"+tf5.getText()+"', booster_today = '"+tf6.getText()+"', booster_total = '"+tf7.getText()+"' WHERE name = '"+tf1.getText()+"'"; 
				updateDB(sql);
				JOptionPane.showMessageDialog(this,"������ �Ϸ�Ǿ����ϴ�.","�޽���",JOptionPane.INFORMATION_MESSAGE);
				
				comfirmedAdmin.SelectAll(comfirmedAdmin.getModel());
				tf1.setText("");
				tf2.setText("");
				tf3.setText("");
				tf4.setText("");
				tf5.setText("");
				tf6.setText("");
				tf7.setText("");
			}
			
			
		}else if(obj == btnCancel) {
			this.dispose();
		}

		
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
				tf7.setText(rs.getString(7));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void updateDB(String sql) {
		DB.executeQuery(sql);
		
	}

}
