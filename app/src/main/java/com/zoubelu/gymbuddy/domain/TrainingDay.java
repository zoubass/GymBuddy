package com.zoubelu.gymbuddy.domain;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by root on 3.12.16.
 */
@DatabaseTable(tableName = "trainingday")
public class TrainingDay implements Serializable{

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private Long date;
    @ForeignCollectionField
    private ForeignCollection<Exercise> exercises;
    @DatabaseField(canBeNull = true)
    private String comment;

    /*
    //NICE TO HAVE
    private Byte photo;
     */

    public TrainingDay() {
    }

    public TrainingDay(Long date) {
        this.date = date;
        //TODO: otestovat
//        this.exercises = (ForeignCollection<Exercise>) new ArrayList<Exercise>();
    }

    public TrainingDay(Long date, ForeignCollection<Exercise> exercises, String comment) {
        this.date = date;
        this.exercises = exercises;
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ForeignCollection<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ForeignCollection<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
