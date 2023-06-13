<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.example.webPOS.dto.Member" %>
<%@ page import="com.example.webPOS.constants.SessionConstants" %>
<%@ page import="com.example.webPOS.constants.urlpath.UrlParamAction" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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

<body>
<h2>

    <li style="padding-top: 7px;"><strong>[${name}님] 환영합니다.</strong></li>
    <br/>

    <li><a href="/logout" class="nav-link">
        <button type="submit">로그 아웃</button>
    </a></li>
    <c:choose>
    <c:when test="${role eq 'staff'}">
    <li><a href="/trade/actionName/<%=UrlParamAction.ORDER%>" class="nav-link">
        <button type="submit">상품 판매</button>
    </a></li>
    <li><a href="/statistic" class="nav-link">
        <button type="submit">통계</button>
    </a></li>
    </c:when>
    <c:when test="${role eq 'manager'}">
    <li><a href="/trade/actionName/<%=UrlParamAction.SALE%>" class="nav-link">
        <button type="submit">상품 판매</button>
    </a></li>
    <li><a href="/trade/actionName/<%=UrlParamAction.ORDER%>" class="nav-link">
        <button type="submit">상품 발주</button>
    </a></li>
    <li><a href="/statistic" class="nav-link">
        <button type="submit">통계</button>
    </a></li>
    </c:when>
    <c:when test="${role eq 'admin'}">
    <li><a href="/admin/actionName/<%=UrlParamAction.MANAGE_MEMBER%>" class="nav-link">
        <button type="submit">회원 관리</button>
    </a></li>
    <li><a href="/admin/actionName/<%=UrlParamAction.MANAGE_PRODUCT%>" class="nav-link">
        <button type="submit">상품 등록</button>
    </a></li>
    <li><a href="/admin/actionName/<%=UrlParamAction.DELETE_PRODUCT%>" class="nav-link">
        <button type="submit">상품 삭제</button>
    </a></li>
    </c:when>
    </c:choose>
</body>

</html>