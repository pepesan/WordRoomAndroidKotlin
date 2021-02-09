package com.example.wordroomandroidkotlin

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.setTitle(R.string.list_fragment_label)
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*
        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

         */
        val recyclerView =  view.findViewById<RecyclerView>(R.id.recyclerview)
        var adapter = WordListAdapterFragment(list = arrayListOf(), activity = activity as RecycleViewActivity)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(activity!!)

        wordViewModel.allWords.observe(activity!!, Observer { words ->
            // Update the cached copy of the words in the adapter.
            adapter = WordListAdapterFragment(list = words, activity = activity as RecycleViewActivity)
            recyclerView.adapter = adapter
        })

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
    }
    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_recycleview)
        item.isVisible = false
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /*
        if(item.itemId ==android.R.id.home) {
            findNavController().navigate(R.id.action_detailFragment_to_FirstFragment)
        }
        //(activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
         */
        return super.onOptionsItemSelected(item)


    }
}