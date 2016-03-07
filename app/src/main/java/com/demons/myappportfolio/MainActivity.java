package com.demons.myappportfolio;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final String POPULAR_MOVIES_PACKAGE_NAME = "me.swiftly.popularmovies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setLaunchActionForButton(R.id.movies, POPULAR_MOVIES_PACKAGE_NAME);

        int[] buttonIds = { R.id.scores, R.id.library, R.id.bigger, R.id.xyz, R.id.capstone };

        // Set OnClickListener for each button
        for(int id: buttonIds) {
            setActionForButton(id);
        }
    }

    private void setLaunchActionForButton(int id, final String packageName) {
        Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
                if (intent != null) {
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "You don't have this application installed.", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setActionForButton(int id) {
        // Get the text of the button
        final String buttonText = ((Button) findViewById(id)).getText().toString();
        final String toastText = String.format("This button will launch %s!", buttonText);

        Button button = (Button) findViewById(id);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, toastText, Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
