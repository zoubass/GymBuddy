package com.zoubelu.gymbuddy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.calendar.CalendarView;
import com.zoubelu.gymbuddy.db.Database;

import java.util.Date;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getTrainingCalendar();
        Thread.setDefaultUncaughtExceptionHandler(new ErrorHandlerActivity());
    }


    private void getTrainingCalendar() {
        CalendarView cv = (CalendarView) findViewById(R.id.calendar_view);
        HashSet<Date> events = new HashSet<>();
        events.add(new Date());
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                Intent intent = new Intent(MainActivity.this,TrainingDayActivity.class);
                intent.putExtra("SELECTED_DATE",date);
                startActivity(intent);
//                Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //TODO: Implement on destroy which closes databaseHElper
//
//    @Override
//    protected void onDestroy() {
//        db.close();
//        super.onDestroy();
//    }
}
