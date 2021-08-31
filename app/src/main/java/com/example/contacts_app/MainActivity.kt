package com.example.contacts_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var helper = MyDBHelper(this)
        var dbTable = helper.readableDatabase

        register_main.setOnClickListener {
            var i = Intent(this,Register::class.java)
            startActivity(i)
        }

        login_main.setOnClickListener {

            var userName = username.text.toString()
            var pass = password.text.toString()
            var args = listOf<String>(userName,pass).toTypedArray()

            var result = dbTable.rawQuery("SELECT * FROM LoginCredentials WHERE UName= ? AND Pass = ? ",args)

            if(userName.isEmpty() || pass.isEmpty())
                Toast.makeText(
                    this,
                    "Username or password is not entered!!!",
                    Toast.LENGTH_SHORT
                ).show()

            else if(result.moveToNext()){

                var i = Intent(this, HomeScreen::class.java)

                var nameIndex = result.getColumnIndex("Name")
                var loginName = result.getString(nameIndex)
                i.putExtra("name",loginName)
                startActivity(i)
             }

            else
                Toast.makeText(this,"Wrong Credentials !!! ",Toast.LENGTH_SHORT).show()


            result.close()
        }

    }
}