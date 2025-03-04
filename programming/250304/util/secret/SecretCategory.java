package util.secret;

public enum SecretCategory {
    SLACK_BOT_URL("SLACK_BOT_URL"),     TOGETHER_API_KEY("TOGETHER_API_KEY");

    public final String key;
    private SecretCategory(String key) {
        this.key = key;
    };
}