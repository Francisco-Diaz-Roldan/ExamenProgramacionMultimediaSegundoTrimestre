package com.example.examenprogramacionmultimediasegundotrimestre.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.examenprogramacionmultimediasegundotrimestre.adapter.CesurProvider

class DBOpenHelper private constructor(context: Context?):
    SQLiteOpenHelper(context, CesurContract.NOMBRE_DB, null, CesurContract.VERSION) {

    override fun onCreate(sqLiteDatabase: SQLiteDatabase?) {
        try {
            sqLiteDatabase?.execSQL(
                "CREATE TABLE ${CesurContract.Companion.Entrada.NOMBRE_TABLA}" +
                        "(${CesurContract.Companion.Entrada.COLUMNA_ID} INTEGER NOT NULL" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_NOMBRE} NVARCHAR(20)" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_DIRECCION} NVARCHAR(20)" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_CIUDAD} NVARCHAR(20)" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_IMAGEN} INTEGER NOT NULL" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_LATITUD} REAL NOT NULL" +
                        ",${CesurContract.Companion.Entrada.COLUMNA_LONGITUD} REAL NOT NULL);"
            )

            // Inserto los datos en la tabla
            inicializarBBDD(sqLiteDatabase)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun inicializarBBDD(db: SQLiteDatabase?) {
        val lista = CesurProvider.listaCesures // Asegúrate de tener una lista válida de CesurProvider
        for (cesur in lista) {
            db?.execSQL(
                "INSERT INTO ${CesurContract.Companion.Entrada.NOMBRE_TABLA}(" +
                        "${CesurContract.Companion.Entrada.COLUMNA_ID}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_NOMBRE}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_DIRECCION}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_CIUDAD}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_IMAGEN}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_LATITUD}," +
                        "${CesurContract.Companion.Entrada.COLUMNA_LONGITUD})" +
                        " VALUES (" +
                        "${cesur.id}," +
                        "'${cesur.nombre}'," +
                        "'${cesur.direccion}'," +
                        "'${cesur.ciudad}'," +
                        "${cesur.imagen}," +
                        "${cesur.latitud}," +
                        "${cesur.longitud});"
            )
        }
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ${CesurContract.Companion.Entrada.NOMBRE_TABLA};")
        onCreate(sqLiteDatabase)
    }

    companion object {
        private var dbOpen: DBOpenHelper? = null
        fun getInstance(context: Context?): DBOpenHelper? {
            if (dbOpen == null) dbOpen = DBOpenHelper(context)
            return dbOpen
        }
    }
}