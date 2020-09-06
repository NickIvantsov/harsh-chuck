package com.gmail.harsh_chuck.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R

abstract class JokesHistoryAdapter<T>(context: Context) :
    RecyclerView.Adapter<JokesHistoryAdapter<T>.ViewHolder>() {
    val mItems: ArrayList<T> = ArrayList()

    private val mContext: Context = context


    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view: View = inflater.inflate(R.layout.joke_history_category_item, viewGroup, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {
        val tvCategoryHistoryJoke: TextView = inflate.findViewById(R.id.tv_category_history_joke)

    }

    abstract fun addItem(item: T)
}

