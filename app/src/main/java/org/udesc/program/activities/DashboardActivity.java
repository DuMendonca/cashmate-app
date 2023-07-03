package org.udesc.program.activities;

// Import the required libraries

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.udesc.program.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DashboardActivity extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    public PieChart pieChart;

    public Spinner mouth;

    public ListView listView;

    private ArrayAdapter<String> adapter;

    private ArrayAdapter<String> adapterSpinner;
    private ArrayList<String> arrayList;

    public ImageButton btn_home;
    public ImageButton btn_view;
    public ImageButton btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        initMenuButton();
        initField();

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
        configureSpinnerMouth();
        fetchExpenseByMouth();
    }

    private void setData() {

        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Lazer",
                        40,
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Alimentação",
                        30,
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Transporte",
                        5,
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Saúde",
                        24,
                        Color.parseColor("#29B6F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Moradia",
                        37,
                        Color.parseColor("#5529F6")));
        pieChart.addPieSlice(
                new PieModel(
                        "Emergência",
                        5,
                        Color.parseColor("#E46510")));
        pieChart.addPieSlice(
                new PieModel(
                        "Outros",
                        25,
                        Color.parseColor("#68351D")));

        // To animate the pie chart
        pieChart.startAnimation();
    }

    private void configureSpinnerMouth() {
        List<String> list = getValuesInSpinner();

        adapterSpinner = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, list);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mouth.setAdapter(adapterSpinner);

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

    private List<String> getValuesInSpinner() {
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

    private void fetchExpenseByMouth() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://localhost:8080/expense/mouth/" + mouth.getSelectedItemPosition())
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
        mouth = findViewById(R.id.dashboardSpinnerMouth);
        listView = findViewById(R.id.DashboardListView);
        // Link those objects with their
        // respective id's that
        // we have given in .XML file
        pieChart = findViewById(R.id.piechart);
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
