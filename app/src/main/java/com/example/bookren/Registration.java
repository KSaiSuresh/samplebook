package com.example.bookren;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
        String name=rname.getText().toString();
        String email=remail.getText().toString();
        String pass=rpass.getText().toString();
        String cpass=rcpass.getText().toString();
        String phno=rphno.getText().toString().trim();
        String mobile="+" + "91" + phno;
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(pass))
        {
            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(cpass))
        {
            Toast.makeText(getApplicationContext(),"Confirm Password",Toast.LENGTH_SHORT).show();
        }
        if(TextUtils.isEmpty(phno)||phno.length()==10)
        {
            Toast.makeText(getApplicationContext(),"Invalid Phno",Toast.LENGTH_SHORT).show();
        }
        if (!pass.equals(cpass))
        {
            Toast.makeText(getApplicationContext(),"password donot match",Toast.LENGTH_SHORT).show();
        }
        else
        {
           // progressDialog.setTitle("Creating Acount");
           // progressDialog.setMessage("Validating Data");
           // progressDialog.setCanceledOnTouchOutside(false);
          //  progressDialog.show();
            Intent intent=new Intent(getApplicationContext(),VerifyPhoneActivity.class);
            intent.putExtra("mobile",mobile);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            intent.putExtra("password",pass);
            intent.putExtra("phno",phno);

            startActivity(intent);

            String True = intent.getStringExtra("True");

               // insertdb(name,email,pass,phno);



        }
    }


}
