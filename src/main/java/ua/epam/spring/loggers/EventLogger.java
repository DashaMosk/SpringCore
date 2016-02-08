package ua.epam.spring.loggers;

import ua.epam.spring.beans.Event;

/**
 * Created by Daria on 05.02.2016.
 */
public interface EventLogger {
    void logEvent(Event event);
}
