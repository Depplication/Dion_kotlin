package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView

class InfoChangeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_change)



        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)
        val SaveBtn : Button = findViewById(R.id.Save_Btn)

        BackArrow.setOnClickListener {
            finish()
        }

        SaveBtn.setOnClickListener {
            SaveInfo()
            finish()
        }
    }

    private fun SaveInfo(){
        //정보 저장
    }
}