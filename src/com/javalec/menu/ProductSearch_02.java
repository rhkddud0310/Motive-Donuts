/*	---------------------------------------------------------------------------------------------------------------

		(1) Desc :	Menu Page에서 돋보기 선택 시 제품 검색 Page 구현하기.
		
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

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.javalec.account.Account;
import com.javalec.base.AfterMain;
import com.javalec.base.Main;
import com.javalec.cart.Cart;
import com.javalec.common.ShareVar;
import com.javalec.dao.MenuDao;
import com.javalec.dto.MenuListViewDto;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ProductSearch_02 extends JFrame {

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
	private JLabel lblCategory1;
	private JLabel lblCategory2;
	private JLabel lblCategory3;
	private JLabel lblCategory4;
	private JScrollPane scrollPane;
	private JTable innerTable;
	
	// ShareVar.loginID를 이용하여 로그인한 사용자의 아이디에 접근
	private String custid = ShareVar.loginID;
	
	private String currentKeyword = "";
	private int currentCategoryNumber = 1;
	private String currentCategoryName = "전체";
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
					ProductSearch_02 frame = new ProductSearch_02();
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
	public ProductSearch_02() {
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
		contentPane.add(getLblCategory1());
		contentPane.add(getLblCategory2());
		contentPane.add(getLblCategory3());
		contentPane.add(getLblCategory4());
		contentPane.add(getScrollPane());
		contentPane.add(getLblScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("");
			lblIPhone.setIcon(new ImageIcon(ProductSearch_02.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("");
			lblScreen.setIcon(new ImageIcon(ProductSearch_02.class.getResource("/com/javalec/image/ProductSearch Result Page 배경화면.png")));
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
			lblProSearch.setBounds(27, 135, 30, 30);
		}
		return lblProSearch;
	}
	private JTextField getTfProSearch() {
		if (tfProSearch == null) {
			tfProSearch = new JTextField();
			tfProSearch.setFont(new Font("맑은 고딕", Font.BOLD, 15));
			tfProSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					 	currentKeyword = tfProSearch.getText();
					 	
					 	changeCategoryFontWeightBySelect(currentCategoryNumber);
						drawMenuListByCategoryName(currentKeyword, currentCategoryName);
					}
				}
			});
			tfProSearch.setBounds(65, 130, 220, 40);
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
			lblCancel.setBounds(295, 130, 50, 40);
		}
		return lblCancel;
	}
	
	// *******************************************************************************************************************
	
	// --- Functions (2) ----
	
	// 제품 검색 화면으로 이동하기.
	private void searchScreen() {
		this.setVisible(false);
		ProductSearch_01 proSearch = new ProductSearch_01();
		proSearch.setVisible(true);
	}
	
	// *******************************************************************************************************************
	
	private JLabel getLblCategory1() {
		if (lblCategory1 == null) {
			lblCategory1 = new JLabel("전체");
			lblCategory1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						currentCategoryNumber = 1;
						currentCategoryName = "전체";
					 	changeCategoryFontWeightBySelect(currentCategoryNumber);
						drawMenuListByCategoryName(currentKeyword, currentCategoryName);
					}
				}
			});
			lblCategory1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategory1.setForeground(new Color(0, 0, 128));
			lblCategory1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
			lblCategory1.setBounds(40, 172, 40, 30);
		}
		return lblCategory1;
	}
	private JLabel getLblCategory2() {
		if (lblCategory2 == null) {
			lblCategory2 = new JLabel("도넛");
			lblCategory2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						currentCategoryNumber = 2;
						currentCategoryName = "도넛";
					 	changeCategoryFontWeightBySelect(currentCategoryNumber);
						drawMenuListByCategoryName(currentKeyword, currentCategoryName);
					}
				}
			});
			lblCategory2.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategory2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			lblCategory2.setBounds(100, 172, 40, 30);
		}
		return lblCategory2;
	}
	private JLabel getLblCategory3() {
		if (lblCategory3 == null) {
			lblCategory3 = new JLabel("음료");
			lblCategory3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						currentCategoryNumber = 3;
						currentCategoryName = "음료";
					 	changeCategoryFontWeightBySelect(currentCategoryNumber);
						drawMenuListByCategoryName(currentKeyword, currentCategoryName);
					}
				}
			});
			lblCategory3.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategory3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			lblCategory3.setBounds(160, 172, 40, 30);
		}
		return lblCategory3;
	}
	private JLabel getLblCategory4() {
		if (lblCategory4 == null) {
			lblCategory4 = new JLabel("Set");
			lblCategory4.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == 1) {
						currentCategoryNumber = 4;
						currentCategoryName = "셋트";
					 	changeCategoryFontWeightBySelect(currentCategoryNumber);
						drawMenuListByCategoryName(currentKeyword, currentCategoryName);
					}
				}
			});
			lblCategory4.setHorizontalAlignment(SwingConstants.CENTER);
			lblCategory4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			lblCategory4.setBounds(220, 172, 40, 30);
		}
		return lblCategory4;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(8, 202, 355, 379);
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
	private void drawMenuListByCategoryName(String keyword, String categoryName) {
		tableInit();
		appendMenuItemsByCategory(keyword, categoryName);
	}
	
	private void appendMenuItemsByCategory(String keyword, String categoryName) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		MenuDao dao = new MenuDao();
		products = dao.searchAllByCategoryOrName(keyword, categoryName);
		
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
		
		// Table Cell 내용 정렬하기.
		// 가운데 정렬
		DefaultTableCellRenderer center = new DefaultTableCellRenderer();
		center.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = innerTable.getColumnModel();
		
		tcm.getColumn(1).setCellRenderer(center);
		tcm.getColumn(2).setCellRenderer(center);
		
		// 오른쪽 정렬
		DefaultTableCellRenderer right = new DefaultTableCellRenderer();
		right.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tcm.getColumn(3).setCellRenderer(right);
	}
	
	private void changeCategoryFontWeightBySelect(int selNum) {
		if (selNum == 1) {
			lblCategory1.setForeground(new Color(0, 0, 128));
			lblCategory1.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblCategory1.setForeground(new Color(0, 0, 0));
			lblCategory1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
		
		if (selNum == 2) {
			lblCategory2.setForeground(new Color(0, 0, 128));
			lblCategory2.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblCategory2.setForeground(new Color(0, 0, 0));
			lblCategory2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
		
		if (selNum == 3) {
			lblCategory3.setForeground(new Color(0, 0, 128));
			lblCategory3.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblCategory3.setForeground(new Color(0, 0, 0));
			lblCategory3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
		
		if (selNum == 4) {
			lblCategory4.setForeground(new Color(0, 0, 128));
			lblCategory4.setFont(new Font("맑은 고딕", Font.BOLD, 13));
		} else {
			lblCategory4.setForeground(new Color(0, 0, 0));
			lblCategory4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		}
	}
} // End
