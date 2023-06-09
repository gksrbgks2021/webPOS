<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head> <title>회원가입</title> </head>
<body>
<!--이메일 중복 체크 -->
<script>

    function CheckMember() {
        let form = document.form;
        let password1 = form.password.value;
        let password2 = form.password1.value;
        let regExpName = /^[가-힣]*$/;
        let regExpEmail = /^[a-zA-Z0-9]([-_\.]?[0-9a-zA-Z])*@[a-zA-Z0-9]([-_\.]?[a-zA-Z0-9])*\.[a-zA-Z]{2,3}$/i;
        let mail1 = form.mail1.value;
        let mail2 = form.mail2.value;
        let email = mail1 + "@"	+ mail2;
        let name = form.name.value;
        //패스워드 일치 체크

        if (password1.localeCompare(password2) !== 0) {
            alert(" 같은 비밀번호를 입력하세요 ");
            password1.select();
            password1.focus();
            return ;
        }

        if (!regExpEmail.test(email)) {
            alert("이메일 입력을 확인해주세요.");
            document.newMember.email1.focus();
            document.newMember.email1.value = "";
            document.newMember.email2.value = "";
            return ;
        }
        function check(regExp, e, msg) {
            if (regExp.test(e.value)) {
                return true;
            }
            alert(msg);
            e.select();
            e.focus();
            return false;
        }
        form.submit();
    }
</script>
<h2>회원 정보 입력</h2>
<form action="step3" method="post">
    <p> <label>이메일:<br>
        <input type="text" name="email" id="email">
    </label>
        <button type ="button" onclick = "fn_dbCheckId()" name=""dbCheckId "class = "checkId">
        중복 확인
        </button>
    </p>
    <!--이메일 중복 체크 구현 -->

    <!---->

    <p> <label>이름:<br>
        <input type="text" name="name" id="name" required>
    </label> </p>
    <p> <label>비밀번호:<br>
        <input type="password" name="password" id="password" required>
    </label> </p>
    <p> <label>비밀번호 확인:<br>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
    </label> </p>

    <select name="role" size = '1' required>
        <option value ='' selected>-- 선택 --</option>
        <option value ='staff'>직원</option>
        <option value ='manager'>매니저</option>
    </select>

    <input type="submit" onclick="javascript:CheckMember()" value="가입 완료" required >
</form>

</body>
</html>