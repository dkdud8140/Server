package com.collar.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collar.book.model.BookRentDTO;
import com.collar.book.model.BookRentVO;
import com.collar.book.persistence.DBContract;
import com.collar.book.service.BookRentService;
import com.collar.book.service.impl.BookRentServiceImplV1;

/*
 * Web Browser의 Request를 처리할 클래스
 */
@WebServlet("/rent/*")
public class BookRentController extends HttpServlet{

	private static final long serialVersionUID = 172572039048068065L;
	
	protected BookRentService brSer;

	public BookRentController() {
		brSer = new BookRentServiceImplV1() ;
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String subPath = req.getPathInfo();
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		if(subPath.equals("/list")) {
			//도서대여전체목록
			brSer.selectAll();
			out.println("도서대여 전체 목록 보기");
			
		} else if(subPath.equals("/seq")){
			//주문번호로 찾기
			
			String strSeq = req.getParameter("id");
			Long nSeq = Long.valueOf(strSeq);
			BookRentDTO brDTO = brSer.findById(nSeq);
			
			// view에서 보여줄 데이터 생성
			ServletContext app = this.getServletContext();
			
			
			//brSer가 return한 brDTO를
			// app 객체에 BOOK이라는 속성변수로 셋팅하기
			app.setAttribute("BOOK", brDTO);
			
			// book.jsp 파일을 읽어서
			// app에 setting한 Book 변수와 함께 랜더링 하라
			RequestDispatcher disp = app.getRequestDispatcher("/WEB-INF/views/book.jsp");
			
			//랜더링한 view 데이터를 웹 브라우저로 response하라
			disp.forward(req, resp);
			
		} else   if(subPath.equals("/isbn")) {
			//도서코드로 찾기
			brSer.findByISBN("isbn");
			
		} else if(subPath.equals("/buyer")) {
			//회원코드로 찾기
			brSer.findByBuyserCode("buyercode");
			
			
		} else if(subPath.equals("/rent")) {
			//대여정보 추가, 대여하기
			BookRentVO bookRentVO = new BookRentVO();
			brSer.insert(bookRentVO);
			
		} else if(subPath.equals("/return")) {
			//반납하기
			BookRentVO bookRentVO = new BookRentVO();
			brSer.update(bookRentVO);
			
		} else {
			//더이상그만하기
		}
		
	}

	
	
}
