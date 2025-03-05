package service.github;

import model.dto.Repo;
import model.dto.TIL;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TILServiceImpl implements TILService {
    @Override
    public List<TIL> getTIL(Repo repo) throws Exception {
        Logger logger = Logger.getLogger("service.github");
        HttpClient client = HttpClient.newHttpClient();
//        try {
        switch (repo.repoType()) {
            case GITHUB:
                String[] tokens = repo.link().split("/");
                logger.info(Arrays.toString(tokens));
                HttpResponse<String> response = client.send(HttpRequest.newBuilder()
                        .uri(URI.create("https://api.github.com/repos/%s/%s/issues".formatted(tokens[3], tokens[4])))
                        .header("Accept", "application/vnd.github+json")
                        .build(), HttpResponse.BodyHandlers.ofString());
                logger.info("%d".formatted(response.statusCode()));
//                    logger.info(response.body());
                Pattern pattern = Pattern.compile("\"body\":\"(.*?)\",\""); // 수정된 정규 표현식 (최소 매칭)
                Matcher matcher = pattern.matcher(response.body());
                List<TIL> tils = new ArrayList<>();
                while (matcher.find()) {
//                    logger.info(matcher.group(1));
                    tils.add(new TIL(
                            repo.name(),
                            repo.link(),
                            repo.name() + "의 TIL",
                            matcher.group(1),
                            new Date().toString()
                    ));
                }
                return tils;
            case VELOG:
            case TISTORY:
                throw new Exception("지원하지 않는 종류");
        }
//        } catch (Exception e) {
//            logger.severe(e.getMessage());
//        }
        return List.of();
    }

}