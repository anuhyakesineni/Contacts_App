package com.example.contacts_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_home_screen.*


class HomeScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        var b:Bundle? = intent.extras
        var nameHome = "Welcome " + b?.getString("name")

        name_home_screen.setText(nameHome)

        add_contact.setOnClickListener {
            var i = Intent(this,AddContact::class.java)
            startActivity(i)
        }

        display_contacts.setOnClickListener {
            var i = Intent(this,DisplayContacts::class.java)
            startActivity(i)
        }

        delete_contact.setOnClickListener {
            var i = Intent(this,DeleteContact::class.java)
            startActivity(i)
        }


        sign_out.setOnClickListener {
            var i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
    }
}