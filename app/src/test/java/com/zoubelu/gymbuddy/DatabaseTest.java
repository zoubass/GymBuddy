package com.zoubelu.gymbuddy;

import android.app.Activity;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.EagerForeignCollection;
import com.zoubelu.gymbuddy.activity.MainActivity;
import com.zoubelu.gymbuddy.db.Database;
import com.zoubelu.gymbuddy.domain.Exercise;
import com.zoubelu.gymbuddy.domain.TrainingDay;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 16)
public class DatabaseTest {

    Database db;

//    @Before
    public void startUp() {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        db = OpenHelperManager.getHelper(activity.getApplicationContext(), Database.class);
    }

    @Test
    public void shouldCreateDatabaseWithSchema() throws Exception {
        Assert.assertTrue(db != null);
    }

    @Test
    public void testTrainingDayInit() {
        TrainingDay td = new TrainingDay(new Date().getTime());
        System.out.println(td.getDate());
    }
}