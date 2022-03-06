package com.example.chefswipe

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    private var mLogin: Button? = null
    private var mEmail: EditText? = null
    private var mPassword: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private var firebaseAuthStateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()
        firebaseAuthStateListener = AuthStateListener {
            FirebaseAuth.getInstance().currentUser
        }
        mLogin = findViewById<View>(R.id.Login) as Button
        mEmail = findViewById<View>(R.id.Email) as EditText
        mPassword = findViewById<View>(R.id.Password) as EditText

        //Login user
        mLogin!!.setOnClickListener {
            val email = mEmail!!.text.toString()
            val password = mPassword!!.text.toString()
            mAuth!!.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@LoginActivity) { task -> //Check is registration fails
                    if (!task.isSuccessful) {
                        Toast.makeText(this@LoginActivity, "Sign-in Error", Toast.LENGTH_SHORT)
                            .show()
                    }
                    else {
                        val intent = Intent(this@LoginActivity, MainActivity::class.java)
                        startActivity(intent)
                    }
                }
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(firebaseAuthStateListener!!)
    }

    override fun onStop() {
        super.onStop()
        mAuth!!.removeAuthStateListener(firebaseAuthStateListener!!)
    }
}