package util.logger;

import java.util.logging.Level;

public enum MyLoggerLevel {
    DEBUG(Level.FINE), INFO(Level.INFO), WARN(Level.WARNING), ERROR(Level.SEVERE);
    final Level level;

    MyLoggerLevel(Level level) {
        this.level = level;
    }
}