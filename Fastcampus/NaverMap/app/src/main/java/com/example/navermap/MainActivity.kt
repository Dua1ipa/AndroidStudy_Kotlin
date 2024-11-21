package com.example.navermap

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.UiThread
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navermap.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.Tm128
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMainBinding
    private lateinit var naverMap: NaverMap
    private var isMapInit = false

    private var restaurantListAdapter = RestaurantListAdapter {
        collapseBottomSheet()
        moveCamera(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.bottomSheetLayout.searchResultRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = restaurantListAdapter
        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(query?.isNotEmpty()==true){
                    SearchRepository.getGoodRestaurant(query).enqueue(object : Callback<SearchResult>{
                        override fun onResponse(p0: Call<SearchResult?>, p1: Response<SearchResult?>) {  //응답 성공
                            Log.e("Main", "onResponse:${p1.body().toString()}")

                            val searchItemList = p1.body()?.items.orEmpty()
                            if(searchItemList.isEmpty()){  //검색 내용이 없으면
                                Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
                                return
                            } else if (isMapInit.not()){
                                Toast.makeText(this@MainActivity, "지도가 초기화 되지 않았습니다.", Toast.LENGTH_SHORT).show()
                                return
                            }

//                            val markers = Marker()
//                            markers.position = LatLng(searchItemList.first().mapy.toDouble(), searchItemList.first().mapx.toDouble())
//                            markers.map = naverMap

//                            val cameraUpdate = CameraUpdate
//                                .scrollTo(markers.position)
//                                .animate(CameraAnimation.Easing)
//                            naverMap.moveCamera(cameraUpdate)
                            val markers = searchItemList.map {
                                Marker(
                                    Tm128(
                                        it.mapx.toDouble(),
                                        it.mapy.toDouble()
                                    ).toLatLng()
                                ).apply {
                                    captionText = it.title
                                    map = naverMap
                                    width = Marker.SIZE_AUTO
                                    height = Marker.SIZE_AUTO
                                }
                            }

                            restaurantListAdapter.setData(searchItemList)
                            collapseBottomSheet()
                            moveCamera(markers.first().position)
//                            markers.position = LatLng(searchItemList.first().mapy.toDouble(), searchItemList.first().mapx.toDouble())
//                            markers.map = naverMap
//                            Log.e("Main", "좌표:${searchItemList.first().mapy.toDouble()}, ${searchItemList.first().mapx.toDouble()}")

                        }

                        override fun onFailure(p0: Call<SearchResult?>, p1: Throwable) {  //응답 실패
                            Log.e("Main", "onResponse:${p1.printStackTrace()}")
                        }

                    })
                    return false
                }else{ return true }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

//        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapView) as MapFragment?
//            ?:MapFragment.newInstance().also {
//                supportFragmentManager.beginTransaction().add(R.id.mapView, it).commit()
//            }

        binding.mapView.getMapAsync(this)

    }

    private fun moveCamera(position: LatLng) {
        if(isMapInit.not()) return
        // 찾은 곳으로 지도 이동 //
        val cameraUpdate = CameraUpdate
            .scrollTo(position)
            .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
    }

    private fun collapseBottomSheet(){
        // BottomSheetBehavior를 View에서 가져옴
        val bottomSheetBehavior = BottomSheetBehavior.from<View>(binding.bottomSheetLayout)
        // BottomSheet 상태를 COLLAPSED로 설정
        bottomSheetBehavior.state = STATE_COLLAPSED
    }

    override fun onStart() {
        super.onStart()
        binding.mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding.mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding.mapView.onLowMemory()
    }

    @UiThread
    override fun onMapReady(p0: NaverMap) {
        naverMap = p0
        isMapInit = true
    }

}