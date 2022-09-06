package kr.hs.dion_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dion_kotlin.databinding.GoodsListItemBinding

class GoodsAdapter : RecyclerView.Adapter<GoodsAdapter.GoodsVH>() {

    var data = mutableListOf<GoodsData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GoodsVH {
        val binding = GoodsListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GoodsVH(binding)
    }

    override fun onBindViewHolder(holder: GoodsVH, position: Int) {
        val goods = data[position]
        holder.setData(goods)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class GoodsVH(val binding: GoodsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(goods: GoodsData) {
            binding.ItemImg.setImageURI(goods.Img)
            binding.TitleText.text = goods.Name
            binding.InfoText.text = goods.Info
        }
    }
}
