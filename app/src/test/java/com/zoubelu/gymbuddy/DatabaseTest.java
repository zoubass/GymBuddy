package com.zoubelu.gymbuddy;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;

import com.zoubelu.gymbuddy.db.Database;

import org.junit.Before;
import org.junit.Test;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class DatabaseTest {
    private Context context = null;

    @Before
    public void startUp(){
        Activity activity= new Activity();
        context =activity;
    }

    @Test
    public void shouldCreateDatabaseWithSchema() throws Exception {
        Database db = new Database(context);
        Cursor cs = db.getReadableDatabase().rawQuery("select * from test",null);
        System.out.println("testing");

    }
}