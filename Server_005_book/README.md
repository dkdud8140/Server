# 도서 관리 Server 프로젝트
* 웹 브라우저에서 데이터를 입력하고 서버로 전송하여 처리하기

## 입력 화면 만들기

* 웹 브라우저에서 데이터를 입력받기 위한 화면 만들기
<form action = "요청Path">
	<p><input name ="변수명">
	<p><input name ="변수명">
		...
	<p><button>전송</button>
</form>

## Controller 만들기

* HttpServlet 클래스를 extends 하여 클래스 생성
* @WebServlet("요청path") Annotation 선언
* doGet() 메소드 재정의
* req.getParameter("변수명")으로 데이터 받기

