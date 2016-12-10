package com.zoubelu.gymbuddy.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.db.Database;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.TrainingDay;
import com.zoubelu.gymbuddy.domain.TrainingStyles;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingDayActivity extends AppCompatActivity {

    private TrainingDay trainingDay;
    private Database db;
    private static final String RM_BTN = "rm_btn_";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_day);
        db = OpenHelperManager.getHelper(this, Database.class);

        initTrainingDay();

        findViewById(R.id.add_exercise_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingDayActivity.this, ExerciseActivity.class);
                intent.putExtra("TRAINING_DAY", trainingDay);
                startActivityForResult(intent, 1);
            }
        });

        initSpinnerData();
        findViewById(R.id.save_training_day_btn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                try {
                    db.getTrainingDayDao().createOrUpdate(trainingDay);
                    finish();
                } catch (SQLException e) {
                    Log.e(TrainingDayActivity.class.getName(), "Failed to create training day. Database cannot save the data. " + e.getMessage());
                }
            }
        });
    }

    private TrainingDay initTrainingDay() {
        trainingDay = new TrainingDay(getIntent().getLongExtra("SELECTED_DATE", -1));
        try {
            trainingDay.setExercises(db.getTrainingDayDao().<Exercise>getEmptyForeignCollection("exercises"));
        } catch (Exception e) {
            Log.e(TrainingDayActivity.class.getName(), "Failed to assignEmpty exercices collection.");
            System.err.println(e);
        }
        return trainingDay;
    }

    private void initSpinnerData() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        for (TrainingStyles s : TrainingStyles.values()) {
            spinnerAdapter.add(s.getText().toUpperCase());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                this.trainingDay = (TrainingDay) data.getExtras().getSerializable("result");
                refreshExercisesList();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                //TODO: Write your code if there's no result

            }
        }
    }

    private void refreshExercisesList() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.training_day_table);
        while (tableLayout.getChildCount() > 0) {
            tableLayout.removeView(tableLayout.getChildAt(tableLayout.getChildCount() - 1));
        }
        for (Exercise e : trainingDay.getExercises()) {
            TableRow newTr = new TableRow(this);
            TextView name = new TextView(this);
            TextView setsAndReps = new TextView(this);
            TextView weight = new TextView(this);
            ImageButton btn = new ImageButton(this);

            //TEXTS
            name.setText(e.getName());
            setsAndReps.setText(e.getSets().toString() + " x " + e.getReps().toString());
            weight.setText(e.getWeight().toString());
            //BUTTON
            btn.setImageResource(R.drawable.previous_icon);
            btn.setId(e.getId());
            btn.setOnClickListener(new DeleteExerciseListener());

            TableRow.LayoutParams params = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            newTr.addView(name, params);
            newTr.addView(setsAndReps, params);
            newTr.addView(weight, params);
            newTr.addView(btn, params);
            tableLayout.addView(newTr);
        }
    }

    protected class DeleteExerciseListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            for (Exercise e : trainingDay.getExercises()) {
                if (view.getId() == e.getId()) {
                    trainingDay.getExercises().remove(e);
                    refreshExercisesList();
                }
            }
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

