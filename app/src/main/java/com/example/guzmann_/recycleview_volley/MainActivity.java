package com.example.guzmann_.recycleview_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.guzmann_.adapter.StudentAdapter;
import com.example.guzmann_.models.students;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String dataUrl = "";
    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;
    private RecyclerView recyclerView;
    private List<students> studentParseData;
    private JSONObject jsonObject;
    private students student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataUrl = "https://jsoneditoronline.org/?id=3c3a5b4aa09c4a319221da3111df560c";
        requestQueue = Volley.newRequestQueue(this);
        recyclerView = findViewById(R.id.recyclerView);
        studentParseData = new ArrayList<>();
        loadDataToRecyclerView();
    }

    private void loadDataToRecyclerView() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, dataUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("students");

                    for(int i=0; i< jsonArray.length(); i++){
                        jsonObject = jsonArray.getJSONObject(i);
                        student.setImageProf(jsonObject.getString("image"));
                        student.setName(jsonObject.getString("firstname") + " " + jsonObject.getString("lastname"));
                        student.setAge(jsonObject.getInt("age"));
                        student.setCourse(jsonObject.getString("course"));
                        student.setRollNo(jsonObject.getInt("rollno"));

                        studentParseData.add(student);
                    }

                    setRecyclerView(studentParseData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getMessage();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void setRecyclerView(List<students> studentParseData) {
        StudentAdapter studentAdapter = new StudentAdapter(this, studentParseData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(studentAdapter);
    }
}
