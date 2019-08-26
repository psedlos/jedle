package com.example.jedle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {
    //static public MainContainer mc;// = new MainContainer();
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //if(this.mc == null)
        //{mc = new MainContainer();}
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        Runnable clientListen = new ClientListen();//mc);
        Thread UDPList = new Thread( clientListen );
        UDPList.start();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*action_settings) {
            return true;
        }
        if (id == R.id.show_main) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        }
        if (id == R.id.show_radio) {
            Intent myIntent = new Intent(this, RadioActivity.class);
            startActivity(myIntent);
            return true;
        }
        if (id == R.id.show_driver) {
            Intent myIntent = new Intent(this, DriverInterface.class);
            startActivity(myIntent);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
