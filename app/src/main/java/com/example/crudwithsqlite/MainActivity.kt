package com.example.crudwithsqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudwithsqlite.adapter.TaskListAdapter
import com.example.crudwithsqlite.database.DatabaseHelper
import com.example.crudwithsqlite.model.TaskListModel

class MainActivity : AppCompatActivity() {
    lateinit var recycler_task : RecyclerView
    lateinit var btn_add : Button
    var taskListAdapter : TaskListAdapter ?= null
    var dbHandler : DatabaseHelper ?= null
    var tasklist : List<TaskListModel> = ArrayList<TaskListModel>()
    var linearLayoutManager : LinearLayoutManager ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_task = findViewById(R.id.rv_list)
        btn_add = findViewById(R.id.btn_add_items)

        dbHandler = DatabaseHelper(this)

        btn_add.setOnClickListener {
            val i = Intent(applicationContext, add_task::class.java)
            startActivity(i)
        }
    }

    private fun fetchlist(){
        tasklist = dbHandler!!.getAllTask()
        taskListAdapter = TaskListAdapter(tasklist, applicationContext)
        linearLayoutManager = LinearLayoutManager(applicationContext)
        recycler_task.layoutManager = linearLayoutManager
        recycler_task.adapter = taskListAdapter
        taskListAdapter?.notifyDataSetChanged()
    }
}