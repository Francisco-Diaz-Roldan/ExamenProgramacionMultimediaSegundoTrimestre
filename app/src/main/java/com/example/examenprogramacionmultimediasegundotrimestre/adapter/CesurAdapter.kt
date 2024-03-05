package com.example.examenprogramacionmultimediasegundotrimestre.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examenprogramacionmultimediasegundotrimestre.R
import com.example.examenprogramacionmultimediasegundotrimestre.domain.Cesur

class CesurAdapter(private val cesurList:List<Cesur>, private val onClickListener: (Cesur) -> Unit) : RecyclerView.Adapter<CesurViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CesurViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return  CesurViewHolder(layoutInflater.inflate(R.layout.list_item_cesur, parent, false))
    }

    override fun getItemCount(): Int = cesurList.size

    override fun onBindViewHolder(holder: CesurViewHolder, position: Int) {
        val item = cesurList[position]
        holder.render(item, onClickListener)
    }
}