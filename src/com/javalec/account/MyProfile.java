package com.javalec.account;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.javalec.base.Main;
import com.javalec.common.ShareVar;
import com.javalec.dao.AccountDao;
import com.javalec.dto.AccountDto;

public class MyProfile extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 나의 정보
	// Date : 2024.01.11(Ver1.0.0)
	//			   2024.01.14(Ver.1.0.1)
	// Author : Daegeun Lee
	// History : 1. 나의 프로필 화면을 보여준다
	//					2. 탈퇴처리(Update - sysdate())
	// --------------------------------------------------------------//
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblId;
	private JLabel lblName;
	private JLabel lblPhone;
	private JLabel lblBirthday;
	private JLabel lblQuestion1;
	private JLabel lblAnswer1;
	private JLabel lblQuestion2;
	private JLabel lblAnswer2;
	private JLabel lblProfile;
	private JTextField tfId;
	private JTextField tfName;
	private JTextField tfPhone1;
	private JTextField tfPhone2;
	private JTextField tfPhone3;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_1_1;
	private JTextField tfAnswer1;
	private JTextField tfAnswer2;
	private JLabel lblLine;
	private JLabel lblLine_1;
	private JLabel lblLine_1_1;
	private JLabel lblImage;
	private JLabel lblCheck;
	private JTextField tfQuestion1;
	private JTextField tfQuestion2;
	private JTextField tfYear;
	private JTextField tfMonth;
	private JTextField tfDay;
	private JLabel lblChangeMyProfile;
	private JLabel lblDeactive;

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
					MyProfile frame = new MyProfile();
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
	public MyProfile() {
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
		contentPane.add(getLblName());
		contentPane.add(getLblPhone());
		contentPane.add(getLblBirthday());
		contentPane.add(getLblQuestion1());
		contentPane.add(getLblAnswer1());
		contentPane.add(getLblQuestion2());
		contentPane.add(getLblAnswer2());
		contentPane.add(getLblProfile());
		contentPane.add(getTfId());
		contentPane.add(getTfName());
		contentPane.add(getTfPhone1());
		contentPane.add(getTfPhone2());
		contentPane.add(getTfPhone3());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getLblNewLabel_2_1_1());
		contentPane.add(getTfAnswer1());
		contentPane.add(getTfAnswer2());
		contentPane.add(getLblLine());
		contentPane.add(getLblLine_1());
		contentPane.add(getLblLine_1_1());
		contentPane.add(getLblImage());
		contentPane.add(getLblCheck());
		contentPane.add(getTfQuestion1());
		contentPane.add(getTfQuestion2());
		contentPane.add(getTfYear());
		contentPane.add(getTfMonth());
		contentPane.add(getTfDay());
		contentPane.add(getLblChangeMyProfile());
		contentPane.add(getLblDeactive());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/MyProfile.png")));
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
			lblId.setBounds(20, 205, 57, 15);
		}
		return lblId;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("성명   :");
			lblName.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setBounds(20, 245, 57, 15);
		}
		return lblName;
	}
	private JLabel getLblPhone() {
		if (lblPhone == null) {
			lblPhone = new JLabel("전화번호 :");
			lblPhone.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblPhone.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPhone.setBounds(20, 285, 57, 15);
		}
		return lblPhone;
	}
	private JLabel getLblBirthday() {
		if (lblBirthday == null) {
			lblBirthday = new JLabel("생년월일 :");
			lblBirthday.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblBirthday.setHorizontalAlignment(SwingConstants.TRAILING);
			lblBirthday.setBounds(20, 326, 57, 15);
		}
		return lblBirthday;
	}
	private JLabel getLblQuestion1() {
		if (lblQuestion1 == null) {
			lblQuestion1 = new JLabel("질문1  :");
			lblQuestion1.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblQuestion1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion1.setBounds(20, 366, 57, 15);
		}
		return lblQuestion1;
	}
	private JLabel getLblAnswer1() {
		if (lblAnswer1 == null) {
			lblAnswer1 = new JLabel("답변  :");
			lblAnswer1.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblAnswer1.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer1.setBounds(20, 399, 57, 15);
		}
		return lblAnswer1;
	}
	private JLabel getLblQuestion2() {
		if (lblQuestion2 == null) {
			lblQuestion2 = new JLabel("질문2  :");
			lblQuestion2.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblQuestion2.setHorizontalAlignment(SwingConstants.TRAILING);
			lblQuestion2.setBounds(20, 432, 57, 15);
		}
		return lblQuestion2;
	}
	private JLabel getLblAnswer2() {
		if (lblAnswer2 == null) {
			lblAnswer2 = new JLabel("답변  :");
			lblAnswer2.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblAnswer2.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAnswer2.setBounds(20, 465, 57, 15);
		}
		return lblAnswer2;
	}
	private JLabel getLblProfile() {
		if (lblProfile == null) {
			lblProfile = new JLabel("프로필  :");
			lblProfile.setFont(new Font("CookieRun Regular", Font.PLAIN, 13));
			lblProfile.setHorizontalAlignment(SwingConstants.TRAILING);
			lblProfile.setBounds(20, 503, 57, 15);
		}
		return lblProfile;
	}
	private JTextField getTfId() {
		if (tfId == null) {
			tfId = new JTextField();
			tfId.setText(accountdto.getCustid());
			tfId.setEditable(false);
			tfId.setBounds(91, 199, 96, 30);
			tfId.setColumns(10);
		}
		return tfId;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setText(accountdto.getCustname());
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(91, 239, 154, 30);
		}
		return tfName;
	}
	private JTextField getTfPhone1() {
		if (tfPhone1 == null) {
			tfPhone1 = new JTextField();
			tfPhone1.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone1.setEditable(false);
			tfPhone1.setText("010");
			tfPhone1.setBounds(91, 279, 38, 30);
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
			tfPhone2.setEditable(false);
			tfPhone2.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone2.setColumns(10);
			tfPhone2.setBounds(149, 279, 38, 30);
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
			tfPhone3.setEditable(false);
			tfPhone3.setHorizontalAlignment(SwingConstants.CENTER);
			tfPhone3.setColumns(10);
			tfPhone3.setBounds(207, 279, 38, 30);
		}
		return tfPhone3;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("-");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(131, 285, 15, 15);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("-");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_1.setBounds(191, 285, 15, 15);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("년");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setBounds(147, 326, 15, 15);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("월");
			lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1.setBounds(210, 326, 15, 15);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("일");
			lblNewLabel_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2_1_1.setBounds(272, 326, 15, 15);
		}
		return lblNewLabel_2_1_1;
	}
	private JTextField getTfAnswer1() {
		if (tfAnswer1 == null) {
			tfAnswer1 = new JTextField();
			tfAnswer1.setText(accountdto.getAnswer1());
			tfAnswer1.setEditable(false);
			tfAnswer1.setColumns(10);
			tfAnswer1.setBounds(91, 392, 250, 30);
		}
		return tfAnswer1;
	}
	private JTextField getTfAnswer2() {
		if (tfAnswer2 == null) {
			tfAnswer2 = new JTextField();
			tfAnswer2.setText(accountdto.getAnswer2());
			tfAnswer2.setEditable(false);
			tfAnswer2.setColumns(10);
			tfAnswer2.setBounds(91, 458, 250, 30);
		}
		return tfAnswer2;
	}
	private JLabel getLblLine() {
		if (lblLine == null) {
			lblLine = new JLabel("");
			lblLine.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/Line1.png")));
			lblLine.setBounds(76, 230, 225, 3);
		}
		return lblLine;
	}
	private JLabel getLblLine_1() {
		if (lblLine_1 == null) {
			lblLine_1 = new JLabel("");
			lblLine_1.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1.setBounds(76, 351, 225, 3);
		}
		return lblLine_1;
	}
	private JLabel getLblLine_1_1() {
		if (lblLine_1_1 == null) {
			lblLine_1_1 = new JLabel("");
			lblLine_1_1.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/Line1.png")));
			lblLine_1_1.setBounds(76, 490, 225, 3);
		}
		return lblLine_1_1;
	}
	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			showMyProfile();
			lblImage.setBackground(new Color(233, 233, 233));
			lblImage.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/Profile.png")));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBounds(87, 495, 108, 108);
		}
		return lblImage;
	}
	private JLabel getLblCheck() {
		if (lblCheck == null) {
			lblCheck = new JLabel("");
			lblCheck.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					checkAction();
				}
			});
			lblCheck.setBounds(205, 603, 108, 30);
		}
		return lblCheck;
	}
	private JTextField getTfQuestion1() {
		if (tfQuestion1 == null) {
			tfQuestion1 = new JTextField();
			tfQuestion1.setText(accountdto.getQuestion1());
			tfQuestion1.setEditable(false);
			tfQuestion1.setColumns(10);
			tfQuestion1.setBounds(91, 359, 250, 30);
		}
		return tfQuestion1;
	}
	private JTextField getTfQuestion2() {
		if (tfQuestion2 == null) {
			tfQuestion2 = new JTextField();
			tfQuestion2.setText(accountdto.getQuestion2());
			tfQuestion2.setEditable(false);
			tfQuestion2.setColumns(10);
			tfQuestion2.setBounds(91, 425, 250, 30);
		}
		return tfQuestion2;
	}
	private JTextField getTfYear() {
		if (tfYear == null) {
			tfYear = new JTextField();
			// 생년월일을 나누어서 텍스트필드에 설정
		    String[] birthdayParts = splitBirthday(accountdto.getBirthday());
		    if (birthdayParts.length == 3) {
		        tfYear.setText(birthdayParts[0]); 
		    }
			tfYear.setHorizontalAlignment(SwingConstants.CENTER);
			tfYear.setEditable(false);
			showMyProfile();
			tfYear.setColumns(10);
			tfYear.setBounds(91, 319, 51, 30);
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
			tfMonth.setHorizontalAlignment(SwingConstants.CENTER);
			tfMonth.setEditable(false);
			showMyProfile();
			tfMonth.setColumns(10);
			tfMonth.setBounds(164, 319, 42, 30);
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
			tfDay.setHorizontalAlignment(SwingConstants.CENTER);
			tfDay.setEditable(false);
			showMyProfile();
			tfDay.setColumns(10);
			tfDay.setBounds(226, 319, 42, 30);
		}
		return tfDay;
	}
	private JLabel getLblChangeMyProfile() {
		if (lblChangeMyProfile == null) {
			lblChangeMyProfile = new JLabel("");
			lblChangeMyProfile.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					changeProfile();
				}
			});
			lblChangeMyProfile.setBounds(62, 603, 108, 30);
		}
		return lblChangeMyProfile;
	}
	private JLabel getLblDeactive() {
		if (lblDeactive == null) {
			lblDeactive = new JLabel("");
			lblDeactive.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					deactive();
				}
			});
			lblDeactive.setIcon(new ImageIcon(MyProfile.class.getResource("/com/javalec/image/deactive.png")));
			lblDeactive.setFont(new Font("굴림", Font.PLAIN, 12));
			lblDeactive.setBounds(270, 530, 70, 30);
		}
		return lblDeactive;
	}
	// --- Function ---
	
	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}
	
	// Account 화면으로
	private void checkAction() {
		this.setVisible(false);
		Account account = new Account();
		account.setVisible(true);
	}

	// ChangeProfile
	private void changeProfile() {
		this.setVisible(false);
		ChangeProfile changeProfile = new ChangeProfile();
		changeProfile.setVisible(true);
	}

	// 고객 정보 보기
	private void showMyProfile() {
	int filenameValue = (Integer.toString(ShareVar.filename) != null) ? ShareVar.filename : 0;
    String filePath = Integer.toString(filenameValue);
    lblImage.setIcon(new ImageIcon(filePath));
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
	
	// 탈퇴하기
	private void deactive() {
		 int option = JOptionPane.showConfirmDialog(this, "정말 탈퇴하시겠습니까?", "탈퇴 확인", JOptionPane.YES_NO_OPTION);

		    if (option == JOptionPane.YES_OPTION) {
		        // 사용자가 "예"를 선택한 경우
		        AccountDao accountDao = new AccountDao(custid);
		        boolean result = accountDao.deactiveAction();
		        
		        if (result) {
					// 탈퇴 성공시
		        	JOptionPane.showMessageDialog(this, "그동안 감사합니다. 탈퇴가 완료 되었습니다.", "탈퇴 완료", JOptionPane.INFORMATION_MESSAGE);
		        	// 메인 화면으로 이동
		        	this.setVisible(false); // 현재화면 끄고
		        	Main main = new Main();
		        	main.main(null); // 홈 화면 키기
					} else {
					// 탈퇴 실패 시
					JOptionPane.showMessageDialog(null, "헹~ 속았지~!! 탈퇴 안되지롱!!");
					}
		    } else {
		        // 사용자가 "아니오"를 선택한 경우
		        JOptionPane.showMessageDialog(this, "그래요 가지말아요. 우리랑 함께해요.", "취소", JOptionPane.INFORMATION_MESSAGE);
		    }
	}
	
} // End
