package com.reactive.kafkapoc1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;
import java.io.Serial;
import java.util.List;

@Table("speakers")
public class Speaker {

    @Id
    private int id;
    @JsonProperty("first")
    private String firstName;
    @JsonProperty("last")
    private String lastName;
    @JsonProperty("favorite")
    private boolean favourite;
    @JsonProperty("company")
    private String company;
    private String twitterHandle;

    @Transient
    private List<Session> sessions;

    public Speaker() {

    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public Speaker(String firstName, String lastName, boolean favourite, String company, String twitterHandle) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.favourite = favourite;
        this.company = company;
        this.twitterHandle = twitterHandle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTwitterHandle() {
        return twitterHandle;
    }

    public void setTwitterHandle(String twitterHandle) {
        this.twitterHandle = twitterHandle;
    }

    @Override
    public String toString() {
        return "Speakers{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
