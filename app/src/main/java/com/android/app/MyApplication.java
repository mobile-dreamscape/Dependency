package com.android.app;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.connectivity.listener.NetworkChangeListener;
import com.android.connectivity.receiver.NetworkChangeReceiver;

public class MyApplication extends Application implements NetworkChangeListener {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        networkChangeReceiver.initListener(this);
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void isOnline() {
        Toast.makeText(this, "Online", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isOffline() {
        Toast.makeText(this, "Offline", Toast.LENGTH_SHORT).show();
    }
}
