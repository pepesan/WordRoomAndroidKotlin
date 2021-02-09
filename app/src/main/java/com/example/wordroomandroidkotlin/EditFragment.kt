package com.example.wordroomandroidkotlin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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
                val word =Word(id!!,editWordView.text.toString())
                wordViewModel.updateElement(word)
                findNavController().navigate(R.id.action_editFragment_to_detailFragment)
            }
        }
        val button_delete = view.findViewById<Button>(R.id.button_delete)
        button_delete.setOnClickListener{
            val word =Word(id!!,editWordView.text.toString())
            findNavController().navigate(R.id.action_editFragment_to_FirstFragment)
            wordViewModel.deleteElement(word)
        }
    }

}