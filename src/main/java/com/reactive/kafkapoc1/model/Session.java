package com.reactive.kafkapoc1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sessions")
public class Session {

    @Id
    private int id;
    @JsonProperty("title")
    private String sessionTitle;
    private String eventYear;
    private int speakerId;

    private String roomName;
    private Room room;

    public Session(String sessionTitle, String eventYear, int speakerId, String roomName, Room room) {
        this.sessionTitle = sessionTitle;
        this.eventYear = eventYear;
        this.speakerId = speakerId;
        this.roomName = roomName;
        this.room = new Room(roomName,0);
    }

    public Session() {
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

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = new Room(roomName,0);
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
