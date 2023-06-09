<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>회원가입 성공</title>
</head>
<body>
<p><strong>${registerRequest.name}님</strong>
    회원 가입을 완료했습니다.</p>
<p><a href='/login' />[로그인 페이지 이동]</a></p>
</body>
</html>