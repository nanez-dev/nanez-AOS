package org.techtown.nanez.join

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import org.techtown.nanez.R

class JoinEmailNumActivity : AppCompatActivity()  {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emailnum_join)

        val email = intent.getStringExtra("EMAIL").toString()
        val email_input = findViewById<EditText>(R.id.email_input)
        email_input.setText(email)

        val sharedPreferences =
            getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString("email", email)
        val commit = editor.commit()

        findViewById<TextView>(R.id.next_text).setOnClickListener {
            startActivity(
                Intent(this@JoinEmailNumActivity, JoinPasswordActivity::class.java)
            )
        }

    }
}