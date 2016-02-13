package ua.epam.spring.loggers;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ua.epam.spring.beans.Event;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

/**
 * Created by Daria on 05.02.2016.
 */
@Component
public class FileEventLogger implements EventLogger {
    @Value("eventLog.log")
    private File file;
    public FileEventLogger(){}
    public FileEventLogger(String fileName) {
        this.file = new File(fileName);
    }

    @PostConstruct
    public void init() throws IOException {
        file.canWrite();
    }
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(),true);
        } catch (IOException ex) {
            System.out.println("Something wrong");
        }

    }
}
