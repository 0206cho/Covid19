package user.isolated;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class IsolatedMonitoring extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblMonitoring;
	private Color color;

// JFrame�� �ҷ���
	public IsolatedMonitoring() {
		this.setTitle("�ڰ�����͸� ���");
		setSize(500, 400);
		setLocationRelativeTo(this); // �� �ڽ����κ��� ������� ��ġ ����
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // ������ ȭ��ũ�� ���� �Ұ�

		// ��� �г�
		pUp = new JPanel();
		pUp.setLayout(new BorderLayout());
		pUp.setBackground(Color.white);

		// �ΰ�
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("�㳷���� �濪�� ���� ����Ͻô� �����ںе� �����մϴ�!!");
		// lblicon.setPreferredSize(new Dimension(150, 150));

		pUp.add(lblicon, BorderLayout.WEST);
	

		// �߾��г�
		color = new Color(0xE6FFFF);

		pCenter = new JPanel();
		pCenter.setLayout(new BorderLayout());
		pCenter.setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		pCenter.setBackground(color);
		
		lblMonitoring = new JLabel("<html>���ڰ�����͸� �����<br/><br/><br/>"
				+ "�� ���� ��ħ, �������� ü�� �����ϱ�<br/><br/><br/>"
				+ "�� ȣ������� �� ���� ������ ��Ÿ������ ������ �ǰ����� Ȯ��<br/><br/><br/>"
				+ "�� ���Ǽҿ��� 1�� 1ȸ �̻� ���� ��, ���� ���� �˷��ֱ�<br/></html>");
		lblMonitoring.setFont(new Font("�������", Font.BOLD, 14));
		pCenter.add(lblMonitoring);

		// ��� �г� ���̱�
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);

		this.setVisible(true); // ȭ�鿡 �������� ��
	}


	public static void main(String[] args) {
		new IsolatedMonitoring(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
