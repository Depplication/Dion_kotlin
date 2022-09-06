package kr.hs.dion_kotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ExpandableListView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import kr.hs.dion_kotlin.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private var listdata: MutableList<PromotionData> = mutableListOf()
    private val promotionAdapter = PromotionAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        setRcv()
        initNavigationMenu()

        binding.navHeaderMain.findViewById<TextView>(R.id.header_name).text = App.prefs.name


    }

    private fun GetAdvertising(category: Category) {
        RetrofitBuilder.api.GetAdvertisingList(category).enqueue(object :
            Callback<AdvertisingListData> {
            override fun onResponse(
                call: Call<AdvertisingListData>,
                response: Response<AdvertisingListData>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body().toString() // GsonConverter를 사용해 데이터매핑
                    Log.d("testasd", data)
                    listdata.clear()
                    for (i in 0 until response.body()!!.list.size) {
                        listdata.add(
                            PromotionData(
                                Uri.EMPTY,
                                response.body()!!.list[i].title,
                                response.body()!!.list[i].storeExplain
                            )
                        )
                    }
                    promotionAdapter.data = listdata
                    promotionAdapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<AdvertisingListData>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }

    private fun setRcv() {
        binding.RcvPromotion.adapter = promotionAdapter
        binding.RcvPromotion.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        GetAdvertising(Category.ALL)
    }

    private fun initNavigationMenu() {
        binding.MainNavigationView.setNavigationItemSelectedListener(this)

        binding.MenuBtn.setOnClickListener {
            binding.MainDrawerLayout.openDrawer(GravityCompat.END)
        }

        binding.NestSv.setOnClickListener {
            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
        }

        initCategoryList()

    }

    private fun initCategoryList() {
        val parentList = mutableListOf(
            CategoryData("카테고리", R.drawable.forward_arrow),
            CategoryData("내정보", null),
            CategoryData("고객센터", R.drawable.forward_arrow)
        )
        val childList = mutableListOf(
            mutableListOf(
                CategoryData("전체", R.drawable.all),
                CategoryData("음식", R.drawable.food),
                CategoryData("뷰티", R.drawable.beuaty),
                CategoryData("생활", R.drawable.plant),
                CategoryData("운동", R.drawable.health),
                CategoryData("인테리어", R.drawable.interior),
            ),
            mutableListOf(),
            mutableListOf(
                CategoryData("1 : 1 문의", R.drawable.headset),
                CategoryData("자주묻는질문", R.drawable.faq)
            )
        )

        val categoryList =
            binding.navCategoryListView.findViewById<ExpandableListView>(R.id.nav_category_list_view)
        val categoryListAdapter = CategoryListAdapter(this, parentList, childList)
        categoryList.setAdapter(categoryListAdapter)
        categoryList.setOnGroupClickListener { expandableListView, view, i, l ->
            when (i) {
                1 -> {
                    binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                    val intent = Intent(this, MyInfoActivity::class.java)
                    startActivity(intent)
                }
            }
            false
        }
        categoryList.setOnChildClickListener { expandableListView, view, i, i2, l ->
            when (i) {
                0 -> {
                    when (i2) {
                        0 -> {
                            //TODO 전체
                            binding.CategoryIcon.setImageResource(R.drawable.all)
                            binding.CategoryText.text = "전체"
                            GetAdvertising(Category.ALL)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                        1 -> {
                            //TODO 음식
                            binding.CategoryIcon.setImageResource(R.drawable.food)
                            binding.CategoryText.text = "음식"
                            GetAdvertising(Category.FOOD)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                        2 -> {
                            //TODO 뷰티
                            binding.CategoryIcon.setImageResource(R.drawable.beuaty)
                            binding.CategoryText.text = "뷰티"
                            GetAdvertising(Category.BEAUTY)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                        3 -> {
                            //TODO 생활
                            binding.CategoryIcon.setImageResource(R.drawable.plant)
                            binding.CategoryText.text = "생활"
                            GetAdvertising(Category.PLANT)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                        4 -> {
                            //TODO 운동
                            binding.CategoryIcon.setImageResource(R.drawable.health)
                            binding.CategoryText.text = "운동"
                            GetAdvertising(Category.EXERCISE)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                        5 -> {
                            //TODO 인테리어
                            binding.CategoryIcon.setImageResource(R.drawable.interior)
                            binding.CategoryText.text = "인테리어"
                            GetAdvertising(Category.INTERIOR)
                            binding.MainDrawerLayout.closeDrawer(GravityCompat.END)
                        }
                    }
                }
                2 -> {
                    when (i2) {
                        0 -> {
                            //TODO 1:1
                        }
                        1 -> {
                            //TODO 자주묻는질문
                        }
                    }
                }
            }
            false
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Category -> {
                Toast.makeText(this, "카테고리", Toast.LENGTH_SHORT).show()
            }
            R.id.MyInfo -> {
                Toast.makeText(this, "내정보", Toast.LENGTH_SHORT).show()
            }
            R.id.Service_Center -> {
                Toast.makeText(this, "고객센터", Toast.LENGTH_SHORT).show()
            }
        }
        return false
    }


    override fun onResume() {
        super.onResume()
        binding.navHeaderMain.findViewById<TextView>(R.id.header_name).text = App.prefs.name
    }
}