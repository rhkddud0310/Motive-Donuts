/*	---------------------------------------------------------------------------------------------------------------

		(1) Desc :	Menu Page 및 제품 검색 Page에서 선택한 제품에 대한 상세 설명 페이지 구현하기.
		
		(2) Date
			1) 2024.01.10. (Ver 0.0.0) => (4)History - 1)
			2) 2024.01.11. (Ver 0.0.1) => (4)History - 2)
			3) 2024.01.14. (Ver 0.0.2) => (4)History - 
			
		(3) Author : Gwangyeong Kim
		
		(4) History
			1) 이대근 팀장님께서 만드신 기본 IPhone 배경화면 Class 가져오기.
			
			2) 마우스 이벤트를 사용하여 JFrame 아무 곳이나 클릭해서 창 이동하는 기능 추가하기.
				1. 마우스 클릭하는 위치의 좌표값 불러오기.
					① addMouseListener(new MouseAdapter() {}); / mousePressed(MouseEvent e) {}
					② initialClick = e.getPoint();
				2. Drag 하는 동안 Frame 이동하기.
					① addMouseMotionListener(new MouseAdapter() {}); / mouseDragged(MouseEvent e) {}

	--------------------------------------------------------------------------------------------------------------- */


package com.javalec.menu;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import com.javalec.account.Account;
import com.javalec.base.AfterMain;
import com.javalec.base.Main;
import com.javalec.cart.Cart;
import com.javalec.common.ShareVar;
import com.javalec.dao.CartDao;
import com.javalec.dao.MenuDao;
import com.javalec.dto.CartAppendingDto;
import com.javalec.dto.MenuDetailedViewDto;
import com.javalec.dto.types.PurchaseGubun;
import com.javalec.purchase.Purchase;

public class ProductDetailed extends JFrame {

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
	private JLabel lblProductName;
	private JLabel lblProductEnglishName;
	private JLabel lblProductPrice;
	private JLabel lblProductImage;
	private JButton btnNutrional;
	private JPanel panelOfAlergy;
	private JLabel lblIngredientTitle;
	private JLabel lblIngredientContents;
	private JPanel panelOfAction;
	private JLabel lblProDetailedLogo;
	private JLabel lblGoToPurchase;
	private JLabel lblGoToCart;
	
	// ShareVar.loginID를 이용하여 로그인한 사용자의 아이디에 접근
	private String custid = ShareVar.loginID;
	
	private MenuDetailedViewDto product;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductDetailed frame = new ProductDetailed("딸기 딜라이트 요거트 블렌디드");
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
	public ProductDetailed(String productId) {
		try {
			initItem(productId);
		} catch (IllegalArgumentException e) {
			// open Error PopUp, ... etc
		}
		initUi();
	}
	
	private void initUi() {
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
		contentPane.add(getLblProDetailedLogo());
		contentPane.add(getLblProductImage());
		contentPane.add(getLblProductName());
		contentPane.add(getLblProductEnglishName());
		contentPane.add(getLblProductPrice());
		contentPane.add(getBtnNutrional());
		contentPane.add(getPanelOfAlergy());
		contentPane.add(getPanelOfAction());
		contentPane.add(getLblScreen());
		contentPane.add(getLblIPhone());
	}
	
