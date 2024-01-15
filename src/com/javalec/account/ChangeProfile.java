package com.javalec.account;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.javalec.common.ShareVar;
import com.javalec.dao.AccountDao;
import com.javalec.dao.SignDao;
import com.javalec.dto.AccountDto;
import com.javalec.sign.SignUp;

public class ChangeProfile extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 개인정보 수정
	// Date : 2024.01.08(Ver1.0.0)
	//			   2024.01.15(Ver1.0.1
	// Author : Daegeun Lee
	// History : 1. ID&PW를 받아서 DB에 있는 데이터와 비교한뒤 true, false로 체크한다
	//					2. 정규식으로 예외처리한다
	//					3. 수정일 추가
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
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
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
	private JLabel lblFile;
	private JLabel lblCancel;
	private JLabel lblChange;
	private JCheckBox chkAgree;
	private JTextField tfFilePath;
	private JTextField tfYear;
	private JTextField tfMonth;
	private JTextField tfDay;

	// ShareVar.loginID를 이용하여 로그인한 사용자의 아이디에 접근
	String custid = ShareVar.loginID;
	AccountDao accountdao = new AccountDao(custid);
	AccountDto accountdto = accountdao.showProfile2();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangeProfile frame = new ChangeProfile();
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
	public ChangeProfile() {
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
		contentPane.add(getTfYear());
		contentPane.add(getTfMonth());
		contentPane.add(getTfDay());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_2_1());
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
		contentPane.add(getLblFile());
		contentPane.add(getLblChange());
		contentPane.add(getLblCancel());
		contentPane.add(getChkAgree());
		contentPane.add(getTfFilePath());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}

	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen
					.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/ChangeProfile.png")));
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
			tfId.setText(custid);
			tfId.setEditable(false);
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
			tfName.setText(accountdto.getCustname());
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
			// 전화번호를 나누어서 텍스트필드에 설정
		    String[] phoneParts = splitPhoneNumber(accountdto.getPhone());
		    if (phoneParts.length == 3) {
		        tfPhone2.setText(phoneParts[1]); 
		    }
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
			tfPhone2.setBounds(149, 249, 38, 30);
		}
		return tfPhone2;
	}

	private JTextField getTfPhone3() {
		if (tfPhone3 == null) {
			tfPhone3 = new JTextField();
			// 전화번호를 나누어서 텍스트필드에 설정
		    String[] phoneParts = splitPhoneNumber(accountdto.getPhone());
		    if (phoneParts.length == 3) {
		        tfPhone3.setText(phoneParts[2]); 
		    }
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

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("년");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(147, 296, 15, 15);
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setBounds(220, 296, 15, 15);
		}
		return lblNewLabel_2_1;
	}

	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("일");
			lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1_1.setBounds(290, 296, 15, 15);
		}
		return lblNewLabel_2_1_1;
	}

	private JComboBox getCbQuestion1() {
		if (cbQuestion1 == null) {
			cbQuestion1 = new JComboBox();
			cbQuestion1.setModel(new DefaultComboBoxModel(new String[] { "선택하여 주세요.", "베를린에서 물을 마시면 안되는 이유는?",
					"어머님의 성함은?", "아버님의 성함은?", "태어나신 고향은 어디입니까?", "가장 친한 친구의 이름은?" }));
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
			cbQuestion2.setModel(new DefaultComboBoxModel(new String[] { "선택하여 주세요.", "가장 좋아하는 음식은?", "가장 싫어하는 음식은?",
					"기억에 남는 선물은?", "어릴적 꿈은?", "존경하는 사람의 이름은?" }));
			cbQuestion2.setSelectedIndex(0);
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
			lblLine.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/Line1.png")));
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
			lblLine_1.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1.setBounds(76, 321, 225, 3);
		}
		return lblLine_1;
	}

	private JLabel getLblLine_1_1() {
		if (lblLine_1_1 == null) {
			lblLine_1_1 = new JLabel("");
			lblLine_1_1.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1_1.setBounds(76, 460, 225, 3);
		}
		return lblLine_1_1;
	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBackground(new Color(233, 233, 233));
			lblImage.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/Profile.png")));
			showImage();
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(87, 465, 108, 108);
		}
		return lblImage;
	}

	private JLabel getLblFile() {
		if (lblFile == null) {
			lblFile = new JLabel("");
			lblFile.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					filePath();
				}
			});
			lblFile.setIcon(new ImageIcon(ChangeProfile.class.getResource("/com/javalec/image/경로.png")));
			lblFile.setBounds(194, 501, 95, 39);
		}
		return lblFile;
	}

	private JLabel getLblCancel() {
		if (lblCancel == null) {
			lblCancel = new JLabel("");
			lblCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					myProfile();
				}
			});
			lblCancel.setBounds(205, 603, 108, 30);
		}
		return lblCancel;
	}
	private JLabel getLblChange() {
		if (lblChange == null) {
			lblChange = new JLabel("");
			lblChange.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changeAction();
				}
			});
			lblChange.setBounds(60, 604, 111, 30);
		}
		return lblChange;
	}
	private JCheckBox getChkAgree() {
		if (chkAgree == null) {
			chkAgree = new JCheckBox("정보 수정에 동의하시겠습니까?");
			chkAgree.setBackground(Color.WHITE);
			chkAgree.setBounds(91, 570, 210, 23);
		}
		return chkAgree;
	}
	private JTextField getTfFilePath() {
		if (tfFilePath == null) {
			tfFilePath = new JTextField();
			// 클릭하면 데이트를 가져와서 처리하니깐 여기에 Image를 받는다
			// Image 처리 : filename이 틀려야 보여주기가 가능
			tfFilePath.setVisible(false);
//			String filePath = Integer.toString(ShareVar.filename);
//			tfFilePath.setText(filePath);
//			lblImage.setIcon(new ImageIcon(filePath));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			tfFilePath.setEditable(false);
			tfFilePath.setColumns(10);
			tfFilePath.setBounds(193, 471, 148, 25);
		}
		return tfFilePath;
	}
	private JTextField getTfYear() {
		if (tfYear == null) {
			tfYear = new JTextField();
			// 생년월일을 나누어서 텍스트필드에 설정
		    String[] birthdayParts = splitBirthday(accountdto.getBirthday());
		    if (birthdayParts.length == 3) {
		        tfYear.setText(birthdayParts[0]); 
		    }
			tfYear.setEditable(false);
			tfYear.setBounds(91, 290, 51, 30);
			tfYear.setColumns(10);
		}
		return tfYear;
	}
	private JTextField getTfMonth() {
		if (tfMonth == null) {
			tfMonth = new JTextField();
			// 생년월일을 나누어서 텍스트필드에 설정
		    String[] birthdayParts = splitBirthday(accountdto.getBirthday());
		    if (birthdayParts.length == 3) {
		        tfMonth.setText(birthdayParts[1]); 
		    }
			tfMonth.setEditable(false);
			tfMonth.setColumns(10);
			tfMonth.setBounds(170, 290, 42, 30);
		}
		return tfMonth;
	}
	private JTextField getTfDay() {
		if (tfDay == null) {
			tfDay = new JTextField();
			// 생년월일을 나누어서 텍스트필드에 설정
		    String[] birthdayParts = splitBirthday(accountdto.getBirthday());
		    if (birthdayParts.length == 3) {
		        tfDay.setText(birthdayParts[2]); 
		    }
			tfDay.setEditable(false);
			tfDay.setColumns(10);
			tfDay.setBounds(240, 290, 42, 30);
		}
		return tfDay;
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

	// MyProfile 화면
	private void myProfile() {
		this.setVisible(false);
		MyProfile myProfile = new MyProfile();
		myProfile.setVisible(true);
	}

	// 특수 문자 확인을 위한 조건 추가
	private boolean specialCharacter(char specialKey) {
		return "!@#$%^&*()-_=+`~/?,.<>{}[];:|\"'\\".indexOf(specialKey) != -1;
	}
	
	private void changeAction() {
		// 입력 안했을시 체크 받기
		int check = inputCheck();
		
		char[] charcustpw = pfPassword1.getPassword();
		String custpw = new String(charcustpw);
		String custname = tfName.getText().trim();
		String phone1 = tfPhone1.getText().trim();
		String phone2 = tfPhone2.getText().trim();
		String phone3 = tfPhone3.getText().trim();
		String phone = phone1 + "-" + phone2 + "-" + phone3;
		String question1 = (String) cbQuestion1.getSelectedItem();
		String answer1 = tfAnswer1.getText().trim();
		String question2 = (String) cbQuestion2.getSelectedItem();
		String answer2 = tfAnswer2.getText().trim();
		String imagePath = tfFilePath.getText().trim();
		// Image File
		FileInputStream input = null;
		File file;

		if (imagePath.isEmpty() ) {
	        // 이미지를 선택하지 않은 경우 디폴트 이미지 경로로 설정
//			URL defaultImagePath = SignUp.class.getResource("/com/javalec/image/Profile.png");
			String filePath = Integer.toString(ShareVar.filename);
//			tfFilePath.setText(filePath);
//		    try {
//		        URI uri = defaultImagePath.toURI();
		        file = new File(filePath);
		        try {
		        	input = new FileInputStream(file);
		        } catch (FileNotFoundException e) {
		            // 예외 처리
		        }
//		    } catch (URISyntaxException e) {
//		        e.printStackTrace();  // 예외 처리
//		    }
			
	    } else {
//	         이미지를 선택한 경우 입력 받은 경로 사용
	        file = new File(imagePath);
	        
	        try {
	        	
	            input = new FileInputStream(file);
	        } catch (FileNotFoundException e) {
	            // 예외 처리
	        }
	    }
		
		if (check != 0) {
			JOptionPane.showMessageDialog(null, "항목을 입력 하세요.");
		} else {
			AccountDao accountDao = new AccountDao(custpw, custname, phone, question1, answer1, question2, answer2, input);
			boolean result = accountDao.updateAction(); // 회원가입 성공 여부 확인

			if (result) {
			// 회원정보 변경 성공
				JOptionPane.showMessageDialog(null, custname + "님의 회원정보가 변경되었습니다!");
			// 로그인 화면으로 전환
				this.setVisible(false);
				MyProfile myProfile = new MyProfile();
				myProfile.setVisible(true);
			} else {
			// 정보변경 실패 시
			JOptionPane.showMessageDialog(null, "정보변경에 실패하였습니다! 다시 작성하여 주세요");
			// 다시 맨 처음 화면
			this.setVisible(false);
			setVisible(true);
			}
		}
	}

	// 작성 안했을시 처리
	private int inputCheck() {
		int checkResult = 0;
		char[] passwordChars1 = pfPassword1.getPassword();
		char[] passwordChars2 = pfPassword2.getPassword();

		if (passwordChars1.length == 0) { // PW1에 입력 안했을 때
			checkResult++;
			pfPassword1.requestFocus();
		}
		if (passwordChars2.length == 0) { // PW2에 입력 안했을 때
			checkResult++;
			pfPassword2.requestFocus();
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
		if (cbQuestion1.getSelectedItem().equals("선택하여 주세요.")) {
			checkResult++;
			cbQuestion1.requestFocus();
		}
		if (tfAnswer1.getText().trim().length() == 0) {
			checkResult++;
			tfAnswer1.requestFocus();
		}
		if (cbQuestion2.getSelectedItem().equals("선택하여 주세요.")) {
			checkResult++;
			cbQuestion2.requestFocus();
		}
		if (tfAnswer2.getText().trim().length() == 0) {
			checkResult++;
			tfAnswer2.requestFocus();
		}
		if (!chkAgree.isSelected()) {
			checkResult++;
			chkAgree.requestFocus();
		}
		return checkResult;
	}

	// -----------------[[[ File ]]]]]]---------------------------------------------------

	private void filePath() {
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG", "PNG", "BMP", "GIF", "jpg", "png", "bmp", "gif");
		chooser.setFileFilter(filter);

		int ret = chooser.showOpenDialog(null);
		if (ret != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다.");
			tfFilePath.setVisible(false);
			return; 
		}
		String filePath = chooser.getSelectedFile().getPath();
		tfFilePath.setText(filePath);
		ImageIcon icon = new ImageIcon(filePath);
		Image img = icon.getImage();
		Image changeImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // 사이즈를 100, 100으로 조정해준다
		ImageIcon icon1 = new ImageIcon(changeImg);
		tfFilePath.setVisible(true);
		lblImage.setIcon(icon1);

	}// filePath
	
	// 로그인한 고객 이미지 가져오기
	private void showImage() {
		// 데이터를 가져와서 처리하니깐 여기에 Image를 받는다
		int filenameValue = (Integer.toString(ShareVar.filename) != null) ? ShareVar.filename : 0;
        String filePath = Integer.toString(filenameValue);
        lblImage.setIcon(new ImageIcon(filePath));
	}
	
	// 생년월일을 나누는 메서드
		private String[] splitBirthday(String birthdayNumber) {
		    String[] birthdayParts = new String[3];
		    
		    // 전화번호에서 "-"를 기준으로 분리
		    String[] parts = birthdayNumber.split("-");
		    
		    // parts 배열의 길이가 3일 때만 처리
		    if (parts.length == 3) {
		    	birthdayParts[0] = parts[0]; 
		    	birthdayParts[1] = parts[1]; 
		    	birthdayParts[2] = parts[2]; 
		    }
		    
		    return birthdayParts;
		}
	
		// 전화번호를 나누는 메서드
		private String[] splitPhoneNumber(String phoneNumber) {
		    String[] phoneParts = new String[3];
		    
		    // 전화번호에서 "-"를 기준으로 분리
		    String[] parts = phoneNumber.split("-");
		    
		    // parts 배열의 길이가 3일 때만 처리
		    if (parts.length == 3) {
		        phoneParts[0] = parts[0]; // "010"
		        phoneParts[1] = parts[1]; // "????"
		        phoneParts[2] = parts[2]; // "????"
		    }
		    
		    return phoneParts;
		}
} // End
