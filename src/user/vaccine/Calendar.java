package user.vaccine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.DateFormatter;

import net.sourceforge.jdatepicker.JDatePicker;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Calendar extends JFrame implements ActionListener {
	
	private Object JDatePicker;
	private JButton b1;
	private JPanel p1;
	private JPanel p2;
	private JPanel p3;
	private JTextField tx1;
	private UtilDateModel model;
	private VaccineReservation vaccineReservation;
	private JLabel lbl1;
	//JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public Calendar(String title, int width, int height, VaccineReservation vaccineReservation) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); 	//화면 가운데 찍음
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임 종료버튼이 클릭될때 프로그램도 같이 사라짐 
		this.vaccineReservation = vaccineReservation;
		
		p1 = new JPanel();
		p2 = new JPanel();
		p3 = new JPanel();
		
		 model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.addActionListener(this);
		add(datePicker);
		
		lbl1 = new JLabel("접종날짜");
		lbl1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		
		tx1 = new JTextField(10);
		b1 = new JButton("확인");
		b1.addActionListener(this);

		p1.add(lbl1);
		p1.add(datePicker);
		p1.add(b1);
		p1.setBackground(Color.white);
		p2.add(tx1);
		
		p3.add(p1);
		p3.setBackground(Color.white);
		//p3.add(p2);

		add(p3);
	
		
		setVisible(true);
		
		
	}
	
//	public static void main(String[] args) {
//		new Calendar("내 프레임", 300, 200);
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if(obj == b1) {
			vaccineReservation.getDateTx().setText(model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay());
			vaccineReservation.getDateTx().setEnabled(false);
		}
	}

}
