package model.dto;

public record Repo(
        String name,
        String link,
        String category,
        RepoType repoType
) {
    public static Repo makeRepo(String name, String link) {
        return new Repo(name, link, "", handleRepoType(link));
    }

    public static Repo makeRepo(String name, String link, String category) {
        return new Repo(name, link, category, handleRepoType(link));
    }

    private static RepoType handleRepoType(String link) {
        if (link.contains("github")) {
            return RepoType.GITHUB;
        } else if (link.contains("tistory")) {
            return RepoType.TISTORY;
        } else if (link.contains("velog")) {
            return RepoType.VELOG;
        }
        throw new IllegalArgumentException();
    }
}