// Junyang Jin 27625174
// This is a solo project

package com.ics45j.junyanj1.simpleyoutubepocket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            String link = extras.getString(Intent.EXTRA_TEXT);
            EditText editLink = findViewById(R.id.LinktoVideo);
            editLink.setText(link);
            Toast.makeText(this, "Video link sharing succeeded",Toast.LENGTH_LONG).show();
        }
    }

    public void addVideoLink(View view){
        Intent intent = new Intent(this, AddVideoLink.class);
        startActivity(intent);
    }

    public void viewVideoLink(View view){
        Intent intent = new Intent(this, ViewVideoLink.class);
        startActivity(intent);
    }

    public void searchVideoLink(View view){
        Intent intent = new Intent(this, SearchVideoLink.class);
        startActivity(intent);
    }

    public void removeVideoLink(View view){
        Intent intent = new Intent(this, RemoveVideoLink.class);
        startActivity(intent);
    }

}
