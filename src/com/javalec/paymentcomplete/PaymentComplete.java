package com.javalec.paymentcomplete;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.account.Account;
import com.javalec.base.Main;
import com.javalec.cart.Cart;
import com.javalec.dao.PaymentCompleteDao;
import com.javalec.dto.PaymentCompleteDto;
import com.javalec.menu.Menu;


public class PaymentComplete extends JFrame {

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
	private JLabel lblPaymentMethod;
	private JLabel lblMyPoints;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JScrollPane scrollPane;
	private JTable table_Purchase;
	private JTextField tfMuy;
	private JTextField tfMyPoints;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblNewLabel_2_1_1_1_1;
	
	
	//Table 
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	private JTextField tfPaymentMethod;
	private JLabel lblTotalPrice;
	private JTextField tfTotalPrice;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentComplete frame = new PaymentComplete();
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
	public PaymentComplete() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				receiptTableInit(); 
				receiptTableData(); 
			
	
				
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		contentPane.add(getLblMyPoints());
		contentPane.add(getLblPaymentMethod());
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
		contentPane.add(getTfMyPoints());
		contentPane.add(getLblNewLabel_2_1_1_1_1());
		contentPane.add(getTfPaymentMethod());
		contentPane.add(getTfTotalPrice());
		contentPane.add(getLblTotalPrice());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(PaymentComplete.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen.setIcon(new ImageIcon(PaymentComplete.class.getResource("/com/javalec/image/아이폰 홈 화면.png")));
		}
		return lblHomeScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("12 : 00");
			lblTimer.setBounds(36, 32, 62, 21);
			lblTimer.setForeground(new Color(255, 255, 255));
			lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
			lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		}
		return lblTimer;
	}
	private JLabel getLblHome() {
		if (lblHome == null) {
			lblHome = new JLabel("");
			lblHome.setBounds(40, 600, 50, 50);
			lblHome.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					homeScreen();
				}
			});
			lblHome.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Home button.png")));
			lblHome.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHome;
	}
	private JLabel getLblMenu() {
		if (lblMenu == null) {
			lblMenu = new JLabel("");
			lblMenu.setBounds(120, 600, 50, 50);
			lblMenu.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					menuScreen();
				}
			});
			lblMenu.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Menu button.png")));
			lblMenu.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblMenu;
	}
	private JLabel getLblCart() {
		if (lblCart == null) {
			lblCart = new JLabel("");
			lblCart.setBounds(200, 600, 50, 50);
			lblCart.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					cartScreen();
				}
			});
			lblCart.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Cart button.png")));
			lblCart.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblCart;
	}
	private JLabel getLblAccount() {
		if (lblAccount == null) {
			lblAccount = new JLabel("");
			lblAccount.setBounds(280, 600, 50, 50);
			lblAccount.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					accountScreen();
				}
			});
			lblAccount.setIcon(new ImageIcon(Main.class.getResource("/com/javalec/image/Account button.png")));
			lblAccount.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblAccount;
	}
	private JLabel getLblHome1() {
		if (lblHome1 == null) {
			lblHome1 = new JLabel("Home");
			lblHome1.setBounds(44, 645, 42, 15);
			lblHome1.setFont(new Font("CookieRun", Font.BOLD, 12));
			lblHome1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHome1;
	}
	private JLabel getLblMenu1() {
		if (lblMenu1 == null) {
			lblMenu1 = new JLabel("Menu");
			lblMenu1.setBounds(124, 645, 42, 15);
			lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenu1.setFont(new Font("CookieRun", Font.BOLD, 12));
		}
		return lblMenu1;
	}
	private JLabel getLblCart1() {
		if (lblCart1 == null) {
			lblCart1 = new JLabel("Cart");
			lblCart1.setBounds(204, 645, 42, 15);
			lblCart1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCart1.setFont(new Font("CookieRun", Font.BOLD, 12));
		}
		return lblCart1;
	}
	private JLabel getLblAccount1() {
		if (lblAccount1 == null) {
			lblAccount1 = new JLabel("Account");
			lblAccount1.setBounds(276, 645, 62, 15);
			lblAccount1.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccount1.setFont(new Font("CookieRun", Font.BOLD, 12));
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
			lblNewLabel = new JLabel("결제가 완료되었습니다!");
			lblNewLabel.setBounds(59, 114, 271, 34);
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("CookieRun", Font.BOLD, 25));
		}
		return lblNewLabel;
	}
	private JLabel getLblPaymentMethod() {
		if (lblPaymentMethod == null) {
			lblPaymentMethod = new JLabel("결제수단:");
			lblPaymentMethod.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblPaymentMethod.setBounds(59, 448, 61, 16);
			lblPaymentMethod.setForeground(new Color(0, 0, 0));
		}
		return lblPaymentMethod;
	}
	private JLabel getLblMyPoints() {
		if (lblMyPoints == null) {
			lblMyPoints = new JLabel("마이포인트: ");
			lblMyPoints.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblMyPoints.setBounds(59, 485, 61, 16);
			lblMyPoints.setForeground(new Color(0, 0, 0));
		}
		return lblMyPoints;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 177, 340, 178);
			scrollPane.setViewportView(getTable_Purchase());
		}
		return scrollPane;
	}
	private JTable getTable_Purchase() {
		if (table_Purchase == null) {
			table_Purchase = new JTable();
			table_Purchase.setForeground(new Color(0, 0, 0));
			table_Purchase.setBackground(new Color(244, 208, 208));
			
			table_Purchase.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table_Purchase.setModel(outerTable);
			
		}
		return table_Purchase;
	}
	
	private JTextField getTfMyPoints() {
		if (tfMyPoints == null) {
			tfMyPoints = new JTextField();
			tfMyPoints.setHorizontalAlignment(SwingConstants.TRAILING);
			tfMyPoints.setEditable(false);
			tfMyPoints.setColumns(10);
			tfMyPoints.setBackground(new Color(244, 208, 208));
			tfMyPoints.setBounds(132, 480, 130, 26);
		}
		return tfMyPoints;
	}
	private JLabel getLblNewLabel_2_1_1_1_1() {
		if (lblNewLabel_2_1_1_1_1 == null) {
			lblNewLabel_2_1_1_1_1 = new JLabel("Pts");
			lblNewLabel_2_1_1_1_1.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblNewLabel_2_1_1_1_1.setBounds(274, 485, 35, 16);
		}
		return lblNewLabel_2_1_1_1_1;
	}
	
	private JTextField getTfPaymentMethod() {
		if (tfPaymentMethod == null) {
			tfPaymentMethod = new JTextField();
			tfPaymentMethod.setHorizontalAlignment(SwingConstants.TRAILING);
			tfPaymentMethod.setEditable(false);
			tfPaymentMethod.setColumns(10);
			tfPaymentMethod.setBackground(new Color(244, 208, 208));
			tfPaymentMethod.setBounds(132, 443, 130, 26);
		}
		return tfPaymentMethod;
	}
	private JLabel getLblTotalPrice() {
		if (lblTotalPrice == null) {
			lblTotalPrice = new JLabel("결제금액:");
			lblTotalPrice.setFont(new Font("CookieRun", Font.PLAIN, 12));
			lblTotalPrice.setForeground(Color.BLACK);
			lblTotalPrice.setBounds(59, 409, 61, 16);
		}
		return lblTotalPrice;
	}
	private JTextField getTfTotalPrice() {
		if (tfTotalPrice == null) {
			tfTotalPrice = new JTextField();
			tfTotalPrice.setHorizontalAlignment(SwingConstants.TRAILING);
			tfTotalPrice.setEditable(false);
			tfTotalPrice.setColumns(10);
			tfTotalPrice.setBackground(new Color(244, 208, 208));
			tfTotalPrice.setBounds(132, 404, 130, 26);
		}
		return tfTotalPrice;
	}
	
	//FUNCTIONS
	
	
	// TABLE 초기화
	private void purchaseTableInit() {
		
		
		// Table Column 명 정하기
				outerTable.addColumn("");
				outerTable.addColumn("");
				outerTable.addColumn("");
				outerTable.addColumn("");
				outerTable.setColumnCount(4);

		
		// Table Column 크기 정하기
				int colNo = 0;
				TableColumn col = table_Purchase.getColumnModel().getColumn(colNo);
				int width = 100;
				col.setPreferredWidth(width);
				
				colNo = 1;
				col = table_Purchase.getColumnModel().getColumn(colNo);
				width = 100;
				col.setPreferredWidth(width);
				
				colNo = 2;
				col = table_Purchase.getColumnModel().getColumn(colNo);
				width = 100;
				col.setPreferredWidth(width);
				
				colNo = 3;
				col = table_Purchase.getColumnModel().getColumn(colNo);
				width = 100;
				col.setPreferredWidth(width);

				table_Purchase.setAutoResizeMode(table_Purchase.AUTO_RESIZE_OFF);

				int i = outerTable.getRowCount();
				for (int j = 0; j < i; j++) {
					outerTable.removeRow(0);
				}		
				
	}
	


	
	//PURCHASE TABLE DATA 초기화
	
	private void receiptTableInit() {
		// Table Column 명 정하기
		outerTable.addColumn("");
		outerTable.addColumn("");
		outerTable.addColumn("");
		outerTable.setColumnCount(3);


// Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = table_Purchase.getColumnModel().getColumn(colNo);
		int width = 100;
		col.setPreferredWidth(width);
		
		colNo = 1;
		col = table_Purchase.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);
		
		colNo = 2;
		col = table_Purchase.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		table_Purchase.setAutoResizeMode(table_Purchase.AUTO_RESIZE_OFF);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}					
}
		
		

	//PURCHASE TABLE DATA 구매내역 데이터에서 불러오기 
	
	
	private void receiptTableData() {
		
//		
//		PaymentCompleteDao paymentCompleteDao = new PaymentCompleteDao();
//		ArrayList<PaymentCompleteDto> dtoList = PaymentCompleteDao.selectList();
//
//		int listCount = dtoList.size();
//
//		for (int i = 0; i < listCount; i++) {
//
//			String[] temp = { Integer.toString(dtoList.get(i).getCartseqno()), dtoList.get(i).getModelnum(),
//					Integer.toString(dtoList.get(i).getStosize()), Integer.toString(dtoList.get(i).getCartqty()),
//					Integer.toString(dtoList.get(i).getStoprice()), dtoList.get(i).getColor() 
//					
//			
//			};
//			
//			outerTable.addRow(temp);
//			
//			
			
			
			
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

} // End
