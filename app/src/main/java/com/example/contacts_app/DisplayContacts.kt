package com.example.contacts_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_display_contacts.*


class DisplayContacts : AppCompatActivity() {

    lateinit var myAdapter:AdapterClass
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_contacts)

        var tempContactModel=ArrayList<DataClass>()

        var helper = MyDBHelper(this)
        var db = helper.readableDatabase
        var result = db.rawQuery("SELECT * FROM AddContact", null)

        if(!result.moveToNext())
            Toast.makeText(this, "No contacts", Toast.LENGTH_LONG).show()


        while(result.moveToNext())
        {
            var x = result.getColumnIndex("ContactName")
            var y = result.getColumnIndex("Email")
            var z = result.getColumnIndex("PhoneNo")

            var name = DataClass(
                result.getString(x).toString(), result.getString(y).toString(), result.getString(z).toString()
            )
            tempContactModel.add(name)
        }
        result.close()




        var layoutmanager = LinearLayoutManager(this)
        layoutmanager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = layoutmanager
        myAdapter = AdapterClass(this, tempContactModel)
        recyclerView.adapter = myAdapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.activity_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_multiples->{
                if(::myAdapter.isInitialized){
                    myAdapter.deleteSelectedItems()

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }


}