package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

class PromotionDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promotion_details)



        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)

        setVp()

        BackArrow.setOnClickListener {
            finish()
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