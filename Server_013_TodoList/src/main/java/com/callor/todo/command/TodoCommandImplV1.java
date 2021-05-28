/**
 * 
 */
package com.callor.todo.command;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.callor.todo.config.DBInfo;
import com.callor.todo.service.TodoService;
import com.callor.todo.service.impl.TodoServiceImplV1;

public class TodoCommandImplV1 implements TodoCommand {

	// lpgger 객체인 log를 선언하고
	// 생성을 할 때 관리자이름으로 TODO를 부착하라
	// TODO라는 이름으로 Logger를 싱글톤으로 생성하라
	// foctory 패턴
	// 객체를 생성한느 클래스.method()를 준비해두고
	// 필요에 따라 생성된 객체를 제공받는 패턴
	private static final Logger log = LoggerFactory.getLogger("TODO");
	
	private TodoService tdS ;
	public TodoCommandImplV1() {
		tdS = new TodoServiceImplV1();
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String td_doit = req.getParameter("doit");
		String td_seq = req.getParameter("seq");

		//Server App에서  System.out.println() 대신
		// 사용할 console 출력 method
		log.debug(td_doit);
		log.debug(td_seq);
		
		//Map으로 만든 동적(Dynamic) vo
		// value를 Object로 만든 이유
		// table에서 데이터를 select 하거나
		//	INSERT, UPDATE를 수행할 떄
		// 	각 칼럼의 Data Type이 다양한 type으로 구성되어 있기 때문에
		// 	미리 어떤 타입으로 지정하기가 어려워
		// 	Object 타입으로 지정한다
		Map<String, Object> tdVO = null ;
		
		// 최초로 TODO 추가하는 날짜, 시각을 자동생성
		Date date = new Date(System.currentTimeMillis());
		
		// 현재 시스템의 날짜를 가져오기
		// 날짜를 문자열로 변환하기 위하여 format을 생성하고
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat st = new SimpleDateFormat("HH:mm:ss");
		
		// 생성된 format을 사용하여 날짜와 시각 데이터를
		//문자열로 변환하여 변수에 저장
		String curDate = sd.format(date);
		String curTime = st.format(date);

		//request로부터 URI 정보를 추출하기
		String uriPath = req.getRequestURI();
		log.debug("URL {}", uriPath);
		
		//rootPath == contextRootPath == contextPath
		String rootPath = req.getContextPath();
		//문자열.substring(어디서부터) : 어디서부터 ~ 끝까지
		//uriPath에서 rootPath를 제외한 추출하여 달라
		String path = uriPath.substring(rootPath.length());
		
		log.debug("PATH : {}", path);
		
		if(path.equals("/insert")) {
			tdVO = new HashMap<String, Object>();
			
			tdVO.put(DBInfo.td_sdate, curDate);
			tdVO.put(DBInfo.td_stime, curTime);
			tdVO.put(DBInfo.td_doit, td_doit);
			
			log.debug("VO 데이터 {}", tdVO.toString());
			
			// insert로부터 전달받은 숫자
			// 0보다 크면 정상 insert 되었고
			// 그렇지 않으면 정상작동하지 않은 것
			Integer ret = tdS.insert(tdVO);

		} else if(path.equals("/expire")) {
			Long seq = Long.valueOf(td_seq);
			tdVO = tdS.findById(seq);
			log.debug("Find By Id {}", tdVO.toString());
			
			//현재 조회된 TO DO 정보에서 만료일자를 검사하여
			// null이거나 ""이면
			Object e_date = tdVO.get(DBInfo.td_edate);
			if( e_date == null || e_date.equals("") ) {
				tdVO.put(DBInfo.td_edate, curDate);
				tdVO.put(DBInfo.td_etime, curTime);
			}
			
			//값이 있으면
			else {
				tdVO.put(DBInfo.td_edate, null);
				tdVO.put(DBInfo.td_etime, null);
			}
		
			log.debug("after set {}", tdVO.toString());
			tdS.update(tdVO);
		}
		
		
		// Map type의 VO에 현재 날짜, 시각, 할 일 정보를 저장하기
		// VO 칼럼을 추가하면서 동시에 데이터 저장하기
		// Map type의 VO에 데이터를 put() 할 때
		//	만약 key에 해당하는 데이터가 이미 있으면
		//	기존의 데이터를 수정한다
		// 없으면 새로운 칼럼을 추가하고 데이터 저장
		tdVO.put("name", "홍길동");
		tdVO.put("name", "이몽룡");
		tdVO.put("name", "성춘향");
 
//		if(ret < 1) {
//			req.setAttribute("ERROR", "Insert Fail");
//		} else {
//			req.setAttribute("COMP", "Insert SUCCES");
//		}
		
		// "/todo/"
		res.sendRedirect( req.getContextPath() );
	}

}



