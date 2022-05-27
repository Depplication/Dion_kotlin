package kr.hs.dion_kotlin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.WindowManager
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.gms.maps.model.LatLng
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import java.io.IOException
import java.util.*
import kotlin.collections.ArrayList

class PromotionDetailsActivity : AppCompatActivity() {
    var locationManager: LocationManager? = null
    private val REQUEST_CODE_LOCATION: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_details)

        val MapFrag : FrameLayout = findViewById(R.id.Map_Frag)

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)

        val SL : ScrollView = findViewById(R.id.ScrollLayout)

        setVp() //뷰페이저2 셋팅..

        BackArrow.setOnClickListener {
            finish()
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.Map_Frag, MapsFragment())
            .commit()
    }


    private fun setVp() { //리사이클러뷰와 동일함...
        val Indicator = findViewById<WormDotsIndicator>(R.id.Promotion_Indicator) //인디케이터란? 뷰페이저 밑의 동그란 점 같은것들...
        val VPBanner = findViewById<ViewPager2>(R.id.Vp_Promotion)
        val viewPagerAdapter = ViewPagerAdapter( //뷰페이저 어댑터에
            arrayListOf( //새로운 arrayListOf에 값들을 넣어서 넘겨줌
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_baseline_visibility_24
            )
        )
        VPBanner.adapter = viewPagerAdapter //뷰페이저의 어댑터를 위에서 만든 어댑터로 연결
        VPBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL //뷰페이저의 방향을 가로로 설정
        viewPagerAdapter.notifyDataSetChanged() //변경사항 적용

        Indicator.setViewPager2(VPBanner) //뷰페이저와 인디케이터 연결
    }

    @SuppressLint("MissingPermission")
    fun getCurrentLoc(): LatLng {
        val locationProvider: String = LocationManager.GPS_PROVIDER

        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val lastKnownLocation: Location? = locationManager.getLastKnownLocation(locationProvider)

        if(lastKnownLocation?.latitude == null){
            Toast.makeText(this, "위치를 찾을 수 없습니다.\n보이는 현재 위치는 기본 위치(시드니)입니다.", Toast.LENGTH_SHORT).show()
            return LatLng(-34.0, 151.0)
        }
        return LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
    }
}