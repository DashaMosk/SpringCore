package ua.epam.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.ClassPathResource;
import ua.epam.spring.loggers.ConsoleEventLogger;
import ua.epam.spring.loggers.EventLogger;
import ua.epam.spring.loggers.FileEventLogger;

import java.text.DateFormat;
import java.util.*;

/**
 * Created by Daria on 11.02.2016.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = "ua.epam.spring")
public class AppConfig {
    @Bean
    public static PropertyPlaceholderConfigurer propertyPlaceholderConfigurer () {
        PropertyPlaceholderConfigurer placeholderConfigurer = new PropertyPlaceholderConfigurer();
        placeholderConfigurer.setLocation(new ClassPathResource("client.properties"));
        placeholderConfigurer.setIgnoreResourceNotFound(true);
        placeholderConfigurer.setSystemPropertiesMode(PropertyPlaceholderConfigurer.SYSTEM_PROPERTIES_MODE_OVERRIDE);
        return placeholderConfigurer;
    }

    @Autowired
    ConsoleEventLogger consoleEventLogger;

    @Autowired
    FileEventLogger fileEventLogger;

    @Bean(name="loggers")
    public List<EventLogger> loggers() {
        List<EventLogger> loggerList = new ArrayList<EventLogger>();
        loggerList.add(consoleEventLogger);
        loggerList.add(fileEventLogger);
        return loggerList;
    }

    @Bean(name="loggermp")
    public Map<EventType, EventLogger> loggermp() {
        Map<EventType, EventLogger> logMap = new HashMap<EventType, EventLogger>();
        logMap.put(EventType.INFO, consoleEventLogger);
        logMap.put(EventType.ERROR, fileEventLogger);
        return logMap;
    }

    @Bean
    public DateFormat dateFormat() {
        return DateFormat.getDateInstance();
    }

    @Bean
    public  Date date() {
        return new Date();
    }

}

