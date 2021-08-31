package com.example.contacts_app

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        var helper = MyDBHelper(this)
        var dbTable = helper.readableDatabase


        register.setOnClickListener {

            var name = name_reg.text.toString()
            var username = username_reg.text.toString()
            var password = password_reg.text.toString()
            var confirmPass = confirm_password.text.toString()

            val i = Intent(this, MainActivity::class.java)

            var args = listOf<String>(name,username).toTypedArray()
            var result = dbTable.rawQuery("SELECT * FROM LoginCredentials WHERE Name = ? AND UName= ? ",args)


            if( username.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPass.isEmpty())
            Toast.makeText(
                this,
                "All fields are mandatory.Kindly fill all the fields to register ",
                Toast.LENGTH_SHORT
            ).show()

            else if((password == confirmPass) && result.moveToNext()){
                Toast.makeText(
                    this,
                    "Username already exists. Please Login to continue ! ",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(i)
            }

            else if ((password == confirmPass) && (!result.moveToNext()) ) {

                var cv= ContentValues()
                cv.put("UName",username)
                cv.put("PASS",password)
                cv.put("Name",name)
                dbTable.insert("LoginCredentials",null,cv)

                Toast.makeText(
                    this,
                    "Registered successfully! Login to continue",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(i)
            }

            else
                Toast.makeText(
                    this,
                    "Password and Confirm Password fields are mismatched!!!",
                    Toast.LENGTH_SHORT
                ).show()

            result.close()

        }
    }
}