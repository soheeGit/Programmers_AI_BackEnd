<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>주식 거래소에 잘 오셨읍니다</title>
</head>
<body>
    <p>돈 많이 벌어가세요!</p>
    <p><%= request.getAttribute("accounts") %></p>
    <form method="POST">
        <input hidden name="account_id" value="0">
        <label>
            별명:
            <input type="text" name="nickname">
        </label>
        <button>버튼</button>
    </form>
    <form action="delete">
        <input hidden name="account_id" value="0">
        <label>
            아이디:
            <input type="text" name="id">
        </label>
        <button>버튼</button>
    </form>
</body>
</html>