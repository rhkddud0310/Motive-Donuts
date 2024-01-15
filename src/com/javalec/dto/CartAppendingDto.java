package com.javalec.dto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import com.javalec.dto.types.PurchaseGubun;

public record CartAppendingDto(
		String custid,
		String proname,
		Instant purdate,
		PurchaseGubun gubun
) {
	public CartAppendingDto {
		Objects.requireNonNull(proname);
		
		if (custid == null) {
			custid = "(noname)";
		}
		
		if (purdate == null) {
			purdate = Instant.now().truncatedTo(ChronoUnit.MILLIS);
		}
	}
}
