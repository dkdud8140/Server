<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set value="${pageContext.request.contextPath}" var="rootPath" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>To do List</title>
<style>
* {
	box-sizing: border-box;
	margin: 0;
	padding: 0;
}

body {
	background-color: #050C0F;
}

h1, form.doit, table.td_list {
	width: 50%;
	margin: 10px auto;
	border-radius: 5px;
}

h1 {
	background-color: white;
	color: #050C0F;
	padding: 1em;
	text-align: center;
	/* text에 그림자 지정 */
	text-shadow: 1px 1px 1px #eee;
}

form.doit {
	border: 1px solid rgba(5, 12, 15, 0.5);
	text-align: center;
	align-content: center;
}

form.doit input {
	margin: 10px auto;
	width: 90%;
	/*box를 클릭하면 진한 테두리가 생기는 것을 방지*/
	outline: 0;
	border: 1px solid #eee;
	padding: 15px;
	font-weight: bolder;
}

form.doit input:hover {
	background-color: #eee;
}

table.td_list {
	color: white;
	border-collapse: collapse;
	border-spacing: 0;
}

table.td_list td {
	padding: 7px;
	border-top: 1px solid #ccc;
	cursor : pointer;
	
	/*
	실험적인 css 적용하기
	user-select:none;은 text를 dblclick했을 떄
	선택박스가 나타자니 않도록 적용
	user-select : 기능이 적용되는 브라우저용
	-webkit- : 크롬, 구글, 사파리
	-moz- : 파이어폭스 계열
	-ms- : 익스플로러
	-o- : 오페라
	
	
	*/
	user-select : none;
	-webkit-user-select : none;
	-moz-user-select : none;
	-ms-user-select : none;
	-o-user-select : none;
	
	
}

table.td_list td:hover {
	cursor: pointer;
}

table.td_list tr:last-child td {
	border-bottom: 1px solid #ccc;
}

table.td_list td.count {
	font-size: 20px;
	text-align: right;
	width: 5%;
	color: yellow;
}

table.td_list td.sdate, table.td_list td.edate {
	font-size: 10px;
	text-align: center;
	width: 20%;
}

table.td_list td.doit {
	font-size: 20px;
	text-align: left;
	color: white;
	max-width: 0;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	
	
}

.through-text {
	background-color: silver;
	text-decoration: line-through red;
}

@media screen and (max-width:480px) {
	h1, form.doit, table.td_list {
	width : 95%;
	margin: 0 auto ;
	}
}

@media screen and (max-width:800px) {
	h1, form.doit, table.td_list {
	width : 70%;
	margin: 0 auto ;
	}
}
</style>

<script>
document.addEventListener("DOMContentLoaded",()=>{
	document.querySelector("table").addEventListener("dblclick",(ev)=>{
		
		ev.preventDefault()
		
		let tagName = ev.target.tagName
		if(tagName == "TD"){
			
			//클릭된 TD Tag를 감싸고 있는 TR 객체가 뭔지 확인하기
			let seq = ev.target.closest("TR").dataset.seq;
			let edate = ev.target.closest("TR").dataset.edate;
			
			let msg = edate ? "TODO 완료를 취소합니다"	: "TODO 를 완료처리 합니다";
			
			if(confirm(msg)) {
				location.href="${rootPath}/expire?seq=" + seq
			}
		}
		alert(tagName);
	})
})
</script>
	
	


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


		<form class="doit" method="POST" action="${rootPath}/insert">
			<input name="doit" placeholder="할 일을 입력한 후 Enter">
		</form>

		<div class="msg">${ERROR}${COMP}</div>
		<table class="td_list">
			<c:forEach items="${TDLIST}" var="TD" varStatus="ST">
				<tr data-seq="${TD.td_seq}" data-edate="${TD.td_edate}">
					<td class="count">${ST.count}</td>
					<td class="sdate">${TD.td_sdate}<br />${TD.td_stime}</td>
					<td class="doit ${empty TD.td_edate ? '' : 'through-text' }">${TD.td_doit}</td>
					<td class="edate">${TD.td_edate}<br />${TD.td_etime}</td>
				</tr>
			</c:forEach>
		</table>

	</div>
</body>
</html>