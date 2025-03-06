package util.logger;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class MyLogger {
    private final Logger logger;
    public MyLogger(Class<?> clazz) {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        this.logger = Logger.getLogger(clazz.getName());
//        this.logger.setLevel(Level.parse(dotenv.get("LOG_LEVEL")));
//        this.logger.setLevel(dotenv.get("MODE").equals("DEV") ? Level.INFO : Level.SEVERE);
        String mode = dotenv.get("MODE");
        if (mode.isBlank()) {
            throw new RuntimeException("mode is missing");
        }
        switch (mode) {
            case "DEV":
                this.logger.setLevel(Level.INFO);
                break;
            case "PROD":
                this.logger.setLevel(Level.SEVERE);
                break;
        }
    }

    public void info(String msg) {
        LogRecord record = new LogRecord(Level.INFO, msg);
        record.setSourceClassName(logger.getName()); // 원래 클래스 이름 설정
        logger.log(record);
    }

    public void severe(String msg) {
        LogRecord record = new LogRecord(Level.SEVERE, msg);
        record.setSourceClassName(logger.getName());
        logger.log(record);
    }
}