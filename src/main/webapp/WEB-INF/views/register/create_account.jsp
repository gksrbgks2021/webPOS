<%@ page contentType="text/html; charset=utf-8" %>
    <!DOCTYPE html>
    <html>

    <head>
        <title>계정 생성</title>
    </head>

    <body>
        <h2>정보 입력</h2>
        <form action="main" method="post">
            <p> <label>이름:<br><input type="text" name="name" id="name" value="${registerRequest.name}"></label> </p>
            <p> <label>ID:<br><input type="text" name="ID" id="ID" value="${registerRequest.ID}"></label> </p>
            <p> <label>PASSWORD:<br><input type="password" name="password" id="${registerRequest.password}"></label> </p>
            <p> <label>EMAIL:<br><input type="text" name="email" id="email" value="${registerRequest.email}"></label> </p>
            <p> <label>직급:<br><input type="text" name="role" id="${registerRequest.role}"></label> </p><input
                type="submit" value="완료">
        </form>
    </body>

    </html>