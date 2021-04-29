package com.callor.db.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.db.model.BookVO;
import com.callor.db.service.BookService;
import com.callor.db.service.impl.BookServiceImplV1;

// localhost:8080/db/book 이라고 요청을 하면
//					(URI) 
// localhost:8080 = Tomcat을 호출하는 부분 URL
// /db : ContextRoot = 프로젝트의 별명
// /book : 요청 path
// ?변수=값 : Query 요청 값
@WebServlet("/book/*")
public class BookController extends HttpServlet{

	private BookService bSer;
	
	public BookController() {
		bSer = new BookServiceImplV1();
	}
	/*
	 * WAS 관련 프로젝트를 수행할 떄
	 * 클래스를 만들고 서버를 한 번 Run한 후에
	 * 다시 클래스 코드를 변경하면
	 * Tomcat이 자동으로 재 시작된다
	 * 그 때 Tomcat이 내부적으로 클래스의 정보를 참조하는 
	 * 일종의 key 값
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		 
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		/*
		 db/book/*의 pattern으로 요청을 하면
		 * 부분에 추가된 sub Path를 추출하여 여러가지 요청을 처리할 수 있다.
		 이 때 추가된 sub Path를 추출할 때는 req.getPathInfo()를 사용하여 추출한다
		*/
		String subPath = req.getPathInfo();
		if(subPath.equals("/select")){
			List<BookVO> bookList = bSer.selectAll();
			out.println(bookList.toString());
		} 
		
		
		else if(subPath.equals("/find")) {
		
			String isbn = req.getParameter("isbn");
			BookVO bookVO = bSer.findById(isbn);
			
			if(bookVO == null) {
				out.print("찾는 데이터 없음") ;
			} else
			out.println(bookVO.toString());
		}
		
		
		out.println(subPath);
		out.close();
		
	}// end doget
	

	
	private void selectAll() {
		List<BookVO> bookList = bSer.selectAll();
		
		//resp.setContentType("text/html;charset=UTF-8");
		
		//Integer.valueOf("A0001");
		
		PrintWriter out = null ;
		out.println("WELCOME TO MY HOME");
		//out.println(req.getContextPath());
		
		for(BookVO vo : bookList) {
			
			out.print("<p>");
			out.print(vo.getBk_isbn());
			out.print(vo.getBk_title());
			out.print(vo.getBk_ccode());
			out.print(vo.getBk_acode());
			out.print(vo.getBk_date());
			out.print(vo.getBk_pages());
			out.println(vo.getBk_price());
		}
		
		
		out.close();

	}
	
	
	private void callService() {
		
		BookVO bookVO = new BookVO();
		bookVO.setBk_title("자바입문");
		bookVO.setBk_ccode("easy");
		bookVO.setBk_acode("박은종");

		//데이터 추가
		bSer.insert(bookVO);
		//전체리스트
		List<BookVO> bookList = bSer.selectAll();
		
		//도서정보 1개 조회
		BookVO retVO = bSer.findById("970000000");
		
		//도서정보 변경
		bSer.update(bookVO);
		
		//도서정보 데이터 1개 삭제
		bSer.delete("970000000");

	}
	
	
		
}
