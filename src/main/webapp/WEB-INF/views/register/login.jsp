<%@ page contentType="text/html; charset=utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>로그인</title>
    </head>

    <body>
        <h1>편의점 POS 시스템</h1>
        <h2>로그인</h2>
        <form action="main" method="post">
            <p> <label>ID:<br><input type="text" name="ID" id="ID"></label> </p>
            <p> <label>PASSWORD:<br><input type="text" name="password" id="password"></label> </p>
            <input
                type="submit" value="login">
        </form>
        <form action="create_account" method="post">
            <input type="submit" value="create an account">
        </form>
    </body>

    </html>

    무야