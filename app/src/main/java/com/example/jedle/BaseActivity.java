package com.example.jedle;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {
    //static public ClientSend clientSend = new ClientSend();
    //static public ClientListen clientListen = new ClientListen();
    static public Thread UDPList;
    static public TcpClient mTcpClient;// = new TcpClient();
    RPInode RPInode;
    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
    //    super.onCreate(savedInstanceState);
    //}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        //UDPList = new Thread(clientListen);
        //UDPList.start();
        this.RPInode = MainActivity.RPInode;


        //menu.getItem(0).setTitle(MainActivity.RPInode.recieverName);
        //menu.getItem(1).setTitle("dis_connect");
        //menu.getItem(2).setTitle("connect");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //mTcpClient = ConnectTask.mTcpClient;
        //noinspection SimplifiableIfStatement
        //action_settings) {
        //    return true;
        //}
        if (id == R.id.dis_connect) {
            if(ConnectTask.mTcpClient != null){
                ConnectTask.mTcpClient.stopClient();
                ConnectTask.mTcpClient = null;
            }
            if(ConnectTask.mTcpClient == null){
                Log.d("Connect","mTcpClient is null");
            }else{
                Log.d("Connect","mTcpClient is NOT null");
            }
            return true;
        }
        if (id == R.id.connect) {
            new ConnectTask().execute("");
            if(ConnectTask.mTcpClient == null){
                Log.d("Connect","mTcpClient is null");
            }else{
                Log.d("Connect","mTcpClient is NOT null");
            }
            return true;
        }
        if (id == R.id.reconnect) {
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
            return true;
        }
        if (id == R.id.refresh){
            RPInode.refreshSwitches();
        }
        if (id == R.id.warsztat){
            RPInode.recieverName = "Warsztat";
            RPInode.refreshSwitches();
        }
        if (id == R.id.kury){
            RPInode.recieverName = "Kury";
            RPInode.refreshSwitches();
        }

        return super.onOptionsItemSelected(item);
    }
}
