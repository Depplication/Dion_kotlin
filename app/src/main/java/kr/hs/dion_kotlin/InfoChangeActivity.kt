package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Pattern

class InfoChangeActivity : AppCompatActivity() {
    private val OriginPhoneNumET : EditText by lazy {
        findViewById(R.id.OriginPhoneNum_ET)
    }
    private val NewPhoneNumET : EditText by lazy {
        findViewById(R.id.NewPhoneNum_ET)
    }
    private val AccountEt : EditText by lazy {
        findViewById(R.id.Account_ET)
    }
    private val AddressEt : EditText by lazy {
        findViewById(R.id.Address_ET)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_change)

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)
        val SaveBtn : Button = findViewById(R.id.Save_Btn)

        BackArrow.setOnClickListener {
            finish()
        }

        SaveBtn.setOnClickListener {
            if(checkChange()){
                SaveInfo()
                Toast.makeText(this, "성공적으로 정보를 저장했습니다.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private fun SaveInfo(){
        //정보 저장
    }

    private fun checkChange(): Boolean {
        if (!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$", OriginPhoneNumET.text.toString())){
            OriginPhoneNumET.requestFocus()
            Toast.makeText(this, "기존의 전화번호와 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            return false
        } else if(!Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$", NewPhoneNumET.text.toString())){
            NewPhoneNumET.requestFocus()
            Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
            return false
        } else if(AddressEt.text.isNullOrBlank()){
            AddressEt.requestFocus()
            Toast.makeText(this, "주소란이 비었습니다.", Toast.LENGTH_SHORT).show()
            return false
        } else if(AccountEt.text.isNullOrBlank()) {
            AccountEt.requestFocus()
            Toast.makeText(this, "계좌번호란이 비었습니다.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}