package com.example.bookren;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookren.UserDetails.Users;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class PasswordReset extends AppCompatActivity {
EditText Rpass,Rcpass;
Button Rbutton;
String no="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        no=getIntent().getStringExtra("number");
       Log.i("kk",no);
        Rpass=(EditText)findViewById(R.id.Rpass);
        Rcpass=(EditText)findViewById(R.id.Rcpass);
        Rbutton=(Button)findViewById(R.id.Rbutton);

        Rbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                UpadateDB();
            }
        });
    }

    private void UpadateDB()
    {
        String Pas=Rpass.getEditableText().toString().trim();
        String rcpas=Rcpass.getEditableText().toString().trim();
        if (TextUtils.isEmpty(Pas))
        {
            Toast.makeText(getApplicationContext(),"Enter New Password",Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(rcpas))
        {
            Toast.makeText(getApplicationContext(),"Reenter Password",Toast.LENGTH_SHORT).show();
        }
        else if (Pas.equals(rcpas))
        {
            DatabaseReference root= FirebaseDatabase.getInstance().getReference();
            Map<String,Object> updatedvalues =new HashMap<>();
            updatedvalues.put("password",Pas);
            root.child("Users").child(no).updateChildren(updatedvalues);
            Intent intent=new Intent(getApplicationContext(),login.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Password reset success",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
