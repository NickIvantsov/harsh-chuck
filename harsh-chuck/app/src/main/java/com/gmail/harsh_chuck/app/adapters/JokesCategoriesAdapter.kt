package com.gmail.harsh_chuck.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R
import javax.inject.Inject


class JokesCategoriesAdapter @Inject constructor(val context: Context) :
    RecyclerView.Adapter<JokesCategoriesAdapter.JokesCategoriesViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    val data: MutableList<String> = ArrayList()

    class JokesCategoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvJokesCategories: TextView = itemView.findViewById(R.id.tv_jokes_categories)
        val cbJokesCategories: CheckBox = itemView.findViewById(R.id.cb_jokes_categories)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokesCategoriesViewHolder {
        val itemView = inflater.inflate(R.layout.jokes_categories_item, parent, false)
        return JokesCategoriesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: JokesCategoriesViewHolder, position: Int) {
        val infoElement = data[position]
        holder.tvJokesCategories.text = infoElement

        holder.cbJokesCategories.setOnClickListener {
            Toast.makeText(context, infoElement, Toast.LENGTH_SHORT).show()
        }
    }

    fun add(newElement: String) {
        data.add(newElement)
        notifyItemInserted(data.size - 1)
    }

    fun removeAt(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, data.size)
    }

    fun removeElement(newElement: String) {
        loop@ for (oldElement in data) {
            data.remove(oldElement)
            notifyItemInserted(data.size - 1)
        }

    }


    fun clearElements() {
        data.clear()
        notifyDataSetChanged()
    }


    override fun getItemCount() = data.size

}