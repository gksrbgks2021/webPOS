<%@ page import="com.example.webPOS.dto.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    /**
     * this is test code.
     */
    Member member = (Member) session.getAttribute("loginMember");
    if(member == null) System.out.println("세션 없어짐");
    else System.out.println(member.getName()+"로그인중");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>

<h2>[주문 완료 페이지]</h2>

<p><%=member.getName()%>님 주문이 완료되었습니다 ! </p><br>

<form action="/finish" method="get">
    <button type="submit">메인 화면으로 돌아가기</button>
</form>

</body>
</html>
