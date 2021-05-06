package com.collar.book.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.collar.book.model.BookRentDTO;
import com.collar.book.model.BookRentVO;
import com.collar.book.persistence.DBContract;
import com.collar.book.service.BookRentService;

public class BookRentServiceImplV1 implements BookRentService {
	

	protected static Connection dbConn ;

	public BookRentServiceImplV1() {
			dbConn = DBContract.getDBConnection();
	}
	
	/*
	 * exception 처리 방법 2가지
	 * 1. try-catch를 사용하기
	 * 		exeption이 발생할 코드를 감싸서
	 * 		"직접" exeption을 처리하기
	 * 2. 떠넘기기(던지기)
	 * 		method throws를 추가하여
	 * 		호출한 method에게 exeption을 되돌려 보내기
	 * 		현재 메소드에서 직접 익셉션을 처리하는 코드가 없어서 
	 * 		코드가 다소 간소화 된다.
	 * 
	 * 		호출한 곳에서 대신 익셉션을 처리한다.
	 * 
	 */
	
	
	
	
	protected List<BookRentDTO> select(PreparedStatement pStr) throws SQLException {
		
		List<BookRentDTO> brList = new ArrayList<BookRentDTO>() ;
		
		ResultSet rStr = pStr.executeQuery();
		
		while(rStr.next()) {
			
			BookRentDTO brDTO = new BookRentDTO(); 
			brDTO.setBr_seq(rStr.getLong("구분번호"));
			brDTO.setBr_sdate(rStr.getString("대여일"));
			
			brDTO.setBr_bcode(rStr.getString("회원코드"));
			brDTO.setBr_bname(rStr.getString("회원명"));
			brDTO.setBr_tel(rStr.getString("회원연락처"));
			
			brDTO.setBr_isbn(rStr.getString("ISBN"));
			brDTO.setBr_name(rStr.getString("도서명"));
			
			brDTO.setBr_price(rStr.getInt("대여금"));
			brDTO.setBr_edate(rStr.getString("반납일"));
			
			brList.add(brDTO) ;
		}
		
		return brList ;
		
	}
	
	

	@Override
	public List<BookRentDTO> selectAll() {
		// TODO 전체리스트
		
		String sql = " SELECT * FROM view_도서대여정보 " ;
		
		PreparedStatement pStr = null ;
		
		try {
			pStr = dbConn.prepareCall(sql);
			List<BookRentDTO> brList = this.select(pStr);
			pStr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public BookRentDTO findById(Long seq) {
		// TODO PK로 조회하기
		
		/*
		DB INJECTION 공격에 매우 취약한 코드
		만약 매개변수로 직접 SQL 명령문을 다음과 같이 만들면
		"WHERE 도서명 = " "+ TITLE ;
		TITLE 변수값에 1 OR 1 = 1 과 같은 문자열을 담아서 보내면
		실제 쿼리는 WHERE 도서명 = 1 OR 1 1= 1 과 같이 만들어져 보인다.
		
		Prepare... 를 사용할 때는 조건문의 변수가 포함 될 위치에
		? 기호를 사용한다
		where 도서명 = ? 
		*/
		String sql = " SELECT * FROM view_도서대여정보 " ;
		sql += " WHERE 구분번호 = ? " ;
		
		PreparedStatement pStr = null ;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setLong(1, seq);
			
			// PK값으로 검색해서 List에는 1번밖에 데이터가 없다
			// List의 0번 데이터만 getter하여 DTO에 담기
//			BookRentDTO brDTO = this.select(pStr).get(0);
			
			List<BookRentDTO>brList = this.select(pStr);
			BookRentDTO brDTO = null ;
			if(brList != null && brList.size()>0) {
				brDTO = brList.get(0);
			}
			
			pStr.close();
			return brDTO ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<BookRentDTO> findByISBN(String bk_isbn) {
		// TODO ISBN으로 조회
		
		String sql = " SELECT * FROM view_도서대여정보 " ;
		sql += " WHERE ISBN = ? " ;
		
		PreparedStatement pStr = null ;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bk_isbn);

			List<BookRentDTO> brList = this.select(pStr);
			
			pStr.close();
			
			return brList ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
	@Override
	public List<BookRentDTO> findByBookName(String bTitle) {
		// TODO 도서명으로 조회
		
		String sql = " Select * from view_도서대여정보 " ;
		sql += " where 도서명 like '%'|| ? || '%' ";
		
		PreparedStatement pStr = null ;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1,  bTitle);

			List<BookRentDTO> brList = this.select(pStr);
			
			pStr.close();
			
			return brList ;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<BookRentDTO> findByBuyerName(String bName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookRentDTO> findByBuyserCode(String bCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookRentDTO> findByTerm(String sDate, String dDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookRentDTO> findByOverDue(String dDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BookRentVO bookRentVO) {
		// TODO 도서 대여 정보 저장.추가
		
		//BookRentVO bookRentVO = new BookRentVO() ;
		
		String sql = " INSERT INTO tbl_book_rent ";
		sql += " (br_seq, br_sdate, br_isbn, br_bcode,br_price) ";
		sql += "  VALUES(seq_book_rent.NEXTVAL, ?, ?, ?, ? ) " ;
		
		PreparedStatement pStr = null ;
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bookRentVO.getBr_sdate());
			pStr.setString(2, bookRentVO.getBr_isbn());
			pStr.setString(3, bookRentVO.getBr_bcode());
			pStr.setInt(4, bookRentVO.getBr_price());
			//인설트,업데이트,델리트와 같은 SQL은
			//executeUpdate 메소드로 처리
			//정상적으로 SQL이 성공하면 result에는 0보다 큰 값이 담긴다.
			int result = pStr.executeUpdate();
			pStr.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return 0 ;
		

	}

	@Override
	public void update(BookRentVO bookRentVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public int delete(Long seq) {
		// TODO Auto-generated method stub
		return 0;
	}

}
