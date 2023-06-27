package org.udesc.program;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    private static final String TAG = "LIFE_CYCLE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("bundle");
        String name = bundle.getString("name");
        setContentView(R.layout.activity_dashboard);

        Log.i(TAG, "onCreate");
    }

    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }
}
