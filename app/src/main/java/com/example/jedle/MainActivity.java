package com.example.jedle;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.VideoView;

import com.longdo.mjpegviewer.MjpegView;

public class MainActivity extends BaseActivity {

    static TcpClient mTcpClient;
    static public RPInode RPInode = new RPInode();
    MjpegView viewer;
    VideoView videoView;
    private Handler mHandler;

    Runnable refresher = new Runnable() {
        @Override
        public void run() {
            try {
                RPInode.refreshSwitches();
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

        RPInode.ipaddr = "192.168.1.150";
        RPInode.switches = new Switch[]{findViewById(R.id.switch1), findViewById(R.id.switch2), findViewById(R.id.switch3), findViewById(R.id.switch4), findViewById(R.id.switch5), findViewById(R.id.switch6), findViewById(R.id.switch7), findViewById(R.id.switch8)};
        RPInode.checks = new CheckBox[] {findViewById(R.id.checkBox1), findViewById(R.id.checkBox2), findViewById(R.id.checkBox3), findViewById(R.id.checkBox4), findViewById(R.id.checkBox5), findViewById(R.id.checkBox6), findViewById(R.id.checkBox7), findViewById(R.id.checkBox8)};
        RPInode.switchesNames = new TextView[]{findViewById(R.id.textView1),findViewById(R.id.textView2),findViewById(R.id.textView3),findViewById(R.id.textView4),findViewById(R.id.textView5),findViewById(R.id.textView6),findViewById(R.id.textView7),findViewById(R.id.textView8)};
        RPInode.WarsztatMainActivity();

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
