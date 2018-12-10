package com.ics45j.junyanj1.simpleyoutubepocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class SearchVideoLink extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<LinkClip> lcList, searchResults;
    private LinkAdapter LCAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("LinkClip");

        lcList = new ArrayList<LinkClip>();
        searchResults = new ArrayList<LinkClip>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                lcList.add(dataSnapshot.getValue(LinkClip.class));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        myRef.addChildEventListener(childEventListener);

        LCAdapter = new LinkAdapter(this, searchResults);
        ListView results = findViewById(R.id.SearchResultList);
        results.setAdapter(LCAdapter);
    }

    public void search(View view){
        LCAdapter.clear();
        boolean found = false;
        for (LinkClip lc : lcList){
            EditText text = (EditText) findViewById(R.id.NameQuery);
            String search = text.getText().toString();
            if(lc.getYoutuberName().equalsIgnoreCase(search)){
                LCAdapter.add(lc);
                found = true;
            }
        }
        EditText search = (EditText) findViewById(R.id.NameQuery);
        if(!found){
            Toast.makeText(this, search.getText().toString()+" not found.", Toast.LENGTH_LONG).show();

        }
        search.setText("");
    }

    public void goHome(View view)
    {
        Intent intent = new Intent( this, MainActivity.class);
        startActivity(intent);
    }
}
