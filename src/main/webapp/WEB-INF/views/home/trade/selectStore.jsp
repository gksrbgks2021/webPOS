<%@ page import="com.example.webPOS.constants.ModelConstants" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>지점 선택</title>
    <style>
        mybox{
            width : 500px;
        }
    </style>
</head>
<body>
<h1>편의점 지점 선택</h1>

<p>거래할 편의점을 선택해주세요</p><br/>

<div style="width:500px;">
    <c:choose>
        <c:when test="${storeList == null}">
            <p>가게 정보가 없습니다!</p>
        </c:when>
        <c:otherwise>
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); grid-gap: 10px;">
                <c:forEach items="${storeList}" var="store">
                    <form method="post" action="${actionName}/storeName/${store.storeName}">
                        <input type="hidden" name="storeName" value="${store.storeName}">
                        <button type="submit" name="selectedStore" value="${store.storeName}"
                                style="width: 120px; aspect-ratio: 1/1;">${store.storeName}</button>
                    </form>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
