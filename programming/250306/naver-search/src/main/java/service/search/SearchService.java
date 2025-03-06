package service.search;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import model.dto.APIClientParam;
import model.dto.NaverAPIResult;
import model.dto.NaverAPIResultItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.api.APIClient;
import util.logger.MyLogger;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class SearchService {
    private static final Logger log = LogManager.getLogger(SearchService.class);
    private final String clientID;
    private final String clientSecret;
    private final MyLogger logger;
    private final APIClient apiClient;

    public SearchService() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.clientID = dotenv.get("NAVER_CLIENT_ID");
        this.clientSecret = dotenv.get("NAVER_CLIENT_SECRET");
        if (clientID == null || clientSecret == null) {
            throw new RuntimeException("NaverSearchAPI: clientID or clientSecret are missing");
        }
        this.logger = new MyLogger(SearchService.class);
        this.apiClient = new APIClient();
//        logger.info(clientID);
//        logger.info(clientSecret);
        logger.info("NaverSearchAPI initailized");
    }

    public List<NaverAPIResultItem> searchByKeyword(String keyword) throws IOException, InterruptedException {
        logger.info("Search by keyword: " + keyword);
        // https://developers.naver.com/docs/serviceapi/search/news/news.md
        HashMap<String, String> body = new HashMap<>();
        APIClientParam param = new APIClientParam(
                "https://openapi.naver.com/v1/search/news.json?query=%s".formatted(keyword),
                "GET",
                body
                , "X-Naver-Client-Id", clientID, "X-Naver-Client-Secret", clientSecret
        );
//        logger.info(apiClient.callAPI(param));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(apiClient.callAPI(param), NaverAPIResult.class).items();
    }
}