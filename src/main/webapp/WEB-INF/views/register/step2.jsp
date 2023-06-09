<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>회원가입</title>
        <script>
            function checkPassword() {
                var password = document.getElementById("password").value;
                var confirmPasswordInput = document.getElementById("confirmPassword");
                var confirmPassword = confirmPasswordInput.value;
                var emailInput = document.getElementById("email");
                var email = emailInput.value;
                var emailRegex = /^[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*@[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*\.[a-zA-Z]{2,3}$/;

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
            <input type="text" id="email" name="email"><br>

            <label for="name">이름:</label><br>
            <input type="text" id="name" name="name"><br>

            <label for="password">비밀번호:</label><br>
            <input type="password" id="password" name="password"><br>

            <label for="confirmPassword">비밀번호 확인:</label><br>
            <input type="password" id="confirmPassword" name="confirmPassword"><br>

            <select name="role" size='1'>
                <option value='' selected>-- 선택 --</option>
                <option value='staff'>직원</option>
                <option value='manager'>매니저</option>
            </select>

            <input type="submit" value="가입 완료">

        </form>
    </body>

    </html>