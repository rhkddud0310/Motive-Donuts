package com.javalec.sign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUp extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 로그인&회원가입
	// Date : 2024.01.08(Ver1.0)
	// Author : Daegeun Lee
	// History : 1. ID&PW를 받아서 DB에 있는 데이터와 비교한뒤 true, false로 체크한다
	//			 2. 정규식으로 예외처리한다
	// --------------------------------------------------------------//
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblId;
	private JLabel lblPw;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblBirthday;
	private JLabel lblQuestion1;
	private JLabel lblAnswer1;
	private JLabel lblQuestion2;
	private JLabel lblAnswer2;
	private JLabel lblProfile;
	private JTextField tfId;
	private JPasswordField pfPassword1;
	private JLabel lblPwCheck;
	private JPasswordField pfPassword2;
	private JTextField tfName;
	private JTextField tfPhone1;
	private JTextField tfPhone2;
	private JTextField tfPhone3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JComboBox cbYear;
	private JLabel lblNewLabel_2;
	private JComboBox cbMonth;
	private JLabel lblNewLabel_2_1;
	private JComboBox cbDay;
	private JLabel lblNewLabel_2_1_1;
	private JComboBox cbQuestion1;
	private JTextField tfAnswer1;
	private JComboBox cbQuestion2;
	private JTextField tfAnswer2;
	private JLabel lblLine;
	private JLabel lblCompare;
	private JLabel lblLine_1;
	private JLabel lblLine_1_1;
	private JLabel lblImage;
	private JCheckBox chkAgree;
	private JLabel lblFile;
	private JLabel lblCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	public SignUp() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		contentPane.add(getLblId());
		contentPane.add(getLblPw());
		contentPane.add(getLblName());
		contentPane.add(getLblPhone());
		contentPane.add(getLblBirthday());
		contentPane.add(getLblQuestion1());
		contentPane.add(getLblAnswer1());
		contentPane.add(getLblQuestion2());
		contentPane.add(getLblAnswer2());
		contentPane.add(getLblProfile());
		contentPane.add(getTfId());
		contentPane.add(getPfPassword1());
		contentPane.add(getLblPwCheck());
		contentPane.add(getPfPassword2());
		contentPane.add(getTfName());
		contentPane.add(getTfPhone1());
		contentPane.add(getTfPhone2());
		contentPane.add(getTfPhone3());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getCbYear());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getCbMonth());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getCbDay());
		contentPane.add(getLblNewLabel_2_1_1());
		contentPane.add(getCbQuestion1());
		contentPane.add(getTfAnswer1());
		contentPane.add(getCbQuestion2());
		contentPane.add(getTfAnswer2());
		contentPane.add(getLblLine());
		contentPane.add(getLblCompare());
		contentPane.add(getLblLine_1());
		contentPane.add(getLblLine_1_1());
		contentPane.add(getLblImage());
		contentPane.add(getChkAgree());
		contentPane.add(getLblFile());
		contentPane.add(getLblCancel());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/SignUp.png")));
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

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID   :");
			lblId.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblId.setHorizontalAlignment(SwingConstants.TRAILING);
			lblId.setBounds(20, 95, 57, 15);
		}
		return lblId;
	}
	private JLabel getLblPw() {
		if (lblPw == null) {
			lblPw = new JLabel("PW   :");
			lblPw.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblPw.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPw.setBounds(20, 135, 57, 15);
		}
		return lblPw;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("성명   :");
			lblName.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setBounds(20, 215, 57, 15);
		}
		return lblName;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("전화번호 :");
			lblPhone.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPhone.setBounds(20, 255, 57, 15);
		}
		return lblPhone;
	}
	private JLabel getLblBirthday() {
		if (lblBirthday == null) {
			lblBirthday = new JLabel("생년월일 :");
			lblBirthday.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblBirthday.setHorizontalAlignment(SwingConstants.TRAILING);
			lblBirthday.setBounds(20, 296, 57, 15);
		}
		return lblBirthday;
	}
	private JLabel getLblQuestion1() {
		if (lblQuestion1 == null) {
			lblQuestion1 = new JLabel("질문1  :");
			lblQuestion1.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblQuestion1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion1.setBounds(20, 336, 57, 15);
		}
		return lblQuestion1;
	}
	private JLabel getLblAnswer1() {
		if (lblAnswer1 == null) {
			lblAnswer1 = new JLabel("답변  :");
			lblAnswer1.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblAnswer1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer1.setBounds(20, 369, 57, 15);
		}
		return lblAnswer1;
	}
	private JLabel getLblQuestion2() {
		if (lblQuestion2 == null) {
			lblQuestion2 = new JLabel("질문2  :");
			lblQuestion2.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblQuestion2.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion2.setBounds(20, 402, 57, 15);
		}
		return lblQuestion2;
	}
	private JLabel getLblAnswer2() {
		if (lblAnswer2 == null) {
			lblAnswer2 = new JLabel("답변  :");
			lblAnswer2.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblAnswer2.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer2.setBounds(20, 435, 57, 15);
		}
		return lblAnswer2;
	}
	private JLabel getLblProfile() {
		if (lblProfile == null) {
			lblProfile = new JLabel("프로필  :");
			lblProfile.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblProfile.setHorizontalAlignment(SwingConstants.TRAILING);
			lblProfile.setBounds(20, 473, 57, 15);
		}
		return lblProfile;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setBounds(91, 89, 96, 30);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JPasswordField getPfPassword1() {
		if (pfPassword1 == null) {
			pfPassword1 = new JPasswordField();
			pfPassword1.setBounds(91, 129, 154, 30);
		}
		return pfPassword1;
	}
	private JLabel getLblPwCheck() {
		if (lblPwCheck == null) {
			lblPwCheck = new JLabel("Check  :");
			lblPwCheck.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPwCheck.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblPwCheck.setBounds(20, 175, 57, 15);
		}
		return lblPwCheck;
	}
	private JPasswordField getPfPassword2() {
		if (pfPassword2 == null) {
			pfPassword2 = new JPasswordField();
			pfPassword2.setBounds(91, 169, 154, 30);
		}
		return pfPassword2;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setColumns(10);
			tfName.setBounds(91, 209, 154, 30);
		}
		return tfName;
	}
	private JTextField getTfPhone1() {
		if (tfPhone1 == null) {
			tfPhone1 = new JTextField();
			tfPhone1.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone1.setEditable(false);
			tfPhone1.setText("010");
			tfPhone1.setBounds(91, 249, 38, 30);
			tfPhone1.setColumns(10);
		}
		return tfPhone1;
	}
	private JTextField getTfPhone2() {
		if (tfPhone2 == null) {
			tfPhone2 = new JTextField();
			tfPhone2.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone2.setColumns(10);
			tfPhone2.setBounds(149, 249, 38, 30);
		}
		return tfPhone2;
	}
	private JTextField getTfPhone3() {
		if (tfPhone3 == null) {
			tfPhone3 = new JTextField();
			tfPhone3.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone3.setColumns(10);
			tfPhone3.setBounds(207, 249, 38, 30);
		}
		return tfPhone3;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("-");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(131, 255, 15, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(191, 255, 15, 15);
		}
		return lblNewLabel_1;
	}
	private JComboBox getCbYear() {
		if (cbYear == null) {
			cbYear = new JComboBox();
			cbYear.setModel(new DefaultComboBoxModel(new String[] {"1920", "1921", "1922", "1923", "1924", "1925", "1926", "1927", "1928", "1929", "1930", "1931", "1932", "1933", "1934", "1935", "1936", "1937", "1938", "1939", "1940", "1941", "1942", "1943", "1944", "1945", "1946", "1947", "1948", "1949", "1950", "1951", "1952", "1953", "1954", "1955", "1956", "1957", "1958", "1959", "1960", "1961", "1962", "1963", "1964", "1965", "1966", "1967", "1968", "1969", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023"}));
			cbYear.setSelectedIndex(80);
			cbYear.setBounds(91, 289, 52, 30);
		}
		return cbYear;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("년");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(147, 296, 15, 15);
		}
		return lblNewLabel_2;
	}
	private JComboBox getCbMonth() {
		if (cbMonth == null) {
			cbMonth = new JComboBox();
			cbMonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			cbMonth.setSelectedIndex(0);
			cbMonth.setBounds(164, 289, 42, 30);
		}
		return cbMonth;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setBounds(210, 296, 15, 15);
		}
		return lblNewLabel_2_1;
	}
	private JComboBox getCbDay() {
		if (cbDay == null) {
			cbDay = new JComboBox();
			cbDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
			cbDay.setBounds(226, 289, 42, 30);
		}
		return cbDay;
	}
	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("일");
			lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1_1.setBounds(272, 296, 15, 15);
		}
		return lblNewLabel_2_1_1;
	}
	private JComboBox getCbQuestion1() {
		if (cbQuestion1 == null) {
			cbQuestion1 = new JComboBox();
			cbQuestion1.setModel(new DefaultComboBoxModel(new String[] {"베를린에서 물을 마시면 안되는 이유는?", "어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?"}));
			cbQuestion1.setBounds(91, 329, 250, 30);
		}
		return cbQuestion1;
	}
	private JTextField getTfAnswer1() {
		if (tfAnswer1 == null) {
			tfAnswer1 = new JTextField();
			tfAnswer1.setColumns(10);
			tfAnswer1.setBounds(91, 362, 250, 30);
		}
		return tfAnswer1;
	}
	private JComboBox getCbQuestion2() {
		if (cbQuestion2 == null) {
			cbQuestion2 = new JComboBox();
			cbQuestion2.setModel(new DefaultComboBoxModel(new String[] {"가장 좋아하는 음식은?", "가장 싫어하는 음식은?", "기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?"}));
			cbQuestion2.setSelectedIndex(4);
			cbQuestion2.setBounds(91, 395, 250, 30);
		}
		return cbQuestion2;
	}
	private JTextField getTfAnswer2() {
		if (tfAnswer2 == null) {
			tfAnswer2 = new JTextField();
			tfAnswer2.setColumns(10);
			tfAnswer2.setBounds(91, 428, 250, 30);
		}
		return tfAnswer2;
	}
	private JLabel getLblLine() {
		if (lblLine == null) {
			lblLine = new JLabel("");
			lblLine.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/Line1.png")));
			lblLine.setBounds(76, 200, 225, 3);
		}
		return lblLine;
	}
	private JLabel getLblCompare() {
		if (lblCompare == null) {
			lblCompare = new JLabel("");
			
			// 비밀번호 입력란에 DocumentListener 추가
	        DocumentListener passwordListener = new DocumentListener() {
	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                comparePw();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                comparePw();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                comparePw();
	            }
	        };

	        pfPassword1.getDocument().addDocumentListener(passwordListener);
	        pfPassword2.getDocument().addDocumentListener(passwordListener);
			lblCompare.setBounds(259, 175, 82, 23);
		}
		return lblCompare;
	}
	private JLabel getLblLine_1() {
		if (lblLine_1 == null) {
			lblLine_1 = new JLabel("");
			lblLine_1.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1.setBounds(76, 321, 225, 3);
		}
		return lblLine_1;
	}
	private JLabel getLblLine_1_1() {
		if (lblLine_1_1 == null) {
			lblLine_1_1 = new JLabel("");
			lblLine_1_1.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1_1.setBounds(76, 460, 225, 3);
		}
		return lblLine_1_1;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBackground(new Color(233, 233, 233));
			lblImage.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/Profile.png")));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(92, 467, 100, 100);
		}
		return lblImage;
	}
	private JCheckBox getChkAgree() {
		if (chkAgree == null) {
			chkAgree = new JCheckBox("정보 제공에 동의하시겠습니까?");
			chkAgree.setBackground(new Color(255, 255, 255));
			chkAgree.setBounds(91, 566, 210, 23);
		}
		return chkAgree;
	}
	private JLabel getLblFile() {
		if (lblFile == null) {
			lblFile = new JLabel("");
			lblFile.setIcon(new ImageIcon(SignUp.class.getResource("/com/javalec/image/경로.png")));
			lblFile.setBounds(191, 501, 95, 39);
		}
		return lblFile;
	}
	private JLabel getLblCancel() {
		if (lblCancel == null) {
			lblCancel = new JLabel("");
			lblCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					signInScreen();
				}
			});
			lblCancel.setBounds(205, 603, 108, 30);
		}
		return lblCancel;
	}
	
	// --- Function ---
	
	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}
	
	// 비밀번호 입력 받았을때 바로 비교해주기
	private void comparePw() {
	    String pw1 = new String(pfPassword1.getPassword());
	    String pw2 = new String(pfPassword2.getPassword());

	    if (pw1.isEmpty() || pw2.isEmpty()) {
	        lblCompare.setText(""); // 둘 다 비어있을 때는 메시지를 비움
	    } else if (pw1.equals(pw2)) {
	        lblCompare.setText("일치");
	        lblCompare.setForeground(Color.BLUE);
	    } else {
	        lblCompare.setText("불일치");
	        lblCompare.setForeground(Color.RED);
	    }
	}
	
	// Account - SignIn화면
	private void signInScreen() {
		this.setVisible(false);
		SignIn signIn = new SignIn();
		signIn.setVisible(true);
	}
} // End
