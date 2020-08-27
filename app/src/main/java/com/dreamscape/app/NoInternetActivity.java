package com.dreamscape.app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NoInternetActivity extends AppCompatActivity {

    public static NoInternetActivity noInternetActivity;
    public static boolean active;
    public static boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        noInternetActivity = NoInternetActivity.this;

    }

    @Override
    protected void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    protected void onStop() {
        super.onStop();
        active = false;
    }

    @Override
    public void onBackPressed() {

    }
}
