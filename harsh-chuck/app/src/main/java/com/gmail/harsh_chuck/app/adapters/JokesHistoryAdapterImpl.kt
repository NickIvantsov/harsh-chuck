package com.gmail.harsh_chuck.app.adapters

import android.content.Context
import javax.inject.Inject

class JokesHistoryAdapterImpl @Inject constructor(context: Context) :
    JokesHistoryAdapter<String>(context) {

    override fun addItem(item: String) {
        mItems.forEach {
            when (it) {
                item -> {
                    return
                }
            }
        }
        mItems.add(item)
        notifyItemInserted(mItems.size - 1)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategoryHistoryJoke.text = mItems[position]
        holder.tvCategoryHistoryJoke.setOnClickListener {
            //todo need impl
        }
    }

}