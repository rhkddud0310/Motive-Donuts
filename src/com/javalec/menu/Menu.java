/*	---------------------------------------------------------------------------------------------------------------

		(1) Desc :	Menu를 카테고리 별로 나열하는 Page 구현하기.
		
		(2) Date
			1) 2024.01.10. (Ver 0.0.0.0) => (4)History - 1), 2), 3), 4)
			2) 2024.01.11. (Ver 0.0.0.1) => (4)History - 5)
			3) 2024.01.12. (Ver 0.0.0.2) => (4)History - 6)
			4) 2024.01.13. (ver 0.0.0.3) => (4)History - 7)
			
		(3) Author : Gwangyeong Kim
		
		(4) History
			1) 이대근 팀장님께서 만드신 기본 IPhone 배경화면 Class 가져오기.
			
			2) Menu Page 전용 배경화면으로 변경하기.
			
			3) 돋보기 클릭 시 제품 검색 화면으로 이동하는 기능 추가하기.
				1. lblproSearch.addMouseListener(new MouseAdapter() {}); / mouseClicked(MouseEvent e) {}
				2. searchScreen();
				
			4) 장바구니 수량 확인하기.
				1. addWindowListener(new WindowAdapter() {});
				2. windowOpened(WindowEvent e) {}
				3. cartQty();
				
			5) 마우스 이벤트를 사용하여 JFrame 아무 곳이나 클릭해서 창 이동하는 기능 추가하기.
				1. 마우스 클릭하는 위치의 좌표값 불러오기.
					① addMouseListener(new MouseAdapter() {}); / mousePressed(MouseEvent e) {}
					② initialClick = e.getPoint();
				2. Drag 하는 동안 Frame 이동하기.
					① addMouseMotionListener(new MouseAdapter() {}); / mouseDragged(MouseEvent e) {}
					
			6) 화면 상단부 Category 배너 부분 구현을 위해 JLabel 추가하기.
			
			7) Category List 구현을 위해 JScrollPane 및 JTable 추가하기.

	--------------------------------------------------------------------------------------------------------------- */

package com.javalec.menu;

