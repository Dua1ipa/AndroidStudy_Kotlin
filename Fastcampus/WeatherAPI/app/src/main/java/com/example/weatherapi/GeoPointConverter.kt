package com.example.weatherapi

import android.util.Log

class GeoPointConverter {

    private val NX = 149
    private val NY = 253

    private val RE = 6371.00877;  //지도반경
    private val GRID = 5.0;
    private val SLAT1 = 30.0;
    private val SLAT2 = 60.0;
    private val OLON = 126.0;
    private val OLAT = 38.0;
    private val XO = 210 / GRID;
    private val YO = 675 / GRID;

    private val DEGRAD = Math.PI / 180.0;
    private val RADDEG = 180 / Math.PI;

    private val re = RE / GRID
    private val slat1 = SLAT1 * DEGRAD
    private val slat2 = SLAT2 * DEGRAD
    private val olon = OLON * DEGRAD
    private val olat = OLAT * DEGRAD

    data class Point (val nx: Int, val ny: Int)

    fun convert(lat: Double, lon: Double): Point {
        var sn = Math.tan(Math.PI * 0.25 + slat2 * 0.5) / Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sn = Math.log(Math.cos(slat1) / Math.cos(slat2)) / Math.log(sn)
        var sf = Math.tan(Math.PI * 0.25 + slat1 * 0.5)
        sf = Math.pow(sf, sn) * Math.cos(slat1) / sn
        var ro = Math.tan(Math.PI * 0.25 + olat * 0.5)
        ro = re * sf / Math.pow(ro, sn)
        var ra = Math.tan(Math.PI * 0.25 + (lat) * DEGRAD * 0.5)

        var theta = lon * DEGRAD - olon
        if(theta > Math.PI) theta -= 2.0 * Math.PI
        if(theta < -Math.PI) theta += 2.0 * Math.PI
        theta *= sn
        val nx = ra * Math.sin(theta) + XO + 1.5
        val ny = ro - ra * Math.cos(theta) + YO + 1.5

        Log.d("GeoPointConverter", "lat: $lat, lon: $lon -> nx: $nx, ny: $ny")

        return Point(nx.toInt(), ny.toInt())
    }

}