package com.android.connectivity.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import com.android.connectivity.listener.NetworkChangeListener;

public class NetworkChangeReceiver extends BroadcastReceiver {

    NetworkChangeListener  networkChangeListener;

    public void initListener(NetworkChangeListener networkChangeListener) {
        this.networkChangeListener = networkChangeListener;
    }

    @Override
    public void onReceive(Context context, Intent intent)
    {
        try
        {
            if (isOnline(context)) {
                networkChangeListener.isOnline();
            } else {
                networkChangeListener.isOffline();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private boolean isOnline(Context context) {
        try {

            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network network;
            if (connectivityManager != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    network = connectivityManager.getActiveNetwork();
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
                    return networkCapabilities != null && (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                } else {
                    NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
                    return (netInfo != null && netInfo.isConnected());
                }
            }
            return false;

        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }
}
