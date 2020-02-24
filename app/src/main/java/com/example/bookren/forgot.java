package com.example.bookren;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookren.UserDetails.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class forgot extends AppCompatActivity {
EditText fno;
Button fbutton;
String no,forg="true";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        fno=(EditText)findViewById(R.id.fno);
        fbutton=(Button)findViewById(R.id.fbutton);
        fbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                no=fno.getEditableText().toString().trim();
                Log.i("asd",no);
                if(TextUtils.isEmpty(no))
                {
                    Toast.makeText(getApplicationContext(),"Enter Number",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    final DatabaseReference root;
                    root=FirebaseDatabase.getInstance().getReference();
                    root.addListenerForSingleValueEvent(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            if (dataSnapshot.child("Users").child(no).exists())
                            {
                                Log.i("lol",no);
                                String mobile="+" + "91" + no;
                                Intent intent=new Intent(getApplicationContext(),VerifyPhoneActivity.class);
                                intent.putExtra("Phone",mobile);
                                intent.putExtra("now",no);
                                intent.putExtra("key","true");
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"erroe",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError)
                        {

                        }
                    });
                }
            }
        });
    }
}
