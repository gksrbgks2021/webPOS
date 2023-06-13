<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>상품 등록</title>
</head>
<body>
<h2>상품 정보 입력</h2>
<form action="#" method="post">
    <label for="productID">상품 ID:</label><br>
    <input type="text" id="productID" name="productID"><br>

    <label for="netprice">원가:</label><br>
    <input type="text" id="netprice" name="netprice"><br>

    <label for="costprice">정가:</label><br>
    <input type="text" id="costprice" name="costprice"><br>

    <label for="name">상품 이름:</label><br>
    <input type="text" id="name" name="name"><br>

    <label for="quantityreceived">재고:</label><br>
    <input type="number" id="quantityreceived" name="quantityreceived" min="0" max="100" /><br>

    <input type="submit" value="상품 등록 완료">

</form>
</body>
</html>