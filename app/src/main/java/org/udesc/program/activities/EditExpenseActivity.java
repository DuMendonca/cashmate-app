package org.udesc.program.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.udesc.program.R;
import org.udesc.program.model.Expense;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class EditExpenseActivity extends AppCompatActivity {

    public EditText description;
    public EditText price;
    public Spinner tag;
    public Spinner mouth;

    private ArrayAdapter<String> adapterSpinnerMouth;

    private ArrayAdapter<String> adapterSpinnerTag;

    public Button btn_delete;
    public Button btn_confirm;

    public ImageButton btn_home;
    public ImageButton btn_view;
    public ImageButton btn_add;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_expense);

        initField();
        initMenuButton();
        configureSpinnerMouth();
        configureSpinnerTag();

        btn_confirm.setOnClickListener(view -> editExpense());
        btn_confirm.setOnClickListener(view -> deleteExpense());
    }

    private void initMenuButton() {
        btn_home = findViewById(R.id.btn_home);
        btn_view = findViewById(R.id.btn_view);
        btn_add = findViewById(R.id.btn_add);

        configureButtonMenu();
    }

    private void initField() {
        description = findViewById(R.id.editExpenseTextDescription);
        price = findViewById(R.id.editExpenseTextValue);
        tag = findViewById(R.id.editExpenseSpinnerTAG);
        mouth = findViewById(R.id.editExpenseSpinnerMonth);
        btn_delete = findViewById(R.id.editExpenseButtonDelete);
        btn_confirm = findViewById(R.id.editExpenseButtonAccept);
    }

    private void configureButtonMenu() {
        btn_add.setOnClickListener(view -> moveToActivity(NewExpenseActivity.class));
        btn_home.setOnClickListener(view -> moveToActivity(DashboardActivity.class));
        btn_view.setOnClickListener(view -> moveToActivity(ViewExpenseActivity.class));
    }

    private void moveToActivity(Class<?> activityClass) {
        Intent intent = new Intent(getApplicationContext(), activityClass);
        startActivity(intent);
    }

    private void configureSpinnerMouth() {
        List<String> list = getValuesForMouth();

        adapterSpinnerMouth = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        adapterSpinnerMouth.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mouth.setAdapter(adapterSpinnerMouth);

        mouth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void configureSpinnerTag() {
        List<String> list = getValuesForTag();

        adapterSpinnerTag = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        adapterSpinnerTag.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tag.setAdapter(adapterSpinnerTag);

        tag.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                Toast.makeText(getBaseContext(), list.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }

    private List<String> getValuesForMouth() {
        final List<String> list = new ArrayList<>();

        list.add("January");
        list.add("February");
        list.add("March");
        list.add("April");
        list.add("May");
        list.add("June");
        list.add("July");
        list.add("August");
        list.add("September");
        list.add("October");
        list.add("November");
        list.add("December");

        return list;
    }

    private List<String> getValuesForTag() {
        final List<String> list = new ArrayList<>();

        list.add("Leisure");
        list.add("Food");
        list.add("Transport");
        list.add("Health");
        list.add("Home");
        list.add("Emergency");
        list.add("Others");

        return list;
    }

    private void editExpense() {
        String id = new String();
        String json = new Gson().toJson(setFieldInExpense());

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), json);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/expense/" + id)
                .put(requestBody)
                .build();
        try {
            Call call = client.newCall(request);
            call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Expense setFieldInExpense() {
        Expense expense = new Expense();

        expense.setDescription(description.getText().toString());
        expense.setPrice(price.getText().toString());
        expense.setMouthOfYears(mouth.getSelectedItem().toString());
        expense.setTag(tag.getSelectedItem().toString());

        return expense;
    }

    private void deleteExpense() {
        String id = new String();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/expense/" + id)
                .delete()
                .build();
        try {
            Call call = client.newCall(request);
            call.execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
