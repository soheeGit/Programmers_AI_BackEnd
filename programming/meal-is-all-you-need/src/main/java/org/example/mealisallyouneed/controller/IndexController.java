package org.example.mealisallyouneed.controller;

import org.apache.ibatis.session.SqlSession;
import org.example.mealisallyouneed.config.MyBatisConfig;
import org.example.mealisallyouneed.model.mapper.AnimeMapper;
import org.example.mealisallyouneed.model.mapper.TestMapper;
import org.example.mealisallyouneed.model.vo.Anime;
import org.example.mealisallyouneed.model.vo.AnimeRequestDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class IndexController {
    final Logger logger = Logger.getLogger(IndexController.class.getName());
    @GetMapping("/")
    public String index(Model model) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
//            TestMapper testMapper = session.getMapper(TestMapper.class);
//            int result = testMapper.selectOnePlusOne();
            AnimeMapper animeMapper = session.getMapper(AnimeMapper.class);
            List<Anime> result = animeMapper.getAllAnimes();
            model.addAttribute("result", result);
        }
        return "index";
    }

    @PostMapping("/anime")
    public String anime(Model model, @ModelAttribute AnimeRequestDTO dto) {
        try (SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            AnimeMapper animeMapper = session.getMapper(AnimeMapper.class);
            int count = animeMapper.insertAnime(
                    new Anime(
                            UUID.randomUUID().toString(),
                            dto.title(), dto.description(),
                            "", // default 라서
                            0
                    ));
            logger.info(count + " anime inserted");
            // 커밋해주셔야해요... ㅠㅠ
            session.commit();
        }
//        return "index";
        return "redirect:/";
    }

    @PostMapping("/vote")
    public String vote(@RequestParam("uuid") String uuid) {
        try(SqlSession session = MyBatisConfig.getSqlSessionFactory().openSession()) {
            AnimeMapper animeMapper = session.getMapper(AnimeMapper.class);
            animeMapper.insertAnimeVote(uuid);
            session.commit();
        }
        return "redirect:/";
    }
}