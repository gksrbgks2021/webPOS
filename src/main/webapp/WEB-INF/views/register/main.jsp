<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
    <html>

    <head>
        <title>메인화면</title>
    </head>

    <body>
        <h1>편의점 POS 시스템</h1>
        <h2><strong>${registerRequest.name} 님</strong> 환영합니다!</h2>
        <form action="login" method="post">
            <input
                type="submit" value="로그아웃">
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