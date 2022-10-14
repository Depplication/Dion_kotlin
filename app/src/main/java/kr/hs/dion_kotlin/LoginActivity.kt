package kr.hs.dion_kotlin

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Paint
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
import kr.hs.dion_kotlin.*
import kr.hs.dion_kotlin.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityLoginBinding

    //private var size: Int = 0

    private val MY_PERMISSION_ACCESS_ALL = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        SetDialog()

        settingText() //텍스트 셋팅 함수
        settingListener()

//        binding.tvPw.viewTreeObserver.addOnGlobalLayoutListener(
//            object : ViewTreeObserver.OnGlobalLayoutListener {
//                override fun onGlobalLayout() {
//
//                    binding.tvPw.addOnLayoutChangeListener { v, left, top, right, bottom, oldLeft, oldTop, oldRight, oldBottom
//                        ->
//                        size = right - left + 500
//                        Log.d("SizeSizeSize1", size.toString())
//
//                    }
//
//
//                    binding.tvPw.layoutParams.width = (size)
//                    Log.d("SizeSizeSize2", size.toString())
//
//
//                    // 1회성을 위해 Listener 제거
//                    binding.tvPw.viewTreeObserver.removeOnGlobalLayoutListener(this)
//
//                }
//            })


    }


    private fun settingText() {
        binding.JoinText.paintFlags = Paint.UNDERLINE_TEXT_FLAG //회원가입 텍스트에 밑줄 추가
        binding.tvId.bringToFront()
        binding.tvPw.bringToFront()
    }

    private fun settingListener() {
        binding.LoginBtn.setOnClickListener(this) //로그인 버튼 온클릭리스너 셋팅
        binding.JoinText.setOnClickListener(this) //회원가입 텍스트 온클릭리스너 셋팅
    }

    override fun onClick(view: View?) { //온클릭리스너 셋팅해둔 것들이 클릭됐을때...
        when (view) { //클릭된것이 view 담김 그래서 when문으로 판별
            binding.LoginBtn -> { //로그인 버튼이 클릭되면
                val loginData = LoginData(binding.PwET.text.toString(), binding.IdET.text.toString())
                LoginPost(loginData)
            }
            binding.JoinText -> { //회원가입 텍스트가 클릭되면
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            }

        }
    }

    private fun LoginPost(loginData: LoginData) {
        RetrofitBuilder.api.LoginPost(loginData).enqueue(object :
            Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body().toString() // GsonConverter를 사용해 데이터매핑
                    Log.d("testasd", data)
                    App.prefs.token = response.body()!!.tokenData.token
                    App.prefs.id = response.body()!!.userData.id
                    App.prefs.point = response.body()!!.userData.point
                    App.prefs.name = response.body()!!.userData.name
                    Intent()
                } else {
                    Toast(response.code())
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.d("testasd", "실패$t")
                Toast(-1)
            }

        })
    }

    private fun Intent() {
        val intent = Intent(this, MainActivity::class.java)
        finish()
        startActivity(intent)
    }
    private fun Toast(code: Int) {
        if(code == 404){
            Toast.makeText(this, "아이디 또는 비밀번호가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "서버 에러", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_PERMISSION_ACCESS_ALL) {
            if (grantResults.isNotEmpty()) {
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        finish()
                    }
                }
            }
        }
    }

    private fun SetDialog() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                    .setTitle("알림")
                    .setMessage("위치 권한이 거부되었습니다. 사용을 원하시면 설정에서 해당 권한을 직접 허용하셔야 합니다.")
                    .setNeutralButton("설정", object : DialogInterface.OnClickListener {
                        override fun onClick(dialogInterface: DialogInterface, i: Int) {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                            intent.setData(Uri.parse("package:" + getPackageName()))
                            startActivity(intent)
                        }
                    })
                    .setPositiveButton("나가기", object : DialogInterface.OnClickListener {
                        override fun onClick(dialogInterface: DialogInterface, i: Int) {
                            finish()
                        }
                    })
                    .setCancelable(false)
                    .create()
                    .show()
            } else {
                ActivityCompat.requestPermissions(this, arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION), MY_PERMISSION_ACCESS_ALL)
            }
        }
    }

}
