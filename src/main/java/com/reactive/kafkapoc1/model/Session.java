package com.reactive.kafkapoc1.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("sessions")
public class Session {

    @Id
    private int id;

    private String sessionTitle;
    private String eventYear;
    private int speakerId;

    public Session( String sessionTitle, String eventYear, int speakerId) {
        this.sessionTitle = sessionTitle;
        this.eventYear = eventYear;
        this.speakerId = speakerId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getSessionTitle() {
        return sessionTitle;
    }

    public void setSessionTitle(String sessionTitle) {
        this.sessionTitle = sessionTitle;
    }

    public String getEventYear() {
        return eventYear;
    }

    public void setEventYear(String eventYear) {
        this.eventYear = eventYear;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", sessionTitle='" + sessionTitle + '\'' +
                ", eventYear='" + eventYear + '\'' +
                '}';
    }
}
