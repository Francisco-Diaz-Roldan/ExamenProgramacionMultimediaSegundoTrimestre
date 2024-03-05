package com.example.examenprogramacionmultimediasegundotrimestre.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examenprogramacionmultimediasegundotrimestre.adapter.CesurProvider
import com.example.examenprogramacionmultimediasegundotrimestre.databinding.ActivityOpenStreetMapsBinding
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.ItemizedIconOverlay
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus
import org.osmdroid.views.overlay.OverlayItem

private lateinit var  map: MapView
private lateinit var  binding: ActivityOpenStreetMapsBinding
class OpenStreetMapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Configuration.getInstance()
            .load(applicationContext, this.getPreferences(Context.MODE_PRIVATE))
        binding = ActivityOpenStreetMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val paisLatitud = intent.getDoubleExtra("latitud", 0.0)
        val paisLongitud = intent.getDoubleExtra("longitud", 0.0)

        map = binding.map
        map.setTileSource(TileSourceFactory.MAPNIK)
        map.setMultiTouchControls(true)
        val mapController = map.controller
        mapController.setZoom(19)

        val items: ArrayList<OverlayItem> = ArrayList()

        val listaCesures = CesurProvider.listaCesures

        for (cesur in listaCesures) {
            val overlayItem = OverlayItem(
                "${cesur.nombre}\n${cesur.direccion}",
                "${cesur.ciudad}",
                GeoPoint(cesur.latitud, cesur.longitud),
            )
            items.add(overlayItem)
        }

        val mOverlay: ItemizedOverlayWithFocus<OverlayItem> =
            ItemizedOverlayWithFocus(
                items,
                object : ItemizedIconOverlay.OnItemGestureListener<OverlayItem?>{
                    override fun onItemSingleTapUp(index: Int, item: OverlayItem?): Boolean {
                        return true
                    }

                    override fun onItemLongPress(index: Int, item: OverlayItem?): Boolean {
                        return false
                    }
                }, applicationContext
            )
        mOverlay.setFocusItemsOnTap(true)
        map.getOverlays().add(mOverlay)
        mapController.setCenter(GeoPoint(paisLatitud, paisLongitud))
    }

    public override fun onResume() {
        super.onResume()
        map.onResume()
    }

    public override fun onPause() {
        super.onPause()
        map.onPause()
    }
}