package com.ingenera.springworkshop.models.bindmodels;

import org.hibernate.validator.constraints.Length;

public class ScoreBindModel {
    private String score;
    private String textContent;

    public ScoreBindModel() {
    }

    public String getScore() {
        return score;
    }


    public void setScore(String score) {
        this.score = score;
    }

    @Length(min = 3,message = "Comment text content must be more than 3 characters!")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
}
