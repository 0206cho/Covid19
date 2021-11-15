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

public class IsolatedTogether extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblTogether;
	private Color color;

// JFrame�� �ҷ���
	public IsolatedTogether() {
		this.setTitle("�ڰ��ݸ�������� ���� �� �������� ���� ��Ȱ��Ģ");
		setSize(480, 600);
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
		
		lblTogether = new JLabel("<HTML>���ڰ��ݸ�������� ���� �� �������� ���� ��Ȱ��Ģ��<BR/><BR/>"
				+ "  �� ���� �Ǵ� �������� �ִ��� �ڰ��ݸ�����ڿ�<BR/>"
				+ "      �������� �ʵ��� �ϱ�<BR/>"
				+ "      - Ư��, ����, �ӻ��, �Ҿ�, ������ȯ, �� ��<BR/>"
				+ "       �鿪���� ���ϵ� ���� ���� �����ϱ�<BR/>"
				+ "      - �ܺ����� �湮 �����ϱ�<BR/>"
				+ "  �� �ڰ��ݸ�����ڿ� �Ұ����ϰ� ������ ���<BR/>"
				+ "      ����ũ�� ���� ���� 2m�̻��� �Ÿ��� �α�<BR/>"
				+ "  �� �ڰ��ݸ�����ڿ� ������ �������� ��Ȱ�Ͻð�,<BR/>"
				+ "      �������� ����ϴ� ������ ���� ȯ�⸦ ��Ű��<BR/>"
				+ "  �� �� �Ǵ� �ռ������� �̿��Ͽ�<BR/>"
				+ "      �帣�� ���� 30�� �̻� ���� �� �ı�<BR/>"
				+ "  �� �ڰ��ݸ�����ڿ� ��Ȱ��ǰ�� �����Ͽ� ����ϱ�<BR/>"
				+ "      - �ڰ��ݸ�������� �Ǻ� �� ħ������ �ܵ���Ź<BR/>"
				+ "      - �ڰ��ݸ�������� �ı�� ���� ������ �и��Ͽ�<BR/>"
				+ "       ������ �ı� ���� �ٸ� ����� ������� �ʵ��� �ϱ�<BR/>"
				+ "  �� ���̺� ��, ��������, ��Ǳⱸ, Ű����, ħ�� �� ���̺� ��<BR/>"
				+ "      �ձ��� ���� ��� ���� ǥ�� ���� �۱�<BR/>"
				+ "  �� �ڰ��ݸ�������� �ǰ����¸� ���� ��� �����ϱ�<BR/></HTML>");
		
		lblTogether.setFont(new Font("�������", Font.BOLD, 14));
		pCenter.add(lblTogether);

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
		new IsolatedTogether(); // MyFrame() ������ �ҷ���
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
