package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.lang.model.util.Elements;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import jdbc.DB;
import user.comfirmed_case.Distancing_Map;
import user.isolated.IsolatedLogin;
import user.medicalstation.Medicalstation;
import user.vaccine.VaccineIntro;

public class UserMain extends JFrame implements ActionListener {

	private JLabel lblicon;
	private JPanel p1, top_right;
	private JPanel p2;
	private Graphics Graphics;
	private java.awt.Graphics g;
	private JButton b1;
	private JButton b2;
	private JButton b3;
	private JButton b4;
	private JButton b5;

	String url = "https://search.naver.com/search.naver?sm=tab_hty.top&where=nexearch&query=%EC%BD%94%EB%A1%9C%EB%82%98&oquery=%EC%BD%94%EB%A1%9C%EB%82%98&tqi=hh3z4lprvTVssa%2FOlrRssssssv8-379439";
	org.jsoup.select.Elements confirmed_elm, death_elm, totalCon_elm, totalDea_elm;
	String today_confirmed, today_death, today_totalCon, today_totalDea;
	Document doc;
	private JLabel covid_label;
	private JPanel last;
	private JPanel test_panel;
	private JPanel p6;
	private JLabel local_label;

	// JFrame을 상속 받아 만드는 방법 << 이걸 더 선호함.
	public UserMain(String title, int width, int height) {
		this.setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(this); // 화면 가운데 찍음
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //닫을수 있는 특정 상수값을 주었기 때문에 프레임
		// 종료버튼이 클릭될때 프로그램도 같이 사라짐

		// 실행 간격 지정(3600초)
		int sec = 3600;
		// 주기적인 작업을 위한
		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
		exec.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				covidScoreboard();
				// 에러 발생시 중지시킴
				exec.shutdown();
			}
		}, 0, sec, TimeUnit.SECONDS);

		p1 = new JPanel();
		p1.setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon("images/logo2.JPG");
		lblicon = new JLabel(icon);
		lblicon.setToolTipText("밤낮없이 방역을 위해 고생하시는 관계자분들 응원합니다!!");
		// lblicon.setPreferredSize(new Dimension(150, 150));
		p1.setBackground(Color.white);
		p1.add(lblicon, BorderLayout.WEST);

		top_right = new JPanel();
		top_right.setBackground(Color.white);
		top_right.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
		top_right.setLayout(new FlowLayout(FlowLayout.RIGHT));
		covid_label = new JLabel();
		covid_label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
//		local_label = new JLabel();
//		local_label.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		top_right.add(covid_label);
//		top_right.add(local_label);

		p6 = new JPanel();
		p6.setBackground(Color.white);
		p6.setLayout(new BorderLayout());
		p6.add(top_right, BorderLayout.EAST);

		test_panel = new JPanel();
		test_panel.setLayout(new BorderLayout());
		test_panel.add(p1, BorderLayout.WEST);
		test_panel.add(p6);

		p2 = new JPanel();
		p2.setBackground(Color.WHITE);
		p2.setLayout(null);

		b1 = new JButton("백신접종현황");
		b1.setBounds(210, 100, 140, 100);
		b1.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b1.setFocusPainted(false);
		b1.setBackground(Color.white);
		b1.addActionListener(this);

		b2 = new JButton("백신예약");
		b2.setBounds(380, 100, 120, 100);
		b2.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b2.setFocusPainted(false);
		b2.setBackground(Color.white);
		b2.addActionListener(this);

		b3 = new JButton("선별진료소");
		b3.setBounds(530, 100, 120, 100);
		b3.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b3.setFocusPainted(false);
		b3.setBackground(Color.white);
		b3.addActionListener(this);

		b5 = new JButton("자가격리자");
		b5.setBounds(680, 100, 120, 100);
		b5.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		b5.setFocusPainted(false);
		b5.setBackground(Color.white);
		b5.addActionListener(this);

		p2.add(b1);
		p2.add(b2);
		p2.add(b3);
		p2.add(b5);

		last = new JPanel();
		last.setBackground(Color.white);
		last.setLayout(new BorderLayout());
		last.add(test_panel, BorderLayout.NORTH);
		last.add(p2);
		add(last);

		setVisible(true);
	}

	public void paint(Graphics g) {
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
		new UserMain("이용자", 1020, 420);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj == b1) {
			new Distancing_Map("지역별 거리두기 단계", 665, 830);
		} else if (obj == b2) {
			new VaccineIntro("백신 예약 및 조회", 800, 600);
		} else if (obj == b3) {
			new Medicalstation("선별 진료소 목록", 1800, 870);
		} else if (obj == b5) {
			new IsolatedLogin("자가격리자 로그인", 650, 520);
		}
	}

	public void covidScoreboard() {
//		// 실행 간격 지정(3600초)
//		int sec = 3600;
//		// 주기적인 작업을 위한
//		final ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);
//		exec.scheduleAtFixedRate(new Runnable() {
//			@Override
//			public void run() {
		try {

			doc = Jsoup.connect(url).get();
			confirmed_elm = doc
					.select("#_cs_production_type > div > div.main_tab_area > div > div > ul > li.info_01 > em");
			death_elm = doc.select("#_cs_production_type > div > div.main_tab_area > div > div > ul > li.info_04 > em");
			totalCon_elm = doc
					.select("#_cs_production_type > div > div.main_tab_area > div > div > ul > li.info_01 > p");
			totalDea_elm = doc
					.select("#_cs_production_type > div > div.main_tab_area > div > div > ul > li.info_04 > p");

			today_confirmed = confirmed_elm.text();
			today_death = death_elm.text();
			today_totalCon = totalCon_elm.text();
			today_totalDea = totalDea_elm.text();

			covid_label.setText("일일 확진자 :  " + today_confirmed + "  누적 확진자 :  " + today_totalCon + "  일일 사망자 :  "
					+ today_death + "  ");
//					local_label.setText("<html><body><center>" +"sdfdsf"+ "</body></html>");
//					System.out.println("일일 확진자 : " + today_confirmed);
//					System.out.println("일일 사망자 : " + today_death);
//					System.out.println("누적 확진자 : " + today_totalCon);
//					System.out.println("누적 사망자 : " + today_totalDea);
		} catch (Exception e) {
			e.printStackTrace();

			// 에러 발생시 중지시킴
//					exec.shutdown();
//				}
//			}
//		}, 0, sec, TimeUnit.SECONDS);
		}
	}

}
