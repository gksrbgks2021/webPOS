<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>관리자 페이지</title>
</head>
<body>
<h1>관리자 페이지</h1>

<form action="admin/productRegistration" method="post">
    <button type="submit">상품 등록</button>
</form>

<form action="admin/userManagement" method="post">
    <button type="submit">회원 관리</button>
</form>

</body>
</html>
