package com.example.mobile_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserActivity1 extends AppCompatActivity {

    EditText nameBox;
    EditText numberBox;
    EditText lineBox;
    EditText line1Box;
    Button delButton;
    Button saveButton;

    DatabaseHelper sqlHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    long userId=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user1);

        nameBox = findViewById(R.id.name);
        numberBox = findViewById(R.id.Number);
        lineBox = findViewById(R.id.line);
        line1Box = findViewById(R.id.line1);
        delButton = findViewById(R.id.deleteButton);
        saveButton = findViewById(R.id.saveButton);
        sqlHelper = new DatabaseHelper(this);
        db = sqlHelper.getWritableDatabase();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userId = extras.getLong("_id");
        }
        // если 0, то добавление
        if (userId > 0) {
            // получаем элемент по id из бд
            userCursor = db.rawQuery("select * from " + DatabaseHelper.TABLE1 + " where " +
                    DatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(userId)});
            userCursor.moveToFirst();
            nameBox.setText(userCursor.getString(1));
            numberBox.setText(userCursor.getString(2));
            lineBox.setText(userCursor.getString(3));
            line1Box.setText(userCursor.getString(4));

            userCursor.close();
        } else {
            // скрываем кнопку удаления
            delButton.setVisibility(View.GONE);
        }
    }

    public void save(View view){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.COLUMN_NAME, nameBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_NUMBER, numberBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_LINE, lineBox.getText().toString());
        cv.put(DatabaseHelper.COLUMN_LINE1, line1Box.getText().toString());

        if (userId > 0) {
            db.update(DatabaseHelper.TABLE1, cv, DatabaseHelper.COLUMN_ID + "=" + userId, null);
        } else {
            db.insert(DatabaseHelper.TABLE1, null, cv);
        }
        goHome();
    }
    public void delete(View view){
        db.delete(DatabaseHelper.TABLE1, "_id = ?", new String[]{String.valueOf(userId)});
        goHome();
    }
    private void goHome(){
        // закрываем подключение
        db.close();
        // переход к главной activity
        Intent intent = new Intent(this, ItemsActivity1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}