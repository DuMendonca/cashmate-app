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
        ContentResolver contentResolver = getContentResolver();
        Uri uri = ContactsContract
                .CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = contentResolver
                .query(uri,
                        null,
                        null,
                        null,
                        null);

        if (cursor.getCount() > 0 && cursor.moveToNext()) {
            @SuppressLint("Range")
            String name = cursor
                    .getString(cursor
                            .getColumnIndex(ContactsContract
                                    .CommonDataKinds
                                    .Phone
                                    .DISPLAY_NAME));

            Intent intent = new Intent(
                    this,
                    DashboardActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("name", name);
            intent.putExtra("bundle", bundle);

            startActivity(intent);
        }
    }
}