package com.example.kakaologin

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.kakaologin.databinding.ActivityMapBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.database
import com.kakao.sdk.common.util.Utility

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object{
        private const val TAG = "MapActivity"
    }
    private lateinit var binding: ActivityMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private var trackingPersonID : String = ""
    private val markerMap = hashMapOf<String, Marker>()

    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ){
        permissions ->
        when{
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                //fine location 권한이 허용된 경우
                getCurrentLocation()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // coarse location 권한이 허용된 경우
                getCurrentLocation()
            }
            else -> {  //권한이 없으면
                // 설정으로 보내기 
            }
        }
    }
    private val locationCallback = object : LocationCallback(){
        override fun onLocationResult(p0: LocationResult) {
            super.onLocationResult(p0)
            for(location in p0.locations){
                val uid = Firebase.auth.currentUser?.uid.orEmpty()

                val locationMap = mutableMapOf<String, Any>()
                locationMap["latitude"] = location.latitude
                locationMap["longtitude"] = location.longitude

                Firebase.database.reference.child("Person").child(uid).updateChildren(locationMap)

                //파이어베이스 내 위치 업로드
                //지도에 마커 움직이기
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        requestLocationPermission()
        setupFirebaseDatabase()
    }

    override fun onResume() {
        super.onResume()

        getCurrentLocation()
    }

    override fun onPause() {
        super.onPause()

        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // 현재 위치 함수 //
    private fun getCurrentLocation(){
        val locationRequest = LocationRequest
            .Builder(Priority.PRIORITY_HIGH_ACCURACY, 5*1000)
            .build()

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestLocationPermission()  // 권한이 없으면
            return
        }
        //권한이 있는 상태
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())

        //마지막 위치 업데이트
        fusedLocationClient.lastLocation.addOnSuccessListener {
            googleMap.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(it.latitude, it.longitude), 16.0f)
            )
        }
    }

    // 위치 권한 함수 //
    private fun requestLocationPermission(){
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
    }

    // 파이어베이스에 데이터 넣는 함수 //
    private fun setupFirebaseDatabase() {
        Firebase.database.reference.child("Person")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val person = snapshot.getValue(Person::class.java) ?: return
                    val uid = person.uid ?: return

                    if(markerMap[uid] == null)
                        markerMap[uid] = makeNewMarker(person, uid) ?: return
                }
                // 변경 되면 (사용자 위치 변동) //
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                    val person = snapshot.getValue(Person::class.java) ?: return
                    val uid = person.uid ?: return

                    if(markerMap[uid] == null)
                        markerMap[uid] = makeNewMarker(person, uid) ?: return
                    else
                        markerMap[uid]?.position = LatLng(person.latitude ?: 0.0, person.longitude ?: 0.0)

                    if(uid == trackingPersonID){
                        googleMap.animateCamera(
                            CameraUpdateFactory.newCameraPosition(
                                CameraPosition.Builder()
                                    .target(LatLng(person.latitude ?: 0.0, person.longitude ?: 0.0))
                                    .zoom(16.0f)
                                    .build()
                            )
                        )
                    }
                }

                override fun onChildRemoved(snapshot: DataSnapshot) { }

                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) { }

                override fun onCancelled(error: DatabaseError) { }

            })
    }

    // 마커 생성 함수 //
    private fun makeNewMarker(person: Person, uid: String) : Marker? {
        val marker = googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(person.latitude ?: 0.0 , person.longitude ?: 0.0))
                .title(person.name.orEmpty())
        ) ?: return null

        // 지도 마커에 프로필 사진 로드 시키기 //
        Glide.with(this).asBitmap()
            .load(person.profilePhoto)
            .transform(RoundedCorners(60))
            .override(200)
            .listener(object :RequestListener<Bitmap>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.let {
                        runOnUiThread {
                            marker.setIcon(
                                BitmapDescriptorFactory.fromBitmap(
                                    resource
                                )
                            )
                        }
                    }
                    return true
                }
            }).submit()
        return marker
    }

    // 지도가 준비 함수 //
    override fun onMapReady(p0: GoogleMap) {
        googleMap = p0

        //요청된 위치 정보
        googleMap.setMaxZoomPreference(20.0f)
        googleMap.setMinZoomPreference(10.0f)

        googleMap.setOnMarkerClickListener (this)  //마커를 누르면
        googleMap.setOnMapClickListener {  //지도를 누르면
            trackingPersonID = ""
        }
    }

    // 마커 클릭 함수 //
    override fun onMarkerClick(p0: Marker): Boolean {

        trackingPersonID = p0.tag as? String ?: ""

        val bottomSheetBehavior = BottomSheetBehavior.from(binding.emojiBottomSheetLayout)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        return false
    }

}