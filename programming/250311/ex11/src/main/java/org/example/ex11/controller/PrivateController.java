package org.example.ex11.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.ex11.model.GeminiResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/private")
public class PrivateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpClient client = HttpClient.newHttpClient();
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        ObjectMapper mapper = new ObjectMapper();
        String answer = "";
        String prompt = req.getParameter("prompt");
        if (prompt == null || prompt.isBlank()) {
            prompt = "프롬프트가 없습니다";
            answer = "프롬프트를 제대로 입력해주세요!";
        } else {
            prompt += " no markdown, under 300 character, use korean language, nutshell please";
            Map<String, List<Map<String, List<Map<String, String>>>>> geminiMap = new HashMap<>();
            List<Map<String, String>> parts = List.of(new HashMap<>());
            parts.get(0).put("text", prompt);
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
//            answer = httpResponse.body();
                answer = mapper.readValue(httpResponse.body(), GeminiResponse.class).candidates().get(0).content().parts().get(0).text();
            } catch (Exception e) {
//            throw new RuntimeException(e);
                answer = e.getMessage();
            }
        }
        req.setAttribute("answer", answer);
        req.setAttribute("prompt", prompt);
        // forward -> 주소를 유지시키고
        // + request 객체도 유지시킴
        // -> 내가 처리한 다음에 넘겨도 된다
        req.getRequestDispatcher("/WEB-INF/private.jsp").forward(req, resp);
    }
}