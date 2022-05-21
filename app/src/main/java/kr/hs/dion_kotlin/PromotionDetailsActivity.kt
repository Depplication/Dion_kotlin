package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PromotionDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_details)

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)

        BackArrow.setOnClickListener {
            finish()
        }
    }
}