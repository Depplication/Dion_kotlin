package kr.hs.dion_kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.databinding.DataBindingUtil
import kr.hs.dion_kotlin.databinding.ActivityJoinBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class JoinActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityJoinBinding
    private var Page: Int = 1
    private var data: UserData = UserData("", "", "", "", "", "")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_join)
        settingText()
        settingListener()
    }

    private fun settingText() {
        binding.tvIdCall.bringToFront()
        binding.tvPwAddress.bringToFront()
        binding.tvCPwBank.bringToFront()
        binding.tvNameAccount.bringToFront()
    }

    private fun settingListener() { //리스너 셋팅
        binding.backArrow.setOnClickListener(this)
        binding.NextJoinBtn.setOnClickListener(this)
        binding.AddressBtn.setOnClickListener(this)
        binding.BankBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) { //온클릭리스너 셋팅해둔 것들이 클릭됐을때...
        when (view) { //클릭된것이 view 담김 그래서 when문으로 판별
            binding.backArrow -> {
                if (Page == 2) {
                    ChangeUi(true)
                } else {
                    finish()
                }
            }
            binding.NextJoinBtn -> {
                CheckJoin()
            }
            binding.AddressBtn -> {
                //TODO 주소 입력
            }
            binding.BankBtn -> {
                //TODO 은행 선택
            }
        }
    }

    private fun CheckJoin() {
        if (Page == 1) {
            if (!Pattern.matches(
                    "^(?=.*[A-Za-z])(?=.*[0-9]).{5,11}.\$",
                    binding.IdCallET.text.toString()
                )
            ) {
                binding.IdCallET.requestFocus()
                Toast.makeText(this, "아이디는 6~12자 문자와 숫자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT).show()
            } else if (!Pattern.matches(
                    "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[\$@\$!%*#?&]).{7,14}.\$",
                    binding.PwAddressET.text.toString()
                )
            ) {
                binding.PwAddressET.requestFocus()
                Toast.makeText(this, "비밀번호는 8~15자 문자와 숫자, 특수문자가 필수로 포함되어야합니다.", Toast.LENGTH_SHORT)
                    .show()
            } else if (binding.PwAddressET.text.toString() != binding.CPwBankET.text.toString()) {
                binding.CPwBankET.requestFocus()
                Toast.makeText(this, "비밀번호와 비밀번호 확인이 일치하지않습니다.", Toast.LENGTH_SHORT).show()
            } else if (binding.NameAccountET.text.isNullOrBlank()) {
                binding.NameAccountET.requestFocus()
                Toast.makeText(this, "이름란이 비어있습니다.", Toast.LENGTH_SHORT).show()
            } else {
                ChangeUi(false)
            }
        } else if (Page == 2) {
            if (!Pattern.matches(
                    "^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}\$",
                    binding.IdCallET.text.toString()
                )
            ) {
                binding.IdCallET.requestFocus()
                Toast.makeText(this, "올바른 핸드폰 번호가 아닙니다.", Toast.LENGTH_SHORT).show()
//            } else if(binding.PwAddressET.text.isNullOrBlank()){
//                binding.PwAddressET.requestFocus()
//                Toast.makeText(this, "주소를 입력하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
//            } else if(binding.CPwBankET.text.isNullOrBlank()){
//                binding.CPwBankET.requestFocus()
//                Toast.makeText(this, "은행을 선택하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
            } else if (binding.NameAccountET.text.isNullOrBlank()) {
                binding.NameAccountET.requestFocus()
                Toast.makeText(this, "계좌번호를 입력하지 않으셨습니다.", Toast.LENGTH_SHORT).show()
            } else {
                ChangeUi(false)
            }
        }
    }

    private fun ChangeUi(isback: Boolean) {
        if (isback) {
            binding.tvIdCall.text = "ID"
            binding.IdCallET.text = null
            binding.IdCallET.hint = "아이디를 입력해주세요."
            binding.tvPwAddress.text = "Pw"
            binding.PwAddressET.text = null
            binding.PwAddressET.hint = "비밀번호를 입력해주세요"
            binding.PwAddressET.isEnabled = true
            binding.AddressBtn.visibility = View.GONE
            binding.tvCPwBank.text = "CPW"
            binding.CPwBankET.text = null
            binding.CPwBankET.hint = "비밀번호를 재입력해주세요."
            binding.CPwBankET.isEnabled = true
            binding.BankBtn.visibility = View.GONE
            binding.tvNameAccount.text = "NAME"
            binding.NameAccountET.text = null
            binding.NameAccountET.hint = "이름을 입력해주세요."
            binding.NextJoinBtn.text = "NEXT"
            Page = 1
        } else {
            if (Page == 1) {
                data.userId = binding.IdCallET.text.toString()
                data.password = binding.PwAddressET.text.toString()
                data.name = binding.NameAccountET.text.toString()
                binding.tvIdCall.text = "CALL"
                binding.IdCallET.text = null
                binding.IdCallET.hint = "전화번호를 입력해주세요."
                binding.tvPwAddress.text = "ADD"
                binding.PwAddressET.text = null
                binding.PwAddressET.hint = "주소를 입력해주세요"
                binding.PwAddressET.isEnabled = false
                binding.AddressBtn.visibility = View.VISIBLE
                binding.tvCPwBank.text = "BANK"
                binding.CPwBankET.text = null
                binding.CPwBankET.hint = "은행을 선택해주세요"
                binding.CPwBankET.isEnabled = false
                binding.BankBtn.visibility = View.VISIBLE
                binding.tvNameAccount.text = "ACN"
                binding.NameAccountET.text = null
                binding.NameAccountET.hint = "계좌번호를 입력해주세요."
                binding.NextJoinBtn.text = "Sign Up"
                Page = 2
            } else if (Page == 2) {
                data.number = binding.IdCallET.text.toString()
                data.address = binding.PwAddressET.text.toString() + "테스트 테스트 히히호호"
                data.account = binding.NameAccountET.text.toString()
                JoinPost(data)

            }
        }
    }

    private fun JoinPost(userData: UserData) {
        RetrofitBuilder.api.JoinPost(userData).enqueue(object :
            Callback<Void> {
            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>,
            ) {
                Log.d("testasd", response.toString())
                if (response.isSuccessful) {
                    Log.d("testasd", response.body().toString())
                    var data = response.body().toString() // GsonConverter를 사용해 데이터매핑
                    Log.d("testasd", data)
                    intent()
                } else {
                    Toast(response.code())
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.d("testasd", "실패$t")
            }

        })
    }

    private fun Toast(code: Int) {
        if (code == 409) {
            Toast.makeText(this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "서버 에러", Toast.LENGTH_SHORT).show()
        }
    }

    private fun intent() {
        val intent = Intent(this, LoginActivity::class.java)
        finishAffinity()
        startActivity(intent)
    }


}