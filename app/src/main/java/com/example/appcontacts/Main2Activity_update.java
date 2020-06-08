package com.example.appcontacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity_update extends AppCompatActivity {
      EditText editname ;
      EditText editphone ;
      Button   buUpdate ;
      DbContact db ;
      int id ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_update);
        editname = findViewById(R.id.editText_update_name) ;
        editphone = findViewById(R.id.editText_update_phone);
        buUpdate = findViewById(R.id.button_update) ;
        db = new DbContact(this) ;
         id =getIntent().getIntExtra("id",0);
        Contact contact = db.getContactById(id);
        editname.setText(contact.getName());
        editphone.setText(contact.getPhone()+"");

        buUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editname.getText().toString() ;
                int phone   = Integer.parseInt(editphone.getText().toString());
                db.updateContact(new Contact(id,name,phone));
                Toast.makeText(Main2Activity_update.this,"contact update with success",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.id_delete:
                showAlert();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert() {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("confirmation")
                .setMessage("are you sure ?")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //DELETE CODE
                        db.deleteContact(id);
                        Toast.makeText(Main2Activity_update.this,"contact deleted with success",Toast.LENGTH_LONG).show();
                        finish();

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = alertBuilder.create();
        dialog.show();

    }
}