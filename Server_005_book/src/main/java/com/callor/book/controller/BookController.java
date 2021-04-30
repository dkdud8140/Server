package com.callor.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.book.model.BookDTO;
import com.callor.book.model.BookVO;
import com.callor.book.service.BookService;
import com.callor.book.service.impl.BookServiceImplV1;

// loaclhost:8080/book/book/~~ 로 요청을 하면
// 여기에서 처리하겠다
@WebServlet("/book/*")
public class BookController extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private BookService bSer;
	
	
	public BookController() {
		bSer = new BookServiceImplV1();
	}
	
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// ~~로 되어있는 문자열을 추출하기
		//localhost:8080/book/book/input 로 요청하면
		// input 문자열 추출
		String subPath = req.getPathInfo();
	
		//요청에 응답하기 위한 준비
		//한글인코딩 셋팅
		resp.setContentType("text/html;charset=UTF-8");
		//데이터를 보낼 출력장치 설정
		PrintWriter out = resp.getWriter();
		
		if(subPath.equals("/input")) {
			
			// 포장 풀어서 내용물 확인
			String bk_isbn = req.getParameter("bk_isbn");
			String bk_title = req.getParameter("bk_title");
			String bk_price = req.getParameter("bk_price");
			
			BookVO bookVO = new BookVO();
			
			bookVO.setBk_isbn(bk_isbn);
			bookVO.setBk_title(bk_title);
			bookVO.setBk_price(Integer.valueOf(bk_price));
			
			bookVO.setBk_ccode("C0001");
			bookVO.setBk_acode("A0001");
			bookVO.setBk_date("2021-04-30");
			
			
			
			

			bSer.insert(bookVO);
			
			out.println("받은 데이터 확인");
			out.printf("ISBN : %s\t", bk_isbn);
			out.printf("도서명 : %s\t", bk_title);
			out.printf("가격 : %s\n", bk_price);
			out.close();
		} else if(subPath.equals("/select")) {
			List<BookDTO> bookList = bSer.selectAll();
		} else if(subPath.equals("/isbn")) {
			
			String bk_isbn = req.getParameter("bk_isbn");
			BookDTO bookDTO = bSer.findById(bk_isbn);
			
			ServletContext app = this.getServletContext();
			app.setAttribute("BOOK", bookDTO);
			
			RequestDispatcher disp = app.getRequestDispatcher("/WEB-INF/views/book.jsp");
			
			disp.forward(req, resp);
			
			
		}
		else {
			out.println("오류");
			out.close();
		}
		
	}

	
}
