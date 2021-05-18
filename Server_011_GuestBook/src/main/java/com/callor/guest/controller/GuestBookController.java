package com.callor.guest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.guest.model.GuestBookVO;
import com.callor.guest.service.GuestBookService;
import com.callor.guest.service.impl.GuestBookServiceimplV1;

@WebServlet("/guest/*")
public class GuestBookController extends HttpServlet{
	private static final long serialVersionUID = 7402959432026945336L;
	protected GuestBookService gbS ;
	public GuestBookController() {
		gbS = new GuestBookServiceimplV1();
	}
	
		
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String subPath = req.getPathInfo();
		
		if(subPath.equals("/views")) {
			
			String strSeq = req.getParameter("gb_seq");
			Long gb_seq = Long.valueOf(strSeq);
			GuestBookVO gbVO = gbS.findById(gb_seq);
			
			req.setAttribute("GB", gbVO);
			req.getRequestDispatcher("/WEB-INF/views/view.jsp").forward(req, resp);
		}
		
	}
	
	
	
}