import java.awt.Color;
import java.awt.Dimension;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
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
import com.javalec.dto.CartDto;
import com.javalec.dto.MenuListViewDto;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.GroupLayout.Alignment;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Menu extends JFrame {

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
	private JLabel lblMenuLogo;
	private JLabel lblProSearch;
	private JComboBox cbSelectStore;
	private JLabel lblCartCount;
	private JLabel lblCartCountNum;
	
	private Point initialClick;	// <-- *************************************************************
	
	private JLabel lblSuperCategory1;
	private JLabel lblBaseCategory1;
	private JLabel lblBaseCategory2;
	private JLabel lblBaseCategory3;
	private JScrollPane scrollPane;
	private JTable innerTable;
	
	// ShareVar.loginID를 이용하여 로그인한 사용자의 아이디에 접근
	private String custid = ShareVar.loginID;
	
	private List<MenuListViewDto> products;
	
	// ******************************************************************************************************************
	// -- Table
	private final DefaultTableModel outerTable = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
			// Cell 편집 비활성화
			return false;
		}
	};
	// ******************************************************************************************************************
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				drawMenuListByCategoryName("도넛");
			}
		});
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
		contentPane.add(getLblMenuLogo());
		contentPane.add(getLblSuperCategory1());
		contentPane.add(getLblBaseCategory1());
		contentPane.add(getLblBaseCategory2());
		contentPane.add(getLblBaseCategory3());
		contentPane.add(getLblProSearch());
		contentPane.add(getCbSelectStore());
		contentPane.add(getLblCartCount());
		contentPane.add(getLblCartCountNum());
		contentPane.add(getScrollPane());
		contentPane.add(getLblScreen());
		contentPane.add(getLblIPhone());
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("");
			lblIPhone.setIcon(new ImageIcon(Menu.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}

	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("");
			lblScreen.setIcon(new ImageIcon(Menu.class.getResource("/com/javalec/image/Menu Page 배경화면.png")));
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
	
	// 제품 상세 화면
	private void goToProductDetailed(String productId) {
		this.setVisible(false);
		ProductDetailed nextPage = new ProductDetailed(productId);
		nextPage.setVisible(true);
	}
	
	// *******************************************************************************************************************
	
	private JLabel getLblMenuLogo() {
		if (lblMenuLogo == null) {
			lblMenuLogo = new JLabel("Menu");
			lblMenuLogo.setFont(new Font("CookieRun Regular", Font.BOLD, 32));
			lblMenuLogo.setBounds(30, 85, 110, 45);
		}
		return lblMenuLogo;
	}

	private JLabel getLblProSearch() {
		if (lblProSearch == null) {
			lblProSearch = new JLabel("");
			lblProSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton()==1) {	// 마우스 좌측 버튼 클릭
						searchScreen();
					}
				}
			});
			lblProSearch.setHorizontalAlignment(SwingConstants.CENTER);
			lblProSearch.setIcon(new ImageIcon(Menu.class.getResource("/com/javalec/image/돋보기_검색.png")));
			// ************************************************************************************************************************
			// 돋보기 아이콘에 마우스 커서 둘 경우 나타나는 상태메세지 출력하기.
			lblProSearch.setToolTipText("<html><font face='맑은 고딕' size='5'><b>제품 검색 페이지로 이동합니다.</b></font></html>");
			// ************************************************************************************************************************
			lblProSearch.setBounds(305, 150, 35, 35);
		}
		return lblProSearch;
	}

	private JComboBox getCbSelectStore() {
		if (cbSelectStore == null) {
			cbSelectStore = new JComboBox();
			cbSelectStore.setModel(new DefaultComboBoxModel(new String[] {"주문할 매장을 선택해주시기 바랍니다."}));
			cbSelectStore.setForeground(Color.BLACK);
			cbSelectStore.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			cbSelectStore.setBackground(new Color(147, 8, 42));
			cbSelectStore.setBounds(25, 547, 260, 35);
		}
		return cbSelectStore;
	}

	private JLabel getLblCartCount() {
		if (lblCartCount == null) {
			lblCartCount = new JLabel("\r\n");
			lblCartCount.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			lblCartCount.setIcon(new ImageIcon(Menu.class.getResource("/com/javalec/image/장바구니로 이동.png")));
			lblCartCount.setHorizontalAlignment(SwingConstants.CENTER);
			lblCartCount.setBounds(290, 532, 75, 58);
		}
		return lblCartCount;
	}

	private JLabel getLblCartCountNum() {
		if (lblCartCountNum == null) {
			lblCartCountNum = new JLabel("15");
			lblCartCountNum.setForeground(new Color(255, 255, 255));
			lblCartCountNum.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblCartCountNum.setHorizontalAlignment(SwingConstants.CENTER);
			lblCartCountNum.setBounds(309, 557, 30, 21);
//			cartQty();
		}
		return lblCartCountNum;
	}
	
	// *******************************************************************************************************************
	
	// --- Functions (2) ----
	
	// 제품 검색 화면으로 이동하기.
	private void searchScreen() {
		this.setVisible(false);
		ProductSearch_02 proSearch = new ProductSearch_02();
		proSearch.setVisible(true);
	}
	
	// 장바구니 수량 확인하기.
	private void cartQty() {
		CartDao CartDao = new CartDao();
		ArrayList<CartDto> dtoList = CartDao.selectList();
		
		int listCount = dtoList.size();
		
		lblCartCountNum.setText(Integer.toString(listCount));
	}
	
	// *******************************************************************************************************************
	
	private JLabel getLblSuperCategory1() {
		if (lblSuperCategory1 == null) {
			lblSuperCategory1 = new JLabel("전체 메뉴");
			lblSuperCategory1.setHorizontalAlignment(SwingConstants.CENTER);
			lblSuperCategory1.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			lblSuperCategory1.setBounds(40, 150, 75, 35);
		}
		return lblSuperCategory1;
	}
	private JLabel getLblBaseCategory1() {
		if (lblBaseCategory1 == null) {
			lblBaseCategory1 = new JLabel("도넛");
			lblBaseCategory1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						changeCategoryFontWeightBySelect(1);
						drawMenuListByCategoryName("도넛");
					}
				}
			});
			lblBaseCategory1.setHorizontalAlignment(SwingConstants.CENTER);
			lblBaseCategory1.setForeground(new Color(0, 0, 128));
			lblBaseCategory1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			lblBaseCategory1.setBounds(35, 192, 50, 28);
		}
		return lblBaseCategory1;
	}
	
	private JLabel getLblBaseCategory2() {
		if (lblBaseCategory2 == null) {
			lblBaseCategory2 = new JLabel("음료");
			lblBaseCategory2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						changeCategoryFontWeightBySelect(2);
						drawMenuListByCategoryName("음료");
					}
				}
			});
			lblBaseCategory2.setHorizontalAlignment(SwingConstants.CENTER);
			lblBaseCategory2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			lblBaseCategory2.setBounds(95, 192, 50, 28);
		}
		return lblBaseCategory2;
	}
	private JLabel getLblBaseCategory3() {
		if (lblBaseCategory3 == null) {
			lblBaseCategory3 = new JLabel("Set");
			lblBaseCategory3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						changeCategoryFontWeightBySelect(3);
						drawMenuListByCategoryName("셋트");
					}
				}
			});
			lblBaseCategory3.setHorizontalAlignment(SwingConstants.CENTER);
			lblBaseCategory3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			lblBaseCategory3.setBounds(155, 192, 50, 28);
		}
		return lblBaseCategory3;
	}
	
	// *******************************************************************************************************************
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 227, 355, 305);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}
	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable() { 								// <--*********************************
				public Class getColumnClass(int column) { 				// <--*********************************
					return (column == 0) ? Icon.class : Object.class; 	// <--*********************************
				}
			};
			innerTable.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if(e.getButton()==1) {
						// 선택한 상품 정보 갖고 와서
						int index = innerTable.getSelectedRow();
						MenuListViewDto selectedProduct = products.get(index);
						// 화면 전환에 상품 ID 사용
						goToProductDetailed(selectedProduct.proName());
					}
				}
			});
			innerTable.setFont(new Font("맑은 고딕", Font.BOLD, 12));
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			innerTable.setRowHeight(100); 		// <--***************************************************
			innerTable.setModel(outerTable); 	// <--***************************************************
			innerTable.getTableHeader().setReorderingAllowed(false);	// Column 간 이동 금지
		}
		return innerTable;
	}
	
	// *******************************************************************************************************************
	
	// --- Functions(3) ----
	
	// Table 초기화 하기.
	private void tableInit() {
		// Table Column명 정하기.
		outerTable.addColumn("제품 사진");
		outerTable.addColumn("제품명");
		outerTable.addColumn("제품명(영문)");
		outerTable.addColumn("제품 가격");
		outerTable.setColumnCount(4);
		
		
		// Table Column 크기 정하기.
		// 제품 사진
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 100;
		col.setPreferredWidth(width);
		
		// 제품명
		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		// 제품명(영문)
		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 150;
		col.setPreferredWidth(width);
		
		// 제품 가격
		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);
		
		
		// Table 내용 지우기
		int i = outerTable.getRowCount();
		for(int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
		
	}
	
	// DB에서 Data 불러오기(검색).
	private void drawMenuListByCategoryName(String categoryName) {
		tableInit();
		appendMenuItemsByCategory(categoryName);
	}
	
	private void appendMenuItemsByCategory(String categoryName) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		MenuDao dao = new MenuDao();
		products = dao.selectAllByCategory(categoryName);
		
		for (MenuListViewDto item : products) {
			// Image Size 조절
			ImageIcon format = new ImageIcon(item.imageFile(), item.imageName());
			Image img = format.getImage();
			Image img2 = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			ImageIcon icon = new ImageIcon(img2);
			
			String productName = item.proName();
			String productNameEng = item.engProName();
			String sellPriceWithComma = numberFormat.format(item.sellPrice());
			
			Object[] row = {
					icon,
					productName,
					productNameEng,
					String.format("%s원", sellPriceWithComma)
			};
			
			outerTable.addRow(row);
		}
	}
	
	private void changeCategoryFontWeightBySelect(int selNum) {
		if (selNum == 1) {
			lblBaseCategory1.setForeground(new Color(0, 0, 128));
			lblBaseCategory1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblBaseCategory1.setForeground(new Color(0, 0, 0));
			lblBaseCategory1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
		
		if (selNum == 2) {
			lblBaseCategory2.setForeground(new Color(0, 0, 128));
			lblBaseCategory2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblBaseCategory2.setForeground(new Color(0, 0, 0));
			lblBaseCategory2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
		
		if (selNum == 3) {
			lblBaseCategory3.setForeground(new Color(0, 0, 128));
			lblBaseCategory3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblBaseCategory3.setForeground(new Color(0, 0, 0));
			lblBaseCategory3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
	}
} // End
