package com.flius.guiddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_detail)

        val saveButton: Button = findViewById(R.id.profile_save_button)
        saveButton.setOnClickListener {
            val intent = Intent(this, ProfileModifyActivity::class.java)
            startActivity(intent)
        }
    }
}