package model.dto;

import java.util.Map;

public record APIClientParam(String url, String method, Map<String, String> body, String... headers) {
}