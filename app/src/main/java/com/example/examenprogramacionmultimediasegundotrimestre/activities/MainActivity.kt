package com.example.examenprogramacionmultimediasegundotrimestre.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenprogramacionmultimediasegundotrimestre.R
import com.example.examenprogramacionmultimediasegundotrimestre.adapter.CesurAdapter
import com.example.examenprogramacionmultimediasegundotrimestre.databinding.ActivityMainBinding
import com.example.examenprogramacionmultimediasegundotrimestre.domain.Cesur
import com.example.examenprogramacionmultimediasegundotrimestre.domain.CesurDAO

private lateinit var binding: ActivityMainBinding
private var cesurDAO: CesurDAO = CesurDAO()
private lateinit var listaCesur: List<Cesur>
private lateinit var intentLaunch: ActivityResultLauncher<Intent>
private var id: Int = 0
private var nombre = "Sin nombre"
private var direccion= "Sin direccion"
private var ciudad= "Sin ciudad"
private var imagen= 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listaCesur = cesurDAO.cargarTodosLosCesures(this)
        binding.rvCesur.layoutManager = LinearLayoutManager(this)
        binding.rvCesur.adapter =
            CesurAdapter(listaCesur) { cesur ->
                onItemSelected(cesur)
            }


        intentLaunch =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    val id = data?.getIntExtra("id", 0)
                    nombre = data?.extras?.getString("nombre").toString()
                    direccion = data?.extras?.getString("direccion").toString()
                    ciudad = data?.extras?.getString("ciudad").toString()
                    imagen = data?.extras?.getInt("imagen", 0) ?: 0
                }
            }
    }

    private fun onItemSelected(cesur: Cesur) {
        // Para iniciar la actividad OpenStreetMapsActivity o en GoogleMapsActivity
        val intent = Intent(this, OpenStreetMapsActivity::class.java)
        //Aqui le meto solo lo que vaya a utilizar en el OpenStreetMaps
        intent.putExtra("nombre", cesur.nombre)
        intent.putExtra("direccion", cesur.direccion)
        intent.putExtra("ciudad", cesur.ciudad)
        intent.putExtra("latitud", cesur.latitud)
        intent.putExtra("longitud", cesur.longitud)
        startActivity(intent)
    }

}