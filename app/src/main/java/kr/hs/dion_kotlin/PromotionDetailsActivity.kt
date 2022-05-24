package kr.hs.dion_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class PromotionDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_details)


        val FindLocationBtn : AppCompatButton = findViewById(R.id.Find_Location_Btn)

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)

        setVp()

        BackArrow.setOnClickListener {
            finish()
        }

        FindLocationBtn.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setVp() {
        val Indicator = findViewById<WormDotsIndicator>(R.id.Promotion_Indicator)
        val VPBanner = findViewById<ViewPager2>(R.id.Vp_Promotion)
        val viewPagerAdapter = ViewPagerAdapter(
            arrayListOf(
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_baseline_visibility_24
            )
        )
        VPBanner.adapter = viewPagerAdapter
        VPBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPagerAdapter.notifyDataSetChanged()

        Indicator.setViewPager2(VPBanner)
    }
}