<%@ page contentType="text/html;" language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ page import="com.example.webPOS.dto.Member" %>
<%@ page import="com.example.webPOS.constants.SessionConstants" %>
<%@ page import="com.example.webPOS.constants.urlpath.UrlParamAction" %>
<%
    Member sessionMember = (Member) session.getAttribute(SessionConstants.LOGIN_MEMBER);
    String sessionName = sessionMember.getName();
%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인 페이지</title>
    
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .navbar {
            background-color: #007bff;
            color: white;
            padding: 10px;
            text-align: center;
        }
        .navbar a {
            color: white;
            text-decoration: none;
            margin: 0 15px;
        }
        .navbar button {
            background-color: #0056b3;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
        }
        .navbar button:hover {
            background-color: #004494;
        }
        .sidebar {
            padding: 10px;
        }
        .content {
            flex-grow: 1; padding: 20px; overflow-y: auto;
        }
        .logout-form {
            display: inline;
            margin-left: 30px;
        }
        .nav-link {
            margin-right: 10px;
        }
        .li-button {
            display: inline-block;
            margin: 10px;
        }
        .li-button a {
            background-color: #007bff;
            color: white;
            padding: 10px 20px;
            border-radius: 25px;
            text-decoration: none;
            transition: background-color 0.3s, transform 0.3s;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            margin-bottom: 10px;
        }
        .li-button a:hover {
            background-color: #0056b3;
            transform: translateY(-2px);
        }

    </style>
    <script src="${pageContext.request.contextPath}/static/js/urlconstants.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/adminstyle.css">
</head>
<body>

<div class="navbar">
    <li style="display: inline; padding-top: 7px;"><strong>[${name}님] 환영합니다.</strong></li>
    <span>&nbsp;&nbsp;&nbsp;[${role}] 로 로그인 중</span>
    <form action="/logout" method="get" class="logout-form">
        <button type="submit">로그 아웃</button>
    </form>
</div>

<div class="container">
    <!-- 사이드바 영역 -->
    <div class="sidebar">
        <c:choose>
            <c:when test="${role eq 'staff'}">
                <li class="li-button"><a class = "nav-link" href="/trade/actionName/<%=UrlParamAction.SALE%>">상품 판매</a></li>
                <li class="li-button"><a class = "nav-link" href="/statistic">통계</a></li>
            </c:when>
            <c:when test="${role eq 'manager'}">
                <li class="li-button"><a href="/trade/actionName/<%=UrlParamAction.SALE%>" class="nav-link">상품 판매</a></li>
                <li class="li-button"><a href="/trade/actionName/<%=UrlParamAction.ORDER%>" class="nav-link">상품 발주</a></li>
                <li class="li-button"><a href="/statistic" class="nav-link">통계</a></li>
            </c:when>
            <c:when test="${role eq 'admin'}">
                <li class="li-button"><a class = "nav-link" data-msg="회원 목록"
                    data-url="/admin/actionName/<%=UrlParamAction.MANAGE_MEMBER%>?format=json">회원 관리</a></li>
                <li class="li-button"><a class = "nav-link" data-url="/admin/actionName/${MANAGE_PRODUCT}">상품 등록</a></li>
                <li class="li-button"><a class = "nav-link" data-url="/admin/actionName/${DELETE_PRODUCT}">상품 삭제</a></li>
            </c:when>
        </c:choose>
    </div>
    <!-- 콘텐츠 영역 -->
    <div class="content" id="contentArea">
        <h2></h2>
    </div>
</div>

<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="${pageContext.request.contextPath}/static/js/adminbtns.js"></script>

</body>
</html>