package com.zoubelu.gymbuddy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TrainingDayActivity extends AppCompatActivity {

    private TrainingDay trainingDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_day);

        trainingDay = new TrainingDay(new Date().getTime());

        findViewById(R.id.add_exercise_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainingDayActivity.this, ExerciseActivity.class);
                intent.putExtra("TRAINING_DAY", trainingDay);
                startActivity(intent);
            }
        });

        initSpinnerData();

    }

    private void initSpinnerData() {
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(spinnerAdapter);
        for (TrainingStyles s : TrainingStyles.values()) {
            spinnerAdapter.add(s.getText());
        }
    }


    private void addExerciseToView() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.training_day_table);
        TableRow add_exercise_row = (TableRow) findViewById(R.id.add_exercise_row);
        TableRow newTr = new TableRow(this);
        TextView t = new TextView(this);
        t.setText("TEST ROW");
        newTr.addView(t);

        tableLayout.addView(newTr);

        /*
        Database db = OpenHelperManager.getHelper(this, Database.class);

        try {
            Dao trainingDao = db.getTrainingDayDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
        trainingDay.getExercises();
    }
}

enum TrainingStyles {
    OBJEM("objem"),
    SILA("síla"),
    VYDRZ("výdrž"),
    VYKON("výkon"),
    ULEJVARNA("ulejvárna"),
    JINE("jiné");

    private String text;

    TrainingStyles(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
