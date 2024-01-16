package com.javalec.account;

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

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.javalec.common.ShareVar;
import com.javalec.dao.MyOrderListDao;
import com.javalec.dto.MyOrderListDto;

public class MyOrderList extends JFrame {
	// --------------------------------------------------------------//
	// Desc : 구매내역 불러오기
	// Date : 2024.01.08(Ver1.0.0)
	// 2024.01.15(Ver1.0.1
	// Author : Daegeun Lee
	// History : 1. ID&PW를 받아서 DB에 있는 데이터와 비교한뒤 true, false로 체크한다
	// 2. 정규식으로 예외처리한다
	// 3. 수정일 추가
	// --------------------------------------------------------------//

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblScreen;
	private JLabel lblTimer;
	private JScrollPane scrollPane;
	private JTable innerTable;
	private JLabel lblOK;
	// -- Table
	private final DefaultTableModel outerTable = new DefaultTableModel();

	String custId = ShareVar.loginID;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyOrderList frame = new MyOrderList();
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
	public MyOrderList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 375, 680);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				tableInit();
				tableData();

			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
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
		contentPane.add(getScrollPane());
		contentPane.add(getLblOK());
		contentPane.add(getLblScreen()); // Design-lblScreen-icon에서 사진 넣으세요
		contentPane.add(getLblIPhone());

	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setBounds(0, 0, 374, 680);
			lblIPhone.setIcon(new ImageIcon(MyOrderList.class.getResource("/com/javalec/image/아이폰 테두리.png")));
		}
		return lblIPhone;
	}

	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("New label");
			lblScreen.setBounds(8, 10, 358, 665);
			lblScreen.setIcon(new ImageIcon(MyOrderList.class.getResource("/com/javalec/image/MyOrders.png")));
		}
		return lblScreen;
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

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(36, 213, 299, 371);
			scrollPane.setViewportView(getInnerTable());
		}
		return scrollPane;
	}

	private JTable getInnerTable() {
		if (innerTable == null) {
			innerTable = new JTable();
			innerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// **************************************************
			innerTable.setModel(outerTable);// **********넣어주기
			// **************************************************
		}
		return innerTable;
	}

	private JLabel getLblOK() {
		if (lblOK == null) {
			lblOK = new JLabel("");
			lblOK.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					okAction();
				}
			});
			lblOK.setBounds(133, 606, 112, 28);
		}
		return lblOK;
	}
	// --- Function ---

	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}

	// MyPage 이동
	private void okAction() {
		this.setVisible(false);
		Account account = new Account();
		account.setVisible(true);
	}

	// PURCHASE TABLE 초기화
	private void tableInit() {

		// Table Column 명 정하기
		outerTable.addColumn("상품명");
		outerTable.addColumn("결제수단");
		outerTable.addColumn("결제가격");
		outerTable.addColumn("사용한 포인트");
		outerTable.addColumn("적립 포인트");
		outerTable.addColumn("구매 날짜");
		outerTable.setColumnCount(6);

		// Table Column 크기 정하기
		int colNo = 0;
		TableColumn col = innerTable.getColumnModel().getColumn(colNo);
		int width = 80;
		col.setPreferredWidth(width);

		colNo = 1;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 30;
		col.setPreferredWidth(width);

		colNo = 2;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 70;
		col.setPreferredWidth(width);

		colNo = 3;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 60;
		col.setPreferredWidth(width);

		colNo = 4;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 60;
		col.setPreferredWidth(width);

		colNo = 5;
		col = innerTable.getColumnModel().getColumn(colNo);
		width = 100;
		col.setPreferredWidth(width);

		innerTable.setAutoResizeMode(innerTable.AUTO_RESIZE_OFF);

		int i = outerTable.getRowCount();
		for (int j = 0; j < i; j++) {
			outerTable.removeRow(0);
		}
	}

	private void tableData() {

//		int listCount = myOrderList.size();
//
//		for (int i = 0; i < listCount; i++) {
//			MyOrderListDto myOrderList = MyOrderList.get(i);
//			
//			ArrayList<Dto> dtoList = dao.selectList();
//			
//			int listCount = dtoList.size();
//			
//			for(int i=0; i<listCount; i++) {
//				String temp = Integer.toString(dtoList.get(i).getSeqno());
//				String[] qTxt = {temp, dtoList.get(i).getName(), 
//						  		 	   dtoList.get(i).getTelno(), 
//						  		 	   dtoList.get(i).getRelation()};
//				outerTable.addRow(qTxt);
//		}

		MyOrderListDao myOrderListDao = new MyOrderListDao();
		ArrayList<MyOrderListDto> dtoList = myOrderListDao.selectList();

		int listCount = dtoList.size();

		for (int i = 0; i < listCount; i++) {
	        String temp = dtoList.get(i).getProname();
	        String[] qTxt = { temp, dtoList.get(i).getPayment(), String.valueOf(dtoList.get(i).getPayprice()),
	                String.valueOf(dtoList.get(i).getSpendpoints()), String.valueOf(dtoList.get(i).getAccupoints()),
	                String.valueOf(dtoList.get(i).getOrderdate()) };
	        outerTable.addRow(qTxt);
	    }
//		for (int i = 0; i < listCount; i++) {
//			String temp = dtoList.get(i).getProname();
//			String[] qTxt = { temp, dtoList.get(i).getPayment(), dtoList.get(i).getPayprice(),
//					dtoList.get(i).getSpendpoints(), dtoList.get(i).getOrderdate() };
//			outerTable.addRow(qTxt);
//		}
	}

} // End
