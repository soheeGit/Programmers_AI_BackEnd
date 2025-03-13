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
<%
    String answer = (String) request.getAttribute("answer");
    String prompt = (String) request.getAttribute("prompt"); %>
<form>
    <section class="title">
        프롬프트 : <%= prompt %>
    </section>
    <section>
        답변 : <%= answer %>
    </section>
    <input name="prompt" placeholder="프롬프트를 입력해주세요">
    <button>제출</button>
</form>
</body>
</html>