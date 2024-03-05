package com.example.examenprogramacionmultimediasegundotrimestre.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.examenprogramacionmultimediasegundotrimestre.R
import com.example.examenprogramacionmultimediasegundotrimestre.adapter.CesurProvider
import com.example.examenprogramacionmultimediasegundotrimestre.databinding.ActivityOpenStreetMapsBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.OverlayItem
private lateinit var map: MapView
private lateinit var binding: ActivityOpenStreetMapsBinding
private lateinit var mOverlay: ItemizedOverlayWithFocus<OverlayItem>

class OpenStreetMapsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance()
            .load(applicationContext, this.getPreferences(Context.MODE_PRIVATE))
        binding = ActivityOpenStreetMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cesurLatitud = intent.getDoubleExtra("latitud", 0.0)
        val cesurLongitud = intent.getDoubleExtra("longitud", 0.0)

        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        val mapController = map.controller
        mapController.setZoom(17)

        // Lista de cesures
        val listaCesures = CesurProvider.listaCesures

        // Creo los marcadores
        val items: ArrayList<OverlayItem> = ArrayList()

        for (cesur in listaCesures) {
            val overlayItem = OverlayItem(
                "${cesur.nombre}\n${cesur.direccion}",
                "${cesur.ciudad}",
                GeoPoint(cesur.latitud, cesur.longitud),
            )
            items.add(overlayItem)
        }

        // Añado los marcadores al mapa
        for (cesur in listaCesures) {
            val punto = GeoPoint(cesur.latitud, cesur.longitud)
            val startMarker = Marker(map)
            startMarker.position = punto
            startMarker.icon = ContextCompat.getDrawable(this, R.drawable.cesur2)
            startMarker.title = "nombre" + "\n" + "${cesur.direccion}" + "\n" + "${cesur.ciudad}"
            map.overlays.add(startMarker)
        }

        mapController.setCenter(GeoPoint(cesurLatitud, cesurLongitud))
    }

    override fun onResume() {
        super.onResume()
        map.onResume()
    }

    override fun onPause() {
        super.onPause()
        map.onPause()
    }
}