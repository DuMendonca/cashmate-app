package org.udesc.program;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "LIFE_CYCLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(view -> onClick());

        Log.i(TAG, "onCreate");
    }

    private void onClick() {
        Intent intent = new Intent(getApplicationContext(), ViewExpenseActivity.class);

        startActivity(intent);
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
}
