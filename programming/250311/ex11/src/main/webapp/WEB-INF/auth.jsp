<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta charset="UTF-8">
<head>
  <title>세션과 쿠키</title>
</head>
<body>
<p>세션 좋아! 쿠키 좋아!</p>
<p>세션이 전하는 행운의 숫자 : <%= session.getAttribute("lucky_number")%></p>
<p>쿠키가 전하는 행운의 숫자 : <%= Arrays.stream(request.getCookies()).filter(cookie -> cookie.getName().equals("cookie_number")).findFirst().orElse(new Cookie("cookie_number", "-1")).getValue() %></p>
<form method="post">
  <label>
    아이디
    <input name="id">
  </label>
  <label>
    비밀번호
    <input name="pw" type="password">
  </label>
  <button>로그인</button>
</form>
</body>
</html>