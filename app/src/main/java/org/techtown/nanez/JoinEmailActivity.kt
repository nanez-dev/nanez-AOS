package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class JoinEmailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_join)

        findViewById<TextView>(R.id.next_text).setOnClickListener {
            startActivity(
                Intent(this@JoinEmailActivity, JoinEmailNumActivity::class.java)
            )
        }

    }
}