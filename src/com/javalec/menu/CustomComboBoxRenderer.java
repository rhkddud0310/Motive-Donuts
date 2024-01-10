/*	---------------------------------------------------------------------------------------------

		(1) Desc :	Java Swing에서 JComboBox의 선택 전에 표시되는 부분
					(Drop-down List가 열리기 전)에만 내용 표시하는 기능 구현하기.
		
		(2) Date
			1) 2024.01.10. (Ver 0.0.0.0)
			
		(3) Author : Gwangyeong Kim
		
		(4) History
			1) JPanel 생성하기.
			
			2) JLabel 생성하기.
			
			3) Database 연결 및 Image 불러오기.
				★ 'loadDataFromDatabase();' Method 생성하기.
				1. Database 연결하기.
				2. Query문 작성 및 실행하기.
				3. Image 불러오기.
					① Image Data를 Byte 배열로 가져오기.
					② Byte 배열을 Image로 변환
					③ Image를 JLabel에 표시
				4. Database와 연결 종료
				
	--------------------------------------------------------------------------------------------- */

/*	---------------------------------------------------------------------------------------------
	Java Swing에서 JComboBox의 선택 전에 표시되는 부분에만 내용을 표시하려면
	'JComboBox' Class를 확장하고 'getListCellRendererComponent' Method를
	'@Override'하여 원하는 내용을 표시할 수 있습니다.
	
	다음은 간단한 예제 코드입니다.
	--------------------------------------------------------------------------------------------- */

/*	---------------------------------------------------------------------------------------------
	아래의 예제 코드에서 'CustomComboBoxRenderer' Class는 'DefaultListCellRenderer'를 확장하고,
	'getListCellRendererComponent' Method를 '@Override'하여 원하는 내용을 설정합니다.
	이렇게 하면 JComboBox의 선택 전에 표시되는 부분에만 원하는 내용이 표시됩니다.
	--------------------------------------------------------------------------------------------- */


package com.javalec.menu;

import java.awt.Component;
import java.util.Objects;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

public class CustomComboBoxRenderer extends DefaultListCellRenderer {
	
	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        // 커스텀 로직을 이용하여 내용을 변경
        label.setText("주문할 매장을 선택해주시기 바랍니다." + Objects.toString(value, ""));

        return label;
		
		
//		if (value != null) {
//			// 여기에 원하는 내용을 표시하는 로직을 추가
//			setText("주문할 매장을 선택해주시기 바랍니다." + value.toString());
			
		}
//		return this;
	}
	
	
}
