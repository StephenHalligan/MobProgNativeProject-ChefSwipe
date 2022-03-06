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
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class RegisterActivity : AppCompatActivity() {
    private var mRegister: Button? = null
    private var mName: EditText? = null
    private var mEmail: EditText? = null
    private var mPassword: EditText? = null
    private var mAuth: FirebaseAuth? = null
    private var firebaseAuthStateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mAuth = FirebaseAuth.getInstance()
        firebaseAuthStateListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
            //Check if user is logged in
            if (user != null) {
                val intent = Intent(this@RegisterActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        mRegister = findViewById<View>(R.id.Register) as Button
        mName = findViewById<View>(R.id.Name) as EditText
        mEmail = findViewById<View>(R.id.Email) as EditText
        mPassword = findViewById<View>(R.id.Password) as EditText

        //Register user
        mRegister!!.setOnClickListener {
            val name = mName!!.text.toString()
            val email = mEmail!!.text.toString()
            val password = mPassword!!.text.toString()
            mAuth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this@RegisterActivity) { task -> //Check is registration fails
                    if (!task.isSuccessful) {
                        Toast.makeText(this@RegisterActivity, "Sign-up Error", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        val userId = Objects.requireNonNull(mAuth!!.currentUser)
                            ?.uid
                        val currentUserDb =
                            userId?.let { it1 ->
                                FirebaseDatabase.getInstance().reference.child("Users").child(it1)
                                    .child("Name")
                            }
                        if (currentUserDb != null) {
                            currentUserDb.setValue(name)
                        }
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