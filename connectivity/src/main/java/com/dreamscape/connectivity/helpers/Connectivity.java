package com.dreamscape.connectivity.helpers;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.dreamscape.connectivity.listener.NetworkChangeListener;
import com.dreamscape.connectivity.receiver.NetworkChangeReceiver;

public class Connectivity extends ContextWrapper implements NetworkChangeListener {

    private NetworkChangeReceiver networkChangeReceiver;
    private NetworkChangeListener networkChangeListener;

    public Connectivity(Context context, NetworkChangeListener networkChangeListener) {
        super(context);
        this.networkChangeListener = networkChangeListener;
    }


    public void registerReceiver(){
        networkChangeReceiver = new NetworkChangeReceiver();
        networkChangeReceiver.initListener(this);
        registerReceiver(networkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void unregisterReceiver(){
        unregisterReceiver(networkChangeReceiver);
    }

    @Override
    public void isOnline() {
        if(networkChangeListener != null)
            networkChangeListener.isOnline();
    }

    @Override
    public void isOffline() {
        if(networkChangeListener != null)
            networkChangeListener.isOffline();
    }

}
