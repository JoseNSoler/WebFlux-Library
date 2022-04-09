package com.example.library.dto;

import org.springframework.data.annotation.Id;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ResourceTextDTO {

    @Id
    private String id;

    private Object title;
    private Object subject;

    private Object type;

    private Object format;
    private Object description;
    private boolean available;
    private String borrowTime;

    public ResourceTextDTO() {
    }

    public ResourceTextDTO(String id, Object title, Object subject, Object type, Object format, Object description, boolean available, String borrowTime) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.type = type;
        this.format = format;
        this.description = description;
        this.available = available;
        this.borrowTime = borrowTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getTitle() {
        return title;
    }

    public void setTitle(Object title) {
        this.title = title;
    }

    public Object getSubject() {
        return subject;
    }

    public void setSubject(Object subject) {
        this.subject = subject;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getFormat() {
        return format;
    }

    public void setFormat(Object format) {
        this.format = format;
    }

    public Object getDescription() {
        return description;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
