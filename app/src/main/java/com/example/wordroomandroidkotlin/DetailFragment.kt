package com.example.wordroomandroidkotlin

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
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
        activity?.setTitle(R.string.detail_fragment_label)
        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true);
        setHasOptionsMenu(true)
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
    override fun onPrepareOptionsMenu(menu: Menu){
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.action_recycleview)
        item.isVisible = false
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.action_detail_edit){
            findNavController().navigate(R.id.action_detailFragment_to_editFragment)
        }
        if(item.itemId ==android.R.id.home) {
            findNavController().navigate(R.id.action_detailFragment_to_FirstFragment)
        }
        (activity as AppCompatActivity).getSupportActionBar()?.setDisplayHomeAsUpEnabled(false);
        return super.onOptionsItemSelected(item)

    }


}