<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>[쿼리결과 조회] </title>
</head>
<body>
<h2>쿼리결과 조회 페이지</h2>

<h3>Start Date: ${start}, End Date: ${end}</h3>

<br/>

        <h3>편의점 랭킹 조회 </h3>
        <table border="1">
            <thead>
            <tr>
                <th>편의점 이름</th>
                <th>매출액</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${profitMap}" var="profitMapEntry" varStatus="status">
                <tr>
                    <td>${profitMapEntry.key}</td>
                    <td>${profitMapEntry.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <br>
        <c:if test="${not empty revenueMap and not empty revenueMap[0]}">
            <p>해당 기간에 매출 1등한 편의점은 "${revenueMap[0].key}" 이고 수익은 "${revenueMap[0].value}" 입니다.</p>
        </c:if>

<br>
        <table border="1">
        <h3>[${store}] 편의점 통계 ${interval} 별로 조회 결과</h3>

            <thead>
            <tr>
                <th>날짜</th>
                <th>순이익</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach items="${storeProfitMap}" var="storeProfitMapEntry">
                <tr>
                    <td>${storeProfitMapEntry.key}</td>
                    <td>${storeProfitMapEntry.value}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

<form action="/finish" method="get">
    <button type="submit">메인 화면으로 돌아가기</button>
</form>

</body>
</html>
