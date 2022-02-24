package com.example.bookdb;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {

    public Todo(){}

    @PrimaryKey(autoGenerate = true)
    public int tid;

    public String title;
    public String notes;
    public String author;

    public Todo(String mTitle, String mNotes, String mAuthor) {
        this.title = mTitle;
        this.notes = mNotes;
        this.author = mAuthor;
    }
}