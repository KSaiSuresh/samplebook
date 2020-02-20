package com.example.bookren;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
EditText lname,lpass;
TextView ltext;
Button lbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        lname=(EditText)findViewById(R.id.lname);
        lpass=(EditText)findViewById((R.id.lpass));
        ltext=(TextView)findViewById((R.id.ltext));
        lbutton=(Button)findViewById((R.id.lbutton));
        ltext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent =new Intent(getApplicationContext(),Registration.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
