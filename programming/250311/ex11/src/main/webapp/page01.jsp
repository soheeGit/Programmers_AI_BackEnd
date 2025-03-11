<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<%-- 서버에서 실행이 된다 --%>
<% System.out.println("Hello"); %>
<% response.getWriter().println("Hello 2"); %>
<%= "Hello3" %>
<%! int i = 0; %>
<%= i++ %>
<%= i++ %>
<%= i++ %>
<%= i++ %>
<%= i++ %>
</body>
</html>