<%@ page import="org.example.jpaexercise.model.dto.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>사장님 추첨은 재미로 하셔야 합니다</title>
</head>
<body>
<code>도박 중독 상담은 1336</code>
<section>
    <%
        for (Student s : (List<Student>) request.getAttribute("students")) {
    %>
    <ul>
        <li><%= s.getId() %></li>
        <li><%= s.getName() %></li>
    </ul>
    <% } %>
</section>
</body>
</html>