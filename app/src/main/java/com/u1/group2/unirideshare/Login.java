package com.u1.group2.unirideshare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private TextView email;
    private TextView password;
    private Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.email = findViewById(R.id.emailAddress);
        this.password = findViewById(R.id.password);
        this.signUp = findViewById(R.id.signup);
        setButtonHandler(signUp);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    private void setButtonHandler(Button button) {
        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signIn(email.getText().toString(), password.getText().toString());
                    }
                }
        );
    }

    private void signIn(final String email, String password) {
        completeSignUp(email);
    }

    private void completeSignUp(String email) {
        Intent intent = new Intent(Login.this, PersonalDetails.class);
        intent.putExtra("EMAIL", email);
        startActivity(intent);
    }
}
