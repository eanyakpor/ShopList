package com.example.shoplist

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.View.OnLongClickListener
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//bridge that connects the view on the phone, frontend to the backend of app-specific data
// we will give it a list of data
// listOfTasks -the list of strings
// error with ViewHolder had to add an inner class ViewHolder to fix this error
// was having an error with adapter class because it needs methods that'll
// connect to the list of views, front end to backend app-specic data set
// to fix this this I just added left click and implement all members
// going to create a constuctor for adapter that accepts a list of strings
class TaskItemAdapter(val listOfItems: List<String>,
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
            textView = itemView.findViewById(R.id.text1)
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
        val contactView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
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
        val item = listOfItems.get(position)
        // Set item views based on your views and data model populating ViewHolder
        holder.textView.text = item

//

    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }


    }

