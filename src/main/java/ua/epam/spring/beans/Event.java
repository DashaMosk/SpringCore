package ua.epam.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Daria on 05.02.2016.
 */
@Component
@Scope("prototype")
public class Event {
    private int id;
    private String msg;
    @Autowired
    private Date date;
    @Autowired
    private DateFormat df;
    public Event(){}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Event(Date date, DateFormat df){
        this.date = date;
        this.df = df;
        id = new Random(date.getTime()).nextInt();
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
