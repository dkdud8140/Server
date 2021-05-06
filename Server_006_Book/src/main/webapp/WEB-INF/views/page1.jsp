<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- HTML 주석 : Web 소스보기로 노출-->
<%-- 
jsp 주석
소스보기로 했을 때 보이지 않는 주석
Html 주석보다는 다소 자유롭게 작성 가능
--%>

<%--
standard TagLib
HTML에서 지원하지 않는 기능을 수행하기 위하여
JSP 파일에서 사용하는 확장된 tag 기능
Controller에서 List, 배열에 데이터를 담아 보내면
단순한 HTML, EL tag만으로는 보여주기가 매우 어렵다.
이러한 데이터를 다소 쉽게 표현하기 위한 도구

JSTL : JSP Stansard Tag Lib
JSP 2.0 이상에서 사용가능, Tomcat 5.5 이상에서 사용 가능
Tomcat 5.5. 2006부터 사용하는 톰캣

 --%>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원정보 조회 결과</h1>
	<c:if test="${ empty BUYERS }">회원정보없음</c:if>
	
	<%--
	for(BuyerDTO d : buList) {}
	 --%>
	 
	 <%--
	 items = "Controller에서 받은 list, 배열"
	 List를 전체 반복하면서 각각의 요소를 getter하여
	 var로 지정된 변수에 담기
	 EL tag를 사용하여 각각의 변수를 화면에 출력
	  --%>
	<c:forEach items="${BUYERS}" var = "BUYER">
		<p>${BUYER.bu_code},
		<a href = "page2?bu_code=${BUYER.bu_code}">${BUYER.bu_name}</a>,
		
		${BUYER.bu_birth},
		${BUYER.bu_tel}, 
		${BUYER.bu_addr}</p>
	</c:forEach>
</body>
</html>