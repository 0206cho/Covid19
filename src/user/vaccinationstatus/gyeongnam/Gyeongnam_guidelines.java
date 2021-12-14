package user.vaccinationstatus.gyeongnam;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gyeongnam_guidelines extends JFrame {
	
	private JLabel lblicon;
	private JPanel p1;

	//JFrame�� ��� �޾� ����� ��� << �̰� �� ��ȣ��.
	public Gyeongnam_guidelines(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//ȭ�� ��� ����
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //������ �ִ� Ư�� ������� �־��� ������ ������ �����ư�� Ŭ���ɶ� ���α׷��� ���� ����� 
		
		p1 = new JPanel();
		p1.setBackground(Color.white);
		ImageIcon icon = new ImageIcon("images/1.5.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("�㳷���� �濪�� ���� ����Ͻô� �����ںе� �����մϴ�!!");
		p1.add(lblicon);
		
		add(p1);
		setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		new Gyeongnam_guidelines("�泲 �濪��ħ", 610, 670);
	}

}
