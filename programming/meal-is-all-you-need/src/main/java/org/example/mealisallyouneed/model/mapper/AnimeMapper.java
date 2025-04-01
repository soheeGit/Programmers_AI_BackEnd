package org.example.mealisallyouneed.model.mapper;

import org.example.mealisallyouneed.model.vo.Anime;

import java.util.List;

public interface AnimeMapper {
    int insertAnime(Anime anime);
    List<Anime> getAllAnimes();
    int insertAnimeVote(String uuid);
}