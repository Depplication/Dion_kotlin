package kr.hs.dion_kotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dion_kotlin.databinding.PromotionListItemBinding

class PromotionAdapter : RecyclerView.Adapter<PromotionAdapter.PromotionVH>() {

    var data = mutableListOf<PromotionData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PromotionVH {
        val binding = PromotionListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PromotionVH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: PromotionVH, position: Int) {
        val promotion = data[position]
        holder.setData(promotion)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class PromotionVH(val binding: PromotionListItemBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun setData(promotion: PromotionData) {
            binding.ItemImg.setImageURI(promotion.Img)
            binding.TitleText.text = promotion.Name
            binding.InfoText.text = promotion.Info
            itemView.setOnClickListener {
                val intent = Intent(context, DetailPromotionActivity::class.java).apply {
                    putExtra("data", data)
                }.run { context.startActivity(this) }
            }
        }
    }
}
