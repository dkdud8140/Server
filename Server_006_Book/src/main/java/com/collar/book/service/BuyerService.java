package com.collar.book.service;

import java.util.List;

import com.collar.book.model.BuyerDTO;
import com.collar.book.model.BuyerVO;

/*
 * tbl_buyer 테이블의 CRUD
 */
public interface BuyerService {

	public List<BuyerDTO> selectAll();
	
	public BuyerDTO findById(String bu_code);
	public List<BuyerDTO> findByName(String bu_name);
	public List<BuyerDTO> findByTel(String bu_tel);

	public void insert(BuyerVO buyerVO);
	public void update(BuyerVO buyerVO);
	public void delete(String bu_code);
	
	
}

