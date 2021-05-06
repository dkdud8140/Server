<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core"  prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>도서검색결과</title>
</head>
<body>
	<h1>대여 도서 주문하기</h1>
	<p>================================================<br/>
	회원코드 : ${BUYER.bu_code} <br/>
	회원이름 : ${BUYER.bu_name} <br/>
	전화번호 : ${BUYER.bu_tel} <br/>
	회원생년 : ${BUYER.bu_birth} <br/>
	<p>================================================<br/>
	
	<h1>도서검색결과</h1>
	<c:if test = "${empty BOOKS}"> 검색결과가 없음 </c:if>
	<c:forEach items = "${BOOKS}" var = "BOOK">
		<p>${BOOK.bk_isbn},
		<a href = "insert?bk_isbn=${BOOK.bk_isbn}&bu_code=${BUYER.bu_code}">
		${BOOK.bk_title}</a>,
		${BOOK.bk_cname},
		${BOOK.bk_auname}</p>
	</c:forEach>
		
</body>
</html>