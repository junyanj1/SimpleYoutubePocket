package com.ics45j.junyanj1.simpleyoutubepocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddVideoLink extends AppCompatActivity {
    private DatabaseReference myRef;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("LinkClip");
        Bundle extras = getIntent().getExtras();
        if(extras!=null) {
            String link = extras.getString(Intent.EXTRA_TEXT);
            EditText editLink = findViewById(R.id.LinktoVideo);
            editLink.setText(link);
            Toast.makeText(this, "Video link sharing succeeded",Toast.LENGTH_LONG).show();
        }
    }

    public void addLink(View view){
        EditText editName = findViewById(R.id.YoutuberName);
        String youtuberName = editName.getText().toString();
        EditText editLink = findViewById(R.id.LinktoVideo);
        String videoLink = editLink.getText().toString();
        Date currentTime = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String addingTime = df.format(currentTime);

        if(videoLink.length()>0){
            String key = myRef.push().getKey(); // Let DB reference generate key
            LinkClip lc = new LinkClip(youtuberName, videoLink, addingTime, key);
            myRef.child(key).setValue(lc);
            Toast.makeText(this, "Video link successfully added",Toast.LENGTH_LONG).show();
        }

        editLink.setText("");
        editName.setText("");
    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
