package com.hillal.taskmanagement;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hillal.taskmanagement.Adapter.ToDoAdapter;
import com.hillal.taskmanagement.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


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
        task.setTask("This is task one  .");
        task.setId(1);
        task.setStatus(0);
        taskList.add(task);
        tasksAdapter.setTasks(taskList);

        ToDoModel task2 = new ToDoModel();
        task2.setTask("This is task two .");
        task2.setId(2);
        task2.setStatus(0);
        taskList.add(task2);
        tasksAdapter.setTasks(taskList);


        Button add = findViewById(R.id.floatingAction);




    }


    public static  void addTask(){
        Scanner sc = new Scanner(System.in);
        ToDoModel task = new ToDoModel();



    }
}