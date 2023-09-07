package com.ak.chara;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;

public class ViewEntries extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<EntryModal> entryModalArrayList;
    private DBManager dbManager;
    private EntryRvAdapter entryRvAdapter;
    private RecyclerView entriesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_entry);

        // initializing our all variables.
        entryModalArrayList = new ArrayList<>();
        dbManager = new DBManager(ViewEntries.this);

        // getting our course array
        // list from db handler class.
        entryModalArrayList = dbManager.readEntriesTest();

        // on below line passing our array list to our adapter class.
        entryRvAdapter = new EntryRvAdapter(entryModalArrayList, ViewEntries.this);
        entriesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewEntries.this, RecyclerView.VERTICAL, false);
        entriesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        entriesRV.setAdapter(entryRvAdapter);
    }
}
