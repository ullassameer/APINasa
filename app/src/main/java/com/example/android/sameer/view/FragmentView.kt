package com.example.android.sameer.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.android.sameer.adapter.PhotosAdapter
import com.example.android.sameer.databinding.FragmentViewsBinding


class FragmentView : Fragment() {


    private val viewModel: ViewModel by lazy {
        ViewModelProviders.of(this).get(ViewModel::class.java)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentViewsBinding.inflate(inflater)

        // Allows Data Binding to Observe LiveData with lifecycle of this Fragment
        binding.setLifecycleOwner(this)

        // Giving the binding access to the ViewModel
        binding.viewModel = viewModel

        // Seting adapter(REcyclerview)
        binding.photosGrid.adapter = PhotosAdapter()

        setHasOptionsMenu(true)
        return binding.root
    }

}
