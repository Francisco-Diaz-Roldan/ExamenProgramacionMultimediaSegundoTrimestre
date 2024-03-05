package com.example.examenprogramacionmultimediasegundotrimestre.adapter

import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.examenprogramacionmultimediasegundotrimestre.databinding.ListItemCesurBinding
import com.example.examenprogramacionmultimediasegundotrimestre.domain.Cesur

class CesurViewHolder (view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    val binding = ListItemCesurBinding.bind(view)
    private lateinit var cesur: Cesur


    fun render(item: Cesur, onClickListener: (Cesur) -> Unit) {
        binding.ivImagen.setImageResource(item.imagen)
        binding.tvNombre.text = item.nombre
        binding.tvDireccion.text = item.direccion
        binding.tvCiudad.text = item.ciudad

        itemView.setOnClickListener {
            onClickListener(item)
        }
        cesur = item
        itemView.setOnCreateContextMenuListener(this)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menu!!.setHeaderTitle(cesur.nombre)
        menu.add(this.adapterPosition, 0, 0, "Eliminar")
        menu.add(this.adapterPosition, 1, 1, "Editar")
    }
}