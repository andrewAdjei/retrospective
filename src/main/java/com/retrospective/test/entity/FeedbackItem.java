package com.retrospective.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

@Entity
public class FeedbackItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedbackProvider;
    private String body;
    private String feedbackType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackProvider() {
        return feedbackProvider;
    }

    public void setFeedbackProvider(String feedbackProvider) {
        this.feedbackProvider = feedbackProvider;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getFeedbackType() {
        return feedbackType;
    }

    public void setFeedbackType(String feedbackType) {
        this.feedbackType = feedbackType;
    }

    @Override
    public String toString() {
        return "FeedbackItem{" +
                "id=" + id +
                ", feedbackProvider='" + feedbackProvider + '\'' +
                ", body='" + body + '\'' +
                ", feedbackTypes='" + feedbackType + '\'' +
                '}';
    }
}

enum FeedbackTypes {
    POSITIVE,
    NEGATIVE,
    IDEA
}
