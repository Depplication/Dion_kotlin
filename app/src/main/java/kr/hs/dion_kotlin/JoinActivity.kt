package kr.hs.dion_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {
    val IdEt : EditText by lazy{
        findViewById(R.id.Id_ET)
    }
    val PwEt : EditText by lazy {
        findViewById(R.id.Pw_ET)
    }
    val RPwEt : EditText by lazy {
        findViewById(R.id.RPw_ET)
    }
    val PwToggle : ImageView by lazy {
        findViewById(R.id.Pw_Toggle)
    }
    val PhoneNum : EditText by lazy {
        findViewById(R.id.PhoneNum_ET)
    }
    val AddressEt : EditText by lazy {
        findViewById(R.id.Address_ET)
    }
    val AccountEt : EditText by lazy {
        findViewById(R.id.Account_ET)
    }
    val JoinBtn : Button by lazy{
        findViewById(R.id.Join_Btn)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)



        PwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        RPwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        settingListener()
    }

    private fun settingListener(){ //리스너 셋팅
        PwToggle.setOnClickListener(this)
        JoinBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) { //온클릭리스너 셋팅해둔 것들이 클릭됐을때...
        when(view){ //클릭된것이 view 담김 그래서 when문으로 판별
            PwToggle -> {
                if (PwToggle.tag.equals("0")) { //비밀번호 안 보이고 있던 상황
                    PwToggle.tag = "1"
                    PwToggle.setImageResource(R.drawable.ic_baseline_visibility_24)//켜져있는 아이콘으로 바꾼다.
                    //비밀번호를 보이게 한다.
                    PwEt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                    RPwEt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                } else { //비밀번호 보이고 있던 상황
                    PwToggle.tag = "0"
                    PwToggle.setImageResource(R.drawable.ic_baseline_visibility_off_24)//꺼져있는 아이콘으로 바꾼다.
                    //비밀번호를 가린다.
                    PwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    RPwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

                }
                PwEt.setSelection(PwEt.text.length) //맨 마지막 글자로 커서 보내기
            }
            JoinBtn -> {
                CheckJoin() //회원가입 정규식
            }
        }
    }

    private fun CheckJoin() { //이거는 정규식이라서 설명할 게 없음
        if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{5,11}.\$", IdEt.text.toString())) {
            IdEt.requestFocus()
            Toast.makeText(this, "아이디는 6~12자 문자와 숫자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT).show()
        } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{7,14}.\$", PwEt.text.toString())) {
            PwEt.requestFocus()
            Toast.makeText(this, "비밀번호는 8~15자 문자와 숫자, 특수문자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT).show()
        }else if(PwEt.text.toString() != RPwEt.text.toString()){
            RPwEt.requestFocus()
            Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지않습니다.", Toast.LENGTH_SHORT).show()
        } else if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$", PhoneNum.text.toString())) {
            PhoneNum.requestFocus()
            Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
        } else if(AddressEt.text.isNullOrBlank()){
            AddressEt.requestFocus()
            Toast.makeText(this, "주소란이 비었습니다.", Toast.LENGTH_SHORT).show()
        } else if(AccountEt.text.isNullOrBlank()){
            AccountEt.requestFocus()
            Toast.makeText(this, "계좌번호란이 비었습니다.", Toast.LENGTH_SHORT).show()
        } else if(false){
            //TODO 인증번호 확인
        } else {
            //서버에 포스트...
            val intent = Intent(this, LoginActivity::class.java)
            Toast.makeText(this,"회원가입 성공!", Toast.LENGTH_SHORT).show()
            finish()
            startActivity(intent)
        }
    }
}