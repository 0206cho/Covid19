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

public class IsolatedSympthoms extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblSymptom;
	private Color color;

// JFrame�� �ҷ���
	public IsolatedSympthoms() {
		this.setTitle("�ڷγ�19 �ֿ� ����");
		setSize(500, 450);
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
		
		lblSymptom = new JLabel("<HTML>���ڷγ�19 �ֿ� �����<BR/><BR/><BR/>"
				+ "  �� �߿�(37.5��)<BR/><BR/>"
				+ "  �� ��ħ, ȣ����<BR/><BR/>"
				+ "  �� ����, ������, ����<BR/><BR/>"
				+ "  �� ������<BR/><BR/>"
				+ "  �� �İ�, �̰��ҽ�<BR/><BR/>"
				+ "  �� ��� ��<BR/></HTML>");
		
		lblSymptom.setFont(new Font("�������", Font.BOLD, 14));
		pCenter.add(lblSymptom);

		// ��� �г� ���̱�
		pLast = new JPanel();
		pLast.setLayout(new BorderLayout());
		pLast.setBackground(Color.white);

		pLast.add(pUp, BorderLayout.NORTH);
		pLast.add(pCenter, BorderLayout.CENTER);

		add(pLast);

		this.setVisible(true);
	}


	public static void main(String[] args) {
		new IsolatedSympthoms(); // MyFrame() ������ �ҷ���
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
