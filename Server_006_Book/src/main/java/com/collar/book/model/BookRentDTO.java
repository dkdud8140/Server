package com.collar.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
주문번호	NUMBER			br_seq
회원코드	CHAR(5)			br_bcode
회원명		NVARCHAR2(50)	br_bname
회원연락처	VARCHAR2(20)	br_tel
ISBN		CHAR(13)		br_isbn
도서명		NVARCHAR2(125)	br_name
반납일		VARCHAR2(10)	br_edate
대여금		NUMBER			br_price
대여일		VARCHAR2(10)	br_sdate

select할 때 Service 각 메소드가 리턴할 타입

 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class BookRentDTO {
	
	private Long br_seq = 0L;
	private String br_bcode ;
	private String br_bname;
	private String br_tel;
	private String br_isbn;
	private String br_name;
	private String br_edate;
	private Integer br_price = 0 ;
	private String br_sdate;

	
}
