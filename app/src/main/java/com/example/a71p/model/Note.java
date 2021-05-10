package com.example.a71p.model;

import android.content.Intent;

public class Note {
    private int noteID;
    private String description;

    public Note( String description) {
        this.description = description;
    }
    public Note() {
    }

    public int getNoteID() {
        return noteID;
    }

    public void setNoteID(int noteID) {
        this.noteID = noteID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
