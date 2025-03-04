package util.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.Logger;

public class MyLogger implements ILogger {
    private MyLogger() {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(MyLoggerLevel.DEBUG.level);
        logger = Logger.getLogger(MyLogger.class.getName());
        logger.addHandler(handler);
        logger.setLevel(MyLoggerLevel.DEBUG.level);
        logger.setUseParentHandlers(false);
    } // 생성자를 private
    private final Logger logger;
    private static MyLogger instance;

    public static MyLogger getLogger() {
        if (instance == null) {
            instance = new MyLogger();
        }
        return instance;
    }

    public void setLevel(MyLoggerLevel level) {
        this.logger.setLevel(level.level);
        System.out.println(level.level);
        System.out.println(logger.getLevel());
    }

    @Override
    public void debug(String msg) {
        this.logger.fine(msg);
    }

    @Override
    public void info(String msg) {
        this.logger.info(msg);
    }

    @Override
    public void warn(String msg) {
        this.logger.warning(msg);
    }

    @Override
    public void error(String msg) {
        this.logger.severe(msg);
    }
}

interface ILogger {
    void debug(String msg);
    void info(String msg);
    void warn(String msg);
    void error(String msg);
}