package ua.epam.spring;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.epam.spring.beans.Client;
import ua.epam.spring.beans.Event;
import ua.epam.spring.loggers.ConsoleEventLogger;
import ua.epam.spring.loggers.EventLogger;

import java.util.Map;

/**
 * Created by Daria on 05.02.2016.
 */
public class App {
    private Client client;
    private EventLogger eventLogger;
    private Map<EventType, EventLogger> loggers;

    public App() {
    }
    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = ctx.getBean(App.class);
        Event event = ctx.getBean(Event.class);
        event.setMsg("Some event for user 1");
        app.logEvent(EventType.ERROR, event);
        Event event2 = ctx.getBean(Event.class);
        event2.setMsg("Some event for user 2");
        app.logEvent(EventType.INFO, event2);
        ctx.close();
    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggers.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }
}
