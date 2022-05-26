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
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlin.system.exitProcess

//TODO 메인 타이틀 폰트..?
//TODO 서버 완성되면 서버 연동
class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private val MY_PERMISSION_ACCESS_ALL = 100
    val MainTitle: TextView by lazy {
        findViewById(R.id.Main_Title)
    }
    val JoinText: TextView by lazy {
        findViewById(R.id.Join_Text)
    }
    val LoginBtn: Button by lazy {
        findViewById(R.id.Login_Btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        SetDialog()

        settingText() //텍스트 셋팅 함수
        settingListener()

    }

    private fun settingText() {
        JoinText.paintFlags = Paint.UNDERLINE_TEXT_FLAG //회원가입 텍스트에 밑줄 추가

        val ssb = SpannableStringBuilder("Dion") //Dion이라는 글자를 담은 SpannableStringBuilder 선언
        ssb.apply {
            setSpan(
                ForegroundColorSpan(getColor(R.color.MainColor)),
                0,
                1,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            ) //MainColor색 적용(0~1번째 텍스트 == D)
        }
        MainTitle.text = ssb //메인 타이틀 텍스트에 ssb 담기
    }

    private fun settingListener() {
        LoginBtn.setOnClickListener(this) //로그인 버튼 온클릭리스너 셋팅
        JoinText.setOnClickListener(this) //회원가입 텍스트 온클릭리스너 셋팅
    }

    override fun onClick(view: View?) { //온클릭리스너 셋팅해둔 것들이 클릭됐을때...
        when (view) { //클릭된것이 view 담김 그래서 when문으로 판별
            LoginBtn -> { //로그인 버튼이 클릭되면
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            JoinText -> { //회원가입 텍스트가 클릭되면
                val intent = Intent(this, JoinActivity::class.java)
                startActivity(intent)
            }

        }
    }

//    private fun PermissionGive() {
//        if (ActivityCompat.checkSelfPermission(
//                this,
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            var permissions = arrayOf(
//                android.Manifest.permission.ACCESS_FINE_LOCATION
//            )
//            ActivityCompat.requestPermissions(this, permissions, MY_PERMISSION_ACCESS_ALL)
//        }
//    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == MY_PERMISSION_ACCESS_ALL) {
            if (grantResults.size > 0) {
                for (grant in grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(
                            this,
                            "권한 허용을 거부하셨습니다. 앱을 재 설치하시거나 앱 권한을 따로 허용해주세요",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }
        }
    }

    private fun SetDialog() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
            ) {
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
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    MY_PERMISSION_ACCESS_ALL
                )
            }
        }
    }


}
