package com.callor.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/welcom")
public class MainController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// Request : req, Client(Web)에서 서버에 요청하는 정보를
		//			담고있는 매개변수 (일종의 VO)
		// Response : resp, Server에서 Client로 응답을 할 때 담을 정보들이
		//			저장될 리턴 변수

		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null ;
		out = resp.getWriter();
		
		out.println("<h1>반갑습니다. 나는 Servlet입니다</h1>");
		out.println("<h3>우리 나라 만세</h3>");
	
	}

	
	
	
}
