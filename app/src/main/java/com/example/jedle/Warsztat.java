package com.example.jedle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Warsztat extends BaseActivity {
    public Switch[] switches;
    public Button refreshbutton;
    public ClientSend clientSend;
    public String recieverName;
    public TextView ipaddr;
    public Warsztat(){
        clientSend = new ClientSend();
    }
    int i;
    int relayVal;
    int valueOfSwitches;

    public void WarsztatMainActivity(){


        //Button refreshbutton = findViewById(R.id.refreshButton);
        //final TextView ipaddr = findViewById(R.id.editText);
        //ipaddr.setText("192.168.0.87");
        recieverName = "Warsztat";



        switches[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 0, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 1, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 2, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 3, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 4, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 5, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                doButtonClick(buttonView, isChecked, 6, ipaddr.getText().toString(), recieverName);
            }
        });
        switches[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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
        //for(i = 0; i<8 ;  i++){
        //    relayVal = valueOfSwitches%2;
        //    valueOfSwitches = valueOfSwitches / 2;
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

        //}
    }
    public void getMessage(String dataReceived){
        valueOfSwitches=0;
        String[] command = dataReceived.split(" ");
        if (command != null && command[0].equals("Warsztat")) {
            if(command.length == 10 && command[1].equals("relay_status")) {
                for(int i=0 ; i<8 ; i++){
                    valueOfSwitches = Integer.parseInt(command[2+i])*(2^i)+valueOfSwitches;
                }
                updateSwitches(valueOfSwitches);
            }
        }
    }
}
