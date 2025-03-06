package model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NaverAPIResult(List<NaverAPIResultItem> items, String lastBuildDate) {}
