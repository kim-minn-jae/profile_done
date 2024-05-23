package com.flius.guiddy

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ProfileModifyActivity : AppCompatActivity() {
    private lateinit var profileImageView: ImageView
    private val PICK_IMAGE_REQUEST = 1 //갤러리에서 이미지를 선택하기 위한 요청 코드.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_modify)

        profileImageView = findViewById(R.id.profile_image)
        profileImageView.setOnClickListener {
            openGallery()
        }//프로필 이미지를 클릭하면 갤러리 열기

        //저장 버튼 클릭시 초기 리스트 화면으로 전환
        val saveButton: Button = findViewById(R.id.profile_save_button)
        saveButton.setOnClickListener {
            val intent = Intent(this, ProfileDetailActivity::class.java)
            startActivity(intent)
        }
    }

    //갤러리를 열어 이미지를 선택
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST) //갤러리를 열고 이미지를 선택한 결과 반환
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 이미지가 정상적으로 선택되었는지 확인
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri? = data.data //선택한 이미지의 URI을 가져옴
            profileImageView.setImageURI(imageUri) //ImageView에 선택한 이미지를 설정
        }
    }
}