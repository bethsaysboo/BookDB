package com.example.bookdb;



import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etNotes, etAuthor;
    DbAccessObj dbAccessObj;
    TodoRoomDb todoRoomDb;
    TodoDao todoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        todoRoomDb = TodoRoomDb.getDatabase(this);
        todoDao = todoRoomDb.todoDao();
        etTitle = findViewById(R.id.etTitle);
        etNotes = findViewById(R.id.etNotes);
        etAuthor = findViewById(R.id.etAuthor);
        dbAccessObj = new DbAccessObj(this);
        dbAccessObj.openDb();
    }

    public void dbHandler(View view) {
        switch (view.getId()) {
            case R.id.btnCommit:
                String title = etTitle.getText().toString();
                String notes = etNotes.getText().toString();
                String author= etAuthor.getText().toString();
                insertAsync(title,notes, author);
                /*dbAccessObj.createRow(title,notes);
                etTitle.setText("");
                etNotes.setText("");*/
                break;
            case R.id.btnRetrevie:

                /*TextView dbTextView = findViewById(R.id.tvDb);
                dbTextView.setText(dbAccessObj.readRow());*/
                searchTodo(etTitle.getText().toString());
                break;
        }
    }
    private void searchTodo(String searchString) {
        TextView dbTextView = findViewById(R.id.tvDb);

        new SearchTask(searchString,dbTextView).execute();
    }

    private void insertAsync(String title, String notes, String author) {
        new InsertTask(title,notes,author).execute();
        etTitle.setText("");
        etNotes.setText("");
        etAuthor.setText("");
    }



    class InsertTask extends AsyncTask<Void,Void,Void>{
        String mTitle, mNotes, mAuthor;



        public InsertTask(String title, String notes, String author) {
            mTitle = title;
            mNotes = notes;
            mAuthor= author;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo(mTitle,mNotes,mAuthor));

            return null;
        }
    }

    class SearchTask extends AsyncTask<Void,Void, List<Todo>> {
        String mString;
        TextView mTextView;
        public SearchTask(String searchString, TextView dbTextView) {
            mString = searchString;
            mTextView = dbTextView;
        }

        @Override
        protected List<Todo> doInBackground(Void... voids) {
            return todoDao.findWord(mString);

        }

        @Override
        protected void onPostExecute(List<Todo> todos) {
            super.onPostExecute(todos);
            mTextView.setText(todos.get(0).title + "\n"+ todos.get(0).notes+ "\n"+ todos.get(0).author);
        }
    }
}
