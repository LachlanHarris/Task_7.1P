package com.example.a71p;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a71p.data.DatabaseHelper;
import com.example.a71p.model.Note;

public class NewNote extends AppCompatActivity {
    EditText enterNoteField;
    Button saveButton;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        db = new DatabaseHelper(this);

        enterNoteField = findViewById(R.id.enterNoteField);
        saveButton = findViewById(R.id.saveButton);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String description = enterNoteField.getText().toString();
            long result = db.insertNote(new Note(description));
            if (result > 0)
            {
                Toast.makeText(NewNote.this, "Registered successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewNote.this, MainActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(NewNote.this, "Registration error!", Toast.LENGTH_SHORT).show();
            }
            finish();
            }
        });
    }
}