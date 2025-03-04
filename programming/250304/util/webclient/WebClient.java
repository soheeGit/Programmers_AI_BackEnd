package util.webclient;

import util.logger.MyLogger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class WebClient implements IWebClient {
    private final HttpClient httpClient;
    private final MyLogger logger;
    private WebClient() {
        httpClient = HttpClient.newHttpClient();
        logger = MyLogger.getLogger();
        logger.info("WebClient 생성");
    }
    private static WebClient instance;

    public static WebClient getWebClient() {
        if (instance == null) {
            instance = new WebClient();
        }
        return instance;
    }

    @Override
    public String sendRequest(HttpRequest request) throws IOException, InterruptedException {
        logger.debug("Request 요청 전달 시도");
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        logger.debug(Integer.toString(response.statusCode()));
        return response.body();
    }

    @Override
    public HttpRequest makeRequest(Map<String, String> map) {
        logger.debug("Request 객체 생성 시도");
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .uri(URI.create(map.get("url")))
                .method(map.get("method"), HttpRequest.BodyPublishers.ofString(map.get("body")));
        if (map.containsKey("headers")) {
            builder = builder.headers(map.get("headers").split(";"));
        }
        return builder.build();
    }
}

interface IWebClient {
    String sendRequest(HttpRequest request) throws IOException, InterruptedException;

    HttpRequest makeRequest(Map<String, String> map);
}