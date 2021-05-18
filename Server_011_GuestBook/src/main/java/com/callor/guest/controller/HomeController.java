package com.callor.guest.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.callor.guest.model.GuestBookVO;
import com.callor.guest.service.GuestBookService;
import com.callor.guest.service.impl.GuestBookServiceimplV1;

@WebServlet("/")
public class HomeController extends HttpServlet{
	private static final long serialVersionUID = -3449187460090115340L;

	protected GuestBookService gbS;
	
	public HomeController() {
		gbS = new GuestBookServiceimplV1();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<GuestBookVO> gbList = gbS.selectAll();
		req.setAttribute("GBLIST", gbList);
		req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
		
	}

	
	
	
}
