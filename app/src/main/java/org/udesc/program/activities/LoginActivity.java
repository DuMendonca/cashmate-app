package org.udesc.program.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import org.udesc.program.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LIFE_CYCLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.button2);

        button.setOnClickListener(view ->
                doLogin());
    }


    private void doLogin() {
            Intent intent = new Intent(
                    getApplicationContext(),
                    DashboardActivity.class);

            startActivity(intent);
    }
}