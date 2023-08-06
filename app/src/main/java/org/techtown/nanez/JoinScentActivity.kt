package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class JoinScentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scent_join)

        findViewById<TextView>(R.id.next_text).setOnClickListener {
            startActivity(
                Intent(this@JoinScentActivity, JoinEventActivity::class.java)
            )
        }

    }
}