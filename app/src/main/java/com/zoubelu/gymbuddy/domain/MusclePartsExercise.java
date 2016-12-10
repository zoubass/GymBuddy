package com.zoubelu.gymbuddy.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by root on 8.12.16.
 */

@DatabaseTable
public class MusclePartsExercise implements Serializable {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField(foreign = true, columnName = "muscleParts_foreign_id", canBeNull = false, foreignAutoCreate = true, foreignAutoRefresh = true)
    private MuscleParts muscleParts;
    @DatabaseField(foreign = true, columnName = "exercise_foreign_id", canBeNull = false, foreignAutoCreate = true, foreignAutoRefresh = true)
    private Exercise exercise;

    public MusclePartsExercise() {
    }

    public MusclePartsExercise(MuscleParts muscleParts, Exercise exercise) {
        this.muscleParts = muscleParts;
        this.exercise = exercise;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MuscleParts getMuscleParts() {
        return muscleParts;
    }

    public void setMuscleParts(MuscleParts muscleParts) {
        this.muscleParts = muscleParts;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }
}
