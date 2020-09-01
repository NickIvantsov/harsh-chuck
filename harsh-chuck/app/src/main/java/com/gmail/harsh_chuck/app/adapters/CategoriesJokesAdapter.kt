package com.gmail.harsh_chuck.app.adapters

import android.content.Context
import javax.inject.Inject

class CategoriesJokesAdapter @Inject constructor(context: Context) :
    RadioAdapter<String>(context) {
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        super.onBindViewHolder(viewHolder, i)
        viewHolder.mRadio.text = mItems[i]
    }

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

}