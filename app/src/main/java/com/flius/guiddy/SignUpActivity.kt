package com.flius.guiddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.flius.guiddy.databinding.ActivitySignUpBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding
    lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference
    private lateinit var bt_SignUpConfirm1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = Firebase.auth
        mDbRef = Firebase.database.reference

        bt_SignUpConfirm1 = findViewById(R.id.bt_SignUpConfirm1)
        bt_SignUpConfirm1.setOnClickListener {
            val name = binding.nameEdit.text.toString().trim()
            val email = binding.emailEdit.text.toString().trim()
            val password = binding.passwordEdit.text.toString().trim()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent: Intent = Intent(this@SignUpActivity, SignUpActivity2::class.java)
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    val intent: Intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    startActivity(intent)
                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uId:String){
        mDbRef.child("user").child(uId).setValue(User(name, email, uId))
    }
}