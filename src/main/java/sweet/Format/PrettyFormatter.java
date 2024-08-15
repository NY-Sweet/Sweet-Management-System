package sweet.Format;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class PrettyFormatter extends Formatter {
    // ANSI escape codes for coloring
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BOLD = "\u001B[1m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";

    @Override
    public String format(LogRecord record) {
        String levelColor;
        switch (record.getLevel().getName()) {
            case "SEVERE":
                levelColor = ANSI_RED;  // Red for severe messages
                break;
            case "WARNING":
                levelColor = ANSI_RED;  // Yellow for warnings
                break;
            case "INFO":
                levelColor = ANSI_GREEN;  // Green for info messages
                break;
            default:
                levelColor = ANSI_BLUE;  // Blue for everything else
        }
        return ANSI_BOLD + levelColor + formatMessage(record) + ANSI_RESET + System.lineSeparator();
    }
}
