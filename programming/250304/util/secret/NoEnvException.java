package util.secret;

public class NoEnvException extends Exception {
    public NoEnvException(String key) {
        super("존재하지 않는 환경변수 %s".formatted(key));
    }
}