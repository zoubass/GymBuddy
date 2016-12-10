package com.zoubelu.gymbuddy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.zoubelu.gymbuddy.R;
import com.zoubelu.gymbuddy.calendar.CalendarView;
import com.zoubelu.gymbuddy.db.Database;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int h = 1;
    private final HashSet<Date> events = new HashSet<>();
    private Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (h == 1) {
            Database db = OpenHelperManager.getHelper(this, Database.class);
            db.purge();
        }
        h = 2;
        getTrainingCalendar();
        Thread.setDefaultUncaughtExceptionHandler(new ErrorHandlerActivity());
    }


    private void getTrainingCalendar() {
        try {
            db = OpenHelperManager.getHelper(this, Database.class);
            CalendarView cv = (CalendarView) findViewById(R.id.calendar_view);

            List<TrainingDay> days = db.getTrainingDayDao().queryForAll();
            for (TrainingDay day : days) {
                events.add(new Date(day.getDate()));
            }
            cv.updateCalendar(events);

            cv.setEventHandler(new CalendarView.EventHandler() {
                @Override
                public void onDayLongPress(Date date) {
                    Intent intent = new Intent(MainActivity.this, TrainingDayActivity.class);
                    intent.putExtra("SELECTED_DATE", date.getTime());
                    startActivity(intent);
//                Toast.makeText(MainActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e(MainActivity.class.getName(), "Failed to getTrainingCalendar in MainActivity.");
        }
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

    @Override
    protected void onResume() {
        super.onResume();
        getTrainingCalendar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        if (db != null) {
//            db.close();
//        }
    }
}
