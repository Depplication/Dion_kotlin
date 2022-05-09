package kr.hs.dion_kotlin

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView

//TODO 메인 타이틀 폰트..?
//TODO 서버 완성되면 서버 연동
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    val MainTitle : TextView by lazy {
        findViewById(R.id.Main_Title)
    }
    val JoinText : TextView by lazy {
        findViewById(R.id.Join_Text)
    }
    val LoginBtn : Button by lazy{
        findViewById(R.id.Login_Btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        settingText() //텍스트 셋팅 함수
        settingListener()

    }

    private fun settingText(){
        JoinText.paintFlags = Paint.UNDERLINE_TEXT_FLAG //회원가입 텍스트에 밑줄 추가

        val ssb = SpannableStringBuilder("Dion") //Dion이라는 글자를 담은 SpannableStringBuilder 선언
        ssb.apply {
            setSpan(ForegroundColorSpan(getColor(R.color.MainColor)), 0,1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE) //MainColor색 적용(0~1번째 텍스트 == D)
        }
        MainTitle.text = ssb //메인 타이틀 텍스트에 ssb 담기
    }

    private fun settingListener(){
        LoginBtn.setOnClickListener(this) //로그인 버튼 온클릭리스너 셋팅
        JoinText.setOnClickListener(this) //회원가입 텍스트 온클릭리스너 셋팅
    }

    override fun onClick(view: View?) { //온클릭리스너 셋팅해둔 것들이 클릭됐을때...
        when(view){ //클릭된것이 view 담김 그래서 when문으로 판별
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
}