package com.example.jedle;
import android.os.AsyncTask;
import android.util.Log;

public class ConnectTask extends AsyncTask<String, String, TcpClient> {
    public static TcpClient mTcpClient;// = BaseActivity.mTcpClient;
    RPInode RPInode = MainActivity.RPInode;

    @Override
    protected TcpClient doInBackground(String... message) {

        //we create a TCPClient object
        mTcpClient = new TcpClient(new TcpClient.OnMessageReceived() {
            @Override
            //here the messageReceived method is implemented
            public void messageReceived(String message) {
                //this method calls the onProgressUpdate
                publishProgress(message);
            }
        });
        RPInode.mTcpClient = this.mTcpClient;
        MainActivity.mTcpClient = mTcpClient;
        mTcpClient.run();
        //MainActivity.mTcpClient = this.mTcpClient;
        //BaseActivity.mTcpClient = this.mTcpClient;

        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        //response received from server
        Log.d("test", "response " + values[0]);
        RPInode.getMessage(values[0]);
        //process server response here....

    }
}