package com.example.bookren;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookren.UserDetails.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class login extends AppCompatActivity {
    String s,d,l;
EditText lname,lpass;
TextView ltext,lfor;
Button lbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lname=(EditText)findViewById(R.id.lname);
        lpass=(EditText)findViewById((R.id.lpass));
        ltext=(TextView)findViewById((R.id.ltext));
        lbutton=(Button)findViewById((R.id.lbutton));
        lfor=(TextView)findViewById(R.id.lfor);
        String name=lname.getEditableText().toString().trim();
        String passwor=lpass.getEditableText().toString().trim();
        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Login();
            }
        });

        ltext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent =new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
                finish();

            }
        });
        lfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),forgot.class);
                startActivity(intent);

            }
        });
    }

    private void Login()
    {
        String Id=lname.getEditableText().toString().trim();
        String pass=lpass.getEditableText().toString().trim();
        if (TextUtils.isEmpty(Id))
        {
            Toast.makeText(getApplicationContext(),"Enter Usre Id",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        else
        {
            validatelogin(Id,pass);
        }
    }

    private void validatelogin(final String id, final String password)
    {
        Log.i("xx",id+password);
        final DatabaseReference root;
        root=FirebaseDatabase.getInstance().getReference();


        root.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              //  Log.i("xx", "86");
                if (dataSnapshot.child("Users").child(id).exists()) {
                    Log.i("xx", "88");
                    Users userdata = dataSnapshot.child("Users").child(id).getValue(Users.class);
                   // Log.i("xx", userdata.getPhone() + " " + userdata.getPassword());
                    if (userdata.getPhone().equals(id)) {
                        if (userdata.getPassword().equals(password)) {
                            Toast.makeText(getApplicationContext(), "Login Success ", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();

                        }
                    }
                }


                else
                {
                    Toast.makeText(getApplicationContext(),"does",Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError)
            {
                Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
            }
        });
    }




}
