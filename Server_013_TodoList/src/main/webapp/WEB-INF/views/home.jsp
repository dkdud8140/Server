<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var ="rootPath"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>To do List</title>
<style>
	* {
		box-sizing: border-box;
		margin: 0;
		padding: 0;
	}
	
	body {
	background-color: #050C0F ;
	}
	
	h1, form.doit {
		width : 50%;
		margin : 10px auto ;
		border-radius: 5px ;
	}
	
	h1 {
		background-color: white ;
		color: #050C0F;
		padding: 1em ;
		text-align: center;
		
		/* text에 그림자 지정 */
		text-shadow: 1px 1px 1px #eee ;
	}
	
	form.doit {
		border: 1px solid rgba(5,12,15,0.5) ;
		text-align: center;
		align-content: center;
	}
	
	form.doit input {
		margin : 10px auto ;
		width: 90%;
		
		/*box를 클릭하면 진한 테두리가 생기는 것을 방지*/
		outline: 0 ;
		border: 1px solid #eee ;
		padding: 15px ;
		font-weight: bolder;
	}
	
	form.doit input:hover {
		background-color: #eee ;
	}
	
</style>
</head>
<body>
	<h1>To Do List</h1>
	<div class="input">
		<%--
			form tag의 action 속성
			form tag action 속성은 form에 담긴 데이터를
			submit할 때 (서버로 전송할 때)
			어떤 uri path를 통해서 서버에 보낼것인가를 지정하는 것
			
			만약 이 action 속성을 지정하지 않으면
			현재 파일(home.jsp)을 보여줄 때 사용한 uri 주소가 자동으로 설정된다.
		
			${rootPath}/처럼 주소를 지정하는 것
			form, a tag등에 URL, URI를 지정할 때
			
			주소의 지정방식에 따라 상대주소, 절대주소 방법이 있는데 
			지정하는 방법에 따라 연결이 잘 안되는 경우가 많다. 
			
			우리 프로젝트는 모두 절대 주소 지정방식으로 통일하고
			항상 주소(URI, URL)과 관련된 모든 항목은
			${rootPath} 시작하는 주소로 사용한다
			rootPath = http://localhost:8080/todo
		 --%>
	
	
		<form class ="doit" method ="POST" action="${rootPath}/insert">
			<input name ="td_doit" placeholder="할 일을 입력한 후 Enter">
		</form>
	</div>
</body>
</html>