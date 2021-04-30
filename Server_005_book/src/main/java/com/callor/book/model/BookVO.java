package com.callor.book.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@AllArgsConstructor		//모든 데이터를 매개변수로 받는 생성자
@NoArgsConstructor		//매개변수가 없는 생성자

public class BookVO {

	private String bk_isbn;
	private String bk_title;
	private String bk_ccode;
	private String bk_acode;
	private String bk_date;
	
	private Integer bk_price = 0 ;
	private Integer bk_pages = 0 ;
	
}
