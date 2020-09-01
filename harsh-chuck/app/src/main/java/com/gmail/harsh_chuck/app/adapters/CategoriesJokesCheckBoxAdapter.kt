package com.gmail.harsh_chuck.app.adapters

import android.content.Context

class CategoriesJokesCheckBoxAdapter(val context: Context) :
    CheckBoxAdapter<String>(context) {
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        super.onBindViewHolder(viewHolder, i)
        viewHolder.mCheckBox.text = mItems[i]
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