	private void initItem(String productId) {
		MenuDao dao = new MenuDao();
		
//		Optional<MenuDetailedViewDto> optional = dao.selectById(productId);
//		MenuDetailedViewDto product = optional.orElseThrow(() -> {
//			return new IllegalArgumentException("상품 이름이 잘못되었습니다.");
//		});
		product = dao.selectById(productId) // optional
				.orElseThrow(() -> new IllegalArgumentException("상품 이름이 잘못되었습니다.")); // product
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("");
			lblIPhone.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("");
			lblScreen.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/ProductSearch Page 배경화면.png")));
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
	
	// 제품 영양 정보 화면
	private void goToNutritionalScreen() {
		this.setVisible(false);
		Nutritional nextScreen = new Nutritional(product);
		nextScreen.setVisible(true);
	}
	
	private void goToPurchaseWithSingleProduct() {
		Purchase nextScreen = new Purchase(product);
		nextScreen.setVisible(true);
		this.setVisible(false);
	}
	
	// *******************************************************************************************************************
	
	// --- Functions (2) ----
	
	// 제품 검색 화면으로 이동하기.
	private void searchScreen() {
		this.setVisible(false);
		ProductSearch_01 proSearch = new ProductSearch_01();
		proSearch.setVisible(true);
	}
	private JLabel getLblProductName() {
		if (lblProductName == null) {
			lblProductName = new JLabel(product.proName());
			lblProductName.setFont(new Font("맑은 고딕", Font.BOLD, 25));
			lblProductName.setBounds(34, 270, 295, 30);
		}
		return lblProductName;
	}
	private JLabel getLblProductEnglishName() {
		if (lblProductEnglishName == null) {
			lblProductEnglishName = new JLabel(product.engProName());
			lblProductEnglishName.setForeground(new Color(0, 0, 128));
			lblProductEnglishName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
			lblProductEnglishName.setBounds(36, 300, 295, 25);
		}
		return lblProductEnglishName;
	}
	private JLabel getLblProductPrice() {
		if (lblProductPrice == null) {
			NumberFormat numberFormat = NumberFormat.getInstance();
			String sellPriceWithComma = numberFormat.format(product.sellPrice());
			
			lblProductPrice = new JLabel(String.format("%s 원", sellPriceWithComma));
			lblProductPrice.setFont(new Font("맑은 고딕", Font.BOLD, 22));
			lblProductPrice.setBounds(36, 330, 150, 30);
		}
		return lblProductPrice;
	}
	private JLabel getLblProductImage() {
		if (lblProductImage == null) {
			// Image Size 조절
			Image resizedImage = new ImageIcon(product.imageFile(), product.imageName())
					.getImage()
					.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(resizedImage);
			lblProductImage = new JLabel("");
			lblProductImage.setIcon(icon);
			lblProductImage.setHorizontalAlignment(SwingConstants.CENTER);
			lblProductImage.setBounds(110, 125, 150, 150);
		}
		return lblProductImage;
	}
	private JButton getBtnNutrional() {
		if (btnNutrional == null) {
			btnNutrional = new JButton("제품 영양 정보");
			btnNutrional.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						goToNutritionalScreen();
					}
				}
			});
			btnNutrional.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			btnNutrional.setHorizontalAlignment(SwingConstants.LEADING);
			btnNutrional.setBounds(36, 390, 327, 57);
		}
		return btnNutrional;
	}
	private JPanel getPanelOfAlergy() {
		if (panelOfAlergy == null) {
			panelOfAlergy = new JPanel();
			panelOfAlergy.setBackground(new Color(255, 255, 196));
			panelOfAlergy.setBounds(36, 450, 327, 75);
			panelOfAlergy.setLayout(null);
			panelOfAlergy.add(getLblIngredientTitle());
			panelOfAlergy.add(getLblIngredientContents());
		}
		return panelOfAlergy;
	}
	private JLabel getLblIngredientTitle() {
		if (lblIngredientTitle == null) {
			lblIngredientTitle = new JLabel("알레르기 유발 요인");
			lblIngredientTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
			lblIngredientTitle.setBounds(12, 10, 200, 30);
		}
		return lblIngredientTitle;
	}
	private JLabel getLblIngredientContents() {
		if (lblIngredientContents == null) {
			lblIngredientContents = new JLabel(product.ingredient());
			lblIngredientContents.setForeground(new Color(60, 68, 119));
			lblIngredientContents.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblIngredientContents.setBounds(14, 40, 250, 20);
		}
		return lblIngredientContents;
	}
	private JPanel getPanelOfAction() {
		if (panelOfAction == null) {
			panelOfAction = new JPanel();
			panelOfAction.setBounds(8, 546, 355, 57);
			panelOfAction.setLayout(null);
			panelOfAction.add(getLblGoToCart());
			panelOfAction.add(getLblGoToPurchase());
		}
		return panelOfAction;
	}
	private JLabel getLblProDetailedLogo() {
		if (lblProDetailedLogo == null) {
			lblProDetailedLogo = new JLabel("제품 정보");
			lblProDetailedLogo.setFont(new Font("CookieRun Regular", Font.BOLD, 32));
			lblProDetailedLogo.setBounds(50, 70, 130, 45);
		}
		return lblProDetailedLogo;
	}
	private JLabel getLblGoToCart() {
		if (lblGoToCart == null) {
			lblGoToCart = new JLabel("");
			lblGoToCart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						CartDao dao = new CartDao();
						CartAppendingDto dto = new CartAppendingDto(
								custid,
								product.proName(),
								null,
								PurchaseGubun.ON_CART
						);
						
						dao.addToCart(dto);
						cartScreen();
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGoToCart.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/CartMoveClick.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblGoToCart.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/CartMoveNotClick.png")));
				}
			});
			lblGoToCart.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/CartMoveNotClick.png")));
			lblGoToCart.setHorizontalAlignment(SwingConstants.CENTER);
			lblGoToCart.setBounds(35, 10, 131, 40);
		}
		return lblGoToCart;
	}
	private JLabel getLblGoToPurchase() {
		if (lblGoToPurchase == null) {
			lblGoToPurchase = new JLabel("");
			lblGoToPurchase.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						goToPurchaseWithSingleProduct();
					}
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblGoToPurchase.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/Orderclicked.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblGoToPurchase.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/Ordernotclicked.png")));
				}
			});
			lblGoToPurchase.setIcon(new ImageIcon(ProductDetailed.class.getResource("/com/javalec/image/Ordernotclicked.png")));
			lblGoToPurchase.setHorizontalAlignment(SwingConstants.CENTER);
			lblGoToPurchase.setBounds(185, 10, 131, 40);
		}
		return lblGoToPurchase;
	}
} // End
