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
        Runnable clientListen = new ClientListen(warsztat);//mc);
        Thread UDPList = new Thread( clientListen );
        UDPList.start();
        warsztat.refreshbutton = findViewById(R.id.refreshButton);
        warsztat.ipaddr = findViewById(R.id.editText);
        warsztat.ipaddr.setText("192.168.0.87");
        warsztat.switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};

        warsztat.WarsztatMainActivity();
        //final String recieverName = "Warsztat";
        /*Switch s1 = findViewById(R.id.switch1);
        Switch s2 = findViewById(R.id.switch2);
        Switch s3 = findViewById(R.id.switch3);
        Switch s4 = findViewById(R.id.switch4);
        Switch s5 = findViewById(R.id.switch5);
        Switch s6 = findViewById(R.id.switch6);
        Switch s7 = findViewById(R.id.switch7);
        Switch s8 = findViewById(R.id.switch8);
        Button refreshbutton = findViewById(R.id.refreshButton);*/
        //final TextView ipaddr = findViewById(R.id.editText);
        //ipaddr.setText("192.168.0.87");
        /*s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 0, ipaddr.getText().toString(), recieverName);
            }
        });
        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 1, ipaddr.getText().toString(), recieverName);
            }
        });
        s3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 2, ipaddr.getText().toString(), recieverName);
            }
        });
        s4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 3, ipaddr.getText().toString(), recieverName);
            }
        });
        s5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 4, ipaddr.getText().toString(), recieverName);
            }
        });
        s6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 5, ipaddr.getText().toString(), recieverName);
            }
        });
        s7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 6, ipaddr.getText().toString(), recieverName);
            }
        });
        s8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 7, ipaddr.getText().toString(), recieverName);
            }
        });
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clientSend.sendText(recieverName + " st", ipaddr.getText().toString());
            }
        });*/




    }
   /*public void doButtonClick (CompoundButton buttonView, boolean isChecked, int buttonNumber, String ipaddress, String recieverName){
        String txt = recieverName + " r1 " + Integer.toString(buttonNumber);
        if(isChecked) txt = txt + " 1";
        else txt = txt + " 0";
        clientSend.sendText(txt,ipaddress);
        try{wait(100);}catch (Exception e){Log.e("CListen",e.getMessage());}
        clientSend.sendText(recieverName + " st", ipaddress);
    }*/

}
