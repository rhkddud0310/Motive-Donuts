package com.javalec.cart;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.account.Account;
import com.javalec.base.Main;
import com.javalec.dao.CartDao;
import com.javalec.dto.CartDto;
import com.javalec.menu.Menu;
import com.javalec.purchase.Purchase;


public class Cart extends JFrame {

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
	private JLabel lblNewLabel;
	private JScrollPane scrollPane;
	private JTable cart_Table;
	private int purseq;
	private String custid;
	private String proname;
	
	//Table
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JLabel lblNewLabel_1;
	private JLabel lblOrderButton;
	private JLabel lblItemAdd;
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cart frame = new Cart();
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
	public Cart() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setUndecorated(true); // 타이틀 바 없애기
		contentPane.add(getLblNewLabel());
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
		contentPane.add(getScrollPane());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblOrderButton());
		contentPane.add(getLblItemAdd());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/아이폰 홈 화면.png")));
			lblHomeScreen.setBounds(8, 10, 358, 665);
		}
		return lblHomeScreen;
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
			lblHome1.setFont(new Font("CookieRun", Font.BOLD, 12));
			lblHome1.setHorizontalAlignment(SwingConstants.CENTER);
			lblHome1.setBounds(44, 645, 42, 15);
		}
		return lblHome1;
	}
	private JLabel getLblMenu1() {
		if (lblMenu1 == null) {
			lblMenu1 = new JLabel("Menu");
			lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenu1.setFont(new Font("CookieRun", Font.BOLD, 12));
			lblMenu1.setBounds(124, 645, 42, 15);
		}
		return lblMenu1;
	}
	private JLabel getLblCart1() {
		if (lblCart1 == null) {
			lblCart1 = new JLabel("Cart");
			lblCart1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCart1.setFont(new Font("CookieRun", Font.BOLD, 12));
			lblCart1.setBounds(204, 645, 42, 15);
		}
		return lblCart1;
	}
	private JLabel getLblAccount1() {
		if (lblAccount1 == null) {
			lblAccount1 = new JLabel("Account");
			lblAccount1.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccount1.setFont(new Font("CookieRun", Font.BOLD, 12));
			lblAccount1.setBounds(276, 645, 62, 15);
		}
		return lblAccount1;
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
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("장바구니");
			lblNewLabel.setFont(new Font("CookieRun", Font.PLAIN, 22));
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setBounds(39, 89, 141, 34);
		}
		return lblNewLabel;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(33, 131, 311, 352);
			scrollPane.setViewportView(getCart_Table());
			cartTableInit(); 
			cartTableData(); 
		}
		return scrollPane;
	}
	private JTable getCart_Table() {
		if (cart_Table == null) {
			cart_Table = new JTable() {
				public Class getColumnClass(int column) { 				    //*************************IMAGE SETUP
			        return (column == 0) ? Icon.class : Object.class; 	//*************************IMAGE SETUP
			        }		
			};
			cart_Table.setBackground(new Color(244, 208, 208));
			cart_Table.setFillsViewportHeight(true);
			cart_Table.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					deleteItem();
					cartTableInit(); 
					cartTableData(); 

				}
			});
			cart_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cartTableClick(); 
				
				}
			});
		
			cart_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			cart_Table.setRowHeight(100); 	
			cart_Table.setModel(outerTable);
		}
		return cart_Table;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Select & Swipe to delete item!");
			lblNewLabel_1.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(36, 495, 236, 16);
		}
		return lblNewLabel_1;
	}
	private JLabel getLblOrderButton() {
		if (lblOrderButton == null) {
			lblOrderButton = new JLabel("");
			lblOrderButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					moveToPurchase();		
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblOrderButton.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/Orderclicked.png")));					
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblOrderButton.setIcon(new ImageIcon(Account.class.getResource("/com/javalec/image/Ordernotclicked.png")));			
				}
			});
			lblOrderButton.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/Ordernotclicked.png")));
			lblOrderButton.setBounds(120, 529, 152, 46);
		}
		return lblOrderButton;
	}
	
	private JLabel getLblItemAdd() {
		if (lblItemAdd == null) {
			lblItemAdd = new JLabel("");
			lblItemAdd.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					backToMenu();
				}
				@Override
				public void mouseEntered(MouseEvent e) {
					lblItemAdd.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/Additemclicked.png")));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					lblItemAdd.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/Additemnotclicked.png")));
				}
			});
			lblItemAdd.setIcon(new ImageIcon(Cart.class.getResource("/com/javalec/image/Additemnotclicked.png")));
			lblItemAdd.setBounds(241, 99, 103, 34);
		}
		return lblItemAdd;
	}
	
	
	//---------FUNCTIONS-----------
	
	
	//CART TABLE 초기화
	private void cartTableInit() {
		
		// Table Column 명 정하기
		outerTable.addColumn("");
		outerTable.addColumn("");
		outerTable.addColumn("");
		outerTable.addColumn("");
		outerTable.setColumnCount(4);


		// Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = cart_Table.getColumnModel().getColumn(colNo);
		int width = 100;
		col.setPreferredWidth(width);
		
		colNo = 1;
		col = cart_Table.getColumnModel().getColumn(colNo);
		width = 90;
		col.setPreferredWidth(width);
		
		colNo = 2;
		col = cart_Table.getColumnModel().getColumn(colNo);
		width = 70;
		col.setPreferredWidth(width);
		
		colNo = 3;
		col = cart_Table.getColumnModel().getColumn(colNo);
		width = 47;
		col.setPreferredWidth(width);

		cart_Table.setAutoResizeMode(cart_Table.AUTO_RESIZE_OFF);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}	
	
	}
	
	//CART TABLE DATA 불러오기 
	
	private void cartTableData() {
		CartDao CartDao = new CartDao();
		ArrayList<CartDto> dtoList = CartDao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {

			ImageIcon icon = new ImageIcon("./"+dtoList.get(i).getImagename());
			Image img = icon.getImage();
			Image changeImg = img.getScaledInstance(100,100, Image.SCALE_SMOOTH);
			ImageIcon changeIcon = new ImageIcon(changeImg);
			
			
			
			Object[] temp = { changeIcon,
					dtoList.get(i).getProname(),
					Integer.toString(dtoList.get(i).getSellprice()),						  
					Integer.toString(dtoList.get(i).getPurqty()),};
			
			outerTable.addRow(temp);
			

			
		}

	}
	
	
	//CART TABLE 클릭하였을경우 
	
	private void cartTableClick() {
	
			int i = cart_Table.getSelectedRow();
//			String tkSequence = (String) cart_Table.getValueAt(i, 1);
			String tkSequence = (String) cart_Table.getValueAt(i, 0);

			CartDao dao = new CartDao(tkSequence);
			CartDto dto = dao.cartTableClick();

			purseq = dto.getPurseq();
			custid = dto.getCustid();
			proname = dto.getProname();
	
	}
	
	
	//CART TABLE 에서 "아이템 추가하기" 눌렸을 경우 MENU 페이지로 이동한다. 
	
	private void backToMenu() {

			this.setVisible(false);
			Menu menu = new Menu();
			Menu.main(null);
				
		}
		
	//CART TABLE 에서 "결제하기" 눌렸을 경우 PURCHASE 페이지로 이동한다. 
		
	private void moveToPurchase() {	
		
		this.setVisible(false);
		Purchase purchase = new Purchase();
		Purchase.main(null);
				
	}
	
	
	//CART TABLE 에서 SWIPE 경우 PURCHASE table 에서 데이터에서 삭제 
	
	private void deleteItem() {
		
		CartDao dao = new CartDao(purseq, custid, proname);
		boolean result = dao.deleteItem();

		if (result == true) {
			JOptionPane.showMessageDialog(null, "장바구니에서 삭제되었습니다.");
		} else {
			JOptionPane.showMessageDialog(null, "입력중 문제가 발생");
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // End
