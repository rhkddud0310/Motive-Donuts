/*	---------------------------------------------------------------------------------------------------------------

		(1) Desc :	Menu Page에서 돋보기 선택 시 제품 검색 Page 구현하기.
		
		(2) Date
			1) 2024.01.10. (Ver 0.0.0.0) => (4)History - 1)
			2) 2024.01.11. (Ver 0.0.0.1) => (4)History - 2)
			3) 2024.01.12. (Ver 0.0.0.2) => (4)History - 3), 4), 5)
			4) 2024.01.13. (Ver 0.0.0.3) => (4)History - 6)
			
		(3) Author : Gwangyeong Kim
		
		(4) History
			1) 이대근 팀장님께서 만드신 기본 IPhone 배경화면 Class 가져오기.
			
			2) 마우스 이벤트를 사용하여 JFrame 아무 곳이나 클릭해서 창 이동하는 기능 추가하기.
				1. 마우스 클릭하는 위치의 좌표값 불러오기.
					① addMouseListener(new MouseAdapter() {}); / mousePressed(MouseEvent e) {}
					② initialClick = e.getPoint();
				2. Drag 하는 동안 Frame 이동하기.
					① addMouseMotionListener(new MouseAdapter() {}); / mouseDragged(MouseEvent e) {}
					
			3) 취소 부분 클릭 시 Menu Page로 이동하는 기능 추가하기.
			
			4) 최근 검색어 List 구현을 위해 JScrollPane 및 JTable 추가하기.
			
			5) 선택 삭제, 전체 삭제 JButton 추가하기.
			
			6) 장바구니 수량 확인하기.
				1. addWindowListener(new WindowAdapter() {});
				2. windowOpened(WindowEvent e) {}
				3. cartQty();

	--------------------------------------------------------------------------------------------------------------- */


package com.javalec.menu;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.javalec.account.Account;
import com.javalec.base.AfterMain;
import com.javalec.base.Main;
import com.javalec.cart.Cart;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

public class ProductSearch_01 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblScreen;
	private JLabel lblTimer;
	private JLabel lblHome;
	private JLabel lblMenu;
	private JLabel lblCart;
	private JLabel lblAccount;
	private JLabel lblAccount1;
	private JLabel lblCart1;
	private JLabel lblMenu1;
	private JLabel lblHome1;
	
	private Point initialClick;	// <-- *************************************************************
	
	private JLabel lblProSearchLogo;
	private JLabel lblProSearch;
	private JTextField tfProSearch;
	private JLabel lblCancel;
	private JLabel lblRecentSearchTerms;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JButton btnDeleteSelection;
	private JButton btnDeleteTheWhole;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductSearch_01 frame = new ProductSearch_01();
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
	public ProductSearch_01() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// *************************************************************************************************************
		setUndecorated(true); // Title Bar 없애기
		// 마우스 이벤트를 사용하여 Frame 이동
		// 마우스 클릭하는 위치 좌표값 불러오기.
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				initialClick = e.getPoint();
			}
		});
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int thisX = getLocation().x;
				int thisY = getLocation().y;
				
				// Drag 하는 동안 Frame 이동
				int xMoved = thisX + e.getX() - initialClick.x;
				int yMoved = thisY + e.getY() - initialClick.y;
				
				setLocation(xMoved, yMoved);
			}
		});
		// *************************************************************************************************************
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
		contentPane.add(getLblProSearchLogo());
		contentPane.add(getTfProSearch());
		contentPane.add(getLblProSearch());
		contentPane.add(getLblCancel());
		contentPane.add(getLblRecentSearchTerms());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnDeleteSelection());
		contentPane.add(getBtnDeleteTheWhole());
		contentPane.add(getLblScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("");
			lblIPhone.setIcon(new ImageIcon(ProductSearch_01.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("");
			lblScreen.setIcon(new ImageIcon(ProductSearch_01.class.getResource("/com/javalec/image/ProductSearch Page 배경화면.png")));
			lblScreen.setBounds(8, 10, 358, 665);
		}
		return lblScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("12 : 00");
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
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						homeScreen();
					}
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
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						menuScreen();
					}
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
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						cartScreen();
//						signInScreen();
					}
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
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						accountScreen();
//						signInScreen();
					}
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
	
	// *******************************************************************************************************************
	
	// --- Functions (1) ----
	
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
		AfterMain.main(null); // 홈 화면 키기
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
	
	// *******************************************************************************************************************
	
	private JLabel getLblProSearchLogo() {
		if (lblProSearchLogo == null) {
			lblProSearchLogo = new JLabel("제품 검색");
			lblProSearchLogo.setFont(new Font("CookieRun Regular", Font.BOLD, 32));
			lblProSearchLogo.setBounds(30, 68, 130, 45);
		}
		return lblProSearchLogo;
	}
	
	private JLabel getLblProSearch() {
		if (lblProSearch == null) {
			lblProSearch = new JLabel("");
			lblProSearch.setIcon(new ImageIcon(ProductSearch_01.class.getResource("/com/javalec/image/돋보기_검색.png")));
			lblProSearch.setHorizontalAlignment(SwingConstants.CENTER);
			lblProSearch.setBounds(27, 150, 30, 30);
		}
		return lblProSearch;
	}
	private JTextField getTfProSearch() {
		if (tfProSearch == null) {
			tfProSearch = new JTextField();
			tfProSearch.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfProSearch.setBounds(65, 145, 220, 40);
			tfProSearch.setColumns(10);
		}
		return tfProSearch;
	}
	private JLabel getLblCancel() {
		if (lblCancel == null) {
			lblCancel = new JLabel("취소");
			lblCancel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						menuScreen();
					}
				}
			});
			lblCancel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblCancel.setHorizontalAlignment(SwingConstants.CENTER);
			// ************************************************************************************************************************
			// 돋보기 아이콘에 마우스 커서 둘 경우 나타나는 상태메세지 출력하기.
			lblCancel.setToolTipText("<html><font face='맑은 고딕' size='5'><b>Menu 페이지로 이동합니다.</b></font></html>");
			// ************************************************************************************************************************
			lblCancel.setBounds(295, 145, 50, 40);
		}
		return lblCancel;
	}
	private JLabel getLblRecentSearchTerms() {
		if (lblRecentSearchTerms == null) {
			lblRecentSearchTerms = new JLabel("최근 검색어");
			lblRecentSearchTerms.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			lblRecentSearchTerms.setBounds(36, 200, 75, 15);
		}
		return lblRecentSearchTerms;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 225, 295, 290);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
		}
		return innerTable;
	}
	private JButton getBtnDeleteSelection() {
		if (btnDeleteSelection == null) {
			btnDeleteSelection = new JButton("선택 삭제");
			btnDeleteSelection.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnDeleteSelection.setBounds(36, 530, 100, 40);
		}
		return btnDeleteSelection;
	}
	private JButton getBtnDeleteTheWhole() {
		if (btnDeleteTheWhole == null) {
			btnDeleteTheWhole = new JButton("전체 삭제");
			btnDeleteTheWhole.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			btnDeleteTheWhole.setBounds(230, 530, 100, 40);
		}
		return btnDeleteTheWhole;
	}
} // End
