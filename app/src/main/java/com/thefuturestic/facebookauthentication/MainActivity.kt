package com.thefuturestic.facebookauthentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.Profile
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import java.util.*

class MainActivity : AppCompatActivity() {
    private var callbackManager: CallbackManager? = null
    private var signInButton: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signInButton = findViewById<View>(R.id.sigin_in_btn) as Button

        signInButton!!.setOnClickListener(View.OnClickListener {
            signInWithFacebook()
        })//end of login button

    }

    private fun signInWithFacebook(){
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(this, Arrays.asList("public_profile"))
        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult> {
                override fun onSuccess(loginResult: LoginResult) {
                    Toast.makeText(this@MainActivity, "sign in successful", Toast.LENGTH_SHORT).show()

                }//end of onsuccess


                override fun onCancel() {
                    Log.d("MainActivity", "Facebook onCancel.")
                    Toast.makeText(this@MainActivity, "cancled", Toast.LENGTH_SHORT).show()

                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(this@MainActivity, "something goes wrong", Toast.LENGTH_SHORT).show()
                    error.printStackTrace()
                }
            })


    }//end of signin with facebook

}
