package ua.epam.spring.loggers;

import ua.epam.spring.beans.Event;

import java.util.List;

/**
 * Created by Daria on 05.02.2016.
 */
public class CacheFileLogger extends FileEventLogger{
    private int cacheSize;
    private List<Event> cache;

    public CacheFileLogger(){}

    public CacheFileLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);

        if(cache.size() == cacheSize) {
            writeEventsFromCash();
        }

        cache.clear();
    }

    public void destroy() {
        if(!cache.isEmpty()) {
            writeEventsFromCash();
        }
    }

    private void writeEventsFromCash() {
        for (Event e : cache) {
            super.logEvent(e);
        }
    }
}
