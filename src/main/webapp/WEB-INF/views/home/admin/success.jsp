<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>success process</title>
</head>
<body>
<h1> ==== 안내 메시지 ==== </h1>

<br>
<p><b>${infoMsg}</b></p>

============================

<form action="/finish" method="get">
    <button type="submit">메인 화면으로 돌아가기</button>
</form>
</body>
</html>
