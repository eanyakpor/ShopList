package com.example.shoplist


import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.icu.text.CaseMap.Title
import com.example.shoplist.R

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_todo.view.*
import org.w3c.dom.Text

//bridge that connects the view on the phone, frontend to the backend of app-specific data
// we will give it a list of data
// listOfTasks -the list of strings
// error with ViewHolder had to add an inner class ViewHolder to fix this error
// was having an error with adapter class because it needs methods that'll
// connect to the list of views, front end to backend app-specic data set
// to fix this this I just added left click and implement all members
// going to create a constuctor for adapter that accepts a list of strings
// made list of items hold a class
// removed mutable of list task argument 1
class TaskItemAdapter( private val listOfItems: MutableList<ToDo>,
                       val onClickListener: OnLongClickListener): RecyclerView.Adapter<TaskItemAdapter.ViewHolder>() {
    interface OnLongClickListener {
        // create interface since backend doesnt hapen in adpater class
        // funciton that will delete what postion was clicked
        fun onItemLongClicked(position: Int)
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    // every single row is an item view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //store refrences
        val textView: TextView

        //The code inside the init block is the first to be executed when the class is instantiated.
        init {
            // changed this from R.id.text1 to R.id.tvToDoTitle
            // to better connect with what I have in item_todo xml file
            textView = itemView.findViewById(R.id.tVToDoTitle)
            itemView.setOnLongClickListener {
                // calling the val on click to delete the this certain position
                onClickListener.onItemLongClicked(absoluteAdapterPosition)
                true
            }


        }

    }

    // inflates, process of adding a view, usually from the xml file and create the viewholder
    // tells recycler view how to layout each specfic item

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        // using android simple list layout default here since we not making a complicated app
        // hoverin the android default layout and pressing cntrl+b you can see whats being
        // added as a view
        // changing this R.layout.simple_list_item_1 to R.layout.item_todo
        val contactView = inflater.inflate(R.layout.item_todo, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }
    fun addTodo(todo: ToDo){
        listOfItems.add(todo)
        notifyItemInserted(listOfItems.size-1)
    }
    // function to to deal with strinking through comments
    private fun toggleStrikeThrough(tvToDoTitle: TextView, isChecked: Boolean){
        if (isChecked) {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG

        } else {
            tvToDoTitle.paintFlags = tvToDoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }

    }



    // connects the view, front end with back app-specific data
    // going to use the list of strings to populate whats inside viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get the data model based on position
//        val contact: Contact = mContacts.get(position)
//        // Set item views based on your views and data model
//        val textView = viewHolder.nameTextView
//        textView.setText(contact.name)
//        val button = viewHolder.messageButton
//        button.text = if (contact.isOnline) "Message" else "Offline"
//        button.isEnabled = contact.isOnline
        // get position of added view
        // val:They can be assigned a value only once
        // getting the position of the inserted list of strings right here
        var item = listOfItems[position]

        // Set item views based on your views and data model populating ViewHolder
        //holder.textView.text = item
        //was repalced with whats down below
        holder.itemView.apply {
            tVToDoTitle.text = item.val_title
            cDone.isChecked = item.is_Checked
            toggleStrikeThrough(tVToDoTitle,item.is_Checked)
            // may give errors for using the wrong function
            cDone.setOnCheckedChangeListener { _, isChecked ->
                toggleStrikeThrough(tVToDoTitle,isChecked)
                item.is_Checked = !item.is_Checked
            }
        }

//

    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


    }

