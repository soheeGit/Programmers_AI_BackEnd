package service.github;

import model.dto.TIL;
import model.dto.Repo;

import java.io.IOException;
import java.util.List;

public interface TILService {
    List<TIL> getTIL(Repo repo) throws Exception;
}