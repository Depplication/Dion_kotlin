package kr.hs.dion_kotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
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


        val ArrowChangepoint: ImageView = view.findViewById(R.id.Arrow_Changepoint)

        val ArrowMypoint: ImageView = view.findViewById(R.id.Arrow_Mypoint)

        val InfoSettings: ImageView = view.findViewById(R.id.Info_Settings)

        val Servicecenter: ImageView = view.findViewById(R.id.Arrow_Servicecenter)

        val logout: androidx.constraintlayout.widget.ConstraintLayout = view.findViewById(R.id.logout_Layout)

        val secession: androidx.constraintlayout.widget.ConstraintLayout = view.findViewById(R.id.secession_Layout)

        Servicecenter.setOnClickListener {
            //val intent = Intent(context, 액티비티)
            //startActivity(intent)
            Toast.makeText(context, "서비스센터", Toast.LENGTH_SHORT)
        }

        logout.setOnClickListener {
            //val intent = Intent(context, 액티비티)
            //startActivity(intent)
            Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT)
        }

        secession.setOnClickListener {
            //val intent = Intent(context, 액티비티)
            //startActivity(intent)
            Toast.makeText(context, "탈퇴하기", Toast.LENGTH_SHORT)
        }

        InfoSettings.setOnClickListener {
            val intent = Intent(context, InfoChangeActivity::class.java)
            startActivity(intent)
        }

        ArrowMypoint.setOnClickListener {
            val intent = Intent(context, PointLogActivity::class.java)
            startActivity(intent)
        }

        ArrowChangepoint.setOnClickListener { //포인트 교환을 눌렀다면...

            // Dialog만들기
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.pointchange_log, null)
            val mBuilder = AlertDialog.Builder(mainActivity)
                .setView(mDialogView)

            val mAlertDialog = mBuilder.show()

            val PointEt = mDialogView.findViewById<EditText>(R.id.Point_Et)
            val MoneyEt = mDialogView.findViewById<EditText>(R.id.Money_Et)

            val ChangeBtn = mDialogView.findViewById<AppCompatButton>(R.id.Change_Btn)

            ChangeBtn.setOnClickListener { //교환하기 버튼을 눌렀을 때
                val temptext = PointEt.text.toString() //교환할 포인트의 값을 임시저장
                if (temptext.toInt() >= 5000) { //최소 교환단위 5000이상일 때
                    Toast.makeText(context, "성공적으로 포인트 교환이 신청되었습니다.", Toast.LENGTH_SHORT).show()
                    mAlertDialog.dismiss() //Dialog 닫기
                } else if (false) {
                    //TODO 내가 가진 포인트보다 큰 숫자를 적었을 시에
                } else {
                    Toast.makeText(context, "최소 교환 금액은 5000P입니다.", Toast.LENGTH_SHORT).show()
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
}