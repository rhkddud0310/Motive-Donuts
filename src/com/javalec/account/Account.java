package com.javalec.account;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.javalec.base.Main;
import com.javalec.cart.Cart;
import com.javalec.menu.Menu;

public class Account extends JFrame {
	// --------------------------------------------------------------//
	// Desc : MyPage - 프로필 사진과 나의 정보, 보유 포인트, 구매내역 보기
	// Date : 2024.01.11(Ver1.0)
	// Author : Daegeun Lee
	// History : 1. 
	// --------------------------------------------------------------//

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblHome;
	private JLabel lblMenu;
	private JLabel lblCart;
	private JLabel lblAccount;
	private JLabel lblAccount1;
	private JLabel lblCart1;
	private JLabel lblMenu1;
	private JLabel lblHome1;
	private JLabel lblImage;
	private JLabel lblLogout;
	private JLabel lblFirst;
	private JLabel lblSecond;
	private JLabel lblThird;
	private JLabel lblMyprofile;
	private JLabel lblMyPoint;
	private JLabel lblMyOrder;
	private JLabel lblPoints;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account frame = new Account();
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
	public Account() {
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
		contentPane.add(getLblHome());
		contentPane.add(getLblHome1());
		contentPane.add(getLblMenu());
		contentPane.add(getLblMenu1());
		contentPane.add(getLblCart());
		contentPane.add(getLblCart1());
		contentPane.add(getLblAccount());
		contentPane.add(getLblAccount1());
		contentPane.add(getLblImage());
		contentPane.add(getLblLogout());
		contentPane.add(getLblFirst());
		contentPane.add(getLblSecond());
		contentPane.add(getLblThird());
		contentPane.add(getLblMyprofile());
		contentPane.add(getLblMyPoint());
		contentPane.add(getLblMyOrder());
		contentPane.add(getLblPoints());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}

	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/Mypage.png")));
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

