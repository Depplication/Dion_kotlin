package kr.hs.dion_kotlin

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PromotionAdapter(private val context: Context) : RecyclerView.Adapter<PromotionAdapter.ViewHolder>() {

    var datas = mutableListOf<Promotion_Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_promotion_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
//        holder.itemView.findViewById<RecyclerView>(R.id.Rcv_Promotion).addItemDecoration(VerticalSpaceItemDecoration(20))
    }



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val Img: ImageView = itemView.findViewById(R.id.Promotion_Img)
        private val Title: TextView = itemView.findViewById(R.id.Promotion_Title)
        private val Introduction: TextView = itemView.findViewById(R.id.Promotion_Text)

        fun bind(item: Promotion_Data) {
            Glide.with(itemView).load(item.Img).into(Img)
            Title.text = item.Title
            Introduction.text = item.Introduction

        }
    }


}