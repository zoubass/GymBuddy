package com.zoubelu.gymbuddy.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by root on 3.12.16.
 */
@DatabaseTable
public class Exercise {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(unique = true)
    private String name;
    @DatabaseField
    private Integer reps;
    @DatabaseField
    private Integer sets;
    @DatabaseField
    private Integer weight;
    @DatabaseField(foreign = true, columnName = "muscleParts_foreign_id", canBeNull = false ,foreignAutoCreate = true,foreignAutoRefresh = true)
    private MuscleParts muscleParts;
    @DatabaseField(foreign = true, columnName = "trainingday_foreign_id", canBeNull = false, foreignAutoCreate = true, foreignAutoRefresh = true)
    private TrainingDay trainingDay;

    public Exercise() {
    }

    public Exercise(String name, Integer reps, Integer sets, Integer weight) {
        this.name = name;
        this.reps = reps;
        this.sets = sets;
        this.weight = weight;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public MuscleParts getMuscleParts() {
        return muscleParts;
    }

    public void setMuscleParts(MuscleParts muscleParts) {
        this.muscleParts = muscleParts;
    }

    public TrainingDay getTrainingDay() {
        return trainingDay;
    }

    public void setTrainingDay(TrainingDay trainingDay) {
        this.trainingDay = trainingDay;
    }
}