	private JLabel getLblHome() {
		if (lblHome == null) {
			lblHome = new JLabel("");
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					homeScreen();
				}
			});
			lblHome.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Home button.png")));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome.setBounds(40, 600, 50, 50);
		}
		return lblHome;
	}

	private JLabel getLblMenu() {
		if (lblMenu == null) {
			lblMenu = new JLabel("");
			lblMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					menuScreen();
				}
			});
			lblMenu.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Menu button.png")));
			lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenu.setBounds(120, 600, 50, 50);
		}
		return lblMenu;
	}

	private JLabel getLblCart() {
		if (lblCart == null) {
			lblCart = new JLabel("");
			lblCart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cartScreen();
				}
			});
			lblCart.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Cart button.png")));
			lblCart.setHorizontalAlignment(SwingConstants.CENTER);
			lblCart.setBounds(200, 600, 50, 50);
		}
		return lblCart;
	}

	private JLabel getLblAccount() {
		if (lblAccount == null) {
			lblAccount = new JLabel("");
			lblAccount.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					accountScreen();
				}
			});
			lblAccount.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Account button.png")));
			lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccount.setBounds(280, 600, 50, 50);
		}
		return lblAccount;
	}

	private JLabel getLblHome1() {
		if (lblHome1 == null) {
			lblHome1 = new JLabel("Home");
			lblHome1.setFont(new Font("굴림", Font.BOLD, 12));
			lblHome1.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome1.setBounds(44, 645, 42, 15);
		}
		return lblHome1;
	}

	private JLabel getLblMenu1() {
		if (lblMenu1 == null) {
			lblMenu1 = new JLabel("Menu");
			lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenu1.setFont(new Font("굴림", Font.BOLD, 12));
			lblMenu1.setBounds(124, 645, 42, 15);
		}
		return lblMenu1;
	}

	private JLabel getLblCart1() {
		if (lblCart1 == null) {
			lblCart1 = new JLabel("Cart");
			lblCart1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCart1.setFont(new Font("굴림", Font.BOLD, 12));
			lblCart1.setBounds(204, 645, 42, 15);
		}
		return lblCart1;
	}

	private JLabel getLblAccount1() {
		if (lblAccount1 == null) {
			lblAccount1 = new JLabel("Account");
			lblAccount1.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccount1.setFont(new Font("굴림", Font.BOLD, 12));
			lblAccount1.setBounds(276, 645, 62, 15);
		}
		return lblAccount1;
	}

	private JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/Profile.png")));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblImage.setBackground(new Color(233, 233, 233));
			lblImage.setBounds(137, 120, 108, 108);
		}
		return lblImage;
	}

	private JLabel getLblLogout() {
		if (lblLogout == null) {
//			lblLogout = new JLabel("");
			// 원래 이미지의 파일을 icon에 담는다
			ImageIcon icon = new ImageIcon(Account.class.getResource("/com/javalec/image/logout1.png"));
			// img에 이미지를 담는다.
			Image img = icon.getImage();
			// 그 이미지를 사이즈 조절해서 담아준다
			Image changeImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			// 변경된 이미지를 다시 ImageIcon에 담아준다
			ImageIcon changeIcon = new ImageIcon(changeImg);
			lblLogout = new JLabel(changeIcon); // 변경된 이미지로 JLabel 생성

			// 바뀌는 이미지 담아주기
			ImageIcon icon2 = new ImageIcon(Account.class.getResource("/com/javalec/image/logout2.png"));
			Image img2 = icon2.getImage();
			Image changeImg2 = img2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			ImageIcon changeIcon2 = new ImageIcon(changeImg2);

//			lblLogout.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/logout1.png")));
			lblLogout.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					homeScreen();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblLogout.setIcon(changeIcon2);
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblLogout.setIcon(changeIcon);
				}
			});
			lblLogout.setBounds(280, 142, 50, 50);
		}
		return lblLogout;
	}
	private JLabel getLblFirst() {
		if (lblFirst == null) {
			lblFirst = new JLabel("");
			lblFirst.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblFirst.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/FirstDrink.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblFirst.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/FirstDonut.png")));
				}
			});
			lblFirst.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/FirstDonut.png")));
			lblFirst.setBounds(36, 255, 80, 80);
		}
		return lblFirst;
	}
	private JLabel getLblSecond() {
		if (lblSecond == null) {
			lblSecond = new JLabel("");
			lblSecond.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblSecond.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/SecondDrink.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblSecond.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/SecondDonut.png")));
				}
			});
			lblSecond.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/SecondDonut.png")));
			lblSecond.setBounds(36, 370, 80, 80);
		}
		return lblSecond;
	}
	private JLabel getLblThird() {
		if (lblThird == null) {
			lblThird = new JLabel("");
			lblThird.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblThird.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/ThirdDrink.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblThird.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/ThirdDonut.png")));
				}
			});
			lblThird.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/ThirdDonut.png")));
			lblThird.setBounds(36, 485, 80, 80);
		}
		return lblThird;
	}
	private JLabel getLblMyprofile() {
		if (lblMyprofile == null) {
			lblMyprofile = new JLabel("나의 정보");
			lblMyprofile.setFont(new Font("CookieRun Regular", Font.BOLD, 18));
			lblMyprofile.setBounds(125, 276, 102, 37);
		}
		return lblMyprofile;
	}
	private JLabel getLblMyPoint() {
		if (lblMyPoint == null) {
			lblMyPoint = new JLabel("보유 포인트");
			lblMyPoint.setFont(new Font("CookieRun Regular", Font.BOLD, 18));
			lblMyPoint.setBounds(124, 388, 103, 37);
		}
		return lblMyPoint;
	}
	private JLabel getLblMyOrder() {
		if (lblMyOrder == null) {
			lblMyOrder = new JLabel("구매 내역");
			lblMyOrder.setFont(new Font("CookieRun Regular", Font.BOLD, 18));
			lblMyOrder.setBounds(124, 503, 103, 37);
		}
		return lblMyOrder;
	}
	private JLabel getLblPoints() {
		if (lblPoints == null) {
			lblPoints = new JLabel("0 pts");
			lblPoints.setFont(new Font("굴림", Font.BOLD, 15));
			lblPoints.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPoints.setBounds(214, 391, 124, 37);
		}
		return lblPoints;
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

	// Menu화면
	private void menuScreen() {
		this.setVisible(false);
		Menu menu = new Menu();
		menu.setVisible(true);
	}

	// Cart화면
	private void cartScreen() {
		this.setVisible(false);
		Cart cart = new Cart();
		cart.setVisible(true);
	}

	// Account화면
	private void accountScreen() {
		this.setVisible(false);
		Account account = new Account();
		account.setVisible(true);
	}

} // End
