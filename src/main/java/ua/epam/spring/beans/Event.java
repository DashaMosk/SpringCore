package ua.epam.spring.beans;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Daria on 05.02.2016.
 */
public class Event {
    private int id;
    private String msg;
    private Date date;
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
