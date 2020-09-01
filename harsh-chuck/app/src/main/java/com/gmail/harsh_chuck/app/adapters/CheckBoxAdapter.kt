package com.gmail.harsh_chuck.app.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.gmail.harsh_chuck.R

abstract class CheckBoxAdapter<T>(context: Context) :
    RecyclerView.Adapter<CheckBoxAdapter<T>.ViewHolder>() {
    var mSelectedItem = -1
    val mItems: ArrayList<T> = ArrayList()

    private val mContext: Context = context
    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.mCheckBox.isChecked = i == mSelectedItem

    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val inflater = LayoutInflater.from(mContext)
        val view: View =
            inflater.inflate(R.layout.jokes_categories_check_box_item, viewGroup, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(inflate: View) : RecyclerView.ViewHolder(inflate) {
        val mCheckBox: CheckBox = inflate.findViewById(R.id.cb_categore)

        init {
            val clickListener: View.OnClickListener = View.OnClickListener {
                Toast.makeText(mContext, "${mItems[adapterPosition]}", Toast.LENGTH_SHORT).show()
                mSelectedItem = adapterPosition
                notifyDataSetChanged()
            }
            itemView.setOnClickListener(clickListener)
            mCheckBox.setOnClickListener(clickListener)
        }
    }

    abstract fun addItem(item: T)
}