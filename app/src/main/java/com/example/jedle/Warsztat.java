package com.example.jedle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

public class Warsztat extends BaseActivity {
    public Switch[] switches;
    public Button refreshbutton;
    public ClientSend clientSend;
    public String recieverName;
    public TextView ipaddr;
    public Runnable clientListen;
    public Thread UDPList;
    public Warsztat(){
        clientSend = new ClientSend();
        clientListen = new ClientListen(this);
        UDPList = new Thread( clientListen );
        UDPList.start();
    }
    int i;
    int relayVal;
    int valueOfSwitches;

    public void WarsztatMainActivity(){

        recieverName = "Warsztat";

        switches[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 0, ipaddr.getText().toString(), recieverName);}
        });
        switches[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 1, ipaddr.getText().toString(), recieverName);}
        });
        switches[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 2, ipaddr.getText().toString(), recieverName);}
        });
        switches[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 3, ipaddr.getText().toString(), recieverName);}
        });
        switches[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 4, ipaddr.getText().toString(), recieverName);}
        });
        switches[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 5, ipaddr.getText().toString(), recieverName);}
        });
        switches[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 6, ipaddr.getText().toString(), recieverName);}
        });
        switches[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {doButtonClick(buttonView, isChecked, 7, ipaddr.getText().toString(), recieverName);}
        });
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {clientSend.sendText(recieverName + " st", ipaddr.getText().toString());}
        });
    }

    public void doButtonClick (CompoundButton buttonView, boolean isChecked, int buttonNumber, String ipaddress, String recieverName) {
        String txt = recieverName + " r1 " + Integer.toString(buttonNumber);
        if (isChecked) txt = txt + " 1";
        else txt = txt + " 0";
        clientSend.sendText(txt, ipaddress);
        try {
            wait(100);
        } catch (Exception e) {
            Log.e("CListen", e.getMessage());
        }
        clientSend.sendText(recieverName + " st", ipaddress);
    }
    public void updateSwitches(int valueOfSwitches){
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    int valueOfSwitches = warsztat.valueOfSwitches;
                    for(i = 0; i<8 ;  i++) {
                        relayVal = valueOfSwitches % 2;
                        switches[i].setChecked((relayVal > 0));
                        valueOfSwitches = valueOfSwitches / 2;
                    }
                }
            });
    }
    public void updateSwitches(final String[] commandBits){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    for(i = 0; i<commandBits.length ;  i++) {
                        switches[i].setChecked(commandBits[i].equals("1"));
                    }
                }
        });
    }
    public void getMessage(String dataReceived){
        valueOfSwitches=0;
        String[] command = dataReceived.split(" ");
        if (command != null && command[0].equals("Warsztat")) {
            if(command.length == 10 && command[1].equals("relay_status")) {
                String[] commandpart = Arrays.copyOfRange(command,2,10);
                updateSwitches(commandpart);
            }
        }
    }
}
