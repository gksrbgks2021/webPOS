<%@ page import="com.example.webPOS.dto.Member" %>
<%@ page import="com.example.webPOS.constants.SessionConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<%
    Member sessionMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
    String sessionName = sessionMember.getName();
%>

<!DOCTYPE html>
    <html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"></script>

    <head>
        <title>메인화면</title>
    </head>

    <body>S
        <h2>
            <li style="padding-top: 7px;"><strong>[${name}님] 환영합니다.</strong>
        </li>
        <li class="nav-item"><a href="/logout" class="nav-link" >로그 아웃</a></li>
        <li class="nav-item"><a class="nav-link" href="#">회원 수정</a></li>
        <li class="nav-item"><a class="nav-link" href="#">상품 판매</a></li>
            <li class="nav-item"><a class="nav-link" href="#">상품 발주</a></li>
        <li class="nav-item"><a class="nav-link"
                                href="#">통계</a></li>

    </body>

    </html>