package org.techtown.nanez.login

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.techtown.nanez.main.view.MainActivity
import org.techtown.nanez.R
import org.techtown.nanez.join.JoinActivity

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