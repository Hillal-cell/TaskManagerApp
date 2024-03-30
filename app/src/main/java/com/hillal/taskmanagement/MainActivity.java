package com.hillal.taskmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;

import com.hillal.taskmanagement.Adapter.ToDoAdapter;
import com.hillal.taskmanagement.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity  extends AppCompatActivity{

    private RecyclerView RecyclerView ;

    private ToDoAdapter tasksAdapter;
    private List<ToDoModel> taskList;

    protected  void onCreate(Bundle savedInstanceState) {

        taskList = new ArrayList<>();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        try {
            if (actionBar != null) {
                actionBar.hide();
            }
        } catch (Error e) {
            // Log the error message with a proper tag
            Log.e("SplashActivity", "Error occurred while hiding action bar: " + e.getMessage());
        }
        RecyclerView = findViewById(R.id.recyclerView);
        RecyclerView.setLayoutManager(new LinearLayoutManager(this));

        tasksAdapter = new ToDoAdapter(this);
        RecyclerView.setAdapter(tasksAdapter);

        ToDoModel task = new ToDoModel();
        task.setTask("This is a pre testing .");
        task.setStatus(0);


        taskList.add(task);
        tasksAdapter.setTasks(taskList);
    }
}