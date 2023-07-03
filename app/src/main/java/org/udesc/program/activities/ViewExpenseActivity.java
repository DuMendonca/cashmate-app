package org.udesc.program.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.udesc.program.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ViewExpenseActivity extends AppCompatActivity {

    public ListView listView;

    public ImageButton btn_home;
    public ImageButton btn_view;
    public ImageButton btn_add;

    private ArrayAdapter<String> adapter;
    private ArrayList<String> arrayList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_expense);

        initField();
        initMenuButton();

        fetchAllExpense();
    }

    private void fetchAllExpense() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/expense")
                .build();
        try {
            Call call = client.newCall(request);
            Response response;
            response = call.execute();

            Gson gson = new Gson();
            arrayList = gson.fromJson(response.body().string(), ArrayList.class);
            adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);

            listView.setAdapter(adapter);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void initMenuButton() {
        btn_home = findViewById(R.id.btn_home);
        btn_view = findViewById(R.id.btn_view);
        btn_add = findViewById(R.id.btn_add);

        configureButtonMenu();
    }

    private void initField() {
        listView = findViewById(R.id.viewExpenseListView);
    }

    private void configureButtonMenu() {
        btn_home.setEnabled(false);

        btn_add.setOnClickListener(view -> moveToActivity(NewExpenseActivity.class));
        btn_view.setOnClickListener(view -> moveToActivity(ViewExpenseActivity.class));
    }

    private void moveToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        startActivity(intent);
    }
}
