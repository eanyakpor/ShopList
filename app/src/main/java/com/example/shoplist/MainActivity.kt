package com.example.shoplist

import android.R.layout
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_todo.*
import java.io.File
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    // the layout manager will have manage these in some format
    // changed the mutable list from strings to a class ToDo
    var listOfTasks = mutableListOf<ToDo>()
    lateinit var adapter: TaskItemAdapter
    //
    // string of that are lists that we can populate the recuclerview with

    // Create adapter passing in the sample user data

    // implement interface in main using this variable
    val onLongClickListener = object : TaskItemAdapter.OnLongClickListener {
        //        override fun onItemLongClicked(position: Int) {
//            // remove item from list
//            listOfTasks.removeAt(position)
//            adapter.notifyItemRemoved(position)
//        }
        override fun onItemLongClicked(position: Int) {
            //remove item from list
            listOfTasks.removeAt(position)
            Toast.makeText(this@MainActivity, "item was deleted", Toast.LENGTH_SHORT).show()
            adapter.notifyDataSetChanged()
            // saveItems()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // when running call activity main
        // setting content view to xml file
        setContentView(R.layout.activity_main)
        //     Find the toolbar view inside the activity layout
        //val toolbar: Toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        // setSupportActionBar(toolbar)
        //1. detect when use click button
        // must grab refrence by using findviewbyId
        // a pointer to the object button in our xml file

//        findViewById<Button>(R.id.Btn).setOnClickListener {
//            // now anytime the user clicks on the button any code in here will
//            // run once the button is clicked
//            // takes in a tag message and a regular string to in the format tag: " "
//            Log.i("Hello Emi", "User clicked on the button 2")
//        }
        // recycler view needs a layo ut manager to orgainaze the data on the phone
        // also manages when an item view is needed or not
        // next is the adapter,binds views that are displayed with their data with their app specific
        // data.  that requires a viewholder
        // so basically recyclerview + layoutmanager is front end, adapter is the "road" that connects
        // that with backend app specifc dataset
        // You will have to override two main methods: one to inflate the view and its view holder,
        // and another one to bind data to the view.
        // look up recycler view in xml file using find view by id
        //  loadItem()
        // creating toast messages to check if both icons are the toolbar are clickable
        val arrLeft = findViewById<ImageView>(R.id.leftArrow)
        val menRight = findViewById<ImageView>(R.id.rightMenu)
        arrLeft.setOnClickListener {
            Toast.makeText(this,"left arrow was clicked", Toast.LENGTH_SHORT).show()
        }
        menRight.setOnClickListener {
            Toast.makeText(this,"right menu was clicked", Toast.LENGTH_SHORT).show()
        }
        val recyclerView = findViewById<RecyclerView>(R.id.rView)
        adapter = TaskItemAdapter(listOfTasks, onLongClickListener)
        //Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)


        // what the user entered in the text view and add it to the list of tasks
        //  val inputTextField = findViewById<EditText>(R.id.addItem)

        // going to add implementation to the add button
        //1. call refrecne to add btn using findviewbyid then put code into the set on click listener
        findViewById<FloatingActionButton>(R.id.Btn).setOnClickListener {
            // was tvtodotitle
            val todoTitle = addItem.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = ToDo(todoTitle)
                adapter.addTodo(todo)
                findViewById<EditText>(R.id.addItem).setText("")
            }
//            // code here will cause the button when clicked to do something
//            //Log.i("bruh", "user has clicked the button")
//            //1. grab the text user has inputted into editText editText id is @+id/addItem
//            // here it seems text turns into into a string
//            // text: Return the text that TextView is displaying.
//            // changed from addItem to
//            val userInput = findViewById<EditText>(R.id.addItem).text.toString()
//            //2. add the string list of tasks, String: listOfTasks
//            listOfTasks.add(userInput)
//            // we have to notify our adapater that our data has been update
//            // -1 because we start at 0 in the list of sizes
//            adapter.notifyItemInserted(listOfTasks.size -1 )
//
//            //3. Reset TextField
//            inputTextField.setText("")
//            //userInput.setText(null);
//            // need to get the refrence of edittext than empty string
//            //findViewById<EditText>(R.id.addItem).setText("")
//            saveItems()
        }

    }
}


//    // trying to figure out how to store the data inputed in the user's text view from the recycler view
//    //1. save the data the user has inputted
//    fun getDataFile() : File {
//        return File(filesDir, "data.txt")
//    }
//    // going to load items into task
//    fun loadItem() {
//        try {
//            listOfTasks = org.apache.commons.io.FileUtils.readLines(getDataFile(), Charset.defaultCharset())
//        } catch(ioException: IOException){
//            ioException.printStackTrace()
//        }
//
//    }
//    // create method to get the data we need this data is gotten from reading user input/file
//    // loading items by reading line by line in file
//    //save/ write to a file
//    fun saveItems() {
//        // always use try catch block when working with files
//        // creating a new line in  a file called data.txt were populating it in the .txt file
//        try {
//            org.apache.commons.io.FileUtils.writeLines(getDataFile(),listOfTasks)
//        }
//        catch(ioException: IOException){
//            ioException.printStackTrace()
//        }
//    }
//
//}