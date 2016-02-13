package ua.epam.spring.loggers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.epam.spring.beans.Event;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Daria on 06.02.2016.
 */
@Component
public class CombinedEventLogger implements EventLogger {
    @Resource
    @Qualifier("loggers")
    private List<EventLogger> loggers;

    public CombinedEventLogger(){}

    public CombinedEventLogger(List<EventLogger> loggers) {
        this.loggers = loggers;
    }
    public void logEvent(Event event) {
        for(EventLogger l : loggers) {
            l.logEvent(event);
        }
    }
}
