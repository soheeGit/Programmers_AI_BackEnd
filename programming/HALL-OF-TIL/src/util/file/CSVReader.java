package util.file;

import model.dto.Repo;

import java.io.FileNotFoundException;
import java.util.List;

public interface CSVReader {
    List<Repo> read(String path) throws FileNotFoundException;
}