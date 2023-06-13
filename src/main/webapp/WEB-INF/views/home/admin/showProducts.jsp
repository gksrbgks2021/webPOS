<%@ page import="com.example.webPOS.constants.urlpath.UrlParamAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>상품 관리</title>
</head>
<body>

<h3>[상품 삭제 페이지]</h3>

<table border="1">
  <thead>
  <tr>
    <th>Product ID</th>
    <th>Name</th>
    <th>Net Price</th>
    <th>Cost Price</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${productList}" var="product">
    <tr>
      <td>${product.productId}</td>
      <td>${product.name}</td>
      <td>${product.netPrice}</td>
      <td>${product.costPrice}</td>
      <td>
        <form action="admin/actionName/<%=UrlParamAction.PROCESS_DELETE_PRODUCT%>" method="get">
          <input type="hidden" name="productId" value="${product.productId}">
          <input type="submit" value="삭제">
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
