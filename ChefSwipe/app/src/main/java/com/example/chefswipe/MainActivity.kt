package com.example.chefswipe

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.firebase.firestore.FirebaseFirestore
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import com.lorentzos.flingswipe.SwipeFlingAdapterView.onFlingListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import java.util.ArrayList

import com.example.chefswipe.fragments.HomepageFragment
import com.example.chefswipe.fragments.LogoutpageFragment
import com.example.chefswipe.fragments.NewspageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


//Implement onFlingListener
class MainActivity : AppCompatActivity() {

    private val cards_data: Array<Cards>
        get() {
            TODO()
        }

    var recipeIndexCounter = -1

    //Vars for fragments
    private val homepageFragment = HomepageFragment()
    private val newspageFragment = NewspageFragment()
    private val logoutpageFragment = LogoutpageFragment()

    private val mAuth: FirebaseAuth? = null
    private val firebaseAuthStateListener: AuthStateListener? = null

    //Creating array adapter from arrayAdapter class
    private var arrayAdapter: arrayAdapter? = null

    //Need to initialize index on registration **IMPORTANT**
    private var recipeIndex = 0
    private var db = FirebaseFirestore.getInstance()
    var listView: ListView? = null
    var rowItems: MutableList<Cards>? = null
    var recipeList = ArrayList<Int>()
    var recipeSaved = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val flingContainer = findViewById<View>(R.id.frame) as SwipeFlingAdapterView

        //Create arraylist from Cards
        rowItems = ArrayList()


        //Choose adapter
        arrayAdapter = arrayAdapter(this, R.layout.item, rowItems)

        //set the listener and the adapter
        flingContainer.adapter = arrayAdapter
        updateArrayAdapter()

        //Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
        flingContainer.setFlingListener(object : onFlingListener {

            //Called when adapter is almost empty
            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                updateArrayAdapter()
                Log.d("Items in adapter", itemsInAdapter.toString())
                recipe
            }

            //Called when card is swiped to left
            override fun onLeftCardExit(dataObject: Any) {}

            //Called when card is swiped to right
            override fun onRightCardExit(dataObject: Any) {
                val docRef = db.collection("Sweet Treats").document(Integer.toString(recipeIndexCounter))
                docRef.get().addOnCompleteListener { task: Task<DocumentSnapshot> ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document.exists()) {
                            var browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(document.getString("Link")))
                            startActivity(browserIntent)
                            updateArrayAdapter()
                        }
                    }
                }

            }


            override fun removeFirstObjectInAdapter() {
                recipeIndexCounter++
                //Delete an object from the Adapter (/AdapterView)
                (rowItems as ArrayList<Cards>).removeAt(0)
                Log.d("LIST", "removed object!")
                updateArrayAdapter()
            }

            override fun onScroll(v: Float) {}
        })

        //OnItemClickListener
        flingContainer.setOnItemClickListener { itemPosition: Int, dataObject: Any? -> }

        //Checks if buttons on nav bar have been clicked
        findViewById<BottomNavigationView>(R.id.bottom_navigation)
            .setOnNavigationItemSelectedListener {
            when(it.itemId) {
                //Commented out as we are already on mainpage
                    R.id.ic_mainpage -> intent = Intent(this@MainActivity, MainActivity::class.java)
                R.id.ic_newspage ->  intent = Intent(this@MainActivity, NewsActivity::class.java)
                R.id.ic_logoutpage -> intent = Intent(this@MainActivity, LoginActivity::class.java)
            }
                startActivity(intent)
                true
        }
    }


    //Function can be called to update array adapter
    fun updateArrayAdapter() {
        arrayAdapter!!.notifyDataSetChanged()
    }


    //Called retrieve recipe from Firebase database
    val recipe: Unit
        get() {
            val docRef = db.collection("Sweet Treats").document(Integer.toString(recipeIndex))
            docRef.get().addOnCompleteListener { task: Task<DocumentSnapshot> ->
                if (task.isSuccessful) {
                    val document = task.result
                    recipeIndex++
                    if (document.exists()) {
                        Log.d("Recipe Index", recipeIndex.toString())
                        val Item = Cards("0", document.getString("Name"))
                        rowItems!!.add(Item)
                        updateArrayAdapter()
                    }
                }
            }
        }


    companion object {
        private const val TAG = "Chef Swipe"
    }
}