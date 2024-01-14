package com.javalec.sign;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.javalec.base.AfterMain;
import com.javalec.base.Main;
import com.javalec.common.ShareVar;
import com.javalec.dao.SignDao;

public class SignIn extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 로그인
	// Date : 2024.01.08(Ver1.0.0)
	// Author : Daegeun Lee
	// History : 1. ID&PW를 받아서 DB에 있는 데이터와 비교한뒤 true, false로 체크한다
	// 2. 정규식으로 예외처리한다
	// --------------------------------------------------------------//

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblPwEye;
	private JLabel lblFindID;
	private JLabel lblFindPassword;
	private JLabel lblSignIn;
	private JTextField tfId;
	private JLabel lblSignUp;
	private JPasswordField pfPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignIn frame = new SignIn();
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
	public SignIn() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true); // 타이틀 바 없애기
		contentPane.add(getLblTimer());
		Timer timer = new Timer(100, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateTime(); // 분마다 시간 업데이트
			}
		});
		timer.start();
		contentPane.add(getLblPwEye());
		contentPane.add(getLblFindID());
		contentPane.add(getLblFindPassword());
		contentPane.add(getLblSignIn());
		contentPane.add(getLblSignUp());
		contentPane.add(getTfId());
		contentPane.add(getPfPassword());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setIcon(new ImageIcon(SignIn.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}

	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setIcon(new ImageIcon(SignIn.class.getResource("/com/javalec/image/SignIn.png")));
			lblHomeScreen.setBounds(8, 10, 358, 665);
		}
		return lblHomeScreen;
	}

	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("");
			lblTimer.setForeground(new Color(255, 255, 255));
			lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
			lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
			lblTimer.setBounds(36, 32, 62, 21);
		}
		return lblTimer;
	}

	private JLabel getLblPwEye() {
		if (lblPwEye == null) {
			lblPwEye = new JLabel("");
			lblPwEye.setIcon(new ImageIcon(SignIn.class.getResource("/com/javalec/image/PwEye01.png")));
			lblPwEye.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					checkPassword();
				}
			});
			lblPwEye.setBounds(305, 399, 30, 30);
		}
		return lblPwEye;
	}

	private JLabel getLblFindID() {
		if (lblFindID == null) {
			lblFindID = new JLabel("Forgot ID?");
			lblFindID.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFindID.setForeground(new Color(255, 0, 0));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblFindID.setForeground(new Color(0, 0, 0));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					forgotId();
				}
			});
			lblFindID.setBounds(240, 348, 62, 21);
		}
		return lblFindID;
	}

	private JLabel getLblFindPassword() {
		if (lblFindPassword == null) {
			lblFindPassword = new JLabel("Forgot Password?");
			lblFindPassword.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFindPassword.setForeground(new Color(255, 0, 0));
				}

				@Override
				public void mouseExited(MouseEvent e) {
					lblFindPassword.setForeground(new Color(0, 0, 0));
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					forgotPw();
				}
			});
			lblFindPassword.setBounds(194, 434, 109, 21);
		}
		return lblFindPassword;
	}

	private JLabel getLblSignIn() {
		if (lblSignIn == null) {
			lblSignIn = new JLabel("");
			lblSignIn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					loginAction();
				}
			});
			lblSignIn.setBounds(72, 482, 228, 39);
		}
		return lblSignIn;
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
			tfId.setBounds(72, 315, 228, 30);
			tfId.setColumns(10);
		}
		return tfId;
	}

	private JLabel getLblSignUp() {
		if (lblSignUp == null) {
			lblSignUp = new JLabel("");
			lblSignUp.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					signUp();
				}
			});
			lblSignUp.setBounds(72, 542, 228, 39);
		}
		return lblSignUp;
	}

	private JPasswordField getPfPassword() {
		if (pfPassword == null) {
			pfPassword = new JPasswordField();
			pfPassword.setBounds(72, 399, 228, 30);
		}
		return pfPassword;
	}

	// --- Function ---
	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}

	// Home화면
	private void homeScreen() {
		this.setVisible(false); // 현재화면 끄고
		Main window = new Main();
		window.main(null); // 홈 화면 키기
	}

	// 회원가입
	private void signUp() {
		this.setVisible(false); // 현재화면 끄고
		SignUp signUp = new SignUp();
		signUp.setVisible(true); // 홈 화면 키기
	}

	// 비밀번호 표시
	private boolean passwordVisible = false; // 비밀번호 보이기 여부를 나타내는 변수

	private void checkPassword() {
		if (passwordVisible) {
			pfPassword.setEchoChar('\u2022'); // 비밀번호 숨김
			lblPwEye.setIcon(new ImageIcon(SignIn.class.getResource("/com/javalec/image/PwEye01.png")));
		} else {
			pfPassword.setEchoChar((char) 0); // 비밀번호 표시
			lblPwEye.setIcon(new ImageIcon(SignIn.class.getResource("/com/javalec/image/PwEye02.png")));
		}
		passwordVisible = !passwordVisible; // 상태를 반전시킴 (토글)
	}

	// 아이디 찾기
	private void forgotId() {
		this.setVisible(false); // 현재화면 끄고
		ForgotID forgotId = new ForgotID();
		forgotId.setVisible(true); // 홈 화면 키기
	}

	// 비밀번호 찾기
	private void forgotPw() {
		this.setVisible(false); // 현재화면 끄고
		ForgotPW forgotPw = new ForgotPW();
		forgotPw.setVisible(true); // 홈 화면 키기
	}

	// 특수 문자 확인을 위한 조건 추가
	private boolean specialCharacter(char specialKey) {
		return "!@#$%^&*()-_=+`~/?,.<>{}[];:|\"'\\".indexOf(specialKey) != -1;
	}

	private void loginAction() {
		// 입력 안했을시 어디를 안했는지 체크 받기
		int check = inputCheck();

		String id = tfId.getText();
		char[] charcustpw = pfPassword.getPassword();
		String pw = new String(charcustpw);

		// 입력을 안했을시 공지 또는 빈칸
		if (check != 0 || (id.equals("") && (pw.equals("")))) {
			JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
			tfId.setText("");
			pfPassword.setText("");
			tfId.requestFocus();
		} else {
			SignDao signDao = new SignDao();
			boolean result = signDao.loginCheckAction(id, pw); // 로그인 성공 여부 확인

			if (result) {
				// 로그인 성공시
				ShareVar.loginID = id;
				this.setVisible(false);
				AfterMain afterMain = new AfterMain();
				afterMain.main(null);
			} else {
				// 로그인 실패 시
				JOptionPane.showMessageDialog(null, "정보가 잘못 입력되었습니다. 다시 입력하여 주세요.");
				tfId.setText("");
				pfPassword.setText("");
				tfId.requestFocus();
			}
		}
	}

	// ID & 비밀번호 입력 안했을 때 체크
	private int inputCheck() {
		int checkResult = 0;
		char[] passwordChars = pfPassword.getPassword();

		if (tfId.getText().trim().length() == 0) { // ID에 입력 안했을 때
			checkResult++;
			tfId.requestFocus();
		}
		if (passwordChars.length == 0) { // PW에 입력 안했을 때
			checkResult++;
			pfPassword.requestFocus();
		}

		return checkResult;
	}
} // End
