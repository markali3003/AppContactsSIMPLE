package com.example.appcontacts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2ActivityAddContact extends AppCompatActivity {
      EditText editname ;
      EditText editphone ;
      Button but_save ;
      DbContact db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_add_contact);

        editname = findViewById(R.id.editText_name);
        editphone = findViewById(R.id.editText_phone) ;
        but_save =findViewById(R.id.button_save) ;
        db = new DbContact(this);

    }


    public void saveContact(View view) {

        String name = editname.getText().toString();
        int phone = Integer.parseInt(editphone.getText().toString());
       db.addContact(new Contact(name,phone));
       editname.setText("");
       editphone.setText("");
       Toast.makeText(this,"VOUS AVES AJOUTER UN NOUVEAU CONTACT",Toast.LENGTH_LONG).show();
    }
}
