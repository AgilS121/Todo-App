package com.dicoding.todoapp.ui.list

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
class TaskActivityTest {
    @get:Rule
    val activity = ActivityScenarioRule(TaskActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @Test
    fun test_AddTask() {
        onView(withId(R.id.fab)).perform(click())
        intended(hasComponent(AddTaskActivity::class.java.name))
    }

    @After
    fun clear() {
        Intents.release()
    }
}