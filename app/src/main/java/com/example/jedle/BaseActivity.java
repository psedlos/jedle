package com.example.jedle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends AppCompatActivity {
    static public ClientSend clientSend = new ClientSend();
    static public ClientListen clientListen = new ClientListen();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
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
