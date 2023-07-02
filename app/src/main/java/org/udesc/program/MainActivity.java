package org.udesc.program;

// Import the required libraries
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

public class MainActivity
        extends AppCompatActivity {

    // Create the object of TextView
    // and PieChart class
    public PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard2);



        // Link those objects with their
        // respective id's that
        // we have given in .XML file

        pieChart = findViewById(R.id.piechart);

        // Creating a method setData()
        // to set the text in text view and pie chart
        setData();
    }

    private void setData()
    {

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
}
