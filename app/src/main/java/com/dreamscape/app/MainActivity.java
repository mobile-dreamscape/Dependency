package com.dreamscape.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dreamscape.connectivity.listener.NetworkChangeListener;
import com.dreamscape.connectivity.helpers.Connectivity;

public class MainActivity extends AppCompatActivity {

    private Connectivity connectivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectivity = new Connectivity(this, new NetworkChangeListener() {
            @Override
            public void isOnline() {

            }

            @Override
            public void isOffline() {

            }
        });


        findViewById(R.id.txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), NoInternetActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectivity.registerReceiver();
    }

    @Override
    protected void onPause() {
        super.onPause();
        connectivity.unregisterReceiver();
    }
}
