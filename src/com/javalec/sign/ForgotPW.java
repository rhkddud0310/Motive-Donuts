package com.javalec.sign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.SignDao;

public class ForgotPW extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 비밀번호 찾기
	// Date : 2024.01.10(Ver1.0.0)
	// Author : Daegeun Lee
	// History : 1. 고객의 정보는 중요하니깐 아이디, 성명, 전화번호, 생년월일, 질문, 답변
	// 전부 입력 받아서 DB의 값과 비교한뒤 비밀번호를 알려준다
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
	private JComboBox cbQuestion;
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
		contentPane.add(getCbQuestion());
		contentPane.add(getLblAnswer1());
		contentPane.add(getTfAnswer());
		contentPane.add(getLblNext());
		contentPane.add(getLblCancel());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
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
			tfId.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					char specialKey = e.getKeyChar();
					if (specialCharacter(specialKey)) {
						JOptionPane.showMessageDialog(null, "ID에 특수문자는 안됩니다.", "경고", JOptionPane.ERROR_MESSAGE);
						tfId.setText("");
					}
				}
			});
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
			tfName.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// TextField에 숫자가 입력 되면 지운다
					char specialKey = e.getKeyChar();
					if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_SPACE
							|| specialCharacter(specialKey)) {
						JOptionPane.showMessageDialog(null, "글자만 입력하세요", "경고", JOptionPane.ERROR_MESSAGE);
						tfName.setText("");
					}
				}
			});
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
			tfPhone2.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// TextField에 숫자가 입력 되게한다
					if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					} else {
						JOptionPane.showMessageDialog(null, "잘못 입력 되었습니다. 다시 입력하세요.", "경고", JOptionPane.ERROR_MESSAGE);
						tfPhone2.setText("");
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if (tfPhone2.getText().length() >= 5) {
						JOptionPane.showMessageDialog(null, "4자까지만 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
						tfPhone2.setText(tfPhone2.getText().substring(0, 4));
					}
				}
			});
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
			tfPhone3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// TextField에 숫자가 입력 되게한다
					if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					} else {
						JOptionPane.showMessageDialog(null, "잘못 입력 되었습니다. 다시 입력하세요.", "경고", JOptionPane.ERROR_MESSAGE);
						tfPhone2.setText("");
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					if (tfPhone2.getText().length() >= 5) {
						JOptionPane.showMessageDialog(null, "4자까지만 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
						tfPhone2.setText(tfPhone2.getText().substring(0, 4));
					}
				}
			});
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
			cbYear.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					updateDay();
				}
			});
			cbYear.setModel(new DefaultComboBoxModel(new String[] { "선택", "1920", "1921", "1922", "1923", "1924",
					"1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936",
					"1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948",
					"1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960",
					"1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972",
					"1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984",
					"1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996",
					"1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008",
					"2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020",
					"2021", "2022", "2023" }));
			cbYear.setSelectedIndex(0);
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
			cbMonth.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					updateDay();
				}
			});
			cbMonth.setModel(new DefaultComboBoxModel(
					new String[] { "선택", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			cbMonth.setSelectedIndex(0);
			cbMonth.setBounds(158, 444, 52, 30);
		}
		return cbMonth;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setBounds(214, 451, 15, 15);
		}
		return lblNewLabel_2_1;
	}

	private JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setModel(new DefaultComboBoxModel(new String[] { "선택", "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
					"26", "27", "28", "29", "30", "31" }));
			cbDay.setBounds(232, 444, 52, 30);
		}
		return cbDay;
	}

	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("일");
			lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1_1.setBounds(288, 451, 15, 15);
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

	private JComboBox getCbQuestion() {
		if (cbQuestion == null) {
			cbQuestion = new JComboBox();
			cbQuestion.setModel(new DefaultComboBoxModel(new String[] { "선택하여 주세요.", "베를린에서 물을 마시면 안되는 이유는?",
					"어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?", "가장 좋아하는 음식은?", "가장 싫어하는 음식은?",
					"기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?" }));
			cbQuestion.setBounds(85, 494, 245, 30);
		}
		return cbQuestion;
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
			lblNext.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					insertAction();
				}
			});
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

	// 특수 문자 확인을 위한 조건 추가
	private boolean specialCharacter(char specialKey) {
		return "!@#$%^&*()-_=+`~/?,.<>{}[];:|\"'\\".indexOf(specialKey) != -1;
	}

	private void updateDay() {
		String selectedYear = (String) cbYear.getSelectedItem();
		String selectedMonth = (String) cbMonth.getSelectedItem();

		// 연도와 달을 선택했을시
		if (!selectedYear.equals("선택") && !selectedMonth.equals("선택")) {
			int year = Integer.parseInt(selectedYear);
			int month = Integer.parseInt(selectedMonth);

			if (month == 2) {
				// 4년에 한번 2월달은 29일
				if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
					updateDayComboBoxValues(29);
				} else {
					// 그외는 28일까지
					updateDayComboBoxValues(28);
				}
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				// 4, 6, 9, 11월은 30일
				updateDayComboBoxValues(30);
			} else {
				// 그 외 달은 31일까지 출력
				updateDayComboBoxValues(31);
			}
		} else {
			// 기본 값은 31일
			updateDayComboBoxValues(31);
		}
	}

	private void updateDayComboBoxValues(int maxDay) {
		// Update cbDay with values from 1 to maxDay
		String[] dayValues = new String[maxDay + 1];
		dayValues[0] = "선택";
		for (int i = 1; i <= maxDay; i++) {
			dayValues[i] = Integer.toString(i);
		}
		cbDay.setModel(new DefaultComboBoxModel<>(dayValues));
	}

	// 입력 안했을시 체크 받기 그리고 회원 조회
	private void insertAction() {
		int check = inputCheck();
		String custid = tfId.getText().trim();
		String custname = tfName.getText().trim();
		String phone1 = tfPhone1.getText().trim();
		String phone2 = tfPhone2.getText().trim();
		String phone3 = tfPhone3.getText().trim();
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		String year = (String) cbYear.getSelectedItem();
		String month = (String) cbMonth.getSelectedItem();
		String day = (String) cbDay.getSelectedItem();
		String birthday = year + "-" + month + "-" + day;
		String question = (String) cbQuestion.getSelectedItem();
		String answer = tfAnswer.getText().trim();

		if (check != 0) {
			JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
		} else {
			SignDao signDao = new SignDao();
			SignDao user = signDao.findPw(custid, custname, phone, birthday, question, answer);

			if (user != null) {
				// 회원이 존재할 때의 처리
				String memberStatus = user.getMemberStatus();
				if ("회원".equals(memberStatus)) {
					JOptionPane.showMessageDialog(null, "회원님의 비밀번호는 " + user.getCustpw() + "입니다.");
				} else if ("탈퇴".equals(memberStatus)) {
					JOptionPane.showMessageDialog(null, "탈퇴한 회원입니다.");
				}
			} else {
				// 회원이 존재하지 않을 때의 처리
				JOptionPane.showMessageDialog(null, "입력한 정보로 찾을 수 있는 회원이 없습니다.");
			}
			tfId.setText("");
			tfName.setText("");
			tfPhone2.setText("");
			tfPhone3.setText("");
			cbYear.setSelectedItem("선택");
			cbMonth.setSelectedItem("선택");
			cbDay.setSelectedItem("선택");
			cbQuestion.setSelectedItem("선택하여 주세요.");
			tfAnswer.setText("");
		}
	}

	// 작성 안했을시 처리
	private int inputCheck() {
		int checkResult = 0;
		if (tfId.getText().trim().length() == 0) {
			checkResult++;
			tfId.requestFocus();
		}
		if (tfName.getText().trim().length() == 0) {
			checkResult++;
			tfName.requestFocus();
		}
		if (tfPhone2.getText().trim().length() == 0) {
			checkResult++;
			tfPhone2.requestFocus();
		}
		if (tfPhone3.getText().trim().length() == 0) {
			checkResult++;
			tfPhone3.requestFocus();
		}
		if (cbYear.getSelectedItem().equals("선택")) {
			checkResult++;
			cbYear.requestFocus();
		}
		if (cbMonth.getSelectedItem().equals("선택")) {
			checkResult++;
			cbMonth.requestFocus();
		}
		if (cbDay.getSelectedItem().equals("선택")) {
			checkResult++;
			cbDay.requestFocus();
		}
		if (cbQuestion.getSelectedItem().equals("선택하여 주세요.")) {
			checkResult++;
			cbQuestion.requestFocus();
		}
		if (tfAnswer.getText().trim().length() == 0) {
			checkResult++;
			tfAnswer.requestFocus();
		}
		return checkResult;
	}

} // End
