package com.example.jedle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

public class Warsztat extends BaseActivity {
    public Switch[] switches;
    public Button refreshbutton;
    //public ClientSend clientSend;
    public String recieverName;
    public EditText ipaddr;
    public TextView[] switchesNames;
    //public Runnable clientListen;
    static public TcpClient mTcpClient;
    public Warsztat(){
        //this.clientListen = BaseActivity.clientListen;
        //this.clientSend = BaseActivity.clientSend;
    }
    int i;
    int relayVal;
    int valueOfSwitches;
    //public void WarsztatBaseActivity(){this.mTcpClient = ConnectTask.mTcpClient;}
    public void WarsztatMainActivity(){


        recieverName = "Warsztat";
        switchesNames[0].setText(R.string.textView1);
        switchesNames[1].setText(R.string.textView2);
        switchesNames[2].setText(R.string.textView3);
        switchesNames[3].setText(R.string.textView4);
        switchesNames[4].setText(R.string.textView5);
        switchesNames[5].setText(R.string.textView6);
        switchesNames[6].setText(R.string.textView7);
        switchesNames[7].setText(R.string.textView8);

        switches[0].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 0, recieverName);}}
        });
        switches[1].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 1, recieverName);}}
        });
        switches[2].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 2, recieverName);}}
        });
        switches[3].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 3, recieverName);}}
        });
        switches[4].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 4, recieverName);}}
        });
        switches[5].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 5, recieverName);}}
        });
        switches[6].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 6, recieverName);}}
        });
        switches[7].setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 7, recieverName);}}
        });
        refreshbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTcpClient != null) {
                    mTcpClient.sendMessage(recieverName + " st");
                }
                //clientSend.sendText(recieverName + " st", ipaddr.getText().toString());
            }
        });
    }

    public void doButtonClick (CompoundButton buttonView, boolean isChecked, int buttonNumber, String recieverName) {
        String txt = recieverName + " r1 " + Integer.toString(buttonNumber);
        if (isChecked) txt = txt + " 1";
        else txt = txt + " 0";
        mTcpClient.sendMessage(txt);
        //final Handler handler = new Handler();
        //handler.postDelayed(new Runnable() {
        //    @Override
        //    public void run() {
        //        Log.d("WAIT","if you read that I perhaps has waited for 100ms");
        //    }
        //},100);
        //mTcpClient.sendMessage(recieverName + " st");
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
        if (command != null) {
            if (command[0].equals("Warsztat")) {
                if (command.length == 10 && command[1].equals("relay_status")) {
                    String[] commandpart = Arrays.copyOfRange(command, 2, 10);
                    updateSwitches(commandpart);
                }
            }
            if(command[0].equals("server") && command[1].equals("killed")){
                if(ConnectTask.mTcpClient != null){
                    ConnectTask.mTcpClient.stopClient();
                    ConnectTask.mTcpClient = null;
                }
                new ConnectTask().execute("");
                if(ConnectTask.mTcpClient == null){
                    Log.d("Connect","mTcpClient is null");
                }else{
                    Log.d("Connect","mTcpClient is NOT null");
                }
            }
        }
    }
}
