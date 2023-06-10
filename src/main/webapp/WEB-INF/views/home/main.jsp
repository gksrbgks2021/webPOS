<%@ page import="com.example.webPOS.dto.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>

<!DOCTYPE html>
    <html>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"></script>

    <head>
        <title>메인화면</title>
    </head>

    <body>
        <h2><strong>
            ${name}
            님</strong> 환영합니다!</h2>


        <a href="/logout" class="btn btn-primary">로그아웃</a>

    </body>

    </html>