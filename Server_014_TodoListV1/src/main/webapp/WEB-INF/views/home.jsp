<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set value="${pageContext.request.contextPath}" var ="rootPath"/>

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
	background-color: #050C0F ;
	}
	
	h1, form.doit,table.td_list {
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
	
	table.td_list {
		color : white ;
		border-collapse: collapse;
		/*table을 그릴 때 각 td와 td 간격에 미세하게 여백이 있어서 가끔 선을 그리면
		선과 선 사이에 틈새가 보인다
		이러한 틈색를 막기 위해 설정하는 값*/
		border-spacing:o; 
	}
	
	table.td_list td {
		padding : 7px;
		border-top : 1px solid #acacac ;
		cursor: pointer;
	}
	
	/*table의 가장 마지막 tr(line)의 칼럼에만 적용*/
	table.td_list tr:nth-last-child() td{
		border-bottom : 1px solid #acacac ;
	}
	
	table.td_list td.sdate,table.td_list td.edate {
		font-size: 10px;
		text-align: center;
		width: 20%;
		color : #bbb;
	}  
	
	table.td_list td.doit {
		font-size : 20px;
		text-align: left;
		/*
		width : 무조건 폭을 지정
		max-width : 포함된 text(content) 길이에 따라 조절을 하되 최소 크기만 제한
		*/
		max-width: 0;
		/*
		td의 width보다 큰 text를 말줄임표로 표현하기
		
		td3인데
		첫번째 세번째 td 20% 설정했기 때문에
		여기 td는 전체 tavle width의 60%가 될 것이고
		이 상태에서 max-width를 0으로 지정하고 
		ellipsis 설정을 해주면 된다.
		*/
		
		
		/*
		최대 width보다 text가 더 많더라도 자동으로 줄바꿈하지 않기
		*/
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		
	}
	
	table.td_list tr:hover {
		background-color: #1B4A50;
	}
	
	.underline{
		text-decoration: 2px underline #B43C52;
	}
	
	.text-throth {
		text-decoration: 2px line-through #B43C52;
	}
	
	/*
	media query
	반응형 web에서 사용하는 css 기법
	
	max-width : 화면 폭이 이 값보다 작으면
	min-width : 화면 폭이 이 값보가 크면
	
	*/
	
	@media screen and (max-width: 800px) {
		h1, form.doit,table.td_list {
			width : 95%;
			margin : 5px auto ;
		}
		
		h1 {
			font-size: 10px;
		}
		
	}
	
</style>

<script>
	/*
	script가 어디에 위치하든 상관 없이
	이 후의 다른 Event가 정상적으로 설정될 수 있도록 선언
	화면에 DOM 요소가 모두 완전하게 나타나면
	내부의 코드를 실행하라
	*/
	document.addEventListener("DOMContentLoaded",()=>{
		// ()=>{} : 화살표 함수
		// 	== function() {}
		document.querySelector("table.td_list").addEventListener("dblclick",(ev)=>{
			//tabled의 TD 항목이 클릭되었을때만 반응하기 위해
			let tagName = ev.target.tagName
			if(tagName == "TD") {
				let seq = ev.target.closest("TR").dataset.seq ;
				let edate = ev.target.closest("TR").dataset.edate;
				
				let msg = edate ? "To Do 완료를 취소합니다." : "To Do 완료 처리 합니다";
				
				if(confirm(msg)) {
					location.href="${rootPath}/sub/expire?seq=" + seq;
				}						
						
						
			}
			
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
	
	
		<form class ="doit" method ="POST" action="${rootPath}/sub/insert">
			<input name ="td_doit" placeholder="할 일을 입력한 후 Enter">
		</form>
		<table class ="td_list" >
			<c:forEach items="${TDLIST}" var ="TD">
				<tr data-seq="${TD.td_seq}" data-edate="${TD.td_edate}">
					
					<td class ="sdate ${empty TD.td_edate ? '' : 'underline' }">${TD.td_sdate}<br/>${TD.td_stime}</td>
					<td class = "doit ${empty TD.td_edate ? '' : 'text-throth' }">${TD.td_doit}</td>
					<td class = "edate">${TD.td_edate}<br/>${TD.td_etime}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>