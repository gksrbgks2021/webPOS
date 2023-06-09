<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <title>로그인</title>
    </head>
    <body>
        <h1>편의점 POS 시스템</h1>
        <h2>로그인</h2>
        <form action="#" method="post">
            <p> <label>ID:<br><input type="text" name="ID" id="ID" required></label> </p>
            <p> <label>PASSWORD:<br><input type="text" name="password" id="password" required></label> </p>
            <input
                type="submit" value="login">
        </form>
        <form action="register/step1" method="post">
            <input type="submit" value="create an account">
        </form>

        <script type="text/javascript">

            function sendit() {
                //form태그의 name값
                let formTag = document.joinForm;
                //input태그의 name값으로 값 받아오기
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