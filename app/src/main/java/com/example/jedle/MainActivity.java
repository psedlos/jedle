package com.example.jedle;

import android.accessibilityservice.GestureDescription;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.longdo.mjpegviewer.MjpegView;

import java.net.URI;

public class MainActivity extends BaseActivity {

    static TcpClient mTcpClient;
    static public Warsztat warsztat = new Warsztat();
    MjpegView viewer;
    private Handler mHandler;

    Runnable refresher = new Runnable() {
        @Override
        public void run() {
            try {
                warsztat.refreshSwitches();
            } catch (Exception e){
                Log.d("TCPref",e.getMessage());
            } finally {
                mHandler.postDelayed(refresher, 5000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);



        new ConnectTask().execute("");
        this.mTcpClient = ConnectTask.mTcpClient;

        mHandler = new Handler();
        startRefresher();

        warsztat.ipaddr = "192.168.1.150";
        warsztat.switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};
        warsztat.switchesNames = new TextView[]{findViewById(R.id.textView1),findViewById(R.id.textView2),findViewById(R.id.textView3),findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView7),findViewById(R.id.textView8)};
        warsztat.WarsztatMainActivity();

        viewer = (MjpegView) findViewById(R.id.mjpegview);
        viewer.setMode(MjpegView.MODE_FIT_WIDTH);
        viewer.setAdjustHeight(true);
        viewer.setUrl("http://192.168.1.150:8554/?action=stream");
        viewer.startStream();
    }
    void startRefresher(){
        refresher.run();
    }
    void stopRefresher(){
        mHandler.removeCallbacks(refresher);
    }
    @Override
    protected void onStop(){
        super.onStop();

        if (mTcpClient != null) {
            mTcpClient.stopClient();
        }
        viewer.stopStream();
    }
    @Override
    protected void onPause(){
        super.onPause();
        viewer.stopStream();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        stopRefresher();
    }
}
