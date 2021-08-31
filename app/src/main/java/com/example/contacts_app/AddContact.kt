package com.example.contacts_app

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_contact.*
import kotlinx.android.synthetic.main.activity_main.*

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contact)

        var helper = MyDBHelper(this)
        var dbTable = helper.readableDatabase

        submit.setOnClickListener {

            var contactName = name_add.text.toString()
            var email = e_mail.text.toString()
            var phoneNo = phone_no.text.toString()


            var args = listOf<String?>(contactName, email, phoneNo).toTypedArray()
            var result = dbTable.rawQuery("SELECT * FROM AddContact WHERE ContactName = ? AND Email = ? AND PhoneNo = ? ",args)

            if (contactName.isEmpty() || email.isEmpty() || phoneNo.isEmpty())
                Toast.makeText(
                    this,
                    "Please fill all the fields ! ",
                    Toast.LENGTH_SHORT
                ).show()

            else if (result.moveToNext())
                Toast.makeText(
                    this,
                    "Contact is already added ! ",
                    Toast.LENGTH_SHORT
                ).show()

            else {
                var cv = ContentValues()
                cv.put("ContactName", contactName)
                cv.put("Email", email)
                cv.put("PhoneNo", phoneNo)

                dbTable.insert("AddContact", null, cv)
                Toast.makeText(this, "Contact added successfully!", Toast.LENGTH_SHORT).show()

            }
            result.close()
        }
    }
}