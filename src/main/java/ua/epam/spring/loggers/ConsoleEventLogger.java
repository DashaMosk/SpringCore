package ua.epam.spring.loggers;

import ua.epam.spring.beans.Event;

/**
 * Created by Daria on 05.02.2016.
 */
public class ConsoleEventLogger implements EventLogger {
    public void logEvent(Event event) {
        System.out.println(event);
    }
}
