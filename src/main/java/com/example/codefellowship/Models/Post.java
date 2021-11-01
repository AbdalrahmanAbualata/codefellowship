package com.example.codefellowship.Models;
import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    @ManyToOne
    private ApplicationUser owner;
    private String body;
    private String timeStamp;

    public Post() {}

    public Post(String body, String timeStamp, ApplicationUser owner) {
        this.body = body;
        this.timeStamp = timeStamp;
        this.owner = owner;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ApplicationUser getOwner() {
        return owner;
    }

    public void setOwner(ApplicationUser owner) {
        this.owner = owner;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
    public String toString() {
        return String.format("At %s, %s wrote: %s ", this.timeStamp, this.owner.getUsername(), this.body);
    }
}
