<%@ page import="java.net.http.HttpClient" %>
<%@ page import="java.net.http.HttpRequest" %>
<%@ page import="java.net.URI" %>
<%@ page import="java.net.http.HttpResponse" %>
<%@ page import="io.github.cdimascio.dotenv.Dotenv" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
<%@ page import="com.fasterxml.jackson.core.type.TypeReference" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>요청 객체 다루기</title>
  <style>
    @font-face {
      font-family: 'RixYeoljeongdo_Regular';
      src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_2102-01@1.0/RixYeoljeongdo_Regular.woff') format('woff');
      font-weight: normal;
      font-style: normal;
    }
    @font-face {
      font-family: 'ChosunGu';
      src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@1.0/ChosunGu.woff') format('woff');
      font-weight: normal;
      font-style: normal;
    }
    * {
      font-family: ChosunGu, serif;
      padding: 0;
      margin: 0;
    }
    .title {
      font-family: RixYeoljeongdo_Regular, serif;
    }
    form {
      width: 100%;
      height: 100vh;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      gap: 1rem;
    }
    form * {
      width: 50%;
    }
  </style>
</head>
<body>
<%! HttpClient client = HttpClient.newHttpClient(); %>
<%! Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load(); %>
<%! ObjectMapper mapper = new ObjectMapper(); %>
<%! String answer = ""; %>
<%
  Map<String, List<Map<String, List<Map<String, String>>>>> geminiMap = new HashMap<>();
  List<Map<String, String>> parts = List.of(new HashMap<>());
  parts.get(0).put("text", request.getParameter("prompt"));
  List<Map<String, List<Map<String, String>>>> contents = List.of(new HashMap<>());
  contents.get(0).put("parts", parts);
  geminiMap.put("contents", contents);
  HttpRequest httpRequest = HttpRequest.newBuilder()
          .uri(URI.create("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=%s".formatted(
                  dotenv.get("GEMINI_KEY")
          )))
          .POST(HttpRequest.BodyPublishers.ofString(
                  mapper.writeValueAsString(geminiMap)))
          .build();
  try {
    HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    answer = httpResponse.body();
  } catch (InterruptedException e) {
    throw new RuntimeException(e);
  }
%>
<form>
  <section class="title">
    프롬프트 : <%= request.getParameter("prompt") %>
  </section>
  <section>
    답변 : <%= answer %>
  </section>
  <input name="prompt" placeholder="프롬프트를 입력해주세요">
  <button>제출</button>
</form>
</body>
</html>