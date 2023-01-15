package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.Remote.ApiService;
import com.example.login.Remote.Network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button loginButton = findViewById(R.id.loginButton);
    String email = ((EditText) findViewById(R.id.emailEditText)).getText().toString();
    String password = ((EditText) findViewById(R.id.passwordEditText)).getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onLoginButtonClick(View view) {

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (validateUsername() && validatePassword()) {
                    User responseRegisterClass = new User(email, password);

                    ApiService apiService = Network.getInstance().create(ApiService.class);
                    apiService.getUser(responseRegisterClass).enqueue(new Callback<UserRes>() {
                        @Override
                        public void onResponse(Call<UserRes> call, Response<UserRes> response) {
                            if (response.body() != null) {
                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, SuccessLogin.class);
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<UserRes> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }



                    });

                }

            }

        });
    }

    private boolean validateUsername() {
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Invalid email address", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean validatePassword() {
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}

