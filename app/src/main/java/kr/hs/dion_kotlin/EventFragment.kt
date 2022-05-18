package kr.hs.dion_kotlin

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_event, container, false)

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


        MenuIcon.setOnClickListener {
            if (MenuLayout.visibility == View.VISIBLE) {
                MenuLayout.visibility = View.GONE
            } else {
                MenuLayout.visibility = View.VISIBLE
            }
        }


        FoodIcon.setOnClickListener {
            changeIcon(FoodIcon)
        }
        BeautyIcon.setOnClickListener {
            changeIcon(BeautyIcon)
        }
        PlantIcon.setOnClickListener {
            changeIcon(PlantIcon)
        }
        HealthIcon.setOnClickListener {
            changeIcon(HealthIcon)
        }
        InteriorIcon.setOnClickListener {
            changeIcon(InteriorIcon)
        }

        setRcv()

    }


    private fun changeIcon(img:ImageView){
        Log.d("Tag", "asd")
        if(img.tag.equals("0")){
            clearTag()
            clearIcon()
            img.tag = "1"
            when(img){
                FoodIcon -> img.setImageResource(R.drawable.food_push)
                BeautyIcon -> img.setImageResource(R.drawable.beauty_push)
                PlantIcon -> img.setImageResource(R.drawable.plant_push)
                HealthIcon -> img.setImageResource(R.drawable.health_push)
                InteriorIcon -> img.setImageResource(R.drawable.interior_push)
            }
        } else if(img.tag.equals("1")){
            img.tag = "0"
            when(img){
                FoodIcon -> img.setImageResource(R.drawable.food)
                BeautyIcon -> img.setImageResource(R.drawable.beauty)
                PlantIcon -> img.setImageResource(R.drawable.plant)
                HealthIcon -> img.setImageResource(R.drawable.health)
                InteriorIcon -> img.setImageResource(R.drawable.interior)
            }
        }
    }

    private fun clearIcon(){
        FoodIcon.setImageResource(R.drawable.food)
        BeautyIcon.setImageResource(R.drawable.beauty)
        PlantIcon.setImageResource(R.drawable.plant)
        HealthIcon.setImageResource(R.drawable.health)
        InteriorIcon.setImageResource(R.drawable.interior)
    }

    private fun clearTag(){
        FoodIcon.tag = "0"
        BeautyIcon.tag = "0"
        PlantIcon.tag = "0"
        HealthIcon.tag = "0"
        InteriorIcon.tag = "0"
    }

    private fun setRcv() {
        val promotionAdapter = PromotionAdapter(mainActivity)
        Rcv_Promotion.adapter = promotionAdapter
        Rcv_Promotion.layoutManager = LinearLayoutManager(mainActivity)
        promotionAdapter.datas = mutableListOf(
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
            Promotion_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "Introduction"),
        )
        promotionAdapter.notifyDataSetChanged()

        Rcv_Promotion.addItemDecoration(VerticalSpaceItemDecoration(5))
    }

    inner class VerticalSpaceItemDecoration(private val verticalSpaceHeight: Int) :
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