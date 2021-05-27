package com.callor.todo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReController {
	
	public static final String prefix = "/WEB-INF/views/";
	public static final String posfix = ".jsp";
	
	/*
	 * Controller에서 전달받은 jsp 파일을
	 * forwarding 하기 위한 Command method
	 */
	public static void forward(HttpServletRequest req, HttpServletResponse res, String file) throws ServletException, IOException {
		
		String path = String.format("%s%s%s", prefix, file, posfix);
		req.getRequestDispatcher(path).forward(req, res);
		
	}

}
