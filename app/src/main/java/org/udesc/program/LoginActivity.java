package org.udesc.program;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LIFE_CYCLE";

    private Button button;

    private EditText userLogin;

    private EditText passLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userLogin = findViewById(R.id.userLogin);
        passLogin = findViewById(R.id.passLogin);
        button = findViewById(R.id.btnLogin);

        button.setOnClickListener(view -> doLogin());
    }


    private void doLogin() {
        boolean isValidUser = validateUserLogin();

        if (isValidUser)
        {
            Toast.makeText(getApplicationContext(), "Login Succeed!", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(
                    getApplicationContext(),
                    DashboardActivity.class);

            intent.putExtra("Value1", "login");
            startActivity(intent);

        }
        else {
            Toast.makeText(getApplicationContext(), "Login Invalid! Please Try Again!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateUserLogin() {
        return userLogin.getText().length() > 0 && passLogin.getText().length() > 0;
    }
}