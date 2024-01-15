package com.javalec.base;

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
import javax.swing.SwingConstants;
import javax.swing.Timer;

import com.javalec.cart.Cart;
import com.javalec.menu.Menu;
import com.javalec.sign.SignIn;

public class Main {
	// --------------------------------------------------------------//
	// Desc : 로그인 전 화면
	// Date : 2024.01.12(Ver1.0.0)
	// Author : Daegeun Lee, Sumin Kim
	// History : 1. 화면 디자인 및 페이지 처리
	// --------------------------------------------------------------//

	private JFrame frame;
	private JLabel lblIPhone;
	private JLabel lblHomeScreen;
	private JLabel lblTimer;
	private JLabel lblHome;
	private JLabel lblMenu;
	private JLabel lblCart;
	private JLabel lblAccount;
	private JLabel lblHome1;
	private JLabel lblMenu1;
	private JLabel lblCart1;
	private JLabel lblAccount1;
	private JLabel lblMainAdvert1;
	private JLabel lblNewLabel;
	private JLabel lblMainDonut1;
	private JLabel lblMainDonut2;
	private JLabel lblMainDonut3;
	private JLabel lblOrderButton;
	private JLabel lblOrder;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(0, 0, 0));
		frame.setBounds(600, 100, 375, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true); // 타이틀 바 없애기
		frame.getContentPane().add(getLblTimer());
		Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime(); // 분마다 시간 업데이트
            }
        });
		timer.start();
		frame.getContentPane().add(getLblHome());
		frame.getContentPane().add(getLblHome1());
		frame.getContentPane().add(getLblMenu());
		frame.getContentPane().add(getLblMenu1());
		frame.getContentPane().add(getLblCart());
		frame.getContentPane().add(getLblCart1());
		frame.getContentPane().add(getLblAccount());
		frame.getContentPane().add(getLblAccount1());
		frame.getContentPane().add(getLblOrder());
		frame.getContentPane().add(getLblOrderButton());
		frame.getContentPane().add(getLblMainAdvert1());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblMainDonut1());
		frame.getContentPane().add(getLblMainDonut2());
		frame.getContentPane().add(getLblMainDonut3());
		frame.getContentPane().add(getLblHomeScreen()); // 기본 홈화면
		frame.getContentPane().add(getLblIPhone()); // 아이폰 테두리
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Main.png")));
			lblHomeScreen.setBounds(8, 10, 358, 665);
		}
		return lblHomeScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("");
			lblTimer.setForeground(new Color(255, 255, 255));
			lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
			lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
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
					signInScreen();
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
					signInScreen();
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
					signInScreen();
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
	private JLabel getLblMainAdvert1() {
		if (lblMainAdvert1 == null) {
			lblMainAdvert1 = new JLabel("");
			lblMainAdvert1.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainAdvert1.png")));
			lblMainAdvert1.setBounds(15, 179, 340, 200);
		}
		return lblMainAdvert1;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("TODAY’S TOP TREATS");
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblNewLabel.setForeground(new Color(0, 128, 255));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblNewLabel.setForeground(new Color(0, 0, 0));
				}
			});
			lblNewLabel.setFont(new Font("CookieRun Regular", Font.BOLD, 21));
			lblNewLabel.setBounds(70, 400, 260, 30);
		}
		return lblNewLabel;
	}
	private JLabel getLblMainDonut1() {
		if (lblMainDonut1 == null) {
			lblMainDonut1 = new JLabel("");
			lblMainDonut1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblMainDonut1.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDrink1.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblMainDonut1.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut1.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					signInScreen();
				}
			});
			lblMainDonut1.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut1.png")));
			lblMainDonut1.setBounds(21, 462, 105, 105);
		}
		return lblMainDonut1;
	}
	private JLabel getLblMainDonut2() {
		if (lblMainDonut2 == null) {
			lblMainDonut2 = new JLabel("");
			lblMainDonut2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblMainDonut2.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDrink2.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblMainDonut2.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut2.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					signInScreen();
				}
			});
			lblMainDonut2.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut2.png")));
			lblMainDonut2.setBounds(134, 462, 105, 105);
		}
		return lblMainDonut2;
	}
	private JLabel getLblMainDonut3() {
		if (lblMainDonut3 == null) {
			lblMainDonut3 = new JLabel("");
			lblMainDonut3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					lblMainDonut3.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDrink3.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblMainDonut3.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut3.png")));
				}
				@Override
				public void mouseClicked(MouseEvent e) {
					signInScreen();
				}
			});
			lblMainDonut3.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/MainDonut3.png")));
			lblMainDonut3.setBounds(246, 462, 105, 105);
		}
		return lblMainDonut3;
	}
	private JLabel getLblOrderButton() {
		if (lblOrderButton == null) {
			lblOrderButton = new JLabel("ORDER");
			lblOrderButton.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/OrderButton.png")));
			lblOrderButton.setBounds(56, 335, 86, 25);
		}
		return lblOrderButton;
	}
	private JLabel getLblOrder() {
		if (lblOrder == null) {
			lblOrder = new JLabel("ORDER NOW");
			lblOrder.setForeground(new Color(255, 255, 255));
			lblOrder.setHorizontalAlignment(SwingConstants.CENTER);
			lblOrder.setFont(new Font("CookieRun Regular", Font.PLAIN, 12));
			lblOrder.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					signInScreen();
				}
			});
			lblOrder.setBounds(56, 335, 86, 25);
		}
		return lblOrder;
	}
	// ---- Function ----
	
	// 실시간 시간 나오기
	private void updateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
        String currentTime = dateFormat.format(new Date());
        lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
        lblTimer.setText(currentTime);
    }
	// Home화면
	private void homeScreen() {
		this.frame.setVisible(false); // 현재화면 끄고
		Main window = new Main();
		window.frame.setVisible(true); // 홈 화면 키기
	}
	
	// 로그인전이라서 Menu, Cart, Account 눌렀을시 모두 로그인 화면으로
	private void signInScreen() {
		this.frame.setVisible(false);
		SignIn signIn = new SignIn();
		signIn.setVisible(true);
	}
} // End
