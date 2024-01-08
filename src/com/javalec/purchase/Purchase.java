package com.javalec.purchase;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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
import com.javalec.menu.Menu;


public class Purchase extends JFrame {

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
	private JLabel lblPoints;
	private JLabel lblitemPrice;
	private JTextField tfItemPrice;
	private JLabel lblNewLabel_1;
	private JTextField tfPointDiscount;
	private JLabel lblNewLabel_1_1;
	private JTextField tfTotalPrice;
	private JRadioButton rbtnCard;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField tfUsePoints;
	private JButton btnUsePoints;
	private JLabel lblNewLabel_1_1_1;
	private JTextField tfPointsGiven;
	private JButton btnCheckout;
	private JScrollPane scrollPane;
	private JTable table_Purchase;
	private JTextField tfMuy;
	private JTextField tfMyPoints;
	private JRadioButton rbtnCard2;
	private JRadioButton rbtnCard3;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_2_1;
	private JLabel lblNewLabel_2_1_1;
	private JLabel lblNewLabel_2_1_1_1;
	private JLabel lblNewLabel_2_1_1_1_1;
	
	
	//Table 
	
	private final DefaultTableModel outerTable = new DefaultTableModel();
	
	
	
	
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Purchase frame = new Purchase();
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
	public Purchase() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		setBounds(600, 100, 375, 680);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setUndecorated(true);
		contentPane.setLayout(null);
		contentPane.add(getLblitemPrice());
		contentPane.add(getLblPoints());
		contentPane.add(getLblPaymentMethod());
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblTimer());
		Timer timer = new Timer(1000, new ActionListener() {
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
		contentPane.add(getTfItemPrice());
		contentPane.add(getTfPointDiscount());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_1_1());
		contentPane.add(getTfTotalPrice());
		contentPane.add(getRbtnCard());
		contentPane.add(getTfUsePoints());
		contentPane.add(getBtnUsePoints());
		contentPane.add(getTfPointsGiven());
		contentPane.add(getLblNewLabel_1_1_1());
		contentPane.add(getBtnCheckout());
		contentPane.add(getScrollPane());
		contentPane.add(getTfMyPoints());
		contentPane.add(getRbtnCard2());
		contentPane.add(getRbtnCard3());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_2_1());
		contentPane.add(getLblNewLabel_2_1_1());
		contentPane.add(getLblNewLabel_2_1_1_1());
		contentPane.add(getLblNewLabel_2_1_1_1_1());
		contentPane.add(getLblHomeScreen());
		contentPane.add(getLblIPhone());
	}
	
	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(Purchase.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}
	private JLabel getLblHomeScreen() {
		if (lblHomeScreen == null) {
			lblHomeScreen = new JLabel("New label");
			lblHomeScreen.setBounds(8, 10, 358, 665);
			lblHomeScreen.setIcon(new ImageIcon(Purchase.class.getResource("/com/javalec/image/아이폰 홈 화면.png")));
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
			lblHome1.setFont(new Font("굴림", Font.BOLD, 12));
			lblHome1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblHome1;
	}
	private JLabel getLblMenu1() {
		if (lblMenu1 == null) {
			lblMenu1 = new JLabel("Menu");
			lblMenu1.setBounds(124, 645, 42, 15);
			lblMenu1.setHorizontalAlignment(SwingConstants.CENTER);
			lblMenu1.setFont(new Font("굴림", Font.BOLD, 12));
		}
		return lblMenu1;
	}
	private JLabel getLblCart1() {
		if (lblCart1 == null) {
			lblCart1 = new JLabel("Cart");
			lblCart1.setBounds(204, 645, 42, 15);
			lblCart1.setHorizontalAlignment(SwingConstants.CENTER);
			lblCart1.setFont(new Font("굴림", Font.BOLD, 12));
		}
		return lblCart1;
	}
	private JLabel getLblAccount1() {
		if (lblAccount1 == null) {
			lblAccount1 = new JLabel("Account");
			lblAccount1.setBounds(276, 645, 62, 15);
			lblAccount1.setHorizontalAlignment(SwingConstants.CENTER);
			lblAccount1.setFont(new Font("굴림", Font.BOLD, 12));
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
			Purchase cart = new Purchase();
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
			lblNewLabel = new JLabel("주문하기");
			lblNewLabel.setBounds(17, 83, 141, 34);
			lblNewLabel.setForeground(new Color(0, 0, 0));
			lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 24));
		}
		return lblNewLabel;
	}
	private JLabel getLblPaymentMethod() {
		if (lblPaymentMethod == null) {
			lblPaymentMethod = new JLabel("결제수단");
			lblPaymentMethod.setBounds(29, 305, 61, 16);
			lblPaymentMethod.setForeground(new Color(0, 0, 0));
		}
		return lblPaymentMethod;
	}
	private JLabel getLblPoints() {
		if (lblPoints == null) {
			lblPoints = new JLabel("마이포인트: ");
			lblPoints.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
			lblPoints.setBounds(29, 364, 61, 16);
			lblPoints.setForeground(new Color(0, 0, 0));
		}
		return lblPoints;
	}
	private JLabel getLblitemPrice() {
		if (lblitemPrice == null) {
			lblitemPrice = new JLabel("상품금액 : ");
			lblitemPrice.setBounds(29, 428, 61, 16);
			lblitemPrice.setForeground(new Color(0, 0, 0));
		}
		return lblitemPrice;
	}
	private JTextField getTfItemPrice() {
		if (tfItemPrice == null) {
			tfItemPrice = new JTextField();
			tfItemPrice.setBackground(new Color(244, 208, 208));
			tfItemPrice.setBounds(200, 423, 102, 26);
			tfItemPrice.setColumns(10);
		}
		return tfItemPrice;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("할인금액:");
			lblNewLabel_1.setBounds(29, 459, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfPointDiscount() {
		if (tfPointDiscount == null) {
			tfPointDiscount = new JTextField();
			tfPointDiscount.setBackground(new Color(244, 208, 208));
			tfPointDiscount.setColumns(10);
			tfPointDiscount.setBounds(200, 454, 102, 26);
		}
		return tfPointDiscount;
	}
	private JLabel getLblNewLabel_1_1() {
		if (lblNewLabel_1_1 == null) {
			lblNewLabel_1_1 = new JLabel("결제금액:");
			lblNewLabel_1_1.setBounds(29, 487, 61, 16);
		}
		return lblNewLabel_1_1;
	}
	private JTextField getTfTotalPrice() {
		if (tfTotalPrice == null) {
			tfTotalPrice = new JTextField();
			tfTotalPrice.setBackground(new Color(244, 208, 208));
			tfTotalPrice.setColumns(10);
			tfTotalPrice.setBounds(200, 482, 102, 26);
		}
		return tfTotalPrice;
	}
	private JRadioButton getRbtnCard() {
		if (rbtnCard == null) {
			rbtnCard = new JRadioButton("카드결제");
			buttonGroup_1.add(rbtnCard);
			rbtnCard.setSelected(true);
			rbtnCard.setBounds(39, 330, 76, 23);
		}
		return rbtnCard;
	}
	private JTextField getTfUsePoints() {
		if (tfUsePoints == null) {
			tfUsePoints = new JTextField();
			tfUsePoints.setColumns(10);
			tfUsePoints.setBackground(new Color(244, 208, 208));
			tfUsePoints.setBounds(90, 392, 130, 26);
		}
		return tfUsePoints;
	}
	private JButton getBtnUsePoints() {
		if (btnUsePoints == null) {
			btnUsePoints = new JButton("사용");
			btnUsePoints.setBounds(218, 392, 117, 29);
		}
		return btnUsePoints;
	}
	private JLabel getLblNewLabel_1_1_1() {
		if (lblNewLabel_1_1_1 == null) {
			lblNewLabel_1_1_1 = new JLabel("포인트적립:");
			lblNewLabel_1_1_1.setBounds(29, 515, 61, 16);
		}
		return lblNewLabel_1_1_1;
	}
	private JTextField getTfPointsGiven() {
		if (tfPointsGiven == null) {
			tfPointsGiven = new JTextField();
			tfPointsGiven.setColumns(10);
			tfPointsGiven.setBackground(new Color(244, 208, 208));
			tfPointsGiven.setBounds(200, 510, 102, 26);
		}
		return tfPointsGiven;
	}
	private JButton getBtnCheckout() {
		if (btnCheckout == null) {
			btnCheckout = new JButton("결제하기");
			btnCheckout.setBounds(120, 548, 117, 29);
		}
		return btnCheckout;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 115, 340, 178);
			scrollPane.setViewportView(getTable_Purchase());
			purchaseTableInit(); 
			purchaseTableData(); 
			myPoints(); 
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
			tfMyPoints.setEditable(false);
			tfMyPoints.setColumns(10);
			tfMyPoints.setBackground(new Color(244, 208, 208));
			tfMyPoints.setBounds(90, 359, 130, 26);
		}
		return tfMyPoints;
	}
	private JRadioButton getRbtnCard2() {
		if (rbtnCard2 == null) {
			rbtnCard2 = new JRadioButton("간편결제");
			buttonGroup_1.add(rbtnCard2);
			rbtnCard2.setBounds(132, 330, 88, 23);
		}
		return rbtnCard2;
	}
	private JRadioButton getRbtnCard3() {
		if (rbtnCard3 == null) {
			rbtnCard3 = new JRadioButton("카카오페이");
			buttonGroup_1.add(rbtnCard3);
			rbtnCard3.setBounds(228, 330, 88, 23);
		}
		return rbtnCard3;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("원");
			lblNewLabel_2.setBounds(305, 428, 35, 16);
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_2_1() {
		if (lblNewLabel_2_1 == null) {
			lblNewLabel_2_1 = new JLabel("원");
			lblNewLabel_2_1.setBounds(305, 459, 35, 16);
		}
		return lblNewLabel_2_1;
	}
	private JLabel getLblNewLabel_2_1_1() {
		if (lblNewLabel_2_1_1 == null) {
			lblNewLabel_2_1_1 = new JLabel("원");
			lblNewLabel_2_1_1.setBounds(305, 487, 35, 16);
		}
		return lblNewLabel_2_1_1;
	}
	private JLabel getLblNewLabel_2_1_1_1() {
		if (lblNewLabel_2_1_1_1 == null) {
			lblNewLabel_2_1_1_1 = new JLabel("Pts");
			lblNewLabel_2_1_1_1.setBounds(303, 515, 35, 16);
		}
		return lblNewLabel_2_1_1_1;
	}
	private JLabel getLblNewLabel_2_1_1_1_1() {
		if (lblNewLabel_2_1_1_1_1 == null) {
			lblNewLabel_2_1_1_1_1 = new JLabel("Pts");
			lblNewLabel_2_1_1_1_1.setBounds(227, 364, 35, 16);
		}
		return lblNewLabel_2_1_1_1_1;
	}
	
	//FUNCTIONS
	
	
	//PURCHASE TABLE 초기화
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
	

	
	//PURCHASE TABLE DATA 불러오기 
	
	private void purchaseTableData() {
		PurchaseDao PurchaseDao = new PurchaseDao();
		ArrayList<PurchaseDto> dtoList = PurchaseDao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {

			String[] temp = { dtoList.get(i).getImage(),
							  dtoList.get(i).getProname(),
							  Integer.toString(dtoList.get(i).getSellprice()),						  
							  Integer.toString(dtoList.get(i).getPurqty()), };
			outerTable.addRow(temp);
		}

	}
	
	
	//마이 포인트 표시 
	
	private void myPoints() {
		
		int point = 0;

		PurchaseDao dao = new PurchaseDao(point);
		PurchaseDto dto = dao.myPoints();

		tfMyPoints.setText(Integer.toString(dto.getPoint()));
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} // End
