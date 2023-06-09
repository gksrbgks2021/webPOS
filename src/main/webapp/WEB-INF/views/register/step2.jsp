<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>회원가입</title>
        <script>
            function checkPassword() {
                let password = document.getElementById("password").value;
                let confirmPasswordInput = document.getElementById("confirmPassword");
                let confirmPassword = confirmPasswordInput.value;
                let emailInput = document.getElementById("email");
                let email = emailInput.value;
                let emailRegex = /^[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*@[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*\.[a-zA-Z]{2,3}$/;

                if (password !== confirmPassword) {
                    alert("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
                    confirmPasswordInput.focus();
                    return false;
                }

                if (!emailRegex.test(email)) {
                    alert("유효한 이메일 주소를 입력해주세요.");
                    emailInput.focus();
                    return false;
                }
                return true;
            }
        </script>
    </head>

    <body>
        <h2>회원 정보 입력</h2>
        <form action="step3" method="post" onsubmit="return checkPassword()">
            <label for="email">이메일:</label><br>
            <input type="text" id="email" name="email" value="${registerform.email}"><br>

            <label for="name">이름:</label><br>
            <input type="text" id="name" name="name" value="${registerform.name}"><br>

            <label for="password">비밀번호:</label><br>
            <input type="password" id="password" name="password" value="${registerform.password}"><br>

            <label for="confirmPassword">비밀번호 확인:</label><br>
            <input type="password" id="confirmPassword" name="confirmPassword" value="${registerform.confirmPassword}"><br>

            <select name="role" size='1' >
                <option value='' ${registerform.role == null ? 'selected' : ''}>-- 선택 --</option>
                <option value='staff' ${registerform.role == 'staff' ? 'selected' : ''}>직원</option>
                <option value='manager' ${registerform.role == 'manager' ? 'selected' : ''}>매니저</option>
            </select>

            <input type="submit" value="가입 완료">

        </form>
    </body>

    </html>