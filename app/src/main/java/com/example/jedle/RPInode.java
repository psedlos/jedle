package com.example.jedle;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Arrays;

public class RPInode extends BaseActivity {
    public Switch[] switches;
    public CheckBox[] checks;
    public String recieverName;
    public String ipaddr;
    public TextView[] switchesNames;
    static public TcpClient mTcpClient;
    public RPInode(){
    }
    int i;
    int valueOfSwitches;
    public void WarsztatMainActivity(){
        //switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};
        //checks = new CheckBox[] {findViewById(R.id.checkBox1), findViewById(R.id.checkBox2), findViewById(R.id.checkBox3), findViewById(R.id.checkBox4), findViewById(R.id.checkBox5), findViewById(R.id.checkBox6), findViewById(R.id.checkBox7), findViewById(R.id.checkBox8)};
        //switchesNames = new TextView[]{findViewById(R.id.textView1),findViewById(R.id.textView2),findViewById(R.id.textView3),findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView7),findViewById(R.id.textView8)};

        recieverName = "Warsztat";
        switchesNames[0].setText(R.string.textView1);
        switchesNames[1].setText(R.string.textView2);
        switchesNames[2].setText(R.string.textView3);
        switchesNames[3].setText(R.string.textView4);
        switchesNames[4].setText(R.string.textView5);
        switchesNames[5].setText(R.string.textView6);
        switchesNames[6].setText(R.string.textView7);
        switchesNames[7].setText(R.string.textView8);

        switches[0].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 0, recieverName);}}
        });
        switches[1].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 1, recieverName);}}
        });
        switches[2].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 2, recieverName);}}
        });
        switches[3].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 3, recieverName);}}
        });
        switches[4].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 4, recieverName);}}
        });
        switches[5].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 5, recieverName);}}
        });
        switches[6].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 6, recieverName);}}
        });
        switches[7].setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){ doButtonClick(buttonView, isChecked, 7, recieverName);}}
        });
        checks[0].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 0,recieverName);}}
        });
        checks[1].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 1,recieverName);}}
        });
        checks[2].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 2,recieverName);}}
        });
        checks[3].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 3,recieverName);}}
        });
        checks[4].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 4,recieverName);}}
        });
        checks[5].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 5,recieverName);}}
        });
        checks[6].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 6,recieverName);}}
        });
        checks[7].setOnCheckedChangeListener(new OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {if(buttonView.isPressed()){doCheckboxClick(buttonView, isChecked, 7,recieverName);}}
        });
    }

    public void doButtonClick (CompoundButton buttonView, boolean isChecked, int buttonNumber, String recieverName) {
        String txt = recieverName + " r1 " + Integer.toString(buttonNumber);
        if (isChecked) txt = txt + " 1";
        else txt = txt + " 0";
        mTcpClient.sendMessage(txt);
    }
    public void doCheckboxClick(CompoundButton buttonView, boolean isChecked, int buttonNumber, String recieverName){
        String txt = recieverName +" rauto " + Integer.toString(buttonNumber);
        if(isChecked){
            txt = txt + " 1";
            switches[buttonNumber].setClickable(false);
            switches[buttonNumber].setAlpha(.4f);
        }
        else {
            txt = txt + " 0";
            switches[buttonNumber].setClickable(true);
            switches[buttonNumber].setAlpha(1.0f);
        }
        mTcpClient.sendMessage(txt);
    }
    public void setChecksChecked(int id, boolean value){
        if(value){
            checks[id].setChecked(true);
            switches[id].setAlpha(.4f);
            switches[id].setClickable(false);
        }else {
            checks[id].setChecked(false);
            switches[id].setAlpha(1.0f);
            switches[id].setClickable(true);
        }
    }
    public void updateSwitches(final String[] commandBits){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    for(i = 0; i<commandBits.length  && i<8 ;  i++) {
                        switches[i].setChecked(commandBits[i].equals("1"));
                    }
                    for(i = 9 ; i<commandBits.length && i< 17 ; i++){
                        setChecksChecked(i-9, commandBits[i].equals("1"));
                    }
                }
        });
    }
    public void refreshSwitches(){
        if (mTcpClient != null) {
            mTcpClient.sendMessage(recieverName + " st");
        }
    }
    public void getMessage(String dataReceived){
        valueOfSwitches=0;
        String[] command = dataReceived.split(" ");
        if (command != null) {
            if (command[0].equals(recieverName)) {
                if (command[1].equals("relay_status")) {
                    String[] commandpart = Arrays.copyOfRange(command, 2, command.length);
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
            if(command[0].equals("")){}
        }
    }
}
