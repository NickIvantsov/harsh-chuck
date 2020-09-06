package com.gmail.harsh_chuck.app.fragments.jokesHistory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.gmail.harsh_chuck.R

class JokesHistoryCategoryFragment : Fragment() {

    companion object {
        fun newInstance() = JokesHistoryCategoryFragment()
    }

    private val viewModel: JokesHistoryCategoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.jokes_history_category_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}