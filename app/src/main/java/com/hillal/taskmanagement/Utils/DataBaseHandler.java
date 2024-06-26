package com.hillal.taskmanagement.Utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hillal.taskmanagement.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static  final int VERSION =1;
    private static  final String NAME = "TodoDataBAse";
    private static  final String TABLE ="TodoTable";
    private static  final String ID ="id";
    private static  final String TASK ="task";
    private static  final String STATUS ="status";
    private static  final String QUERRY_TO_CREATE_TABLE ="CREATE TABLE "+TABLE+"("+ID+"INTEGER PRIMARY KEY AUTOINCREMENT"+TASK+"TEXT"+STATUS+"INTEGER)";

    private SQLiteDatabase db;
    private  DataBaseHandler(Context context){
        super(context,NAME,null,VERSION);
    }

    public  void onCreate(SQLiteDatabase db){
        db.execSQL(QUERRY_TO_CREATE_TABLE);
    }

    public  void onUpgrade(SQLiteDatabase db,int oldVersion, int newVersion){
        //Dropping old tables
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        //create new tables
        onCreate(db);
    }

    public void openDatabase(){
        db = this.getWritableDatabase();
    }
    public void insertTask(ToDoModel task){
        ContentValues cv = new ContentValues();
        cv.put(TASK,task.getTask());
        cv.put(STATUS,0);
        db.insert(TABLE,null,cv);
    }

    @SuppressLint("Range")
    public List<ToDoModel> getAllTasks(){
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor cur = null;
        db.beginTransaction();
        try {
            cur = db.query(TABLE,null,null,null,null,null,null);
            if (cur!=null){
                if (cur.moveToFirst()){
                    do {
                        ToDoModel task = new ToDoModel();
                        task.setId(cur.getColumnIndex(ID));
                        task.setTask(cur.getString(cur.getColumnIndex(TASK)));
                        task.setStatus(cur.getInt(cur.getColumnIndex(STATUS)));
                        taskList.add(task);
                    }while (cur.moveToNext());
                }
            }
        }finally {
            db.endTransaction();
            cur.close();
        }
        return  taskList;
    }


    public void updateStatus(int id ,int status){
        ContentValues cv = new ContentValues();
        cv.put(STATUS,status);
        db.update(TABLE,cv,ID+"=?",new String[]{String.valueOf(id)});
    }

    public void updateTask(int id,String task){
        ContentValues cv = new ContentValues();
        cv.put(TASK,task);
        db.update(TABLE,cv,ID+"=?",new String[]{String.valueOf(id)});
    }

    public void deleteTask(int id){
        db.delete(TABLE,ID+"=?",new String[]{String.valueOf(id)});
    }

}
