<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.5/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.min.js"></script>

</head>
<body>

<h1>편의점 POS 어플리캐이션 </h1>

<h2>로그인</h2>

<form action="login" method="post">
    <div class="row g-3">
        <div class="col-md-6">
            <label for="ID" class="form-label" >ID:</label>
            <input type="text" name="id" id="id"class="form-control" required>
        </div>
    </div>
    <div class="row g-3">
        <div class="col-md-6">
            <label for="password" class="form-label">PASSWORD:</label>
            <input type="password" name="password" id="password" class="form-control" required>
        </div>
    </div>
    <br/>
    <div class="row g-3">
        <div class="col-12">
            <input type="submit" value="login" class="btn btn-primary">
        </div>
    </div>
</form>

<div class="mt-3">
    <form action="register/" method="post">
        <input type="submit" value="create an account" class="btn btn-secondary">
    </form>
</div>

<script type="text/javascript">
    function sendit() {
        let formTag = document.joinForm;
        let nameTag = formTag.username;
        let idTag = formTag.userid;

        if(nameTag.value == null || nameTag.value == ""){
            alert("이름을 입력하세요");
            nameTag.focus();
            return false;
        }

        if(idTag.value == null ||idTag.value == ""){
            alert("아이디를 입력하세요");
            idTag.focus();
            return false;
        }

        formTag.submit();
    }
</script>
</body>
</html>
