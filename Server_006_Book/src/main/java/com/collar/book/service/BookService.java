package com.collar.book.service;

import java.util.List;

import com.collar.book.model.BookDTO;
import com.collar.book.model.BookVO;

public interface BookService {

	public List<BookDTO> selectAll();
	public BookDTO findById(String bk_isbn);
	public List<BookDTO> findByTitle(String bi_title);
	public List<BookDTO> findByCName(String bk_cname);
	public List<BookDTO>findByAName(String bk_aname);
	
	public void insert(BookVO bookVO);
	public void update(BookVO bookVO);
	public void delete(String bk_isbn);
	
}
