package com.example.appcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbContact extends SQLiteOpenHelper {
    private static final String DB_NAME="Db_Contact";
    private static final int DB_VERSION=1 ;
    private static final String KEY_ID="id" ;
    private static final String KEY_NAME="name" ;
    private static final String KEY_PHONE="phone" ;
    private static final String TABLE_CONTACT="contact";






    public DbContact(@Nullable Context context) {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
     String create_table = "CREATE TABLE IF NOT EXISTS "+TABLE_CONTACT+"("+KEY_ID+" integer primary key,"+KEY_NAME+" varchar(30),"+KEY_PHONE+" integer)";
                         db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            String delete_table="Drop TABLE  IF EXISTS "+TABLE_CONTACT;
            db.execSQL(delete_table);
            onCreate(db);
    }

    public void addContact(Contact contact){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());

        db.insert(TABLE_CONTACT,null,values);
    }

    public ArrayList<Contact> getAllContact(){

        ArrayList<Contact> contacts = new ArrayList<>() ;

        SQLiteDatabase db = this.getReadableDatabase();

        String select_query="select * from "+TABLE_CONTACT;
        Cursor cursor = db.rawQuery(select_query,null);
        if(cursor.moveToFirst()){
            do {
                int id  = cursor.getInt(cursor.getColumnIndex(KEY_ID)) ;
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                int phone = cursor.getInt(cursor.getColumnIndex(KEY_PHONE));

                contacts.add(new Contact(id,name,phone)) ;
            }while (cursor.moveToNext());

        }

        return contacts ;
    }

    public Contact getContactById(int id){
        Contact contact=null  ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        String select_query = "select * from "+TABLE_CONTACT+" where "+KEY_ID+" = "+id  ;
       Cursor cursor = db.rawQuery(select_query,null) ;
       if(cursor.moveToFirst()){
           int id_contact = cursor.getInt(cursor.getColumnIndex(KEY_ID));
           String name   = cursor.getString(cursor.getColumnIndex(KEY_NAME)) ;
           int phone =  cursor.getInt(cursor.getColumnIndex(KEY_PHONE)) ;
           contact = new Contact(id_contact,name,phone);
       }
       return contact ;
    }

public void updateContact(Contact contact){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues values = new ContentValues() ;
        values.put(KEY_NAME,contact.getName());
        values.put(KEY_PHONE,contact.getPhone());

        db.update(TABLE_CONTACT,values , KEY_ID+"=?" , new String[]{String.valueOf(contact.getId())}) ;
}

public void deleteContact(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACT,KEY_ID+"=?" , new String[]{String.valueOf(id)}) ;



}
}
