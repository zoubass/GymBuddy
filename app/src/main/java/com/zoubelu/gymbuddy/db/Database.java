package com.zoubelu.gymbuddy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.MuscleParts;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import java.sql.SQLException;

/**
 * Created by root on 3.12.16.
 */

public class Database extends OrmLiteSqliteOpenHelper {
    private static final String TAG = "Database";
    private static final String NAME = "gymbuddy.db";

    private Dao<TrainingDay, Integer> trainingDayDao;
    private Dao<MuscleParts, Integer> musclePartsDao;
    private Dao<Exercise, Integer> exerciseDao;
    private SQLiteDatabase db;

    public Database(Context context) {
        super(context, NAME, null, 1, R.raw.ormlite_config);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, TrainingDay.class);
            TableUtils.createTable(connectionSource, Exercise.class);
            TableUtils.createTable(connectionSource, MuscleParts.class);
            insertInitialData();
        } catch (Exception e) {
            Log.e(TAG, "Failed to create database tables.", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.e(TAG, "Wanted to update the database but its not implemented.");
    }

    private void insertInitialData() {
        try {
            Dao dao = this.getMusclePartsDao();
            dao.create(new MuscleParts("Hrudník"));
            dao.create(new MuscleParts("Záda"));
            dao.create(new MuscleParts("Nohy"));
            dao.create(new MuscleParts("Břicho"));
            dao.create(new MuscleParts("Paže"));
            dao.create(new MuscleParts("Ramena"));
        } catch (SQLException e) {
            Log.e(TAG,"Failed to create initial muscle parts data.");
        }
    }

    public Dao<MuscleParts, Integer> getMusclePartsDao() throws SQLException {
        if (musclePartsDao == null) {
            musclePartsDao = getDao(MuscleParts.class);
        }
        return musclePartsDao;
    }

    public Dao<Exercise, Integer> getExerciseDao() throws SQLException {
        if (exerciseDao == null) {
            exerciseDao = getDao(Exercise.class);
        }
        return exerciseDao;
    }

    public Dao<TrainingDay, Integer> getTrainingDayDao() throws SQLException {
        if (trainingDayDao == null) {
            trainingDayDao = getDao(TrainingDay.class);
        }
        return trainingDayDao;
    }
}
