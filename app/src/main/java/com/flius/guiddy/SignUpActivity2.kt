package com.flius.guiddy

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.flius.guiddy.databinding.ActivitySignUp2Binding
import com.flius.guiddy.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity2 : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var bt_SignUpConfirm2: Button
    private lateinit var btAddPic: Button
    private lateinit var imageView: ImageView
    lateinit var binding: ActivitySignUp2Binding

    private val PICK_IMAGE_REQUEST = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference

        bt_SignUpConfirm2 = findViewById(R.id.bt_SignUpConfirm2)
        btAddPic = findViewById(R.id.bt_addpic)
        imageView = findViewById(R.id.imageView)

        bt_SignUpConfirm2.setOnClickListener {
            val nickname = binding.nicknameEdit.text.toString().trim()
            val country = binding.countryEdit.text.toString().trim()
            val sex = binding.sexEdit.text.toString().trim()
            val language = binding.languageEdit.text.toString().trim()

            inputdb(nickname, country, sex, language, mAuth.currentUser?.uid!!)
        }

        btAddPic.setOnClickListener {
            openGallery()
        }
    }

    private fun inputdb(nickname: String, country: String, sex: String, language: String, uId:String){

        mDbRef.child("profile").child(uId).setValue(Profile(nickname, country, sex, language))
        Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
        val intent: Intent = Intent(this@SignUpActivity2, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun addUserToDatabase(name: String, email: String, uId:String){
        mDbRef.child("user").child(uId).setValue(User(name, email, uId))
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null) {
            val imageUri: Uri? = data.data
            imageView.setImageURI(imageUri)
        }
    }
}