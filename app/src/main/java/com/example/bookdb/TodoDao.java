package com.example.bookdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void insert(Todo todo);

    @Query("SELECT * FROM Todo WHERE title LIKE :searchString ")
    public List<Todo> findWord(String searchString);

  /* @Update
   public void updateWords(Word... words);*/

  /* @Query("DELETE FROM word_table")
   void deleteAll();
   @Query("SELECT * from word_table ORDER BY word ASC")
   List<Word> getAllWords();
   */

}

