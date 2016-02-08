package ua.epam.spring.loggers;

import ua.epam.spring.beans.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 06.02.2016.
 */
public class CombinedEventLogger implements EventLogger {
    private List<EventLogger> logers;

    public CombinedEventLogger(){}

    public CombinedEventLogger(List<EventLogger> logers) {
        this.logers = logers;
    }
    public void logEvent(Event event) {
        for(EventLogger l : logers) {
            l.logEvent(event);
        }
    }
}
