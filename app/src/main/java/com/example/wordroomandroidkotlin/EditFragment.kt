package com.example.wordroomandroidkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController


class EditFragment : Fragment() {
    private lateinit var editWordView: EditText
    var id : Int? = null
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        activity?.setTitle(R.string.edit_fragment_label)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editWordView = view.findViewById(R.id.edit_word)
        Log.d("app","Edit: "+ wordViewModel.selectedItem?.value?.word)
        id = (activity as RecycleViewActivity).selectedId
        wordViewModel.getElementById(id!!)
        wordViewModel.selectedItem?.observe(requireActivity()){
            Log.d("app", "Observed word: $it")
            editWordView.setText(it?.word)
        }
        val button = view.findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWordView.text)) {
                // No hay dato
            } else {
                update()
            }
        }
        val button_delete = view.findViewById<Button>(R.id.button_delete)
        button_delete.setOnClickListener{
            delete()
        }
    }

    private fun update() {
        val word = Word(id!!, editWordView.text.toString())
        wordViewModel.updateElement(word)
        findNavController().navigate(R.id.action_editFragment_to_detailFragment)
    }

    private fun delete() {
        val word = Word(id!!, editWordView.text.toString())
        findNavController().navigate(R.id.action_editFragment_to_FirstFragment)
        wordViewModel.deleteElement(word)
    }

    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_recycleview)
        item.isVisible = false
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.edit_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_edit_delete){
            delete()
        }
        if(item.itemId == R.id.action_edit_edit){
            update()
        }
        if(item.itemId ==android.R.id.home) {
            findNavController().navigate(R.id.action_editFragment_to_detailFragment)
        }
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
        return super.onOptionsItemSelected(item)

    }

}