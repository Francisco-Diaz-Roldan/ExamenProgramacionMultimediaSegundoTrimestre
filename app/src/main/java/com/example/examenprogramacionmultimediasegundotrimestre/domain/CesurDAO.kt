package com.example.examenprogramacionmultimediasegundotrimestre.domain

import android.content.Context
import android.database.Cursor

class CesurDAO {
    fun cargarTodosLosCesures(context: Context?): MutableList<Cesur> {
        lateinit var res: MutableList<Cesur>
        lateinit var c: Cursor
        try {
            val db = DBOpenHelper.getInstance(context)!!.readableDatabase
            val sql = "SELECT * FROM cesures;"
            c = db.rawQuery(sql, null)
            res = mutableListOf()
            // Lee los resultados del cursor y los agrega a la lista
            while (c.moveToNext()) {
                val nuevo = Cesur(
                    c.getInt(0),          // Columna ID
                    c.getString(1),       // Columna NOMBRE
                    c.getString(2),       // Columna DIRECCION
                    c.getString(3),       // Columna CIUDAD
                    c.getInt(4),          // Columna IMAGEN
                    c.getDouble(5),       // Columna LATITUD
                    c.getDouble(6)        // Columna LONGITUD
                )
                res.add(nuevo)
            }
        } finally {
            c.close()
        }
        return res
    }

}