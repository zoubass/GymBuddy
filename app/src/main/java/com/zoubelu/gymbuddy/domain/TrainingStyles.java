package com.zoubelu.gymbuddy.domain;

/**
 * Created by zoubas on 9.12.16.
 */
public enum TrainingStyles {
    OBJEM("objem"),
    SILA("síla"),
    VYDRZ("výdrž"),
    VYKON("výkon"),
    JINE("jiné");

    private String text;

    TrainingStyles(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}