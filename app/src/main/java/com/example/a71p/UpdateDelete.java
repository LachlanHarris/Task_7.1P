package com.example.a71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.a71p.data.DatabaseHelper;

public class UpdateDelete extends AppCompatActivity {

    EditText noteUpdateField;
    Button updateButton;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);
        DatabaseHelper db = new DatabaseHelper(UpdateDelete.this);

        noteUpdateField = findViewById(R.id.noteUpdateField);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        Intent intent = getIntent();
        String description = intent.getStringExtra("description");
        noteUpdateField.setText(description);
        int rowID = intent.getIntExtra("rowID", -1);
        Intent intent2 = new Intent(UpdateDelete.this, ShowNotes.class);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateDescription(description, noteUpdateField.getText().toString());
                startActivity(intent2);
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNote(description);
                startActivity(intent2);
                finish();
            }
        });
    }
}