package kr.hs.dion_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dion_kotlin.databinding.PointlogListItemBinding

class PointLogAdapter : RecyclerView.Adapter<PointLogAdapter.PointLogVH>() {

    var data = mutableListOf<PointLogData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointLogVH {
        val binding = PointlogListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PointLogVH(binding)
    }

    override fun onBindViewHolder(holder: PointLogVH, position: Int) {
        val pointLog = data[position]
        holder.setData(pointLog)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class PointLogVH(val binding: PointlogListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(pointLog: PointLogData) {
            binding.OfficeName.text = pointLog.Office
            binding.Point.text = (pointLog.Point.toString() + "p")
        }
    }
}
