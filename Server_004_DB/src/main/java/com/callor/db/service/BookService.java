package com.callor.db.service;

import java.util.List;

import com.callor.db.model.BookVO;

/*
 *  업무적인 분석이 없이 만드는 DB 프로젝트에서는
 *  필수적으로 필요한 method가 있다
 *  
 *  DB와 연동되는 프로젝트는 1개의 table마다
 *  서비스가 만들어지는데 CRUD를 최소한 구현해야 한다.
 *  
 *  
 *  
 */
public interface BookService {

	
	/*
	 * Web Browser에서 데이터를 보내면
	 * Controller에서 데이터를 전달 받고
	 * Service 클래스에 데이터를 전달하여
	 * DB에 insert를 수행한다.
	 */
	public void insert(BookVO bookVO);	// creat : 데이터를 추가
	
	/*
	 * 필수적인 select()메소드
	 * Web에서 데이터를 보여달라는 요청이 있으면
	 * Controller에서 Service의 select() 메소드를 호출할 것이다
	 * 
	 * select() 메소드는
	 * DB로부터 데이터를 읽어서 Controller에게 return
	 */
	public List<BookVO> selectAll();	// read : 데이터 조회
	
	/*
	 * 필수적인 select()메소드
	 * Controller로부터 PK칼럼(bk_isbn)에 해당하는
	 * 값 한개를 전달받고
	 *  
	 * PK 칼럼에 조건을 1개만 부여하여 select 한 후
	 * 데이터를 return하는 메소드
	 */
	public BookVO findById(String bk_isbn);
	
	//선택적
	//도서명으로 검색
	// 도서명을 전달받아서 해당 도서명의 모든 데이터를 return
	public List<BookVO> findByTitle(String bk_title);
	
	//출판사코드로 검색
	public List<BookVO> findByComp(String bk_comp);
	//저자코드로 검색
	//발행일로 검색
	//가격으로 검색
	
	
	/*
	 * 변경하고자 하는 데이터를 전달받아서
	 * 		(전달받은 데이터에는 반드시 PK값이 포함되어 있어야 한다.)
	 * PK로 조건을 설정하고
	 * 데이터를 UPDATE
	 */
	public void update(BookVO bookVO);	// update : 데이터 변경, 갱신, 수정
	
	/*
	 * 삭제하고자 하는 PK값을 매개변수로 전달받고
	 * 한 개의 데이터를 삭제한다.
	 * 
	 * 두 개 이상의 데이터를 삭제하는 코드는 웬만하면 사용하지 않는다
	 */
	public void delete(String bk_isbn);	// delete : 데이터 삭제
}
