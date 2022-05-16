package kr.hs.dion_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PointLogAdapter(private val context: Context) : RecyclerView.Adapter<PointLogAdapter.ViewHolder>() {

    var datas = mutableListOf<PointLog_Data>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.rcv_pointlog_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val Img: ImageView = itemView.findViewById(R.id.PointLog_Img)
        private val Company_Tv: TextView = itemView.findViewById(R.id.PointLog_Company_Tv)
        private val CompanyPoint_Tv: TextView = itemView.findViewById(R.id.PointLog_CompanyPoint_Tv)

        fun bind(item: PointLog_Data) {
            Glide.with(itemView).load(item.Img).into(Img)
            Company_Tv.text = item.CompanyTitle
            CompanyPoint_Tv.text = item.Point

        }
    }


}