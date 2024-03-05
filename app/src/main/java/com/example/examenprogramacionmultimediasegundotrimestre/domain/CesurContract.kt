package com.example.examenprogramacionmultimediasegundotrimestre.domain

import android.provider.BaseColumns

class CesurContract {
    companion object {
        const val NOMBRE_DB = "cesures_database"
        const val VERSION = 1

        class Entrada : BaseColumns {
            companion object {
                const val NOMBRE_TABLA = "cesures"
                const val COLUMNA_ID = "id"
                const val COLUMNA_NOMBRE = "nombre"
                const val COLUMNA_DIRECCION = "direccion"
                const val COLUMNA_CIUDAD = "ciudad"
                const val COLUMNA_IMAGEN = "imagen"
                const val COLUMNA_LATITUD = "latitud"
                const val COLUMNA_LONGITUD = "longitud"
            }
        }
    }
}
