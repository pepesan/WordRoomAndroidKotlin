package com.example.wordroomandroidkotlin

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController


class DetailFragment : Fragment() {

    var id : Int? = null
    var textlabel: TextView? = null
    var button_edit: Button? = null
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textlabel = view.findViewById(R.id.label_word)
        button_edit = view.findViewById(R.id.button_edit)
        button_edit?.setOnClickListener{
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)
        }
        id = (activity as RecycleViewActivity).selectedId
        Log.d("app","Detail Act prop: "+ id)
        wordViewModel.getElementById(id!!)
        wordViewModel.selectedItem?.observe(requireActivity()){
            Log.d("app", "Observed word: $it")
            textlabel?.text = it?.word
        }

    }


}