package app.num.linechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.angle;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LineChart lineChart = (LineChart) findViewById(R.id.chart);
    String jsonInfromation="{\"submissions\":[{\"total_submissions\": 1, \"date_submitted\": \"05/25/2017\"}, {\"total_submissions\": 1, \"date_submitted\": \"05/26/2017\"}, {\"total_submissions\": 1, \"date_submitted\": \"06/01/2017\"}, {\"total_submissions\": 7, \"date_submitted\": \"06/14/2017\"}]}";

        jsonInfromation = jsonInfromation.replace("\\", "");
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonInfromation);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("submissions");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {

            String sSubmissionCount = null;
            try {

                sSubmissionCount = jsonArray.getJSONObject(i).getString("total_submissions");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            float subCount = Float.parseFloat(sSubmissionCount);
            Entry entry = new Entry(subCount, i);
            entries.add(entry);



        }






        LineDataSet dataset = new LineDataSet(entries, "");

        ArrayList<String> labels = new ArrayList<>();

        jsonInfromation="{\"submissions\":[{\"total_submissions\": 1, \"date_submitted\": \"05/25/2017\"}, {\"total_submissions\": 1, \"date_submitted\": \"05/26/2017\"}, {\"total_submissions\": 1, \"date_submitted\": \"06/01/2017\"}, {\"total_submissions\": 7, \"date_submitted\": \"06/14/2017\"}]}";
        jsonInfromation = jsonInfromation.replace("\\", "");
        try {
            jsonObject = new JSONObject(jsonInfromation);

            jsonArray = jsonObject.getJSONArray("submissions");

            for (int i = 0; i < jsonArray.length(); i++) {

                String sProjectName = jsonArray.getJSONObject(i).getString("date_submitted");
                labels.add(sProjectName);


            }


        } catch (JSONException e) {
            e.printStackTrace();
           }

        LineData data = new LineData(labels, dataset);

        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);

        lineChart.setData(data);

        lineChart.getXAxis().setDrawGridLines(false);


        dataset.setDrawCubic(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setDrawBorders(false);
        lineChart.setDescription(" ");

        lineChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        lineChart.getAxisRight().setDrawAxisLine(true);
        YAxis leftAxis = lineChart.getAxisRight();
        leftAxis.setEnabled(false);





        lineChart.animateX(3000);
    } }