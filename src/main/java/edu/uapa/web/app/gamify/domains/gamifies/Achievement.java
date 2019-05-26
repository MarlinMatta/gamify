package edu.uapa.web.app.gamify.domains.gamifies;

import edu.uapa.web.app.gamify.domains.schools.Teacher;
import edu.uapa.web.app.gamify.models.abstracts.Auditable;
import edu.uapa.web.app.gamify.models.enums.Difficulty;

import javax.persistence.*;
import java.text.DecimalFormat;

@Entity(name = "achievements")
public class Achievement extends Auditable {

    @Column(nullable = false)
    private String difficultyName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    private String description;

    private DecimalFormat quantity;

    private DecimalFormat percent;

    private String iconUrl;

    public Achievement() {
    }

    public Achievement(String difficultyName, Topic topic, Difficulty difficulty, String description, DecimalFormat quantity, DecimalFormat percent, String iconUrl) {
        this.difficultyName = difficultyName;
        this.topic = topic;
        this.difficulty = difficulty;
        this.description = description;
        this.quantity = quantity;
        this.percent = percent;
        this.iconUrl = iconUrl;
    }

    public String getDifficultyName() {
        return difficultyName;
    }

    public void setDifficultyName(String difficultyName) {
        this.difficultyName = difficultyName;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DecimalFormat getQuantity() {
        return quantity;
    }

    public void setQuantity(DecimalFormat quantity) {
        this.quantity = quantity;
    }

    public DecimalFormat getPercent() {
        return percent;
    }

    public void setPercent(DecimalFormat percent) {
        this.percent = percent;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
