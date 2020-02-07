package com.eylem.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    EditText userNameeditText;
    Button loginbtn;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameeditText=findViewById(R.id.userNameeditText);
        loginbtn=findViewById(R.id.loginbtn);
        firebaseDatabase=FirebaseDatabase.getInstance();
        reference=firebaseDatabase.getReference();
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=userNameeditText.getText().toString();
                userNameeditText.setText("");
                add(username);
            }
        });
    }

    public void add(final String userName){
        reference.child("Users").child(userName).child("username").setValue(userName).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login is successful!",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                }
            }
        });
    }

}
