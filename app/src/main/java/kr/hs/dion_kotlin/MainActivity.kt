package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    val Bn_main : BottomNavigationView by lazy{
        findViewById(R.id.Bn_Main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Bn_main.setOnItemSelectedListener { //바텀네비게이션의 아이템을 클릭했을 때
            when (it.itemId) { // 그 아이템의 id가
                R.id.Action_Event -> { //이벤트라면
                    replaceFragment(EventFragment()) //이벤트 프레그먼트로 replace
                }
                R.id.Action_Myinfo -> { //내정보라면
                    replaceFragment(MyinfoFragment()) //내정보 프레그먼트로 replace
                }
            }
            true
        }
        Bn_main.selectedItemId = R.id.Action_Event //이벤트 창이 맨처음에 뜸
    }

    private fun replaceFragment(fragment: Fragment) { //replace 함수
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment) //인자로 넘어온 프레그먼트로 replace 시키겠다
        fragmentTransaction.commit() //replace 시켰으니 commit으로 변경사항 적용
    }
}