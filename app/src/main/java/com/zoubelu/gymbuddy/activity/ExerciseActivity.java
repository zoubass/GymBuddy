package com.zoubelu.gymbuddy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.db.Database;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.MuscleParts;
import com.zoubelu.gymbuddy.domain.MusclePartsExercise;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 5.12.16.
 */

public class ExerciseActivity extends AppCompatActivity {
    private TrainingDay trainingDay;
    private Database db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        trainingDay = (TrainingDay) getIntent().getExtras().getSerializable("TRAINING_DAY");
        final Exercise exercise = new Exercise();
        initCheckBoxes();
        findViewById(R.id.save_exercise_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.exercise_name_text_value);
                EditText sets = (EditText) findViewById(R.id.sets_text_value);
                EditText reps = (EditText) findViewById(R.id.reps_text_value);
                EditText weight = (EditText) findViewById(R.id.weight_text_value);

                String msg = validate(name, sets, reps, weight);

                // show validation
                if (msg != null) {
                    Toast.makeText(ExerciseActivity.this, msg, Toast.LENGTH_LONG).show();
                } else {
                    exercise.setName(name.getText().toString());
                    exercise.setSets(Integer.parseInt(sets.getText().toString()));
                    exercise.setReps(Integer.parseInt(reps.getText().toString()));
                    exercise.setWeight(Integer.parseInt(weight.getText().toString()));
                    exercise.setTrainingDay(trainingDay);
                    trainingDay.getExercises().add(exercise);
                    saveExerciseAndMuscleParts(exercise, getCheckedMuscleParts());

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", trainingDay);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
    }

    private List<MuscleParts> getCheckedMuscleParts() {
        return new ArrayList<>();
    }

    private String validate(EditText name, EditText sets, EditText reps, EditText weight) {
        StringBuilder msg = new StringBuilder();
        //TODO validate
        if (name.getText().length() == 0) {
            msg.append("Nazev cviku musi byt uveden");
        }
        return msg.length() == 0 ? null : msg.toString();
    }

    private Map<MuscleParts, CheckBox> initCheckBoxes() {
        db = OpenHelperManager.getHelper(this, Database.class);
        Map<MuscleParts, CheckBox> musclePartsMap = new HashMap<>();
        try {
            LinearLayout layout = (LinearLayout) findViewById(R.id.checkbox_liner_layout);

            List<MuscleParts> musclePartsList = db.getMusclePartsDao().queryForAll();
            for (MuscleParts p : musclePartsList) {
                CheckBox c = new CheckBox(this);
                c.setText(p.getText());
                musclePartsMap.put(p, c);
                layout.addView(c);
            }
        } catch (Exception e) {
            Log.e(ExerciseActivity.class.getName(), "Failed to query muscle parts list for check boxes.");
        }
        return musclePartsMap;
    }


    private void saveExerciseAndMuscleParts(Exercise exercise, List<MuscleParts> checkedMuscleParts) {
        Database db = OpenHelperManager.getHelper(this, Database.class);
        try {
            List<MusclePartsExercise> musclePartsExercises = new ArrayList<>();
            db.getExerciseDao().createOrUpdate(exercise);

            for (MuscleParts m : checkedMuscleParts) {
                db.getMusclePartsDao().create(m);
                musclePartsExercises.add(new MusclePartsExercise(m, exercise));
            }
        } catch (Exception e) {
            Log.e(ExerciseActivity.class.getName(), "Failed to get dao." + e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (db != null) {
//            db.close();
//        }
    }
}
