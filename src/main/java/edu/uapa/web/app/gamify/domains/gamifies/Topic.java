package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.models.abstracts.Auditable;

import javax.persistence.*;

@Entity(name = "topics")
public class Topic extends Auditable {

    @Column(nullable = false)
    private String topicName;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String example;

    public Topic() {
    }

    public Topic(String topicName, String description, String example) {
        this.topicName = topicName;
        this.description = description;
        this.example = example;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }
}
