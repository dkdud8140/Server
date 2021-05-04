package com.collar.book.controller;

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

import com.collar.book.model.BookRentDTO;
import com.collar.book.model.BookRentVO;
import com.collar.book.model.BuyerDTO;
import com.collar.book.service.BookRentService;
import com.collar.book.service.BuyerService;
import com.collar.book.service.impl.BookRentServiceImplV1;
import com.collar.book.service.impl.BuyerServiceImplV1;

/*
 * Web Browser의 Request를 처리할 클래스
 */
@WebServlet("/rent/*")
public class BookRentController extends HttpServlet {

	private static final long serialVersionUID = 172572039048068065L;

	protected BookRentService brSer;
	protected BuyerService buSer;

	public BookRentController() {
		brSer = new BookRentServiceImplV1();
		buSer = new BuyerServiceImplV1();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// rent/*로 요청이 되면 * 위치에 부착되는 Sub 요청을 분리해낸다
		// rent/seq라고 요청을 하면
		// subPath에는 /seq라는 문자열이 담길 것이다.
		String subPath = req.getPathInfo();

		// outputStream을 사용하여 문자열 방식으로 응답하는 준비를 한
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		if (subPath.equals("/seq")) {

			// rent/seq로 요청이 들어오면

			// 주문번호로 찾기
			String strSeq = req.getParameter("id");

			if (strSeq == null || strSeq.equals("")) {
				out.println("주문번호가 없음");
				out.close();

			} else {

				Long nSeq = Long.valueOf(strSeq);
				BookRentDTO brDTO = brSer.findById(nSeq);

				// view에서 보여줄 데이터 생성
				/*
				 * ServletContext Tomcat을 기반으로 작성된 Web App Service에 요청(req), 응답(res)를 총괄하는 전보가
				 * 담긴 객체 Web App Service를 구형하기 위하여 Req, Res를 처리하는 여러가지 기능을 구현해야 하는데 그러한 기능을 미리
				 * 구현해놓았기 때문에 ServletContext를 getter하는 것만으로 충분하다.
				 * 
				 * DB 등으로 부터 조회된 데이터를 Web에게 응답하고자 할 떄 쉬운 방법으로 전달할 수 있도록 하는 기능이 이미 구현되어 있다.
				 */
				ServletContext app = this.getServletContext();

				// brSer가 return한 brDTO를
				// app 객체에 BOOK이라는 속성변수로 셋팅하기
				// app 객체에 BOOK이라는 객체 변수를 생성하고
				// BOOK 변수에 brDTO 값을 저장한다.
				// BookRentDTO BOOK = brDTO 이런 형식의 코드가 실행된다.
				// 셋팅된 BOOK 객체 변수는 jsp 파일에서 참조하여 값을 표현할 수 있따.
				app.setAttribute("BOOK", brDTO);

				// book.jsp 파일을 읽어서
				// app에 setting한 Book 변수와 함께 랜더링 하라
				// wepapp/WEB-INF/views/book.jsp 파일을 읽어서
				// JAVA 코드로 변환하고, 실행할 준비를 하라
				RequestDispatcher disp = app.getRequestDispatcher("/WEB-INF/views/book.jsp");

				// 랜더링한 view 데이터를 웹 브라우저로 response하라
				disp.forward(req, resp);

			}

		} else if (subPath.equals("/list")) {
			// 도서대여전체목록
			brSer.selectAll();
			out.println("도서대여 전체 목록 보기");

		} else if (subPath.equals("/isbn")) {
			// 도서코드로 찾기
			brSer.findByISBN("isbn");

		} else if (subPath.equals("/buyer")) {
			// 회원코드로 찾기
			brSer.findByBuyserCode("buyercode");

		} else if (subPath.equals("/order")) {
			// rent/order로 요청하면 주문서작성 처음 화면 보여주기
			// 회원이름을 입력하는 화면을 보여주기
			// ServletContext app = req.getServletContext();

			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/order.jsp");
			disp.forward(req, resp);

		} else if (subPath.equals("/order/page1")) {

			String bu_name = req.getParameter("bu_name");
			if (bu_name == null || bu_name.equals("")) {
				out.println("회원이름을 반드시 입력해야합니다.");
				out.close();
			} else {
				List<BuyerDTO> buList = buSer.findByName(bu_name);

				System.out.println("=".repeat(50));
				for (BuyerDTO d : buList) {
					System.out.println(d.toString());
				}
				System.out.println("=".repeat(50));

				ServletContext app = req.getServletContext();
				app.setAttribute("BUYERS", buList);

				RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/page1.jsp");
				disp.forward(req, resp);
			} 

		} else if(subPath.equals("/order/page2")) {
		
			String bu_code = req.getParameter("bu_code") ;
			BuyerDTO buyerDTO = buSer.findById(bu_code);
			
			ServletContext app = req.getServletContext();
			app.setAttribute("BUYER", buyerDTO);
			
			RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/page2.jsp");
			disp.forward(req, resp);
			
			
		} else if (subPath.equals("/return")) {
			// 반납하기
			BookRentVO bookRentVO = new BookRentVO();
			brSer.update(bookRentVO);

		} else {// 더이상그만하기
		}

	}

}
