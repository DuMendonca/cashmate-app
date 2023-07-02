package org.udesc.program;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;

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
                    getApplicationContext(), MainActivity.class);

            startActivity(intent);
    }


}