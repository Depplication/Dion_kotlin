package kr.hs.dion_kotlin

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import kr.hs.dion_kotlin.databinding.ActivityMyInfoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*

class MyInfoActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMyInfoBinding
    var locationManager: LocationManager? = null
    var currentLocation: String = ""
    var latitude: Double? = null
    var longitude: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_info)
        getCurrentLoc()
        settingListener()

        binding.ProfileImg.clipToOutline = true

    }

    private fun settingData(){
        binding.ProfileName.text = App.prefs.name
        binding.MyPointPoint.text = App.prefs.point.toString() + "P"
    }

    private fun settingListener() {
        binding.ProfileImg.setOnClickListener(this)
        binding.SettingBtn.setOnClickListener(this)
        binding.PointLogDetailBtn.setOnClickListener(this)
        binding.LeaveBtn.setOnClickListener(this)
        binding.LogoutLayout.setOnClickListener(this)
    }

    private fun getCurrentLoc() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        var userLocation: Location? = getLatLng()
        if (userLocation != null) {
            latitude = userLocation.latitude
            longitude = userLocation.longitude

            var mGeocoder = Geocoder(applicationContext, Locale.KOREAN)
            var mResultList: List<Address>? = null
            try {
                mResultList = mGeocoder.getFromLocation(
                    latitude!!, longitude!!, 1
                )
            } catch (E: IOException) {
                E.printStackTrace()
            }
            if (mResultList != null) {
                currentLocation = mResultList[0].getAddressLine(0)
                var texts = currentLocation.split(" ")
                binding.MyLocationText.text = texts[1] + " " + texts[2] + " " + texts[3]
            }
        }
    }

    private fun getLatLng(): Location? {
        val locateionProvider = LocationManager.GPS_PROVIDER
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

        }
        return locationManager?.getLastKnownLocation(locateionProvider)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.SettingBtn -> {
                val intent = Intent(this, UpdateProfileActivity::class.java)
                startActivity(intent)
                //TODO 정보 변경
            }
            binding.PointLogDetailBtn -> {
                val intent = Intent(this, PointLogActivity::class.java)
                startActivity(intent)
                //TODO 포인트 내역
            }
            binding.LeaveBtn -> {
                SetDialog()
                //TODO 탈퇴하기
            }
            binding.LogoutLayout -> {
                val intent = Intent(this, LoginActivity::class.java)
                finishAffinity()
                startActivity(intent)
                App.prefs.token = null
                App.prefs.id = -1
            }
        }
    }

    private fun SetDialog() {
        AlertDialog.Builder(this)
            .setTitle("알림")
            .setMessage("정말 탈퇴하시겠습니까?")
            .setNeutralButton("예") { _, i ->
                LeavePost()
            }
            .setPositiveButton("아니요") { _, i ->
            }
            .setCancelable(false)
            .create()
            .show()
    }

    private fun LeavePost() {
        RetrofitBuilder.api.DeleteUser(App.prefs.id).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body().toString() // GsonConverter를 사용해 데이터매핑
                    Log.d("testasd", data)
                    Toast(1)
                    Intent()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("testasd", "실패$t")
                Toast(0)
            }

        })
    }

    private fun Intent(){
        val intent = Intent(this, LoginActivity::class.java)
        finishAffinity()
        startActivity(intent)
    }

    private fun Toast(code:Int){
        if(code==1){
            Toast.makeText(this, "회원 탈퇴에 성공했습니다..", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "알 수 없는 이유로 회원 탈퇴에 실패했습니다.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        settingData()
    }
}