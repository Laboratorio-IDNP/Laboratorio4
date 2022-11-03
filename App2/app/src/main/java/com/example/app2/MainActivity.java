package com.example.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView message;
    private Button sendMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        message = findViewById(R.id.editTextMessage);
        sendMessage = findViewById(R.id.buttonSendMessage);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!message.getText().toString().trim().equals("")) {
                    Intent intent = new Intent();
                    intent.setAction("com.example.App2.MESSAGE");
                    intent.putExtra("data", message.getText().toString());
                    intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
                    intent.setComponent(new ComponentName("com.example.app1","com.example.app1.MessageBroadcastReceiver"));
                    getApplicationContext().sendBroadcast(intent);
                } else{
                    Toast.makeText(getApplicationContext(), "Escribe algo", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}