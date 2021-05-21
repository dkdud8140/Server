<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
form.v1 {
	width: 80%;
	margin: 10px auto;
}

form.v1 fieldset {
	border: 1px solid blue;
	border-radius: 10px;
	padding: 5px;
}

form.v1 label, form.v1 input, form.v1 textarea {
	display: inline-block;
	padding: 5px;
	margin: 5px;
}

form.v1 label {
	width: 150px;
	text-align: right;
}

form.v1 input, form.v1 textarea {
	width: 300px;
	border: 1px solid green;
	border-radius: 5px;
}

form.v1 button {
	outline: 0;
	border: 0;
	width: 100px;
	color: white;
}

form.v1 div button:nth-child(2) {
	background-color: fuchsia; 
}
form.v1 div button:nth-child(3) {
	background-color: orange; 
}
form.v1 div button:nth-child(4) {
	background-color: lime; 
}
form.v1 button:hover {
	background-color: gray;
	color: black;
	cursor: pointer;
}


form.v1 div:nth-last-child{
	width : 460px;
	margin : 0px auto;
	text-align: right;
}

</style>

<script >

	//script를 본문 어디에나 두기위해
	// document에 Event 설정
	document.addEventListener("DOMContentLoaded", function(){
	
		document.querySelector("button.btn_save").addEventListener("click",function(ev){
					
			let dom = document;
			let gb_writer = dom.querySelector("input[name='gb_writer']");
			let gb_password = dom.querySelector("input[name='gb_password']");
			let gb_email = dom.querySelector("input[name='gb_email']");
			let gb_content = dom.querySelector("textarea");
			
			if(gb_writer.value == "") {
				alert("작성자 이름은 반드시 입력해야합니다.");
				//dom.querySelector("input[name='gb_writer']").focus();
				gb_writer.focus();
				
				//이벤트 코드 진행을 멈춰라
				return false ;
			}
			
			if(gb_email.value == "") {
				alert("e-mail은 반드시 입력해야합니다.");
				gb_email.focus();
				return false ;
			}
			
			if(gb_password.value == "") {
				alert("패스워드는 반드시 입력해야합니다.");
				gb_password.focus();
				return false ;
			}
			
			if(gb_content.value == "") {
				alert("내용은 반드시 입력해야합니다.");
				gb_content.focus();
				return false ;
			}

			alert("작성자 : " + gb_writer.value + "\n" 
					+ "e-mail : " + gb_email.value + "\n" 
					+ "비밀번호 : " + gb_password.value + "\n"
					+ "작성내용 : " + gb_content.value + "\n" );
			
			dom.querySelector("form.v1").submit();
			
			
		})
		
	})
	
</script>


</head>
<body>
	<%@ include file="/WEB-INF/views/include_nav.jsp"%>

	<form class="v1" method="POST">
		<fieldset>
			<legend>방명록쓰기</legend>
			<div>
				<label>작성일자</label>
				<input name = "gb_date" type="date" value="${GB.gb_date}">
			</div>
			<div>
				<label>작성시간</label>
				<input name = "gb_time" type="time" value="${GB.gb_time}">
			</div>
			<div>
				<label>작성자</label>
				<input name = "gb_writer" type="text" value="${GB.gb_writer}">
			</div>
			<div>
				<label>E-mail</label> 
				<input name = "gb_email" type="email" value="${GB.gb_email}">
			</div>
			<div>
				<label>비밀번호</label>
				<input name = "gb_password" value="${GB.gb_password}">
			</div>
			<div>
				<label>내용</label>
				<textarea name = "gb_content" rows="5">${GB.gb_content}</textarea> 
			</div>
			<div>
				<label></label>
				<button class = "btn_save" type="button">저장</button>
				<button type="reset">다시작성하기</button>
				<button class = "btn_home" type="button">처음으로</button>
			</div>
		</fieldset>
	</form>
</body>
</html>