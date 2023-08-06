package org.techtown.nanez

import android.content.Intent
import android.os.Bundle
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class JoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join)

        val checkBoxAll = findViewById<CheckBox>(R.id.checkBoxAll)
        val checkBox1 = findViewById<CheckBox>(R.id.checkBox1)
        val checkBox2 = findViewById<CheckBox>(R.id.checkBox2)
        val checkBox3 = findViewById<CheckBox>(R.id.checkBox3)

        checkBoxAll.setOnCheckedChangeListener { _, isChecked ->
            checkBox1.isChecked = isChecked
            checkBox2.isChecked = isChecked
            checkBox3.isChecked = isChecked
        }

        findViewById<TextView>(R.id.agree_text).setOnClickListener {
            if (checkBox1.isChecked && checkBox1.isChecked) {
                startActivity(
                    Intent(this@JoinActivity, JoinEmailActivity::class.java)
                )
            }
            else {
                Toast.makeText(this, "서비스 이용 약관과 개인정보 수집/이용 약관에 동의해주세요.", Toast.LENGTH_SHORT).show()
            }

        }
    }
}