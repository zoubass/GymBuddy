package com.zoubelu.gymbuddy.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zoubelu.gymbuddy.R;

public class ErrorHandlerActivity extends AppCompatActivity implements Thread.UncaughtExceptionHandler {

//    public ErrorHandlerActivity(Context ctx){}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_handler);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        //TODO: implement day saver :D
    }
}
