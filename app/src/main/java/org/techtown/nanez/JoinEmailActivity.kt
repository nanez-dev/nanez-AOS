package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class JoinEmailActivity: AppCompatActivity() {

    var email: String = ""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_join)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.nanez.co.kr/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setPrettyPrinting().create()))
            .build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        var hi="hi"

        findViewById<EditText>(R.id.email_input).doAfterTextChanged { email = it.toString() }

        findViewById<TextView>(R.id.send_text).setOnClickListener{
            if (email == "") {
                Toast.makeText(this@JoinEmailActivity, "이메일을 입력해주세요", Toast.LENGTH_SHORT).show()
            } else{
                val params = HashMap<String, Any>()
                params["email"] = email

                val call = retrofitService.nanezEmail(params)
                call.enqueue(object : Callback<String> {
                    override fun onResponse(call: Call<String>, response: Response<String>) {
                        if (response.isSuccessful) {
                            Log.d("태그", "이메일 인증번호 전송 API 연결 성공")
                            hi=response.body().toString()
                            if(hi=="true"){
                                hi="이메일로 회원가입 인증번호가 전송되었습니다."
                            } else {
                                hi="사용 가능한 이메일을 입력해주세요."
                            }
                            Toast.makeText(this@JoinEmailActivity,hi,Toast.LENGTH_SHORT)
                        } else {
                            Log.d("태그", "이메일 인증번호 전송 응답 실패")
                        }
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {
                        Log.d("태그", "요청 실패")
                        Log.d("태그", "${t.message}")
                    }
                })
            }
        }

        findViewById<TextView>(R.id.next_text).setOnClickListener {
            val intent = Intent(this@JoinEmailActivity,JoinEmailNumActivity::class.java)
            intent.putExtra("EMAIL", email)
            startActivity(intent)
        }

    }
}