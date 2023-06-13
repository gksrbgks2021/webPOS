<%@ page import="com.example.webPOS.constants.urlpath.UrlParamAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 등록</title>
</head>
<body>

<h2>상품 등록 페이지</h2>

<form action="processName/<%=UrlParamAction.PROCESS_ENROLL_PRODUCT%>" method="post">
    <label for="name">상품 이름:</label><br>
        <input type="text" id="name" name="name"><br>
    <label for="netprice">원가:</label><br>
        <input type="text" id="netprice" name="netprice"><br>
    <label for="costprice">정가:</label><br>
        <input type="text" id="costprice" name="costprice"><br>

    <input type="submit" value="상품 등록 완료">
</form>
</body>
</html>