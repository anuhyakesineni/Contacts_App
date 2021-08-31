package com.example.contacts_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_delete_contact.*

class DeleteContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_contact)

        var helper = MyDBHelper(this)
        var dbTable = helper.readableDatabase

        delete.setOnClickListener {
            var name = delete_name.text.toString()
            var arg = listOf<String>(name).toTypedArray()
            var result = dbTable.rawQuery("SELECT ContactName FROM AddContact WHERE ContactName=?", arg)

            if (result.moveToNext()) {
                deleteData(name)
                Toast.makeText(this, "Successfully Deleted", Toast.LENGTH_LONG).show()
                delete_name.setText("")
                delete_name.requestFocus()

            }
            else
                Toast.makeText(this, "Contact doesn't exist", Toast.LENGTH_LONG).show()


            result.close()
        }

    }
        private fun deleteData(name: String) {

            var helper = MyDBHelper(this)
            var dbTable = helper.readableDatabase

            var contactName = arrayOf(name)
            dbTable.delete("AddContact", "ContactName=?", contactName)
            dbTable.close()


    }
}