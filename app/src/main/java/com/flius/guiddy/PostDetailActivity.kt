package com.flius.guiddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PostDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_detail)

        val name = intent.getStringExtra(EXTRA_NAME)
        val details = intent.getStringExtra(EXTRA_DETAILS)

        val nameTextView: TextView = findViewById(R.id.tv_profilename)
        val detailsTextView: TextView = findViewById(R.id.tv_profiledetail5)

        nameTextView.text = name
        detailsTextView.text = details

        val payButton: Button = findViewById(R.id.bt_pay1)
        payButton.text = "Pay"
        payButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        val reviewButton: Button = findViewById(R.id.bt_review1)
        reviewButton.text = "Review"
        reviewButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            //게시물올린 사람에 따라서 이동하는 리뷰페이지가 달라져야함
        }

    }

    companion object {
        const val EXTRA_NAME = "com.flius.guidy.EXTRA_NAME"
        const val EXTRA_DETAILS = "com.flius.guidy.EXTRA_DETAILS"
    }
}