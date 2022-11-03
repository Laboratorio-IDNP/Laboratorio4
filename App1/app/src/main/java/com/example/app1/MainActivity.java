package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;


import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MessageBroadcastReceiver broadcast;
    private static MainActivity instance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MessageBroadcastReceiver broadcast = new MessageBroadcastReceiver();
        registerReceiver(broadcast, new IntentFilter("com.example.App2.MESSAGE"));
        instance = this;
    }

    public static MainActivity  getInstance(){
        return instance;
    }

    public void updateMessage(String message) {
        MainActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                TextView textViewMessage = findViewById(R.id.textViewMessage);
                textViewMessage.setText(message);
            }
        });
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(broadcast != null)
            unregisterReceiver(broadcast);
    }
}