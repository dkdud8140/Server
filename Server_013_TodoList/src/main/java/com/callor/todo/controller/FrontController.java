package com.callor.todo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.command.HomeCommandImplV1;
import com.callor.todo.command.TodoCommand;
import com.callor.todo.command.TodoCommandImplV1;


/*
 * Servlet App에서는 Servlet(Controller) 호출하여
 * 다수 선언하고, 필요할 때마다 URI(URL)을 mapping하여
 * 기능을 수행할 수 있도록 한다
 * 
 * 하지만 
 * 프로젝트가 커지면 다수의 COntroller가 생성되고
 * 그때마다 URI mapping을 하는데 많은 어려움을 겪을 수 있다.
 * 똑같은 객체를 상속받고 코드를 같은 Method(doGet, doPost)를
 * 사용하여 코드를 작성하는데 관리가 어려워지기 시작한다.
 * 
 * URI mapping을 한 곳으로 집중하고
 * POJO (Plan Old Java Object,
 * 어떤 클래스를 상속받지 않은 일반적인 Java Class 형식의 코드)를 사용하여
 * 프로젝트를 진행하도록 하는것
 * Dispatcher Servlet Controller라고도 한다.
 */


@WebServlet("/")
public class FrontController extends HttpServlet{

	// URI mapping을 선언하여 
	// URI에 대한 객체를 준비하여 보관할 장소
	// TodoCommand 인터페이스는 Command 객체들의 Prototype으로 선언되어있고
	// 여기의 Commands mapper 객체에는
	// 여러가지 command 객체들을 저장해 둘 수 있다
	// 인터페이스르르 사용하지 않고 Object를 사용할 수 있지만
	// Object 클래스는 상대적으로 연산비용이 많이 소요되는 
	// 클래스 객체이므로 조금 더 효율적으로 사용하기 위하여
	// 인터페이스를 선언해두었다.
	protected Map<String, TodoCommand> commands ;
	
	//FrontController가 최초 호출될 떄
	// 한번 실행되어서
	// 여러가지 변수등을 초기화 하는 코드
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		commands = new HashMap<String, TodoCommand>();
		
		/*
		 * 만약 rootPath/ 로 요청이 되면 
		 * HomeCommandImplV1 객체를 사용하여
		 * 요청을 처리하기 위한 준비
		 * 
		 */
		// 대리자 등록
		commands.put("/", new HomeCommandImplV1());
		
		/*
		 * 만약
		 * http://localhost:8080/todo/insert로 요청이 오면
		 * TodoCommandImplV1 객체를 사용하여
		 * 요청을 처리하기 위한 준비
		 */
		commands.put("/insert", new TodoCommandImplV1());
		
		commands.put("/expire", new TodoCommandImplV1());
		
		
	}
	
	//doGet(), doPost()로 분리하여 요청을 처리하던 방식을
	// 한 개의 method에서 동시에 처리하기
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		// Web에서 요청한 path 가져오기
		String urlPath = req.getRequestURI();
		String path = urlPath.substring(req.getContextPath().length());
		
		// req 된 URL 중에서
		// 실제 subPath 부분을 사용하여
		// 처리할 객체를 Map으로부터 추출
		TodoCommand subCommand = commands.get(path);
		if(subCommand != null) {
			// 각 Command 객체의 execute() method에게
			// 실제 요청을 처리하도록 위임하기
			subCommand.execute(req, res);
		}
	}


	
}
