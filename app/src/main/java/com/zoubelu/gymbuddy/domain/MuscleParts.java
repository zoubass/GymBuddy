package com.zoubelu.gymbuddy.domain;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by root on 3.12.16.
 */
@DatabaseTable
public class MuscleParts {

    @DatabaseField(generatedId = true)
    private Integer id;

    @DatabaseField(unique = true)
    private String text;

    @ForeignCollectionField
    private ForeignCollection<Exercise> exercises;


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

    public ForeignCollection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ForeignCollection<Exercise> exercises) {
        this.exercises = exercises;
    }
}
