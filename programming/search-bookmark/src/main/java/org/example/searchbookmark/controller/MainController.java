package org.example.searchbookmark.controller;

import jakarta.servlet.http.HttpSession;
import org.example.searchbookmark.model.vo.KeywordSearch;
import org.example.searchbookmark.service.BookmarkService;
import org.example.searchbookmark.service.SearchService;
import org.example.searchbookmark.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/") // 이런 경우는 생략해도 무방함
public class MainController {
    final private MyLogger logger = new MyLogger(this.getClass().getSimpleName());

    // 멤버변수
    private final SearchService searchService;
    private final BookmarkService bookmarkService;
    // 구별을 굳이 안해도 돼 -> 1:1 대응이 되니까

    // 생성자 주입 -> 의존성 주입을 한 타입은? SearchService
    public MainController(SearchService searchService, BookmarkService bookmarkService) {
        this.searchService = searchService;
        this.bookmarkService = bookmarkService;
    }

    // [Form]
    // RequestParam -> 일부분만 받을 때 (input -> name)
    // ModelAttribute -> 전부가 특정한 dto, 엔티티와 매칭될 때
    @GetMapping
    public String index(Model model,
//                        @RequestParam("keyword") String keyword
                        // required = true 가 숨겨져 있어
                        @RequestParam(value = "keyword",
//                                defaultValue = "야구 순위",
                                required = false) String keyword,
                        HttpSession session
    ) throws Exception {
        logger.info(keyword);
        if (keyword == null) {
            return "index";
        }
        List<KeywordSearch> result = searchService.searchByKeyword(keyword);
        Map<String, KeywordSearch> map = new HashMap<>();
        for (KeywordSearch keywordSearch : result) {
            map.put(keywordSearch.uuid(), keywordSearch);
        }
        // 임시 uuid 가지고 있으면 검색결과를 쉽게 불러오게 하려고...
        session.setAttribute("temp", map); // 일종의 캐싱
        // 레디스 같은 걸로 나중엔...
        model.addAttribute("result", result);
        return "index";
    }

    @PostMapping("/bookmark")
    public String bookmark(@RequestParam("uuid") String uuid, Model model, HttpSession session) throws Exception {
        // 임시 저장 후 꺼내다 쓴 것
        Map<String, KeywordSearch> temp = (HashMap<String, KeywordSearch>) session.getAttribute("temp");
//         logger.info(temp.get(uuid).link());
        bookmarkService.createBookmark(temp.get(uuid));
        return "redirect:/"; // servlet으로 보내기
    }
}