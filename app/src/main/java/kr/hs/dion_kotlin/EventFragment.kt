package kr.hs.dion_kotlin

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
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

        val MenuIcon: ImageView = view.findViewById(R.id.Menu_Icon)
        val MenuLayout: LinearLayout = view.findViewById(R.id.Menu_Layout)
        Rcv_Promotion = view.findViewById(R.id.Rcv_Promotion)


        setRcv()

        MenuIcon.setOnClickListener {
            if (MenuLayout.visibility == View.VISIBLE) {
                MenuLayout.visibility = View.GONE
            } else {
                MenuLayout.visibility = View.VISIBLE
            }
        }

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