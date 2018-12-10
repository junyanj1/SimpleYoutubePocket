package com.ics45j.junyanj1.simpleyoutubepocket;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RemoveVideoLink extends AppCompatActivity {
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChildEventListener childEventListener;

    private ArrayList<LinkClip> LinkList;

    private ArrayList<LinkClip> searchResults;

    private LinkAdapter LCAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        // Initializes the references to the database and contacts
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("LinkClip");

        LinkList = new ArrayList<LinkClip>();
        searchResults = new ArrayList<LinkClip>();

        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LinkList.add(dataSnapshot.getValue(LinkClip.class));
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
        ListView results = findViewById(R.id.RemovalList);
        results.setAdapter(LCAdapter);

        results.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LinkClip selectedItem = (LinkClip) parent.getItemAtPosition(position);

                myRef.child(selectedItem.getUid()).removeValue();

                LinkList.remove(selectedItem);

                refresh(selectedItem.getYoutuberName());
            }
        });
    }

    public void refresh(String update){
        LCAdapter.clear();
        for(LinkClip c: LinkList){
            if(c.getYoutuberName().equalsIgnoreCase(update)){
                LCAdapter.add(c);
            }
        }
    }

    public void removeRecord(View view) {
        LCAdapter.clear();
        boolean found = false;
        EditText text = (EditText) findViewById(R.id.RemoveQuery);
        String search = text.getText().toString();
        for(LinkClip lc : LinkList){
            if(lc.getYoutuberName().equalsIgnoreCase(search)){
                LCAdapter.add(lc);
                found = true;
            }
        }
        if(!found){
            Toast.makeText(this, text.getText().toString() + " not found.", Toast.LENGTH_LONG).show();
        }
        text.setText("");
    }
    public void goHome(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
