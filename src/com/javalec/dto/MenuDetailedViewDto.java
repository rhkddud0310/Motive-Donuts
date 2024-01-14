package com.javalec.dto;

public record MenuDetailedViewDto(
		String proName,
		String engProName,
		Integer sellPrice,
		String detail,
		String nutritional,
		String ingredient,
		byte[] imageFile,
		String imageName,
		String categoryName
) {

}
