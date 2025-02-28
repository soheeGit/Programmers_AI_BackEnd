import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Solution05 {
    public static void main(String[] args) {
        Monitoring monitoring = new Monitoring();
        String prompt = System.getenv("LLM_PROMPT");
        String llmResult = useLLM(prompt);
        System.out.println(llmResult);
        String encodedKeyword = URLEncoder.encode("한국영화 " + llmResult, StandardCharsets.UTF_8);
        monitoring.getNews("한국영화"+encodedKeyword, 1, 1, SortType.sim, llmResult);
    }
    public static String useLLM(String prompt) {
        String apiUrl = System.getenv("TOGETHER_URL");
        String apiKey = System.getenv("TOGETHER_API_KEY");
        String model = System.getenv("TOGETHER_MODEL");
        String payload = """
                {
                  "messages": [
                    {
                      "role": "user",
                      "content": "%s"
                    }
                  ],
                  "model": "%s"
                }
                """.formatted(prompt, model);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        String contentTitle = null;
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            System.out.println("response.statusCode() = " + response.statusCode());
            System.out.println("response.body() = " + response.body());
            String responseBody = response.body();
            contentTitle = responseBody.split("\"content\": \"")[1].split("\"")[0];
            System.out.println("contentTitle = " + contentTitle);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return contentTitle;
    }
}

enum SortType {
    sim("sim"), date("date");

    final String value;

    SortType(String value) {
        this.value = value;
    }
}

class Monitoring {
    private final Logger logger;

    public Monitoring() {
        logger = Logger.getLogger(Monitoring.class.getName());
        logger.setLevel(Level.SEVERE);
        logger.info("Monitoring 객체 생성");
    }

    // 1. 검색어를 통해서 최근 1개의 뉴스를 받아올게요
    public void getNews(String keyword, int display, int start, SortType sort, String llmResult) {
        String token = System.getenv("GITHUB_TOKEN");
        String OWNER = "soheeGit";
        String REPO = "auto_monitoring";
        String imageLink = "";
        try {
            String response = getDataFromAPI("news.json", keyword, display, start, sort, llmResult);
            String[] tmp = response.split("title\":\"");
            // 0번째를 제외하곤 데이터
            String[] result = new String[display];
            for (int i = 1; i < tmp.length; i++) {
                result[i - 1] = tmp[i].split("\",")[0];
            }
            logger.info(Arrays.toString(result));
            String dir = "file";
            File directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdirs(); // 파일 디렉토리가 없으면 생성
            }

            File file = new File(directory, "%d_%s.txt".formatted(new Date().getTime(), llmResult));

            if (!file.exists()) {
                logger.info(file.createNewFile() ? "신규 생성" : "이미 있음");
            }
            try (FileWriter fileWriter = new FileWriter(file)) {
                for (String s : result) {
                    fileWriter.write(s + "\n");
                }
                logger.info("기록 성공");
            } // flush 및 close.
            logger.info("제목 목록 생성 완료");
            String imageResponse = getDataFromAPI("image", keyword, display, start, SortType.sim, llmResult);
            // 2. 이미지
            imageLink = imageResponse
                    .split("link\":\"")[1].split("\",")[0]
                    .split("\\?")[0]
                    .replace("\\", "");
            logger.info(imageLink);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(imageLink))
                    .build();
            String[] tmp2 = imageLink.split("\\.");
            Path path = Path.of(dir, "%d_%s.%s".formatted(
                    new Date().getTime(), llmResult, tmp2[tmp2.length - 1]));
            HttpClient.newHttpClient().send(request,
                    HttpResponse.BodyHandlers.ofFile(path));
            createGitHubIssue(OWNER, REPO, llmResult, imageLink, result, token);
        } catch (Exception e) {
            logger.severe(e.getMessage());
        }
    }

    private void createGitHubIssue(String owner, String repo, String title, String body, String[] result, String token) {
        try {
            String url = "https://api.github.com/repos/%s/%s/issues".formatted(owner, repo);
            String jsonBody = """
                    {
                      "title": "%s",
                      "body": "%s![이미지](%s)"
                    }
                    """.formatted(title, result.length > 0 ? result[0] : "내용 없음", body);
            logger.info("JSON Body: " + jsonBody);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .header("Authorization", "Bearer " + token)
                    .header("Accept", "application/vnd.github.v3+json")
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

            // 🌟 응답 로그 추가
            logger.info("GitHub API Response Code: " + response.statusCode());
            logger.info("GitHub API Response Body: " + response.body());

            if (response.statusCode() == 201) {
                logger.info("✅ GitHub 이슈 생성 완료");
            } else {
                logger.warning("⚠️ GitHub 이슈 생성 실패. 응답 확인 필요!");
            }
        } catch (Exception e) {
            logger.severe("GitHub 이슈 생성 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getDataFromAPI(String path, String keyword, int display, int start, SortType sort, String llmResult) throws Exception {
        String url = "https://openapi.naver.com/v1/search/%s".formatted(path);
        String params = "query=%s&display=%d&start=%d&sort=%s".formatted(
                keyword, display, start, sort.value
        );
        HttpClient client = HttpClient.newHttpClient(); // 클라이언트
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + "?" + params))
                .GET()
                .header("X-Naver-Client-Id", System.getenv("NAVER_CLIENT_ID"))
                .header("X-Naver-Client-Secret", System.getenv("NAVER_CLIENT_SECRET"))
                .build();
        try {
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
            logger.info(Integer.toString(response.statusCode()));
            logger.info(response.body());
            return response.body();
        } catch (Exception e) {
            logger.severe(e.getMessage());
            throw new Exception("연결 에러");
        }
    }
}
