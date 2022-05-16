package kr.hs.dion_kotlin

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PointLogActivity : AppCompatActivity() {
    private val Rcv_PointLog: RecyclerView by lazy{
        findViewById(R.id.Rcv_PointLog)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_point_log)

        val BackArrow : ImageView = findViewById(R.id.Back_Arrow)

        BackArrow.setOnClickListener {
            finish()
        }

        setRcv()
    }

    private fun setRcv() {
        val pointlogAdapter = PointLogAdapter(this)
        Rcv_PointLog.adapter = pointlogAdapter
        Rcv_PointLog.layoutManager = LinearLayoutManager(this)
        pointlogAdapter.datas = mutableListOf(
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "100" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "50" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "30" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "70" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "100" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "90" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
            PointLog_Data("https://image.shutterstock.com/image-vector/simple-square-logo-illustration-icon-600w-721374193.jpg", "Title", "10" + "P"),
        )
        pointlogAdapter.notifyDataSetChanged()

        Rcv_PointLog.addItemDecoration(VerticalSpaceItemDecoration(5))
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