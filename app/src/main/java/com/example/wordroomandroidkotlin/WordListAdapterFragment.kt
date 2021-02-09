package com.example.wordroomandroidkotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class WordListAdapterFragment(
    private val list: List<Word>,
    val activity: RecycleViewActivity
)
    : RecyclerView.Adapter<WordListAdapterFragment.MyViewHolder>(){

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
        private var mItem: Word? = null
        var nombre: TextView
        var miid: TextView
        var position: Int? = null

        init {
            nombre = view.findViewById<View>(R.id.title) as TextView
            miid = view.findViewById(R.id.miid)
            view.setOnClickListener(this);

        }

        fun setItem(word: Word, position: Int) {
            mItem = word
            nombre.text = word.word
            miid.text = word.id.toString()
            this.position = position
        }

        override fun onClick(view: View){
            // pasa dato del id
            activity.selectedId = mItem?.id
            (activity as RecycleViewActivity).pasaDato(mItem?.id!!)
            //navega a detalle
            activity.findNavController(R.id.nav_host_fragment)
                .navigate(R.id.action_FirstFragment_to_detailFragment2,)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.word_row, parent, false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cliente = list[position]
        holder.setItem(cliente, position)
        /*
        holder.itemView.setOnClickListener{
            Navigation.createNavigateOnClickListener(R.id.action_SecondFragment_to_detailFragment)
        }
         */
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class WordsComparator : DiffUtil.ItemCallback<Word>() {
        override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean {
            return oldItem.word == newItem.word
        }
    }
}