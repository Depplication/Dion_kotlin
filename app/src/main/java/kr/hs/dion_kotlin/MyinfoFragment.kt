package kr.hs.dion_kotlin

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener

class MyinfoFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_myinfo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val Arrow_Changepoint : ImageView = view.findViewById(R.id.Arrow_Changepoint)

        Arrow_Changepoint.setOnClickListener {

            // Dialog만들기
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.pointchange_log, null)
            val mBuilder = AlertDialog.Builder(mainActivity)
                .setView(mDialogView)

            val  mAlertDialog = mBuilder.show()

            val PointEt = mDialogView.findViewById<EditText>(R.id.Point_Et)
            val MoneyEt = mDialogView.findViewById<EditText>(R.id.Money_Et)

            val ChangeBtn = mDialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.Change_Btn)

            ChangeBtn.setOnClickListener {
                val temptext = PointEt.text.toString()
                if(temptext.toInt() >= 5000) {
                    Toast.makeText(context, "성공적으로 포인트 교환이 신청되었습니다.", Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss()
                }else if(false){
                  //TODO 내가 가진 포인트보다 큰 숫자를 적었을 시에
                } else {
                    Toast.makeText(context, "최소 교환 금액은 5000P입니다.", Toast.LENGTH_SHORT).show()
                }
            }

            PointEt.addTextChangedListener {
                MoneyEt.text = PointEt.text
            }

            val CancleButton = mDialogView.findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.Cancle_Btn)
            CancleButton.setOnClickListener {
                mAlertDialog.dismiss()
            }


        }


    }
}