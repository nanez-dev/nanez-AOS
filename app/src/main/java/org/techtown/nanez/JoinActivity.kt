package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        findViewById<TextView>(R.id.agree_text).setOnClickListener {
            startActivity(
                Intent(this@JoinActivity, JoinEmailActivity::class.java)
            )
        }
    }
}