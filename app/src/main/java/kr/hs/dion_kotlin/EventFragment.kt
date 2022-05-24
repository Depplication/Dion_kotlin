package kr.hs.dion_kotlin

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EventFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    lateinit var Rcv_Promotion: RecyclerView

    lateinit var FoodIcon: ImageView
    lateinit var BeautyIcon: ImageView
    lateinit var PlantIcon: ImageView
    lateinit var HealthIcon: ImageView
    lateinit var InteriorIcon: ImageView

    override fun onAttach(context: Context) { //프레그먼트에서는 this를 사용못함 -> context라는 걸 써야하는데 메인액티비티 위에 이 프레그먼트가 올라 와 있으므로 메인액티비티의 context를 불러옴(context = this)
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_event, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FoodIcon = view.findViewById(R.id.Food_Icon)
        BeautyIcon = view.findViewById(R.id.Beauty_Icon)
        PlantIcon = view.findViewById(R.id.Plant_Icon)
        HealthIcon = view.findViewById(R.id.Health_Icon)
        InteriorIcon = view.findViewById(R.id.Interior_Icon)

        Rcv_Promotion = view.findViewById(R.id.Rcv_Promotion)

        val MenuIcon: ImageView = view.findViewById(R.id.Menu_Icon)
        val MenuLayout: LinearLayout = view.findViewById(R.id.Menu_Layout)


        MenuIcon.setOnClickListener { //오른쪽 상단 메뉴 버튼 누르면
            if (MenuLayout.visibility == View.VISIBLE) { // 카테고리 화면이 보이는 상태라면
                MenuLayout.visibility = View.GONE //카테고리 화면이 안보이게 변경
            } else { //아니라면 (= 카테고리 화면이 안보이는 상태)
                MenuLayout.visibility = View.VISIBLE  //카테고리 화면이 보이게 변경
            }
        }


        FoodIcon.setOnClickListener { //카테고리중의 음식을 누르면
            changeIcon(FoodIcon)
        }
        BeautyIcon.setOnClickListener { //카테고리중의 뷰티를 누르면
            changeIcon(BeautyIcon)
        }
        PlantIcon.setOnClickListener { //카테고리중의 식물을 누르면
            changeIcon(PlantIcon)
        }
        HealthIcon.setOnClickListener { //카테고리중의 건강을 누르면
            changeIcon(HealthIcon)
        }
        InteriorIcon.setOnClickListener { //카테고리중의 인테리어를 누르면
            changeIcon(InteriorIcon)
        }

        setRcv() //리사이클러뷰 셋팅

    }


    private fun changeIcon(img:ImageView){ //이미지뷰를 인자로 받아옴
        if(img.tag.equals("0")){ //위에서 넘어온 인자(이미지뷰)의 tag가 0이라면(클릭되지않은 상태라면)
            clearTag() //모든 태그를 0으로 셋팅하는 함수(다른 녀석이 클릭되어있으면 그 녀석의 클릭 상태가 풀려야하기 때문이다)
            clearIcon() //모든 이미지를 안눌린 상태의 이미지로 셋팅하는 함수(위와 동일한 이유)
            img.tag = "1" //내가 클릭한 이미지의 태그를 1로 셋팅
            when(img){ //만약 내가 클릭한 이미지가...
                FoodIcon -> img.setImageResource(R.drawable.food_push) //푸드라면 푸드이미지를 눌린 이미지로 변경
                BeautyIcon -> img.setImageResource(R.drawable.beauty_push) //뷰티라면 뷰티이미지를 눌린 이미지로 변경
                PlantIcon -> img.setImageResource(R.drawable.plant_push) //식물이라면 식물이미지를 눌린 이미지로 변경
                HealthIcon -> img.setImageResource(R.drawable.health_push) //건강이라면 건강이미지를 눌린 이미지로 변경
                InteriorIcon -> img.setImageResource(R.drawable.interior_push) //인테리어라면 인테리어이미지를 눌린 이미지로 변경
            }
        } else if(img.tag.equals("1")){ //만약 태그가 0이 아니고 1이라면(클릭된 상태라면)
            img.tag = "0" //내가 클릭한 이미지의 태그를 클릭되지않은 상태인 태그 0으로 변경
            when(img){ //만약 내가 클릭한 이미지가...
                FoodIcon -> img.setImageResource(R.drawable.food) //푸드라면 기본 푸드 이미지로 변경
                BeautyIcon -> img.setImageResource(R.drawable.beauty) //뷰티라면 기본 뷰티 이미지로 변경
                PlantIcon -> img.setImageResource(R.drawable.plant) //식물이라면 기본 식물 이미지로 변경
                HealthIcon -> img.setImageResource(R.drawable.health) //건강이라면 기본 건강 이미지로 변경
                InteriorIcon -> img.setImageResource(R.drawable.interior) //인테리어라면 기본 인테리어 이미지로 변경
            }
        }
    }

    private fun clearIcon(){ //모두 다 기본이미지로 변경시키는 함수
        FoodIcon.setImageResource(R.drawable.food)
        BeautyIcon.setImageResource(R.drawable.beauty)
        PlantIcon.setImageResource(R.drawable.plant)
        HealthIcon.setImageResource(R.drawable.health)
        InteriorIcon.setImageResource(R.drawable.interior)
    }

    private fun clearTag(){ //모두 다 태그를 0으로 변경시키는 함수
        FoodIcon.tag = "0"
        BeautyIcon.tag = "0"
        PlantIcon.tag = "0"
        HealthIcon.tag = "0"
        InteriorIcon.tag = "0"
    }

    private fun setRcv() {
        val promotionAdapter = PromotionAdapter(mainActivity) //어댑터 생성
        Rcv_Promotion.adapter = promotionAdapter //광고리사이클러뷰의 어댑터를 위에서 만든 어댑터로 연결
        Rcv_Promotion.layoutManager = LinearLayoutManager(mainActivity) //레이아웃 매니저( 데이터나 아이템들이 리사이클러뷰 내부에서 배치되는 형태를 관리하는 역할 )
        promotionAdapter.datas = mutableListOf( //어댑터의 데이터를 새로운 mutableListof를 만들어서 넣음
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
        )
        promotionAdapter.notifyDataSetChanged() //변경사항 적용

        Rcv_Promotion.addItemDecoration(VerticalSpaceItemDecoration(5)) //리사이클러뷰 아이템 사이의 위 아래 공백을 5만큼 적용
    }

    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) : //리사이클러뷰의 아이템 사이의 공백을 주기 위해서 긁어온 코드 ㅎ
        RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect, view: View, parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.top = verticalSpaceHeight
            outRect.bottom = verticalSpaceHeight
        }
    }

}