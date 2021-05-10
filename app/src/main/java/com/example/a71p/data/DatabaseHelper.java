package com.example.a71p.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.a71p.model.Note;
import com.example.a71p.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + " (" + Util.NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
                + Util.DESCRIPTION + " TEXT);";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + Util.TABLE_NAME;
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }

    public long insertNote(Note note)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.DESCRIPTION, note.getDescription());
        long newRowId = db.insert(Util.TABLE_NAME, null, contentValues);
        db.close();
        return newRowId;
    }

    public boolean fetchNote(String description)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME, new String[]{Util.NOTE_ID},  Util.DESCRIPTION + "=?", new String[]{description}, null, null, null);
        int numberOfRows = cursor.getCount();
        db.close();
        if(numberOfRows > 0)
            return true;
        else
            return false;
    }

    public List<Note> fetchAllNotes (){
        List<Note> noteList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String selectAll = " SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        if (cursor.moveToFirst()) {
            do {
                Note note = new Note();
                note.setDescription(cursor.getString(1));

                noteList.add(note);

            } while (cursor.moveToNext());

        }

        return noteList;
    }
    public void updateDescription(String oldDescription, String newDescription)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.DESCRIPTION, newDescription);
        //String updateDescriptionString = "UPDATE " + Util.TABLE_NAME + " SET " + Util.DESCRIPTION + " " + description + " WHERE " + Util.NOTE_ID + " = " + position;
        db.update(Util.TABLE_NAME, contentValues,  Util.DESCRIPTION + " = " + "'" + oldDescription + "'" , null );
    }

    public boolean deleteNote(String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Util.TABLE_NAME,Util.DESCRIPTION + " = " + "'" + description + "'", null ) > 0;
    }

}
