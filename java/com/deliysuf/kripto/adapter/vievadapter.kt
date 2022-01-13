package com.deliysuf.kripto.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.deliysuf.kripto.R
import com.deliysuf.kripto.model.model

class vievadapter(private val cyripthoList: List<model>,val listener: Listener): RecyclerView.Adapter<vievadapter.viewAdapter>() {

    interface Listener{
        fun onItemClick(crp:model)
    }

    class viewAdapter(view: View):RecyclerView.ViewHolder(view) {
        val currency=view.findViewById<TextView>(R.id.currencyText)
        val price=view.findViewById<TextView>(R.id.priceText)
        fun bind(cyrptho:model,position: Int,listener: Listener){
            val color= arrayListOf<String>("#b6ff46","#ff7f7f","#3be8ff","#a0ffd8","#ddb1ff","#ffbfcf","#d1ff87","#cdffc4")
                        itemView.setOnClickListener {
                              listener.onItemClick(cyrptho)
                        }
                        itemView.setBackgroundColor(Color.parseColor(color[position%8]))

                        currency.text=cyrptho.currency
                        price.text=cyrptho.price




        }

    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewAdapter {
         var view=LayoutInflater.from(parent.context).inflate(R.layout.rcycler_row,parent,false)
         return viewAdapter(view)
     }

     override fun onBindViewHolder(holder: viewAdapter, position: Int) {
     holder.bind(cyripthoList.get(position),position, listener )
     }

     override fun getItemCount(): Int {
         return cyripthoList.size

     }
 }