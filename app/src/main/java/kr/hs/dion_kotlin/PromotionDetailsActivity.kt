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

        setVp() //뷰페이저2 셋팅..

        BackArrow.setOnClickListener {
            finish()
        }

        FindLocationBtn.setOnClickListener { //위치 보기 버튼을 눌렀을 때
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
    }


    private fun setVp() { //리사이클러뷰와 동일함...
        val Indicator = findViewById<WormDotsIndicator>(R.id.Promotion_Indicator) //인디케이터란? 뷰페이저 밑의 동그란 점 같은것들...
        val VPBanner = findViewById<ViewPager2>(R.id.Vp_Promotion)
        val viewPagerAdapter = ViewPagerAdapter( //뷰페이저 어댑터에
            arrayListOf( //새로운 arrayListOf에 값들을 넣어서 넘겨줌
                R.drawable.ic_launcher_foreground,
                R.drawable.ic_launcher_background,
                R.drawable.ic_baseline_visibility_24
            )
        )
        VPBanner.adapter = viewPagerAdapter //뷰페이저의 어댑터를 위에서 만든 어댑터로 연결
        VPBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL //뷰페이저의 방향을 가로로 설정
        viewPagerAdapter.notifyDataSetChanged() //변경사항 적용

        Indicator.setViewPager2(VPBanner) //뷰페이저와 인디케이터 연결
    }
}