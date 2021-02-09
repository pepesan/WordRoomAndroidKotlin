package com.example.wordroomandroidkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var editWordView: EditText
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.setTitle("Add")
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editWordView = view.findViewById(R.id.edit_word)

        val button = view.findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            save()
        }
    }

    private fun save() {
        //SAVE
        wordViewModel.insert(Word(word = editWordView.text.toString()))
        // return to list
        findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    /*
    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_recycleview)
        item.isVisible = false
    }

     */


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_add, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_add){
           save()
        }
        if(item.itemId ==android.R.id.home) {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        //(activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(true);
        return super.onOptionsItemSelected(item)

    }
}