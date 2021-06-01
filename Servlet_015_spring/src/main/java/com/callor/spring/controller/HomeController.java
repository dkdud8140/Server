package com.callor.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.callor.spring.model.MyVO;
import com.callor.spring.service.HomeService;

@Controller
public class HomeController {

	/*
	 * Controller에서 HomeService 인터페이스를 상속받은
	 * MyServiceImplV1 클래스를 사용하여
	 * findById() method를 호출한 후 VO 데이터를 받고 싶다
	 * 
	 * 전통적인 Java 클래스 간의 연결은
	 * 		Interface로 객체를 선언하고 Class로 객체를 생성(초기화)하고 가지고 있다가
	 * 		필요한 메소드를 호출할 수 있도록 만들어짐
	 *
	 * 하지만 SpringFrameWork 환경에서는 필요한 객체를 미리 Spring이 만들어
	 * 		보관하고 있다가 
	 * 		필요한 곳이 있으면 그떄그떄 배분하여 사용할 수 있도록 만들어 준다
	 * 
	 * Project가 커져서 많은 객체가 필요할 떄
	 * 		전통적 프로그래밍은 모든 객체를 선언,생성하여
	 * 		가지고 있어야 하기(메모리에 보관) 때문에 
	 * 		어느정도 프로젝트가 커지면 여러가지 관리해야 할 일들이 많아진다
	 * 
	 * SpringFrameWork 환경에서는 그러한 것들을 극복하기 위하여
	 * 		미리 객체를 만들어서 Spring 컨테이너 라는 곳에 보관해두고
	 * 
	 * 		필요한 곳에 적절히 주입을 해준다
	 * 
	 * 		이러한 개념을 DI(Dependency Inject)라고 하며
	 * 		전통 방식과 반대되는 개념이어서 
	 * 		제어의 역전(IOC)라고 부른다
	 * 
	 */
	
	
	private final HomeService hS ;
	
	@Autowired
	public HomeController(HomeService hS) {
		this.hS = hS;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value="/")
	public String index() {
		return "Hello Korea";
	}
	
	
	@RequestMapping(value="/see",method=RequestMethod.GET)
	public String home(Model model) {
		
		model.addAttribute("MY",hS.findById());
		
		return "home";
	}
	
	@RequestMapping(value="/see",method=RequestMethod.POST)
	public String home(MyVO vo, Model mode) {
		System.out.println(vo.toString());
		mode.addAttribute("My",vo);
		return "home";
	}
	
	
	
}
