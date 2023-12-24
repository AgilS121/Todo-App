package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var taskDetailViewModel: DetailTaskViewModel
    private lateinit var title: TextInputEditText
    private lateinit var description: TextInputEditText
    private lateinit var date: TextInputEditText
    private lateinit var DtButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        supportActionBar?.title = getString(R.string.detail_task)
        initializeView()
        observeDetails()
    }

    private fun initializeView() {
        title = findViewById(R.id.detail_ed_title)
        description = findViewById(R.id.detail_ed_description)
        date = findViewById(R.id.detail_ed_due_date)
        DtButton = findViewById(R.id.btn_delete_task)

        DtButton.setOnClickListener {
            deleteTask()
        }
    }

    private fun observeDetails() {
        val taskIntent = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(application)
        taskDetailViewModel = ViewModelProvider(this, factory).get(DetailTaskViewModel::class.java)

        taskDetailViewModel.apply {
            setTaskId(taskIntent)
            task.observe(this@DetailTaskActivity) {task ->
                task?.let {
                    showContent(task)
                }
            }
        }
    }

    private fun showContent(task: Task) {
        title.setText(task.title)
        description.setText(task.description)
        date.setText(DateConverter.convertMillisToString(task.dueDateMillis))
    }

    private fun deleteTask() {
        taskDetailViewModel.deleteTask()
        finish()
    }
}