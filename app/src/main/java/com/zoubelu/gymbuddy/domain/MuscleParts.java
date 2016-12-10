package com.zoubelu.gymbuddy.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by root on 3.12.16.
 */
@DatabaseTable
public class MuscleParts implements Serializable {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(unique = true)
    private String text;

    public MuscleParts() {
    }

    public MuscleParts(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
