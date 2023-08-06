package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class LoginActivity : AppCompatActivity() {
    var email: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<TextView>(R.id.nanez_join).setOnClickListener {
            startActivity(Intent(this@LoginActivity, JoinActivity::class.java)
            )
        }

        findViewById<TextView>(R.id.login_btn).setOnClickListener {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java)
            )
        }

    }
}