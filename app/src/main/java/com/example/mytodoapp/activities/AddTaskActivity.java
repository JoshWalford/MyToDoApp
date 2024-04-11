package com.example.mytodoapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mytodoapp.OnItemClickListener;
import com.example.mytodoapp.R;
import com.example.mytodoapp.database.DBHandler;
import com.example.mytodoapp.fragments.MonthFragment;

public class AddTaskActivity extends AppCompatActivity implements OnItemClickListener {
    private EditText taskTitle,taskDuration,taskNote;
    private Button saveBtn,cancelBtn;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todolayout);

        taskTitle = findViewById(R.id.edittitle);
        taskDuration = findViewById(R.id.editdur);
        taskNote = findViewById(R.id.notetxt);

        saveBtn= findViewById(R.id.saveBtn);
        cancelBtn = findViewById(R.id.cancelBtn);

        dbHandler = new DBHandler(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = taskTitle.getText().toString();
                String duration = taskDuration.getText().toString();
                String note = taskNote.getText().toString();

                if (title.isEmpty() && duration.isEmpty() && note.isEmpty()) {
                    Toast.makeText(AddTaskActivity.this, "Please fill out all the field", Toast.LENGTH_SHORT).show();
                    return;
                }

                dbHandler.addNewTask(title,duration,note);

                Toast.makeText(AddTaskActivity.this, "Your task has been added", Toast.LENGTH_SHORT).show();
                taskTitle.setText("");
                taskDuration.setText("");
                taskNote.setText("");

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainActivity","Task cancelled");
                finish();
            }
        });
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(AddTaskActivity.this, MonthFragment.class);
        startActivity(intent);
    }
}

