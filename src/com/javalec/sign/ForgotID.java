package com.javalec.sign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ForgotID extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblScreen;
	private JLabel lblTimer;
	private JLabel lblFindID;
	private JLabel lblFindPW;
	private JRadioButton rdbNameQuestion;
	private JLabel lblLine;
	private JLabel lblCancel;
	private JLabel lblNext;
	private JLabel lblLine2;
	private JRadioButton rdbPhoneQuestion;
	private JLabel lblLine3;
	private JRadioButton rdbBirthdayQuestion;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblPanel;
	private JLabel lblName;
	private JTextField tfName;
	private JLabel lblQuestion;
	private JComboBox cbQuestion;
	private JLabel lblAnswer;
	private JTextField tfAnswer;
	private JLabel lblPhone;
	private JTextField tfPhone1;
	private JLabel lblDash1;
	private JTextField tfPhone2;
	private JLabel lblDash2;
	private JTextField tfPhone3;
	private JLabel lblBirthday;
	private JComboBox cbYear;
	private JLabel lblYear;
	private JComboBox cbMonth;
	private JLabel lblMonth;
	private JComboBox cbDay;
	private JLabel lblDay;
	private JLabel lblPanel2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgotID frame = new ForgotID();
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
	public ForgotID() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 375, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true); // 타이틀 바 없애기
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
		contentPane.add(getLblName());
		contentPane.add(getTfName());
		contentPane.add(getLblQuestion());
		contentPane.add(getCbQuestion());
		contentPane.add(getLblAnswer());
		contentPane.add(getTfAnswer());
		contentPane.add(getLblPhone());
		contentPane.add(getTfPhone1());
		contentPane.add(getLblDash1());
		contentPane.add(getTfPhone2());
		contentPane.add(getLblDash2());
		contentPane.add(getTfPhone3());
		contentPane.add(getLblBirthday());
		contentPane.add(getCbYear());
		contentPane.add(getLblYear());
		contentPane.add(getCbMonth());
		contentPane.add(getLblMonth());
		contentPane.add(getCbDay());
		contentPane.add(getLblDay());
		contentPane.add(getLblPanel());
		contentPane.add(getLblLine());
		contentPane.add(getRdbNameQuestion());
		contentPane.add(getLblLine2());
		contentPane.add(getRdbPhoneQuestion());
		contentPane.add(getLblLine3());
		contentPane.add(getRdbBirthdayQuestion());
		contentPane.add(getLblNext());
		contentPane.add(getLblCancel());
		contentPane.add(getLblScreen()); 
		contentPane.add(getLblIPhone());
		contentPane.add(getLblPanel2());
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("New label");
			lblScreen.setBounds(8, 10, 358, 665);
			lblScreen.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/ForgotID.png")));
		}
		return lblScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("12 : 00");
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
			lblFindID.setBounds(77, 205, 102, 39);
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
			lblFindPW.setBounds(193, 205, 102, 39);
		}
		return lblFindPW;
	}
	
	private JRadioButton getRdbNameQuestion() {
		if (rdbNameQuestion == null) {
			rdbNameQuestion = new JRadioButton("등록된 이름과 질문으로 찾기");
			rdbNameQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			buttonGroup.add(rdbNameQuestion);
			rdbNameQuestion.setBackground(new Color(255, 255, 255));
			rdbNameQuestion.setSelected(true);
			rdbNameQuestion.setBounds(79, 258, 216, 23);
		}
		return rdbNameQuestion;
	}
	private JLabel getLblLine() {
		if (lblLine == null) {
			lblLine = new JLabel("");
			lblLine.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/Line1.png")));
			lblLine.setBounds(73, 280, 225, 2);
		}
		return lblLine;
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
			lblCancel.setBounds(195, 602, 74, 39);
		}
		return lblCancel;
	}
	private JLabel getLblNext() {
		if (lblNext == null) {
			lblNext = new JLabel("");
			lblNext.setBounds(104, 601, 74, 39);
		}
		return lblNext;
	}
	private JLabel getLblLine2() {
		if (lblLine2 == null) {
			lblLine2 = new JLabel("");
			lblLine2.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/Line1.png")));
			lblLine2.setBounds(73, 482, 225, 2);
		}
		return lblLine2;
	}
	private JRadioButton getRdbPhoneQuestion() {
		if (rdbPhoneQuestion == null) {
			rdbPhoneQuestion = new JRadioButton("등록된 전화번호와 질문으로 찾기");
			rdbPhoneQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			buttonGroup.add(rdbPhoneQuestion);
			rdbPhoneQuestion.setBackground(Color.WHITE);
			rdbPhoneQuestion.setBounds(79, 460, 216, 23);
		}
		return rdbPhoneQuestion;
	}
	private JLabel getLblLine3() {
		if (lblLine3 == null) {
			lblLine3 = new JLabel("");
			lblLine3.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/Line1.png")));
			lblLine3.setBounds(73, 524, 225, 2);
		}
		return lblLine3;
	}
	private JRadioButton getRdbBirthdayQuestion() {
		if (rdbBirthdayQuestion == null) {
			rdbBirthdayQuestion = new JRadioButton("등록된 생일과 질문으로 찾기");
			rdbBirthdayQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPartition();
				}
			});
			buttonGroup.add(rdbBirthdayQuestion);
			rdbBirthdayQuestion.setBackground(Color.WHITE);
			rdbBirthdayQuestion.setBounds(79, 502, 216, 23);
		}
		return rdbBirthdayQuestion;
	}
	private JLabel getLblPanel() {
		if (lblPanel == null) {
			lblPanel = new JLabel("");
			lblPanel.setIcon(new ImageIcon(ForgotID.class.getResource("/com/javalec/image/Pannel.png")));
			lblPanel.setBounds(34, 300, 302, 148);
		}
		return lblPanel;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("이름  :");
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblName.setBounds(30, 314, 40, 15);
		}
		return lblName;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(80, 308, 96, 30);
		}
		return tfName;
	}
	private JLabel getLblQuestion() {
		if (lblQuestion == null) {
			lblQuestion = new JLabel("질문  :");
			lblQuestion.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblQuestion.setBounds(30, 364, 40, 15);
		}
		return lblQuestion;
	}
	private JComboBox getCbQuestion() {
		if (cbQuestion == null) {
			cbQuestion = new JComboBox();
			cbQuestion.setName("");
			cbQuestion.setModel(new DefaultComboBoxModel(new String[] {"베를린에서 물을 마시면 안되는 이유는?", "어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?", "가장 좋아하는 음식은?", "가장 싫어하는 음식은?", "기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?"}));
			cbQuestion.setBounds(80, 358, 245, 30);
		}
		return cbQuestion;
	}
	private JLabel getLblAnswer() {
		if (lblAnswer == null) {
			lblAnswer = new JLabel("답변  :");
			lblAnswer.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblAnswer.setBounds(30, 414, 40, 15);
		}
		return lblAnswer;
	}
	private JTextField getTfAnswer() {
		if (tfAnswer == null) {
			tfAnswer = new JTextField();
			tfAnswer.setColumns(10);
			tfAnswer.setBounds(80, 408, 243, 30);
		}
		return tfAnswer;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("번호  :");
			lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPhone.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblPhone.setBounds(30, 537, 40, 15);
			lblPhone.setVisible(false);
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
			tfPhone1.setBounds(80, 531, 38, 30);
			tfPhone1.setVisible(false);
		}
		return tfPhone1;
	}
	private JLabel getLblDash1() {
		if (lblDash1 == null) {
			lblDash1 = new JLabel("-");
			lblDash1.setHorizontalAlignment(SwingConstants.CENTER);
			lblDash1.setBounds(120, 538, 15, 15);
			lblDash1.setVisible(false);
		}
		return lblDash1;
	}
	private JTextField getTfPhone2() {
		if (tfPhone2 == null) {
			tfPhone2 = new JTextField();
			tfPhone2.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone2.setColumns(10);
			tfPhone2.setBounds(138, 531, 38, 30);
			tfPhone2.setVisible(false);
		}
		return tfPhone2;
	}
	private JLabel getLblDash2() {
		if (lblDash2 == null) {
			lblDash2 = new JLabel("-");
			lblDash2.setHorizontalAlignment(SwingConstants.CENTER);
			lblDash2.setBounds(180, 538, 15, 15);
			lblDash2.setVisible(false);
		}
		return lblDash2;
	}
	private JTextField getTfPhone3() {
		if (tfPhone3 == null) {
			tfPhone3 = new JTextField();
			tfPhone3.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone3.setColumns(10);
			tfPhone3.setBounds(196, 531, 38, 30);
			tfPhone3.setVisible(false);
		}
		return tfPhone3;
	}
	private JLabel getLblBirthday() {
		if (lblBirthday == null) {
			lblBirthday = new JLabel("생년월일 :");
			lblBirthday.setHorizontalAlignment(SwingConstants.TRAILING);
			lblBirthday.setFont(new Font("CookieRun Regular", Font.PLAIN, 10));
			lblBirthday.setBounds(24, 570, 57, 15);
			lblBirthday.setVisible(false);
		}
		return lblBirthday;
	}
	private JComboBox getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox();
			cbYear.setModel(new DefaultComboBoxModel(new String[] {"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"}));
			cbYear.setSelectedIndex(80);
			cbYear.setBounds(90, 564, 52, 30);
			cbYear.setVisible(false);
		}
		return cbYear;
	}
	private JLabel getLblYear() {
		if (lblYear == null) {
			lblYear = new JLabel("년");
			lblYear.setHorizontalAlignment(SwingConstants.CENTER);
			lblYear.setBounds(146, 570, 15, 15);
			lblYear.setVisible(false);
		}
		return lblYear;
	}
	private JComboBox getCbMonth() {
		if (cbMonth == null) {
			cbMonth = new JComboBox();
			cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			cbMonth.setSelectedIndex(0);
			cbMonth.setBounds(163, 564, 42, 30);
			cbMonth.setVisible(false);
		}
		return cbMonth;
	}
	private JLabel getLblMonth() {
		if (lblMonth == null) {
			lblMonth = new JLabel("월");
			lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
			lblMonth.setBounds(209, 570, 15, 15);
			lblMonth.setVisible(false);
		}
		return lblMonth;
	}
	private JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbDay.setBounds(225, 564, 42, 30);
			cbDay.setVisible(false);
		}
		return cbDay;
	}
	private JLabel getLblDay() {
		if (lblDay == null) {
			lblDay = new JLabel("일");
			lblDay.setHorizontalAlignment(SwingConstants.CENTER);
			lblDay.setBounds(271, 570, 15, 15);
			lblDay.setVisible(false);
		}
		return lblDay;
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
		this.setVisible(true);
		tfName.setText("");
		tfAnswer.setText("");
	}
	
	// 비밀번호 찾기
	private void findPW() {
		this.setVisible(false);
		ForgotPW forgotPw = new ForgotPW();
		forgotPw.setVisible(true);
	}
	
	// 취소 누르면 로그인 화면으로
	private void cancel() {
		this.setVisible(false);
		SignIn signIn = new SignIn();
		signIn.setVisible(true);
	}
	
	// 파티션 구별
	private void actionPartition() {
		if (rdbNameQuestion.isSelected() == true) {
			screenPartition();
		}

		if (rdbPhoneQuestion.isSelected() == true) {
			screenPartition();
		}
		
		if (rdbBirthdayQuestion.isSelected() == true) {
			screenPartition();
		}
	}
	
	// 파티션 따라 화면 바꿔주기
		private void screenPartition() {
			// 이름 보여주고 번호와 생년월일 안보여주기
			if (rdbNameQuestion.isSelected() == true) {
				lblName.setVisible(true);
				tfName.setVisible(true);
				lblPhone.setVisible(false);
				tfPhone1.setVisible(false);
				lblDash1.setVisible(false);
				tfPhone2.setVisible(false);
				lblDash2.setVisible(false);
				tfPhone3.setVisible(false);
				lblBirthday.setVisible(false);
				cbYear.setVisible(false);
				lblYear.setVisible(false);
				cbMonth.setVisible(false);
				lblMonth.setVisible(false);
				cbDay.setVisible(false);
				lblDay.setVisible(false);
				lblLine2.setBounds(73, 482, 225, 2);
				rdbPhoneQuestion.setBounds(79, 460, 216, 23);
				lblLine3.setBounds(73, 524, 225, 2);
				rdbBirthdayQuestion.setBounds(79, 502, 216, 23);
				lblName.setBounds(30, 314, 40, 15);
				tfName.setBounds(80, 308, 96, 30);
				lblQuestion.setBounds(30, 364, 40, 15);
				cbQuestion.setBounds(80, 358, 245, 30);
				lblAnswer.setBounds(30, 414, 40, 15);
				tfAnswer.setBounds(80, 408, 243, 30);
				lblPanel.setBounds(34, 300, 302, 148);
				
			}

			// 번호 보여주고 이름과 생년월일 안보여주기
			if (rdbPhoneQuestion.isSelected() == true) {
				lblName.setVisible(false);
				tfName.setVisible(false);
				lblPhone.setVisible(true);
				tfPhone1.setVisible(true);
				lblDash1.setVisible(true);
				tfPhone2.setVisible(true);
				lblDash2.setVisible(true);
				tfPhone3.setVisible(true);
				lblBirthday.setVisible(false);
				cbYear.setVisible(false);
				lblYear.setVisible(false);
				cbMonth.setVisible(false);
				lblMonth.setVisible(false);
				cbDay.setVisible(false);
				lblDay.setVisible(false);
				lblLine2.setBounds(73, 322, 225, 2);
				rdbPhoneQuestion.setBounds(79, 300, 216, 23);
				lblLine3.setBounds(73, 524, 225, 2);
				rdbBirthdayQuestion.setBounds(79, 502, 216, 23);
				lblPhone.setBounds(30, 356, 40, 15);
				tfPhone1.setBounds(80, 350, 38, 30);
				lblDash1.setBounds(120, 356, 15, 15);
				tfPhone2.setBounds(138, 350, 38, 30);
				lblDash2.setBounds(180, 356, 15, 15);
				tfPhone3.setBounds(196, 350, 38, 30);
				lblQuestion.setBounds(30, 406, 40, 15);
				cbQuestion.setBounds(80, 400, 245, 30);
				lblAnswer.setBounds(30, 456, 40, 15);
				tfAnswer.setBounds(80, 450, 243, 30);
				lblPanel.setBounds(34, 342, 302, 148);
			}
			
			if (rdbBirthdayQuestion.isSelected() == true) {
				lblName.setVisible(false);
				tfName.setVisible(false);
				lblPhone.setVisible(false);
				tfPhone1.setVisible(false);
				lblDash1.setVisible(false);
				tfPhone2.setVisible(false);
				lblDash2.setVisible(false);
				tfPhone3.setVisible(false);
				lblBirthday.setVisible(true);
				cbYear.setVisible(true);
				lblYear.setVisible(true);
				cbMonth.setVisible(true);
				lblMonth.setVisible(true);
				cbDay.setVisible(true);
				lblDay.setVisible(true);
				lblLine2.setBounds(73, 322, 225, 2);
				rdbPhoneQuestion.setBounds(79, 300, 216, 23);
				lblLine3.setBounds(73, 364, 225, 2);
				rdbBirthdayQuestion.setBounds(79, 342, 216, 23);
				lblBirthday.setBounds(24, 398, 57, 15);
				cbYear.setBounds(90, 392, 52, 30);
				lblYear.setBounds(146, 398, 15, 15);
				cbMonth.setBounds(163, 392, 42, 30);
				lblMonth.setBounds(209, 398, 15, 15);
				cbDay.setBounds(225, 392, 42, 30);
				lblDay.setBounds(271, 398, 15, 15);
				lblQuestion.setBounds(30, 448, 40, 15);
				cbQuestion.setBounds(80, 442, 245, 30);
				lblAnswer.setBounds(30, 498, 40, 15);
				tfAnswer.setBounds(80, 492, 243, 30);
				lblPanel.setBounds(34, 384, 302, 148);
			}
		}

	
	private JLabel getLblPanel2() {
		if (lblPanel2 == null) {
			lblPanel2 = new JLabel("New label");
			lblPanel2.setBounds(36, 531, 260, 65);
		}
		return lblPanel2;
	}
} // End
