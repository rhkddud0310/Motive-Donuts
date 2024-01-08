package com.javalec.app;

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
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class App_03 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblIPhone;
	private JLabel lblScreen;
	private JLabel lblTimer;
	private JLabel lblSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App_03 frame = new App_03();
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
	public App_03() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 375, 680);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
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
		contentPane.add(getLblSearch());
		contentPane.add(getLblScreen()); // Design-lblScreen-icon에서 사진 넣으세요
		contentPane.add(getLblIPhone());
		
	}

	private JLabel getLblIPhone() {
		if (lblIPhone == null) {
			lblIPhone = new JLabel("New label");
			lblIPhone.setIcon(new ImageIcon(App_03.class.getResource("/com/javalec/image/아이폰 테두리.png")));
			lblIPhone.setBounds(0, 0, 374, 680);
		}
		return lblIPhone;
	}
	private JLabel getLblScreen() {
		if (lblScreen == null) {
			lblScreen = new JLabel("New label");
			lblScreen.setIcon(new ImageIcon(App_03.class.getResource("/com/javalec/image/App03.png")));
			lblScreen.setBounds(8, 10, 358, 665);
		}
		return lblScreen;
	}
	private JLabel getLblTimer() {
		if (lblTimer == null) {
			lblTimer = new JLabel("");
			lblTimer.setForeground(new Color(0, 0, 0));
			lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
			lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
			lblTimer.setBounds(28, 34, 62, 21);
		}
		return lblTimer;
	}
	
	// --- Function ---
	
	// 실시간 시간 나오기
	private void updateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("h : mm");
		String currentTime = dateFormat.format(new Date());
		lblTimer.setFont(new Font("굴림", Font.BOLD, 16));
		lblTimer.setText(currentTime);
	}
	private JLabel getLblSearch() {
		if (lblSearch == null) {
			lblSearch = new JLabel("");
			lblSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					clickAction();
				}
			});
			lblSearch.setBounds(28, 65, 276, 29);
		}
		return lblSearch;
	}
	// --- Function ---
	private void clickAction() {
		this.setVisible(false);
		App_04 app_04 = new App_04();
		app_04.setVisible(true);
	}
} // End
