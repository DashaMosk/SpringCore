package ua.epam.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ua.epam.spring.beans.Client;
import ua.epam.spring.beans.Event;
import ua.epam.spring.loggers.EventLogger;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Daria on 05.02.2016.
 */

@Component
public class App {
    @Autowired
    private Client client;

    @Autowired
    @Qualifier(value = "consoleeventlogger")
    private EventLogger eventLogger;

    @Resource
    @Qualifier("loggermp")
    private Map<EventType, EventLogger> loggermp;

    @Autowired
    @Qualifier("statisticaspect")
    private StatisticAspect statisticAspect;

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggermp = loggers;
    }

    public StatisticAspect getStatisticAspect() {
        return statisticAspect;
    }

    public App() {
    }

    public static void main(String[] args) {
        // ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        App app = ctx.getBean(App.class);
        Event event = ctx.getBean(Event.class);
        event.setMsg("Some event for user 1");
        app.logEvent(EventType.ERROR, event);
        Event event2 = ctx.getBean(Event.class);
        event2.setMsg("Some event for user 2");
        app.logEvent(EventType.INFO, event2);
        Event event3 = ctx.getBean(Event.class);
        event3.setMsg("Some event for user 3");
        app.logEvent(EventType.ERROR, event3);
        Event event4 = ctx.getBean(Event.class);
        event4.setMsg("Some event for user 4");
        app.logEvent(EventType.INFO, event4);
        Map<Class<?>, Integer> count = app.getStatisticAspect().getCounter();
        for(Map.Entry<Class<?>, Integer> entry : count.entrySet()) {
            System.out.println(entry.getKey().toString() + " = " + entry.getValue());
        }


    }

    private void logEvent(EventType type, Event event) {
        EventLogger logger = loggermp.get(type);
        if (logger == null) {
            logger = eventLogger;
        }
        event.setMsg(event.getMsg().replaceAll(client.getId(), client.getFullName()));
        logger.logEvent(event);
    }
}
