<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>[주문 실패 페이지]</h2>

주문에 실패하였습니다. 에러내용 <br/>

${errorMsg}

<form action="/finish" method="get">
    <button type="submit">메인 화면으로 돌아가기</button>
</form>

</body>
</html>
