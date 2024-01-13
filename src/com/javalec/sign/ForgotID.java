package com.javalec.sign;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.dao.SignDao;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ForgotID extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 아이디 찾기
	// Date : 2024.01.13(Ver1.0.0)
	// Author : Daegeun Lee
	// History : 1. 이름과 질문과 답으로 찾기
	//					2. 전화번호와 질문과 답으로 찾기
	//					3. 생년월일과 질문과 답으로 찾기
	//					4. 회원일 경우 아이디를 알려주고 탈퇴됐다면 탈퇴하였다고 알려준다.
	//						그 외에는 입력한 정보로 회원을 찾을수 없다고 한다
	// --------------------------------------------------------------//

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
			lblNext.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					insertAction();
				}
			});
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
			cbQuestion.setModel(new DefaultComboBoxModel(new String[] { "선택하여 주세요.", "베를린에서 물을 마시면 안되는 이유는?",
					"어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?", "가장 좋아하는 음식은?", "가장 싫어하는 음식은?",
					"기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?" }));
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
					// 4개의 숫자만 받기
					if (tfPhone2.getText().length() >= 5) {
						JOptionPane.showMessageDialog(null, "4자까지만 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
						tfPhone2.setText(tfPhone2.getText().substring(0, 4));
					}
				}
			});
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
			tfPhone3.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					// TextField에 숫자가 입력 되게한다
					if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
					} else {
						JOptionPane.showMessageDialog(null, "잘못 입력 되었습니다. 다시 입력하세요.", "경고", JOptionPane.ERROR_MESSAGE);
						tfPhone3.setText("");
					}
				}

				@Override
				public void keyReleased(KeyEvent e) {
					// 4개의 숫자만 받기
					if (tfPhone3.getText().length() >= 5) {
						JOptionPane.showMessageDialog(null, "4자까지만 입력하세요.", "오류", JOptionPane.ERROR_MESSAGE);
						tfPhone3.setText(tfPhone2.getText().substring(0, 4));
					}
				}
			});
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
			cbYear.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					updateDay();
				}
			});
			cbYear.setSelectedIndex(0);
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
			cbMonth.setModel(new DefaultComboBoxModel(
					new String[] { "선택", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
			cbMonth.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					updateDay();
				}
			});
			cbMonth.setSelectedIndex(0);
			cbMonth.setBounds(163, 564, 52, 30);
			cbMonth.setVisible(false);
		}
		return cbMonth;
	}

	private JLabel getLblMonth() {
		if (lblMonth == null) {
			lblMonth = new JLabel("월");
			lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
			lblMonth.setBounds(217, 570, 15, 15);
			lblMonth.setVisible(false);
		}
		return lblMonth;
	}

	private JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setModel(new DefaultComboBoxModel(new String[] { "선택", "1", "2", "3", "4", "5", "6", "7", "8", "9",
					"10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25",
					"26", "27", "28", "29", "30", "31" }));
			cbDay.setBounds(234, 564, 52, 30);
			cbDay.setVisible(false);
		}
		return cbDay;
	}

	private JLabel getLblDay() {
		if (lblDay == null) {
			lblDay = new JLabel("일");
			lblDay.setHorizontalAlignment(SwingConstants.CENTER);
			lblDay.setBounds(288, 570, 15, 15);
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
			tfName.setText("");
			cbQuestion.setSelectedItem("선택하여 주세요.");
			tfAnswer.setText("");
		}

		if (rdbPhoneQuestion.isSelected() == true) {
			screenPartition();
			tfPhone2.setText("");
			tfPhone3.setText("");
			cbQuestion.setSelectedItem("선택하여 주세요.");
			tfAnswer.setText("");
		}

		if (rdbBirthdayQuestion.isSelected() == true) {
			screenPartition();
			cbYear.setSelectedItem("선택");
			cbMonth.setSelectedItem("선택");
			cbDay.setSelectedItem("선택");
			cbQuestion.setSelectedItem("선택하여 주세요.");
			tfAnswer.setText("");
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
			cbMonth.setBounds(163, 392, 52, 30);
			lblMonth.setBounds(219, 398, 15, 15);
			cbDay.setBounds(237, 392, 52, 30);
			lblDay.setBounds(293, 398, 15, 15);
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

	private void insertAction() {
		// 입력 안했을시 체크 받기
		int check = inputCheck();
		String custname;
		String phone;
		String birthday;
		String question = (String) cbQuestion.getSelectedItem();
		String answer = tfAnswer.getText().trim();

		if (rdbNameQuestion.isSelected() == true) {
			custname = tfName.getText().trim();
			if (check != 0) {
				JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
			} else {
				SignDao signDao = new SignDao();
				SignDao user = signDao.findIdFromName(custname, question, answer);

				if (user != null) {
				    // 회원이 존재할 때의 처리
				    String memberStatus = user.getMemberStatus();
				    if ("회원".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "회원 ID는 " + user.getCustid() + "입니다.");
				    } else if ("탈퇴".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "탈퇴한 회원입니다.");
				    }
				} else {
				    // 회원이 존재하지 않을 때의 처리
				    JOptionPane.showMessageDialog(null, "입력한 정보로 찾을 수 있는 회원이 없습니다.");
				}
				 tfName.setText("");
		        cbQuestion.setSelectedItem("선택하여 주세요.");
				tfAnswer.setText("");
			}
		}

		if (rdbPhoneQuestion.isSelected() == true) {
			String phone1 = tfPhone1.getText().trim();
			String phone2 = tfPhone2.getText().trim();
			String phone3 = tfPhone3.getText().trim();
			phone = phone1 + "-" + phone2 + "-" + phone3;
			if (check != 0) {
				JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
			} else {
				SignDao signDao = new SignDao();
				SignDao user = signDao.findIdFromPhone(phone, question, answer);

				if (user != null) {
				    // 회원이 존재할 때의 처리
				    String memberStatus = user.getMemberStatus();
				    if ("회원".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "회원 ID는 " + user.getCustid() + "입니다.");
				    } else if ("탈퇴".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "탈퇴한 회원입니다.");
				    }
				} else {
				    // 회원이 존재하지 않을 때의 처리
				    JOptionPane.showMessageDialog(null, "입력한 정보로 찾을 수 있는 회원이 없습니다.");
				}
				 tfPhone2.setText("");
				 tfPhone3.setText("");
		        cbQuestion.setSelectedItem("선택하여 주세요.");
				tfAnswer.setText("");
			}
		}

		if (rdbBirthdayQuestion.isSelected() == true) {
			String year = (String) cbYear.getSelectedItem();
			String month = (String) cbMonth.getSelectedItem();
			String day = (String) cbDay.getSelectedItem();
			birthday = year + "-" + month + "-" + day;
			if (check != 0) {
				JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
			} else {
				SignDao signDao = new SignDao();
				SignDao user = signDao.findIdFromBirthday(birthday, question, answer);

				if (user != null) {
				    // 회원이 존재할 때의 처리
				    String memberStatus = user.getMemberStatus();
				    if ("회원".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "회원 ID는 " + user.getCustid() + "입니다.");
				    } else if ("탈퇴".equals(memberStatus)) {
				        JOptionPane.showMessageDialog(null, "탈퇴한 회원입니다.");
				    }
				} else {
				    // 회원이 존재하지 않을 때의 처리
				    JOptionPane.showMessageDialog(null, "입력한 정보로 찾을 수 있는 회원이 없습니다.");
				}
				cbYear.setSelectedItem("선택");
				cbMonth.setSelectedItem("선택");
				cbDay.setSelectedItem("선택");
		        cbQuestion.setSelectedItem("선택하여 주세요.");
				tfAnswer.setText("");
			}
		}

	}

	// 작성 안했을시 처리
	private int inputCheck() {
		int checkResult = 0;
		// 이름으로 찾기 선택시
		if (rdbNameQuestion.isSelected() == true) {
			if (tfName.getText().trim().length() == 0) {
				checkResult++;
				tfName.requestFocus();
			}
		}
		// 전화번호로 찾기 선택시
		if (rdbPhoneQuestion.isSelected() == true) {
			if (tfPhone2.getText().trim().length() == 0) {
				checkResult++;
				tfPhone2.requestFocus();
			}
			if (tfPhone3.getText().trim().length() == 0) {
				checkResult++;
				tfPhone3.requestFocus();
			}
		}
		// 생일로 찾기 선택시
		if (rdbBirthdayQuestion.isSelected() == true) {
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
        String[] dayValues = new String[maxDay+1];
        dayValues[0] = "선택";
        for (int i = 1; i <= maxDay; i++) {
            dayValues[i] = Integer.toString(i);
        }
        cbDay.setModel(new DefaultComboBoxModel<>(dayValues));
    }
} // End
