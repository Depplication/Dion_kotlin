package kr.hs.dion_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {
    val sv : ScrollView by lazy {
        findViewById(R.id.scroll)
    }
    var CheckScroll : Boolean = false
    val tv_rule : TextView by lazy{
        findViewById(R.id.tv_rule)
    }
    val tv_check : TextView by lazy {
        findViewById(R.id.tv_check)
    }
    val RuleCheck : CheckBox by lazy {
        findViewById(R.id.check)
    }
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

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)
        tv_rule.movementMethod = ScrollingMovementMethod() // 스크롤 가능하게 해주는 부분
        tv_rule.setOnTouchListener { _, _ ->
            sv.requestDisallowInterceptTouchEvent(true)//부모 scroll 권한 빼는 부분)
            return@setOnTouchListener false
        }

        tv_rule.setOnScrollChangeListener { _, _, _, _, _ ->
            if (!tv_rule.canScrollVertically(1)) {
                CheckScroll = true
            }
        }

        BackArrow.setOnClickListener {
            finish()
        }

        PwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        RPwEt.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

        settingListener()
    }

    private fun settingListener(){ //리스너 셋팅
        RuleCheck.setOnClickListener(this)
        tv_check.setOnClickListener(this)
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
            RuleCheck -> {
                checkEnabled(RuleCheck)
            }
            tv_check -> {
                checkEnabled(tv_check)
            }
        }
    }

    private fun checkEnabled(v: View){
        if (CheckScroll){
            Log.d("TAG", "TOUCH")
            if(v != RuleCheck){
                RuleCheck.isChecked = !RuleCheck.isChecked
            }
        }
        else {
            RuleCheck.isChecked = false
            Toast.makeText(this, "이용약관 스크롤 후 시도해주세요.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun CheckJoin() { //이거는 정규식이라서 설명할 게 없음
        if(!RuleCheck.isChecked){
            sv.fullScroll(ScrollView.FOCUS_UP)
            Handler(Looper.getMainLooper()).postDelayed({
                IdEt.clearFocus()
            }, 300)
            Toast.makeText(this, "이용약관에 동의 하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
        } else if (!Pattern.matches("^(?=.*[A-Za-z])(?=.*[0-9]).{5,11}.\$", IdEt.text.toString())) {
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