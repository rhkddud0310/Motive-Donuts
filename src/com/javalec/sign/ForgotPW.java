package com.javalec.sign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ForgotPW extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 비밀번호 찾기
	// Date : 2024.01.10(Ver1.0.0)
	// Author : Daegeun Lee
	// History : 1. 
	// --------------------------------------------------------------//
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblFindID;
	private JLabel lblFindPW;
	private JLabel lblId;
	private JTextField tfId;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblPhone;
	private JTextField tfPhone1;
	private JLabel lblNewLabel;
	private JTextField tfPhone2;
	private JLabel lblNewLabel_1;
	private JTextField tfPhone3;
	private JLabel lblBirthday;
	private JComboBox cbYear;
	private JLabel lblNewLabel_2;
	private JComboBox cbMonth;
	private JLabel lblNewLabel_2_1;
	private JComboBox cbDay;
	private JLabel lblNewLabel_2_1_1;
	private JLabel lblQuestion1;
	private JComboBox cbQuestion1;
	private JLabel lblAnswer1;
	private JTextField tfAnswer;
	private JLabel lblCancel;
	private JLabel lblNext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotPW frame = new ForgotPW();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ForgotPW() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		contentPane.add(getLblTimer());
		Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime(); // 분마다 시간 업데이트
            }
        });
		timer.start();
		contentPane.add(getLblFindID());
		contentPane.add(getLblFindPW());
		contentPane.add(getLblId());
		contentPane.add(getTfId());
		contentPane.add(getLblName());
		contentPane.add(getTfName());
		contentPane.add(getLblPhone());
		contentPane.add(getTfPhone1());
		contentPane.add(getLblNewLabel());
		contentPane.add(getTfPhone2());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getTfPhone3());
		contentPane.add(getLblBirthday());
		contentPane.add(getCbYear());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getCbMonth());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getCbDay());
		contentPane.add(getLblNewLabel_2_1_1());
		contentPane.add(getLblQuestion1());
		contentPane.add(getCbQuestion1());
		contentPane.add(getLblAnswer1());
		contentPane.add(getTfAnswer());
		contentPane.add(getLblCancel());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
		contentPane.add(getLblNext());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(ForgotPW.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen.setIcon(new ImageIcon(ForgotPW.class.getResource("/com/javalec/image/ForgotPW.png")));
		}
		return lblHomeScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("");
			lblTimer.setBounds(36, 32, 62, 21);
			lblTimer.setForeground(new Color(255, 255, 255));
			lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
			lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		}
		return lblTimer;
	}
	private JLabel getLblFindID() {
		if (lblFindID == null) {
			lblFindID = new JLabel("");
			lblFindID.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					findID();
				}
			});
			lblFindID.setBounds(76, 205, 103, 39);
		}
		return lblFindID;
	}
	private JLabel getLblFindPW() {
		if (lblFindPW == null) {
			lblFindPW = new JLabel("");
			lblFindPW.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					findPW();
				}
			});
			lblFindPW.setBounds(193, 205, 103, 39);
		}
		return lblFindPW;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID   :");
			lblId.setHorizontalAlignment(SwingConstants.TRAILING);
			lblId.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblId.setBounds(21, 300, 57, 15);
		}
		return lblId;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setColumns(10);
			tfId.setBounds(85, 294, 96, 30);
		}
		return tfId;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("성명   :");
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblName.setBounds(21, 350, 57, 15);
		}
		return lblName;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(85, 344, 154, 30);
		}
		return tfName;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("전화번호 :");
			lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPhone.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblPhone.setBounds(21, 400, 57, 15);
		}
		return lblPhone;
	}
	private JTextField getTfPhone1() {
		if (tfPhone1 == null) {
			tfPhone1 = new JTextField();
			tfPhone1.setText("010");
			tfPhone1.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone1.setEditable(false);
			tfPhone1.setColumns(10);
			tfPhone1.setBounds(85, 394, 38, 30);
		}
		return tfPhone1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("-");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(125, 401, 15, 15);
		}
		return lblNewLabel;
	}
	private JTextField getTfPhone2() {
		if (tfPhone2 == null) {
			tfPhone2 = new JTextField();
			tfPhone2.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone2.setColumns(10);
			tfPhone2.setBounds(143, 394, 38, 30);
		}
		return tfPhone2;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(185, 401, 15, 15);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfPhone3() {
		if (tfPhone3 == null) {
			tfPhone3 = new JTextField();
			tfPhone3.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone3.setColumns(10);
			tfPhone3.setBounds(201, 394, 38, 30);
		}
		return tfPhone3;
	}
	private JLabel getLblBirthday() {
		if (lblBirthday == null) {
			lblBirthday = new JLabel("생년월일 :");
			lblBirthday.setHorizontalAlignment(SwingConstants.TRAILING);
			lblBirthday.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblBirthday.setBounds(21, 450, 57, 15);
		}
		return lblBirthday;
	}
	private JComboBox getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox();
			cbYear.setModel(new DefaultComboBoxModel(new String[] {"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"}));
			cbYear.setSelectedIndex(80);
			cbYear.setBounds(85, 444, 52, 30);
		}
		return cbYear;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("년");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(141, 451, 15, 15);
		}
		return lblNewLabel_2;
	}
	private JComboBox getCbMonth() {
		if (cbMonth == null) {
			cbMonth = new JComboBox();
			cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			cbMonth.setSelectedIndex(0);
			cbMonth.setBounds(158, 444, 42, 30);
		}
		return cbMonth;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setBounds(204, 451, 15, 15);
		}
		return lblNewLabel_2_1;
	}
	private JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbDay.setBounds(220, 444, 42, 30);
		}
		return cbDay;
	}
	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("일");
			lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1_1.setBounds(266, 451, 15, 15);
		}
		return lblNewLabel_2_1_1;
	}
	private JLabel getLblQuestion1() {
		if (lblQuestion1 == null) {
			lblQuestion1 = new JLabel("질문  :");
			lblQuestion1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion1.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblQuestion1.setBounds(21, 500, 57, 15);
		}
		return lblQuestion1;
	}
	private JComboBox getCbQuestion1() {
		if (cbQuestion1 == null) {
			cbQuestion1 = new JComboBox();
			cbQuestion1.setModel(new DefaultComboBoxModel(new String[] {"베를린에서 물을 마시면 안되는 이유는?", "어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?", "가장 좋아하는 음식은?", "가장 싫어하는 음식은?", "기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?"}));
			cbQuestion1.setBounds(85, 494, 245, 30);
		}
		return cbQuestion1;
	}
	private JLabel getLblAnswer1() {
		if (lblAnswer1 == null) {
			lblAnswer1 = new JLabel("답변  :");
			lblAnswer1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer1.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblAnswer1.setBounds(21, 550, 57, 15);
		}
		return lblAnswer1;
	}
	private JTextField getTfAnswer() {
		if (tfAnswer == null) {
			tfAnswer = new JTextField();
			tfAnswer.setColumns(10);
			tfAnswer.setBounds(85, 544, 243, 30);
		}
		return tfAnswer;
	}
	private JLabel getLblCancel() {
		if (lblCancel == null) {
			lblCancel = new JLabel("");
			lblCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cancel();
				}
			});
			lblCancel.setBounds(194, 587, 73, 38);
		}
		return lblCancel;
	}
	private JLabel getLblNext() {
		if (lblNext == null) {
			lblNext = new JLabel("");
			lblNext.setBounds(103, 587, 73, 38);
		}
		return lblNext;
	}
	
	// --- Function ---
	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}
	
	// 아이디 찾기
	private void findID() {
		this.setVisible(false);
		ForgotID forgotId = new ForgotID();
		forgotId.setVisible(true);
	}
	
	// 비밀번호 찾기
	private void findPW() {
		this.setVisible(false);
		this.setVisible(true);
		tfId.setText("");
		tfName.setText("");
		tfPhone2.setText("");
		tfPhone3.setText("");
		tfAnswer.setText("");
	}
	
	// 취소 누르면 로그인창 이동
	private void cancel() {
		this.setVisible(false);
		SignIn signIn = new SignIn();
		signIn.setVisible(true);
	}
} // End
