package com.example.chefswipe

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import android.os.Bundle
import com.example.chefswipe.R
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.view.View
import android.widget.Button
import com.example.chefswipe.MainActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.chefswipe.LoginActivity
import com.example.chefswipe.RegisterActivity

class ChooseLoginOrRegisterActivity : AppCompatActivity() {
    private var mLogin: Button? = null
    private var mRegister: Button? = null
    private var mAuth: FirebaseAuth? = null
    private val firebaseAuthStateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val intent = Intent(this@ChooseLoginOrRegisterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
        setContentView(R.layout.activity_choose_login_or_register)
        mAuth = FirebaseAuth.getInstance()
        mLogin = findViewById<View>(R.id.Login) as Button
        mRegister = findViewById<View>(R.id.Register) as Button

        //Onclick for login button
        mLogin!!.setOnClickListener {
            val intent = Intent(this@ChooseLoginOrRegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        //Onclick for register button
        mRegister!!.setOnClickListener {
            val intent = Intent(this@ChooseLoginOrRegisterActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}