package com.zoubelu.gymbuddy.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.ForeignCollection;
import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.db.Database;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.MuscleParts;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 5.12.16.
 */

public class ExerciseActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        final Map<MuscleParts, CheckBox> musclePartsMap = initCheckBoxes();
        final Exercise exercise = new Exercise();
        findViewById(R.id.save_exercise_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: save to database + HAZI ERROR!!!!!!!!!!!!!!!!!!!!!!
                TrainingDay trainingDay = (TrainingDay) getIntent().getExtras().getSerializable("TRAINING_DAY");

                EditText name = (EditText) findViewById(R.id.exercise_name_text_value);
                EditText sets = (EditText) findViewById(R.id.sets_text_value);
                EditText reps = (EditText) findViewById(R.id.reps_text_value);
                EditText weight = (EditText) findViewById(R.id.weight_text_value);

                validate(name, sets, reps, weight);


                exercise.setName(name.getText().toString());
                exercise.setSets(Integer.getInteger(sets.getText().toString()));
                exercise.setReps(Integer.getInteger(reps.getText().toString()));
                exercise.setWeight(Integer.getInteger(weight.getText().toString()));
                exercise.setMuscleParts(getCheckedMuscleParts(musclePartsMap));
                exercise.setTrainingDay(trainingDay);
                trainingDay.getExercises().add(exercise);
                saveExercise();
            }
        });
    }

    private MuscleParts getCheckedMuscleParts(Map<MuscleParts, CheckBox> musclePartsMap) {
        return null;
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
        Database db = OpenHelperManager.getHelper(this, Database.class);
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


    private void saveExercise() {

    }
}
