package com.example.springbootclasshw1.event;

import org.springframework.context.ApplicationEvent;

public class BookEvent extends ApplicationEvent {
    private String message;
    @Override
    public String toString() {
        return "BookEvent [message=" + message + ", source = " + this.source + "]";
    }
    public BookEvent(Object source, String message) {

        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}
