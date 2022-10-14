package kr.hs.dion_kotlin

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import kr.hs.dion_kotlin.databinding.ActivityPointLogBinding

class PointLogActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPointLogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_point_log)

        setRcv()
        settingListener()

        binding.PointTv.text = App.prefs.point.toString() + "P"



    }

    private fun settingListener() {
        binding.TradeLayout.setOnClickListener(this)
        binding.PointLogArrow.setOnClickListener(this)
        binding.backArrow.setOnClickListener(this)
    }

    private fun setRcv() {
        val pointLogAdapter = PointLogAdapter()
        binding.PointLogRcv.adapter =  pointLogAdapter
        binding.PointLogRcv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        pointLogAdapter.data = mutableListOf(
            PointLogData("회사이름1", 10),
            PointLogData("회사이름2", 10),
            PointLogData("회사이름3", 10),
            PointLogData("회사이름4", 10),
            PointLogData("회사이름5", 10),
            PointLogData("회사이름6", 10),
            PointLogData("회사이름7", 10),
            PointLogData("회사이름8", 10),
            PointLogData("회사이름9", 10),
            PointLogData("회사이름10", 10),
            PointLogData("회사이름11", 10),
            PointLogData("회사이름12", 10),
            PointLogData("회사이름13", 10),
            PointLogData("회사이름14", 10),
            PointLogData("회사이름15", 10),
            PointLogData("회사이름16", 10),
            PointLogData("회사이름17", 10),
            PointLogData("회사이름18", 10),
            PointLogData("회사이름19", 10),
            PointLogData("회사이름20", 10),
        )
        pointLogAdapter.notifyDataSetChanged()
    }

    override fun onClick(p0: View?) {
        when(p0) {
            binding.TradeLayout -> {
                createDialog()
            }
            binding.PointLogArrow -> {
                changeArrow()
            }
            binding.backArrow -> {
                finish()
            }
        }
    }

    private fun changeArrow() {
        if(binding.PointLogRcv.visibility == View.VISIBLE) {
            binding.PointLogRcv.visibility = View.GONE
            binding.PointLogArrow.setImageResource(R.drawable.forward_arrow)
        } else {
            binding.PointLogRcv.visibility = View.VISIBLE
            binding.PointLogArrow.setImageResource(R.drawable.under_arrow)
        }
    }

    private fun createDialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.point_trade_dialog, null)
        val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
        val mAlertDialog = mBuilder.show()
        mAlertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowManager = this.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val params: ViewGroup.LayoutParams? = mAlertDialog.window!!.attributes
        params!!.width = (size.x * 0.85).toInt()
        mAlertDialog?.window?.attributes = params as WindowManager.LayoutParams
        val PointEt = mDialogView.findViewById<EditText>(R.id.Point_Et)
        val MoneyEt = mDialogView.findViewById<EditText>(R.id.Money_Et)
        val TradeBtn = mDialogView.findViewById<AppCompatButton>(R.id.Trade_Btn)
        TradeBtn.setOnClickListener { //교환하기 버튼을 눌렀을 때
            val temptext = PointEt.text.toString() //교환할 포인트의 값을 임시저장
            if(temptext.isNullOrBlank()){
                Toast.makeText(this, "값을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }else if(temptext.length > 9) {
                Toast.makeText(this, "숫자의 길이가 너무 깁니다.", Toast.LENGTH_SHORT).show()
            } else if (temptext.toInt() >= 5000) { //최소 교환단위 5000이상일 때
                if(App.prefs.point < temptext.toInt()){
                    Toast.makeText(this, "가진 포인트보다 높은 숫자를 적을 수 없습니다.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "성공적으로 포인트 교환이 신청되었습니다.", Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss() //Dialog 닫기
                }
            } else {
                Toast.makeText(this, "최소 교환 금액은 5000P입니다.", Toast.LENGTH_SHORT).show()
            }
        }
        PointEt.addTextChangedListener { //포인트 적는 란의 텍스트가 변경되는 걸 감지하는 이벤트리스너
            MoneyEt.text = PointEt.text //위의 포인트텍스트가 변경되면 아래 교환되는 돈의 텍스트도 변경되야 함(P : 원 = 1 : 1)
        }
        val CancleButton = mDialogView.findViewById<AppCompatButton>(R.id.Cancle_Btn)
        CancleButton.setOnClickListener { //취소하기 버튼을 눌렀을 때..
            mAlertDialog.dismiss() //Dialog 닫기
        }
    }

}



