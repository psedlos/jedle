package com.example.jedle;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.IpSecManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends BaseActivity {
    ClientSend clientSend;
    ClientListen clientListen;
    TcpClient mTcpClient;
    static public Warsztat warsztat = new Warsztat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);


        this.clientSend = BaseActivity.clientSend;
        this.clientListen = BaseActivity.clientListen;
        this.mTcpClient = BaseActivity.mTcpClient;

        warsztat.refreshbutton = findViewById(R.id.refreshButton);
        warsztat.ipaddr = findViewById(R.id.editText);
        warsztat.ipaddr.setText("192.168.0.212");
        warsztat.switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};
        warsztat.switchesNames = new TextView[]{findViewById(R.id.textView1),findViewById(R.id.textView2),findViewById(R.id.textView3),findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView7),findViewById(R.id.textView8)};
        warsztat.WarsztatMainActivity();

        clientSend.sendText(warsztat.recieverName + " st",warsztat.ipaddr.getText().toString());

        if (mTcpClient != null) {
            mTcpClient.sendMessage("testing");
        }

        VideoView videoView = findViewById(R.id.videoView);
        //videoView.setVideoURI(dd);

    }
    @Override
    protected void onStop(){
        super.onStop();

        if (mTcpClient != null) {
            mTcpClient.stopClient();
        }

        //SharedPreferences settings;
        //SharedPreferences.Editor editor;
        //settings = this.getPreferences(costan);
        //editor = settings.edit();
        //editor.putString("da","na");
        //editor.commit();
    }
}
