package kr.hs.dion_kotlin

data class AdvertisingData(
    val approve: Boolean,
    val category: String,
    val endDate: String,
    val id: Int,
    val name: String,
    val ownerData: OwnerData,
    val productList: List<Product>,
    val startDate: String,
    val storeExplain: String,
    val timeEnd: String,
    val title: String
)