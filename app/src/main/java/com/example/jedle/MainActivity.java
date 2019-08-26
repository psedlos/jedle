package com.example.jedle;

import android.accessibilityservice.GestureDescription;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ClientSend clientSend = new ClientSend();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Switch s1 = findViewById(R.id.switch1);
        Switch s2 = findViewById(R.id.switch2);
        Switch s3 = findViewById(R.id.switch3);
        Switch s4 = findViewById(R.id.switch4);
        Switch s5 = findViewById(R.id.switch5);
        Switch s6 = findViewById(R.id.switch6);
        Switch s7 = findViewById(R.id.switch7);
        Switch s8 = findViewById(R.id.switch8);
        final TextView ipaddr = findViewById(R.id.editText);
        ipaddr.setText("192.168.8.101");
        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 1 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 2 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 3 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 4 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 5 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 6 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 7 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });
        s8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String txt = "Warsztat r1 8 ";
                if(isChecked) txt = txt + "1";
                else txt = txt + "0";
                clientSend.sendText(txt,(String)ipaddr.getText());
            }
        });





        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
