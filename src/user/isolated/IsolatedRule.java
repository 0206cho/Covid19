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

public class IsolatedRule extends JFrame implements ActionListener {
	private JPanel pUp, pCenter, pLast;
	private JLabel lblicon, lblRule;
	private Color color;

// JFrame�� �ҷ���
	public IsolatedRule() {
		this.setTitle("�ڰ��ݸ� ����� ��Ȱ��Ģ");
		setSize(600, 600);
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
		pCenter.setLayout(new FlowLayout());
		pCenter.setBackground(color);
		
		lblRule = new JLabel("<html>���ڰ��ݸ� ����� ��Ȱ��Ģ��<br/>"
				+ "������ ���� ������ ���� �ݸ���� �ٱ� �������<br/>"
				+ "�������� �������� ȥ�� ��Ȱ�ϱ�<br/>"
				+ "      -�湮 ���� ä�� â���� ���� ���� ȯ���Ű��, �Ļ�� ȥ�ڼ� �ϱ�<br/>"
				+ "      -������ ȥ�ڸ� ����� �� �ִ� ȭ��ǰ�<br/>"
				+ "       ����밡 �ִ� ���� ����ϱ�<br/>"
				+ "          *���� ȭ���, ����븦 ����Ѵٸ�, ��� �� �ҵ�<br/>"
				+ "           (���� �� ������ҵ���)�ϰ� �ٸ� ����� ����ϵ��� �մϴ�.<br/>"
				+ "  ������ �� ������ �Ұ����� ��� �ݵ�� ���� ���Ǽҿ�<br/>" 
				+ "      ���� �����ϱ�<br/>"
				+ "  ������ �Ǵ� �Բ� �����ϴ� �а� ��ȭ �� �������� �ʱ�<br/>"
				+ "      -�Ұ����� ���, ���� �´��� �ʰ�<br/>"
				+ "       ���� ����ũ�� ���� 2m�̻��� �Ÿ��� �α�<br/>"
				+ "  �����ο�ǰ(���ο� ����, �ı��, �޴���ȭ ��)���� ����ϱ�<br/>"
				+ "      -�Ǻ� �� ħ������ �ܵ���Ź<br/>"
				+ "      -�ı�� ���� ������ �и��Ͽ� ������ �ı� ����<br/>"
				+ "       �ٸ� ����� ������� �ʵ��� �ϱ�<br/>"
				+ "  ���ǰ���Ģ ��Ű��<br/>"
				+ "      -�񴩷� 30���̻� �帣�� ���� �� �ı�, ��ħ �� ȣ���������<br/>"
				+ "       ���� ��� ����ũ ����, ����ũ�� ������ �Ҹŷ� ���� ��ħ�ϸ�<br/>"
				+ "       ��ħ, ��ä�� �� �� �İų� �� �ҵ� �ǽ��ϱ�</html>");
		lblRule.setFont(new Font("�������", Font.BOLD, 14));
		pCenter.add(lblRule);

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
		new IsolatedRule(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
