package com.collar.book.service;

import java.sql.Connection;
import java.util.List;

import com.collar.book.model.BookRentDTO;
import com.collar.book.model.BookRentVO;

public interface BookRentService {
	
	public List<BookRentDTO> selectAll();
	
	//PK값으로 조회
	public BookRentDTO findById(Long seq);
	
	//도서코드로 대여목록 조회
	public List<BookRentDTO> findByISBN(String bk_isbn);
	
	//도서명으로 조회
	public List<BookRentDTO> findByBookName(String bTitle);
	
	//회원명으로 대여목록 조회
	public List<BookRentDTO> findByBuyerName(String bName);
	
	//회원코드로 대여목록 조회
	public List<BookRentDTO> findByBuyserCode(String bCode);

	//대여기간으로 대여목록 조회
	public List<BookRentDTO> findByTerm(String sDate, String dDate);
	
	//비반납 대여목록 조회
	//연체자목록
	public List<BookRentDTO> findByOverDue(String dDate);
	
	
	
	
	
	// Prepare...를 통해서 dql을 실행하면
	// CUR가 정상적으로 수행되면 결과값이 정수 1이상 돌아오고
	// 	그렇지 못하면 0값이 되돌아 온다.
	public void insert(BookRentVO bookRentVO);
	public void update(BookRentVO bookRentVO);
	public int delete(Long seq);
	
}
