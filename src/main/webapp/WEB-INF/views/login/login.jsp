<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>편의점 POS - 로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <style>
        .login-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            background-color: #ffffff;
        }
        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 10px;
            font-size: 16px;
        }
        .btn-primary:hover {
            background-color: #0056b3;
        }
        .form-control {
            font-size: 16px;
            padding: 10px;
        }
        .input-group-text {
            background-color: #f8f9fa;
        }
    </style>
</head>
<body>
<div class="login-container">
    <h2 class="text-center mb-4">편의점 POS 로그인</h2>
    
    <c:if test="${not empty error}">
        <div class="alert alert-danger" role="alert">${error}</div>
    </c:if>

    <form action="login" method="post">
        <div class="mb-3">
            <label for="id" class="form-label">아이디</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-user"></i></span>
                <input type="text" name="id" id="id" class="form-control" autocomplete="username" required aria-describedby="idHelp">
            </div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">비밀번호</label>
            <div class="input-group">
                <span class="input-group-text"><i class="fas fa-lock"></i></span>
                <input type="password" name="password" id="password" class="form-control" autocomplete="current-password" required>
                <button type="button" class="btn btn-outline-secondary" onclick="togglePassword()">
                    <i class="fas fa-eye"></i>
                </button>
            </div>
        </div>
        <div class="mb-3 form-check">
            <input type="checkbox" class="form-check-input" id="rememberMe" name="rememberMe">
            <label class="form-check-label" for="rememberMe">로그인 유지</label>
        </div>
        <button type="submit" class="btn btn-primary w-100" id="loginBtn">로그인</button>
    </form>

    <div class="mt-3 text-center">
        <a href="forgot-password" class="text-muted">비밀번호를 잊으셨나요?</a> | 
        <a href="register" class="text-muted">계정 생성</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js"></script>
<script>
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const toggleBtn = document.querySelector('.btn-outline-secondary i');
        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            toggleBtn.classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            passwordField.type = 'password';
            toggleBtn.classList.replace('fa-eye-slash', 'fa-eye');
        }
    }

    document.querySelector('form[action="login"]').addEventListener('submit', function(e) {
        const loginBtn = document.getElementById('loginBtn');
        loginBtn.disabled = true;
        loginBtn.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> 로딩 중...';
    });
</script>
</body>
</html>