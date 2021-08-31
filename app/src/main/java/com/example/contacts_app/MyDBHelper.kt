package com.example.contacts_app

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context,"Contacts_App_DB",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("CREATE TABLE LoginCredentials(Name VARCHAR2,UName VARCHAR2 PRIMARY KEY,Pass VARCHAR2)")
        db?.execSQL("CREATE TABLE AddContact(ContactName VARCHAR2 PRIMARY KEY,Email VARCHAR2 ,PhoneNo VARCHAR2)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS LoginCredentials")
        db?.execSQL("DROP TABLE IF EXISTS AddContact")

    }





}