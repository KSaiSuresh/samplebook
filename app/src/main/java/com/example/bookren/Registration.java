package com.example.bookren;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Registration extends AppCompatActivity {
EditText rname,remail,rpass,rcpass,rphno;
Button rbutton;
 ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        rname=(EditText)findViewById(R.id.rname);
        remail=(EditText)findViewById(R.id.remail);
        rpass=(EditText)findViewById(R.id.rpass);
        rcpass=(EditText)findViewById(R.id.rcpass);
        rphno=(EditText)findViewById(R.id.rphno);
        rbutton=(Button)findViewById(R.id.rbutton);
        progressDialog=new ProgressDialog(this);

        rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                create();
            }
        });

    }

    private void create() {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String MobilePattern = "[0-9]{10}";
       final String name=rname.getText().toString();
       final String email=remail.getText().toString();
      final   String pass=rpass.getText().toString();
      final   String cpass=rcpass.getText().toString();
        final String phno=rphno.getText().toString().trim();
      final   String mobile="+" + "91" + phno;
        Log.i("opop",pass);
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(getApplicationContext(), "Enter name", Toast.LENGTH_SHORT).show();
        }

           else if (TextUtils.isEmpty(email))
            {
                Toast.makeText(getApplicationContext(), "Enter email", Toast.LENGTH_SHORT).show();
            }

               else if (!email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(), "Enter Valid Email", Toast.LENGTH_SHORT).show();
                }

                  else  if (TextUtils.isEmpty(pass))
                    {
                        Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
                    }


                      else   if (!(8 <= pass.length()&& pass.length() <14))
                        {
                            Toast.makeText(getApplicationContext(), "min password length is 8 and max is 13", Toast.LENGTH_SHORT).show();
                            Log.i("gggg",pass);
                        }
    //;o;

                         else   if (TextUtils.isEmpty(cpass))
                            {
                                Toast.makeText(getApplicationContext(), "Confirm Password", Toast.LENGTH_SHORT).show();

                            }

                            else    if (TextUtils.isEmpty(phno))
                                {
                                    Toast.makeText(getApplicationContext(), "Enter Phno", Toast.LENGTH_SHORT).show();
                                }

                                 else   if (android.util.Patterns.PHONE.matcher(phno).matches())
                                    {
                                        Toast.makeText(getApplicationContext(), "Invalid Phno", Toast.LENGTH_SHORT).show();
                                    }

                                     else   if (!pass.equals(cpass))
                                        {
                                            Toast.makeText(getApplicationContext(), "password donot match", Toast.LENGTH_SHORT).show();
                                        }


        else
        {
           // progressDialog.setTitle("Creating Acount");
           // progressDialog.setMessage("Validating Data");
           // progressDialog.setCanceledOnTouchOutside(false);
           // progressDialog.show();


            final DatabaseReference root;
            root= FirebaseDatabase.getInstance().getReference();
            root.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                {

                    if (!(dataSnapshot.child("Users").child(phno).exists()))
                    {
                        String fall="false";
                        Intent intent=new Intent(getApplicationContext(),VerifyPhoneActivity.class);
                        intent.putExtra("mobile",mobile);
                        intent.putExtra("name",name);
                        intent.putExtra("email",email);
                        intent.putExtra("password",pass);
                        intent.putExtra("phno",phno);
                        intent.putExtra("key","false");

                        startActivity(intent);

                        String True = intent.getStringExtra("True");
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"User already Exists",Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError)
                {
                    Toast.makeText(getApplicationContext(),"Network error",Toast.LENGTH_SHORT).show();
                }
            });



               // insertdb(name,email,pass,phno);



        }
    }


}
