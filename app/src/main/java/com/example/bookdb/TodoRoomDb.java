package com.example.bookdb;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
//create a room db and a table todo like dbhelper
public abstract class TodoRoomDb extends RoomDatabase {

    public abstract TodoDao todoDao();
    private static TodoRoomDb INSTANCE;

    static TodoRoomDb getDatabase(Context context){
        INSTANCE = Room.databaseBuilder(context, TodoRoomDb.class, "cognizant_db")
                .build();
        return INSTANCE;
    }



}