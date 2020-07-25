package com.example.mommysfood.ui.home;

import java.util.ArrayList
import org.osmdroid.ResourceProxy
import org.osmdroid.api.IMapView
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.overlay.ItemizedOverlay
import org.osmdroid.views.overlay.OverlayItem

import android.graphics.Point
import android.graphics.drawable.Drawable

class MyItemizedOverlay(pDefaultMarker: Drawable, pResourceProxy: ResourceProxy) :
    ItemizedOverlay<OverlayItem>(pDefaultMarker, pResourceProxy) {
    private val overlayItemList = ArrayList<OverlayItem>()

    fun addItem(p: GeoPoint, title: String, snippet: String) {
        val newItem = OverlayItem(title, snippet, p)
        overlayItemList.add(newItem)
        populate()
    }

    override fun onSnapToItem(arg0: Int, arg1: Int, arg2: Point, arg3: IMapView): Boolean {
        // TODO Auto-generated method stub
        return false
    }

    override fun createItem(arg0: Int): OverlayItem {
        // TODO Auto-generated method stub
        return overlayItemList.get(arg0)
    }

    override fun size(): Int {
        // TODO Auto-generated method stub
        return overlayItemList.size
    }
}

