<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>error page</title>
    <style>
        body {
            background-color: #f0f8ff;
            color: #333; 
            font-family: Arial, sans-serif;
            text-align: center;
            padding: 50px;
        }
        h1 {
            color: #007bff;
        }
        p {
            font-size: 18px;
            margin: 20px 0;
        }
        button {
            background-color: #007bff;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<h1> ==== 에러가 발생하였습니다. ==== </h1>

<br>
<p><b>${errorMsg}</b></p>

============================
<form action="/finish" method="get">
    <button type="submit">메인 화면으로 돌아가기</button>
</form>
</body>
</html>
