<%@ page contentType="text/html; charset=utf-8" %>
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
    </body>

    </html>