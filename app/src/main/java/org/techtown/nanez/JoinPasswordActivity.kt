package org.techtown.nanez

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JoinPasswordActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_join)

        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val email = sharedPreferences.getString("email", "기본값")
        var param1 = HashMap<String, Any>()
        param1["check"] = email.toString()
        Log.d("태그", param1["check"].toString())


        findViewById<TextView>(R.id.next_text).setOnClickListener {
            startActivity(
                Intent(this@JoinPasswordActivity, JoinGenderActivity::class.java)
            )
        }

    }
}