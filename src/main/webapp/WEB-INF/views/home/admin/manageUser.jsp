<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>회원 관리</title>
</head>
<body>

<h3>[회원 삭제 페이지]</h3>

<table border="1">
  <thead>
  <tr>
    <th>ID</th>
    <th>Email</th>
    <th>Name</th>
    <th>Role</th>
    <th>Registration Date</th>
    <th>Actions</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${userList}" var="user">
    <tr>
      <td>${user.id}</td>
      <td>${user.email}</td>
      <td>${user.name}</td>
      <td>${user.role}</td>
      <td>${user.regdate}</td>
      <td>
        <form action="admin/actionName/editUser" method="get">
          <input type="hidden" name="id" value="${user.id}">
          <input type="submit" value="수정">
        </form>
        <form action="admin/actionName/deleteUser" method="get">
          <input type="hidden" name="id" value="${user.id}">
          <input type="submit" value="삭제">
        </form>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>
</body>
</html>
