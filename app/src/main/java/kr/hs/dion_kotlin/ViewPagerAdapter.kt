package kr.hs.dion_kotlin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dion_kotlin.ViewPagerAdapter.PagerViewHolder
import kr.hs.dion_kotlin.databinding.BannerListItemBinding

class ViewPagerAdapter : RecyclerView.Adapter<PagerViewHolder>() {

    var item = listOf<BannerData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val binding = BannerListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)

        return PagerViewHolder(binding)
    }

    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.onBind(item[position])
    }

    class PagerViewHolder(val binding: BannerListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BannerData) {
            binding.itemBanner.setImageURI(data.banner)
        }
    }

}