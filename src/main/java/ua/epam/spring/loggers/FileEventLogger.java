package ua.epam.spring.loggers;

import org.apache.commons.io.FileUtils;
import ua.epam.spring.beans.Event;

import java.io.File;
import java.io.IOException;

/**
 * Created by Daria on 05.02.2016.
 */
public class FileEventLogger implements EventLogger {
    private File file;
    public FileEventLogger(){}
    public FileEventLogger(String fileName) {
        this.file = new File(fileName);
    }

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
