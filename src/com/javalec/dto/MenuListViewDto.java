package com.javalec.dto;

import java.util.Objects;
import java.util.UUID;

// Java 16+
// DTO - record
// immutable fields
public record MenuListViewDto(
		String proName,
		String engProName,
		Integer sellPrice,
		String imageName,
		byte[] imageFile,
		String categoryName
) {
	// Compact Constructor(소괄호 없는 생성자)
	public MenuListViewDto {
		// 만약 name, nameEn, price가 필수라면
		Objects.requireNonNull(proName);
		Objects.requireNonNull(engProName);
		Objects.requireNonNull(sellPrice);
		Objects.requireNonNull(categoryName);
		
		// 조회용 DTO보다는 insert/update 용 DTO에 이런 조치:
		
		// .trim(): 앞뒤 공백 제거
		// .strip(): 앞뒤 공백 제거 + 공백처럼 생긴 모든 유니코드 문자 제거
		proName = proName.strip(); // 앞뒤 공백, 공백처럼 보이는 모든 문자 제거
		engProName = engProName.strip();
		categoryName = categoryName.strip();
		if (sellPrice < 0) {
			throw new IllegalArgumentException("가격은 0보다 크거나 같아야 합니다.");
		}
	}
}
