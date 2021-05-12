<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
input {	width : 200px;
		border : 0.5px solid #cccccc }
h1, h2, h3, h4, h5, h6 { color : #FF4500 }
table {
	border-top : 1px solid #cccccc;
	border-collapse: collapse;}
th,td {	padding : 15px 10px; 
	border-top : 1px solid #cccccc;
	text-align: center}
body { font-family : "Noto Sans CJK KR Light", "돋움", dotum, arial,"굴림", gulim}
</style>


</head>
<body>
	<h1>&#127869;무엇을 먹을까?</h1>
	<%-- form의 method를 POST로 지정하면 Controller의 doPOST() 
	함수가 받아서 처리를 한다. 
	보여지는 search.jsp 파일을 요청한 요청 주소는 /food/search이다
	
	forem에 action을 임의로 지정하지 안흥면
	action="/form/search"가 자동으로 지정된다.--%>
	<form method="POST">
		<%--input placeholder : 입력받스의 안내메시지 --%>
		<p><label>식품명</label><input name="f_name" placeholder="식품명을 입력한 후 Enter"/></p>
	</form>
	
	<%-- div : 일종의 박스태그 --%>
	<div>
		<%--
			c taglib 에는 if else가 없어서 choose를 사용한다
			<c:choose>
				<c:when> : if에 해당하는 부분
				</c:when>
				<c:otherwise> : else에 해당하는 부분
				</c:otherwise>
			</c:choose>
		 --%>
		<c:choose>
			<c:when test="${not empty FOODS}">
				<table>
					<tr style ="background-color : 	#FFDAB9; ">
						<th>식품코드</th>
						<th>식품명</th>
						<th>출시연도</th>
						<th>제조사코드</th>
						<th>분류코드</th>
						<th>제공량</th>
						<th>총내용량</th>
						<th>에너지</th>
						<th>단백질</th>
						<th>지방</th>
						<th>탄수화물</th>
						<th>총당류</th>
					</tr>
					<%--
						Controller에서 전달받은 FOODS(List타입) Attribute를
						for 반복문으로 반복하면서
						item을 getter하여 FOOD(DTO 타입)에 담아라
					 --%>
					<c:forEach items="${FOODS}" var ="FOOD">
					<tr>
						<%--
							ForEach가 만든 FOOD(DTO)에서 
							각 변수 값을 getter하여 화면에 보여라
						 --%>
						<td>${FOOD.fd_code}</td>
						<td>${FOOD.fd_name}</td>
						<td>${FOOD.fd_year}</td>
						<td>${FOOD.fd_ccode}</td>
						<td>${FOOD.fd_icode}</td>
						<td>${FOOD.fd_once}</td>
						<td>${FOOD.fd_capa}</td>
						<td>${FOOD.fd_kcal}</td>
						<td>${FOOD.fd_protein}</td>
						<td>${FOOD.fd_fat}</td>
						<td>${FOOD.fd_carbo}</td>
						<td>${FOOD.fd_sugar}</td>
					</tr>		
					</c:forEach>		
				</table>
			</c:when>
		</c:choose>
	</div>
	
	
</body>
</html>