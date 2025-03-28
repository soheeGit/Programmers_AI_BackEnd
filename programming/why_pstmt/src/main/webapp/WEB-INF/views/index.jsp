<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>안녕 안녕</title>
</head>
<body>
<% String message = (String) request.getAttribute("message"); %>
<%
  if (message == null) {
%>
<h1>로그인 하세요!</h1>
<form action method="post">
  <label>
    유저이름 : <input type="text" name="username" /><br/>
  </label>
  <label>
    비밀번호 : <input type="password" name="password" /><br/>
  </label>
  <button>로그인</button>
</form>
<%
} else {
%>
<h1><%= request.getAttribute("message")%></h1>
<%
  }
%>
<h1></h1>
<header>
  <nav>
    <ul>
      <li>
        <a href="join">가입</a>
      </li>
    </ul>
  </nav>
</header>

</body>
</html>