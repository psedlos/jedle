package com.example.jedle;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Warsztat warsztat;
    //ClientSend clientSend = new ClientSend();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.warsztat = BaseActivity.warsztat;

        warsztat.refreshbutton = findViewById(R.id.refreshButton);
        warsztat.ipaddr = findViewById(R.id.editText);
        warsztat.ipaddr.setText("192.168.0.87");
        warsztat.switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};
        warsztat.WarsztatMainActivity();

        warsztat.clientSend.sendText(warsztat.recieverName + " st",warsztat.ipaddr.getText().toString());

    }
}
