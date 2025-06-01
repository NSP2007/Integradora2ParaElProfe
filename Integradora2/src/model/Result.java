package model;

import java.time.LocalDateTime;

public class Result {
    
    private LocalDateTime date;
    private String group;
    private String id;
    private Deliverable deliverable;
    private int score;
    public Result(String group,Deliverable deliverable,int score) {
        this.date = date;
        this.group = group;
        this.id = generateId();
        this.deliverable = deliverable;
        this.score=score;
    }
    
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public String getGroup() {
        return group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getId() {
        return id;
    }
    public String generateId() {
        return hashCode()+"";
    }
    public Deliverable getDeliverable() {
        return deliverable;
    }
    public void setDeliverable(Deliverable deliverable) {
        this.deliverable = deliverable;
    }

}
