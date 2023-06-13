<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <style>
        label {
            display: block;
            font: 1rem 'Fira Sans', sans-serif;
        }

        input,
        label {
            margin: 0.4rem 0;
        }
    </style>
</head>
<body>
<h3>[편의점 통계 페이지]</h3><br>

<form id="date-form" action="/statistic/query" method="post">
    <label for="start">Start date:</label>
    <input type="date" id="start" name="start" min="2018-01-01" max="2030-12-31">

    <label for="end">End date:</label>
    <input type="date" id="end" name="end" min="2018-01-01" max="2030-12-31">

    <label for="interval">조회 기준:</label>
    <select id="interval" name="interval">
        <option value="day">일</option>
        <option value="week">주</option>
        <option value="month">월</option>
    </select>

    <label for="store">편의점 종류:</label>
    <select id="store" name="store">
        <c:forEach var="store" items="${storeList}">
            <option value="${store.storeName}">${store.storeName}</option>
        </c:forEach>
    </select>

    <input type="submit" value="Submit">
</form>

<script>
    window.onload = function () {
        var today = new Date();
        var dd = String(today.getDate()).padStart(2, '0');
        var mm = String(today.getMonth() + 1).padStart(2, '0'); // January is 0!
        var yyyy = today.getFullYear();

        today = yyyy + '-' + mm + '-' + dd;
        document.getElementById("start").value = today;
        document.getElementById("end").value = today;
    };

    document.getElementById('date-form').addEventListener('submit', function (event) {
        var startDate = new Date(document.getElementById('start').value);
        var endDate = new Date(document.getElementById('end').value);

        if (startDate > endDate) {
            event.preventDefault();
            alert('시작 날짜는 끝 날짜보다 이후일 수 없습니다.');
        }
    });
</script>

</body>
</html>
