import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Logger {
    private final static DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy / HH:mm:ss");
    private StringBuilder logger = new StringBuilder();
    private final Formatter LOG_FORMATTER = new Formatter(logger);

    private final static String LOG_FORMAT3 = "%s | %s | %s | %s\n";
    private final static String LOG_FORMAT2 = "%s | %s | %s\n";
    private final static String LOG_FORMAT1 = "%s | %s \n";
    private File file;

    public Logger(File file) {
        this.file = file;
    }

    public Logger() {
    }

    public void setFileForSave(File file) {
        this.file = file;
    }

    public void writeToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            writer.write(logger.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addToLog(String param1) {
        LOG_FORMATTER.format(LOG_FORMAT1, getCurrentDateTime(), param1);
    }
    public void addToLog(String param1, String param2, String param3) {
        LOG_FORMATTER.format(LOG_FORMAT3, getCurrentDateTime(), param1, param2, param3);
    }

    public void addToLog(String param1, String param2) {
        LOG_FORMATTER.format(LOG_FORMAT2, getCurrentDateTime(), param1, param2, getCurrentDateTime());
    }

    public String getCurrentDateTime() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER);
    }
}
