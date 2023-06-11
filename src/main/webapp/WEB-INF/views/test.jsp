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

</body>
</html>
