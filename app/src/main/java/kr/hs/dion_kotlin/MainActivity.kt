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



        Bn_main.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.Action_Event -> {
                    replaceFragment(EventFragment())
                }
                R.id.Action_Myinfo -> {
                    replaceFragment(MyinfoFragment())
                }
            }
            true
        }
        Bn_main.selectedItemId = R.id.Action_Event
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_main, fragment)
        fragmentTransaction.commit()
    }
}