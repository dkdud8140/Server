# Servlet의 응답처리

##Request와 Response
* Request는 웹 브라우저에서 서버에 요청을 하는 행위
* Response는 서버가 웹 브라우저에게 응답을 하는 행위

## Request의 forword(), Response의 sendRedirect()
* 자기가 서버까지 완료 : forword()는 서버에 있는 jsp 파일을 읽어서 rendering을 수행한 후 Web Broeser에게 전달하는 것
* 책임전가 : sendRedirect()는 서버가 다시 웹브라우저에 다른 요청주소를 보내고 웹브라우저가 그 쪽 주소로 

## forword(), sendRedirect
* forword는 클라이언트의 요청을 끝까지 책임지고 수행하여
	결과를 알려주는 것까지 책임을 지는 코드 수행
* forword()는 데이터를 처리하고, 화면 랜더링까지 수행
* sendRedirect는 클라이언트의 요청을 서버의 다른 요청 주소로
	책임을 떠 넘기는 방식
* 마치 웹 브라우저에 주소를 타이핑하여 입력하는 방식으로 요청을 전달만 한다.


