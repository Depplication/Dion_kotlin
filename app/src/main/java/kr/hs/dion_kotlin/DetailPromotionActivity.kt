package kr.hs.dion_kotlin

import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import kr.hs.dion_kotlin.databinding.ActivityDetailPromotionBinding

class DetailPromotionActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityDetailPromotionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_promotion)

        setVp()
        setRcv()
        setText()
        settingListener()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Map_Layout, MapsFragment())
            .commit()


    }

    private fun setText() {
        val text = "대구광역시 달성군\n9:00~20:00ㄴ\n네모가게\n010-0000-0000ㄴ\ntesttest@test.comㄴ\n테스트\n000-0000-0000ㄴ"
        val sp = SpannableString(text)
        for(i in 1..text.length){
            if(text.substring(i-1, i).equals("ㄴ")){
                sp.setSpan(ForegroundColorSpan(Color.parseColor("#FFFFFF")), i-1, i, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        binding.OfficeInfoTextRight.text = sp
    }

    private fun setRcv() {
        val goodsAdapter = GoodsAdapter()
        binding.RcvGoods.adapter = goodsAdapter
        binding.RcvGoods.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        goodsAdapter.data = mutableListOf(
            GoodsData(Uri.EMPTY, "상품이름", "상품 설명 1234567890123456789012345678901234567890"),
            GoodsData(Uri.EMPTY, "상품이름", "상품 설명 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ"),
            GoodsData(Uri.EMPTY, "상품이름", "상품 설명 ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ")
        )
        goodsAdapter.notifyDataSetChanged()
    }

    private fun settingListener(){ //리스너 셋팅
        binding.backArrow.setOnClickListener(this)
        binding.AccumulateBtn.setOnClickListener(this)
    }

    private fun setVp() {
        val viewPagerAdapter = ViewPagerAdapter()
        binding.VPOffice.adapter = viewPagerAdapter // 어댑터 생성
        binding.VPOffice.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        viewPagerAdapter.item = listOf(
            BannerData(Uri.parse("https://www.charlezz.com/wordpress/wp-content/uploads/2021/02/www.charlezz.com-uri-url-uri-url.png")),
            BannerData(Uri.EMPTY),
            BannerData(Uri.EMPTY),
            BannerData(Uri.EMPTY),
            BannerData(Uri.EMPTY),
            BannerData(Uri.EMPTY)
        )
        viewPagerAdapter.notifyDataSetChanged()
        binding.VPIndicator.attachTo(binding.VPOffice)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.backArrow -> {
                finish()
            }
            binding.AccumulateBtn -> {
                finish()
                //TODO 적립
            }
        }
    }
}