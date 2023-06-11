<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
테스트 페이지

<c:set var="message" value="Hello, JSTL!" />
<c:out value="${message}" />
<c:set var = "salary" scope = "session" value = "${2000*2}"/>
<p>Your salary is : <c:out value = "${salary}"/></p>
<c:choose>

    <c:when test = "${salary <= 0}">
        Salary is very low to survive.
    </c:when>

    <c:when test = "${salary > 1000}">
        Salary is very good.
    </c:when>

    <c:otherwise>
        No comment sir...
    </c:otherwise>
</c:choose>


</body>
</html>
