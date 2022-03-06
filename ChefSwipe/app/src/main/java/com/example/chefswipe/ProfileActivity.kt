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
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileActivity : AppCompatActivity() {
    private var mEditnameButton: Button? = null
    private var mEditname: EditText? = null
    private var mEditPassword: EditText? = null
    private var mEditpasswordButton: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var firebaseAuthStateListener: AuthStateListener? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        mAuth = FirebaseAuth.getInstance()
        firebaseAuthStateListener = AuthStateListener {
            val user = FirebaseAuth.getInstance().currentUser
        }
        mEditname = findViewById<View>(R.id.Editname) as EditText
        mEditnameButton = findViewById<View>(R.id.EditnameButton) as Button
        mEditPassword = findViewById<View>(R.id.Editpassword) as EditText
        mEditpasswordButton = findViewById<View>(R.id.EditpasswordButton) as Button

        //Change username
        mEditnameButton!!.setOnClickListener {
            val editedname = mEditname!!.text.toString()
            val user = FirebaseAuth.getInstance().currentUser
            if (user != null) {
                user.updateEmail(editedname).addOnCompleteListener { task ->
                    if (task.isSuccessful()) {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Email updated!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } else {
                        Toast.makeText(
                            this@ProfileActivity,
                            "Error, email not changed",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            }

            //Change username
            mEditpasswordButton!!.setOnClickListener {
                val editedpassword = mEditPassword!!.text.toString()
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    user.updatePassword(editedpassword).addOnCompleteListener { task ->
                        if (task.isSuccessful()) {
                            Toast.makeText(
                                this@ProfileActivity,
                                "Password updated!",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        } else {
                            Toast.makeText(
                                this@ProfileActivity,
                                "Error, password not changed",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        }
                    }
                }


            }
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.ic_mainpage -> intent =
                        Intent(this@ProfileActivity, MainActivity::class.java)
                    //R.id.ic_newspage -> intent =
                        //Intent(this@ProfileActivity, ProfileActivity::class.java)
                    R.id.ic_logoutpage -> intent =
                        Intent(this@ProfileActivity, LoginActivity::class.java)
                }
                startActivity(intent)
                true
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
