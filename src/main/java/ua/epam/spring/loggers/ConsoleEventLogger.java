package ua.epam.spring.loggers;

import org.springframework.stereotype.Component;
import ua.epam.spring.beans.Event;

/**
 * Created by Daria on 05.02.2016.
 */
@Component("consoleeventlogger")
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
