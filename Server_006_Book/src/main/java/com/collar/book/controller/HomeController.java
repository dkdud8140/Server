package com.collar.book.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.collar.book.model.BookRentDTO;
import com.collar.book.service.BookRentService;
import com.collar.book.service.impl.BookRentServiceImplV1;

@WebServlet("/")
public class HomeController extends HttpServlet{

	protected BookRentService brSer;
	
	public HomeController() {
		brSer = new BookRentServiceImplV1();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<BookRentDTO> brList = brSer.selectAll();
		
		req.setAttribute("BRLIST", brList);
		req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
	
	}
	
	
}
