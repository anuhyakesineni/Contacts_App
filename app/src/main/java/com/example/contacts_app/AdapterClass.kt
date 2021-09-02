package com.example.contacts_app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_each_contact.view.*

class AdapterClass(var context: Context, var contactList:ArrayList<DataClass>): RecyclerView.Adapter<AdapterClass.view>(){

    var selectedList = ArrayList<String>()
    var isSelectedItemMode :Boolean= false

    inner class view(var inner_view:View):RecyclerView.ViewHolder(inner_view){

        fun data(contact:DataClass?,pos:Int){
            itemView.contactName.text = "Name: "+ contact?.name
            itemView.contactEmail.text = "E-mail: "+ contact?.email
            itemView.contactPhoneNo.text = "Phone Number: "+ contact?.phoneNo

            itemView.linearLayout.setOnLongClickListener {

                isSelectedItemMode = true
                markSelectedItems(pos)
            }
            itemView.linearLayout.setOnClickListener {
                if(isSelectedItemMode == true and (contact?.isSelected ==false))
                    markSelectedItems(pos)
                else
                    deSelectItem(pos)
            }
            if(contact?.isSelected==true)
                itemView.select.visibility=View.VISIBLE
            else
                itemView.select.visibility=View.GONE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): view {

        var v = LayoutInflater.from(context).inflate(R.layout.activity_each_contact,parent,false)
        return view(v)

    }

    override fun onBindViewHolder(holder: view, position: Int) {

        var pos = contactList[position]
        holder.data(pos,position)

    }

    override fun getItemCount(): Int {

        return contactList.size

    }
    fun markSelectedItems(pos:Int):Boolean{
        for(i in contactList){
            i.isSelected=false
        }
        var s=contactList.get(pos)
        var name=s.name
        if(!selectedList.contains(name)){
            selectedList.add(name)
        }
        selectedList.forEach{
            for(i in contactList){
                if(i.name==it){
                    i.isSelected=true
                }
            }
        }

        notifyDataSetChanged()
        return true
    }

    fun deSelectItem(pos:Int){
        var name=contactList.get(pos).name
        if(selectedList.contains(name)){
            selectedList.remove(name)
        }
        contactList.get(pos).isSelected=false
        notifyDataSetChanged()

    }

    fun deleteSelectedItems(){

        for(i in selectedList){
            deleteData(i)
        }
        contactList.removeAll { item->item.isSelected==true }
        notifyDataSetChanged()
    }

    private fun deleteData(name:String){

        var helper = MyDBHelper(context)
        var db: SQLiteDatabase =helper.writableDatabase
        var arr= arrayOf(name)
        db.delete("AddContact","ContactName=?", arr)
        db.close()

    }

}


