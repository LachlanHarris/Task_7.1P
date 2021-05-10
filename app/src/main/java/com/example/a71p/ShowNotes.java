package com.example.a71p;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.a71p.data.DatabaseHelper;
import com.example.a71p.model.Note;

import java.util.List;

public class ShowNotes extends AppCompatActivity implements RecyclerViewAdapter.OnRowClickListener{
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        DatabaseHelper db = new DatabaseHelper(ShowNotes.this);
        noteList = db.fetchAllNotes();

        recyclerView = findViewById(R.id.notesList);
        recyclerViewAdapter = new RecyclerViewAdapter(noteList, this, this);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void onItemClick(int position) {
        Intent intent2 = new Intent(ShowNotes.this, UpdateDelete.class);
        intent2.putExtra("description", noteList.get(position).getDescription());
        //intent2.putExtra("rowID", noteList.get(position).);
        startActivity(intent2);
        finish();
    }
}