package com.callor.todo.command;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

public class HomeCommandImplV1 implements TodoCommand {

	protected static final Logger log = LoggerFactory.getLogger("Home");
	
	/*
	 * (서비스) 등의 클래스를 객체로 생성하여
	 * 클래스 내부의 여러 method에서 공용으로 사용할 경우
	 * 객체를 클래스의 맴버 영역에 선언한다
	 * 이 때 객체의 접근제한자를 protected로 하면
	 * 		이 클래스를 상속받는 곳에서도 공용으로 사용할 수 있다.
	 * 이러한 코드가 상당히 잇점은 있는데
	 * 		간혹 메모리 leak(누수)가 발생할 수 있다.
	 * 		이럴 때는 객체를 final로 선언해 주는 것도
	 * 		한 가지 방법이다.
	 * 단, 객체를 final로 선언할 경우는 
	 * 		반드시 생성자에서 초기화를 해주어야 한다.
	 * 
	 */
	protected final TodoService tdS ;
	public HomeCommandImplV1() {
		tdS = new TodoServiceImplV1();
	}
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		List<Map<String, Object>> tdList = tdS.selectAll();
		
		log.debug("SELECT {}", tdList.toString());
		
		req.setAttribute("TDLIST", tdList);
		
		ReqCommand.forword(req, res, "home");
	}

}
