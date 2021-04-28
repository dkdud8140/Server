package com.callor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * Controller  : Web
 * 
 * 
 * 
 * 
 * Request를 제일 먼저 ㅁ받은 main Class이다
   Class 이름은 특별히 중요하지 않다.
 */


//WebServlet Annotation
//지금부터 이 클래스로 생성된 객체는 Web Service에서 Request를 처리할 객체이다 라는 선언
//main() 메소드의 역할을 시행할것이다.
//실제 Request를 처리하기 위해서는 많은 코드를 작성해야 한다
// 그런데 NetWork와 관련된 코드는 너무 복잡하고 다양한 기술을 요구하기 때문에
// 코드를 직접 작성하는 것은 너무 어렵다
// java에서 제공하는 JDK를 상속받아서 대부분의 기능을 처리하고 
// 꼭 필요한 기능만 구현을 하게 된다.

// WebServlet Annotation을 가지고 있는 클래스는
// Tomcat에 의해서 관리되고
// 사용자의 Request에 따라 Tomcat이 클래스의 메소드를
// 호출하고 기능을 수행한다

//만약 사용자가 http://localhost:8080/welcome/home이라고 요청을 하면
// 1.현재 컴퓨터에서 시작한 톰캣이 이 응답을 수신한다
//	http://localhost:8080 : 톰캣을 호출하는 리퀘스트
// 2.톰캣 이후에 보내지는 문자열을 분석하기 시작한다.
//	/welcome 부분을 분석하여 Run As Server로 시작된 프로젝트 중에서
//	root Context가 welcome인 프로젝트를 찾습니다
// 3. 프로젝트 있으면 /home 문자열을 다시 분석하여 프로젝트 내의 클래스 중에서
//	@WebServlet("/home") Annotation으로 설정된 클래스를 찾는다
// 4. 클래스내의 doget(...) 메소드를 호출한다.
// 5. doGet() 메소드의 코드를 재정의(Overriding)하여 서비스를 구현하게 된다

@WebServlet("/home")
public class MainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.println("This is Main Controller");
		out.print("<a href='");
		out.print("http://localhost");
		out.print(":8080");
		out.print("/hello/book'>");
		out.print("Book으로</a>");
		out.close();
	}

	
}
