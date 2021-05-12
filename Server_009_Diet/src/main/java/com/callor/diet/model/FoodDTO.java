package com.callor.diet.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class FoodDTO {
	
	private String fd_code; 	//	식품코드
	private String fd_name; 	//	식품명
	private String fd_year;	 	//	출시연도
	private String fd_ccode; 	//	제조사코드
	private String fd_icode; 	//	분류코드
	
	private Float fd_once; 		//	1회제공량
	private Float fd_capa; 		//	총내용량(g)
	private Float fd_kcal; 		//	에너지(㎉)
	private Float fd_protein; 	//	단백질(g)
	private Float fd_fat; 		//	지방(g)
	private Float fd_carbo; 	//	탄수화물(g)
	private Float fd_sugar; 	//	총당류(g)
	
	private String cp_name; 	//	제조사명
	private String cp_cea; 		//	대표
	private String cp_tel; 		//	대표전화
	private String cp_addr; 	//	주소
	private String cp_item; 	//	주요품목
	private String it_name; 	//	분류명

}
