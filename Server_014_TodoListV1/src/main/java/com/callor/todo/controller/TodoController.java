package com.callor.todo.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.todo.config.DBInfo;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

@WebServlet("/sub/*")
public class TodoController extends HttpServlet{
	private static final long serialVersionUID = -3583478714364002264L;

	protected TodoService tdS ;
	public TodoController() {
		tdS = new TodoServiceImplV1();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String strSeq = req.getParameter("seq");
		
		Long td_seq = Long.valueOf(strSeq);
		
		Map<String,Object> tdVO = tdS.findById(td_seq);
		System.out.println(tdVO.toString());
		
		Object td_edate = tdVO.get(DBInfo.td_edate);
		
		if(td_edate == null || String.valueOf(td_edate).equals("")) {
			Date date = new Date(System.currentTimeMillis());
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
			
			String edate = sd.format(date);
			String etime = st.format(date);
			
			tdVO.put(DBInfo.td_edate, edate);
			tdVO.put(DBInfo.td_etime, etime);
		} else {
			tdVO.put(DBInfo.td_edate, null);
			tdVO.put(DBInfo.td_etime, null);
		}
		
		System.out.println(tdVO.toString());
		
		tdS.update(tdVO);
		System.out.println(tdVO.toString());
		
		resp.sendRedirect(req.getContextPath());
	
	}



	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String td_doit = req.getParameter("td_doit");
		
		//Map type의 VO 선언하기
		Map<String, Object> tdVO = new HashMap<String,Object>();
		tdVO.put(DBInfo.td_doit, td_doit);
		tdS.insert(tdVO);
		
		resp.sendRedirect(req.getContextPath());
	}

	
}
