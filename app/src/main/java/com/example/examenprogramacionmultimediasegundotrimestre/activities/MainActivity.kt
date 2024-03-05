package com.example.examenprogramacionmultimediasegundotrimestre.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.examenprogramacionmultimediasegundotrimestre.R
import com.example.examenprogramacionmultimediasegundotrimestre.adapter.CesurAdapter
import com.example.examenprogramacionmultimediasegundotrimestre.databinding.ActivityMainBinding
import com.example.examenprogramacionmultimediasegundotrimestre.domain.Cesur
import com.example.examenprogramacionmultimediasegundotrimestre.domain.CesurDAO

private lateinit var binding : ActivityMainBinding
private var cesurDAO: CesurDAO = CesurDAO()
private lateinit var listaCesur: List<Cesur>

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
    }

    private fun onItemSelected(cesur: Cesur) {
        Toast.makeText(this, "Yo soy de ${cesur.nombre}", Toast.LENGTH_SHORT).show()
    }
}