package com.example.library.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection="Resources")
public class ResourceText {
    @Id
    private String id;

    private Object title;
    private Object subject;

    private Object type;

    private Object format;
    private Object description;
    private boolean available;
    private String borrowTime ;

    public ResourceText() {
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

    @Override
    public String toString() {
        return "\n ResourceText{" +
                "id='" + id + '\'' +
                ",\ntitle='" + title + '\'' +
                ",\nsubject=" + subject +
                ",\nformat='" + format + '\'' +
                ",\ndescription=" + description +
                ",\navailable=" + available +
                ",\nborrowTime=" + borrowTime +
                "}\n";
    }
}
