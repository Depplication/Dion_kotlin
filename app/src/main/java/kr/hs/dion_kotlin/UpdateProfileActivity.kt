package kr.hs.dion_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import kr.hs.dion_kotlin.R
import kr.hs.dion_kotlin.databinding.ActivityUpdateProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UpdateProfileActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUpdateProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_profile)

        settingListener()
        settingText()

        binding.AddressET.isEnabled = false
        binding.BankET.isEnabled = false


    }

    private fun settingText() {
        binding.tvAccount.bringToFront()
        binding.tvAddress.bringToFront()
        binding.tvBank.bringToFront()
        binding.tvCall.bringToFront()
        binding.tvName.bringToFront()
    }

    private fun settingListener() {
        binding.backArrow.setOnClickListener(this) //로그인 버튼 온클릭리스너 셋팅
        binding.AddressBtn.setOnClickListener(this) //회원가입 텍스트 온클릭리스너 셋팅
        binding.BankBtn.setOnClickListener(this)
        binding.UpdateBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.backArrow -> {
                finish()
            }
            binding.AddressBtn -> {
                //TODO 주소 입력
            }
            binding.BankBtn -> {
                //TODO 은행 선택
            }
            binding.UpdateBtn -> {
                //TODO 유효값 검사 후
                //TODO 정보 업데이트
                var modifyData = ModifyData(binding.AccountET.text.toString(), "주소몰루", binding.NameET.text.toString(), "01012341234")
                ModifyPost(modifyData)
                finish()
            }
        }
    }

    private fun ModifyPost(modifyData: ModifyData) {
        RetrofitBuilder.api.UserModifyPost(App.prefs.id, modifyData).enqueue(object :
            Callback<UserDataX> {
            override fun onResponse(
                call: Call<UserDataX>,
                response: Response<UserDataX>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body().toString() // GsonConverter를 사용해 데이터매핑
                    Log.d("testasd", data)
                    App.prefs.name = binding.NameET.text.toString()
                    finish()
                }
            }

            override fun onFailure(call: Call<UserDataX>, t: Throwable) {
                Log.d("testasd", "실패$t")
                Toast()
            }

        })

    }

    private fun Toast() {
        Toast.makeText(this, "서버 에러", Toast.LENGTH_SHORT).show()
    }

}