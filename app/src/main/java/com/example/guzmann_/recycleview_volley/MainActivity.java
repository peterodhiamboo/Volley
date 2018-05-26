package com.example.guzmann_.recycleview_volley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private JsonObjectRequest jsonObjectRequest;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;

    private List<students> studentParseData;

    @BindString(R.string.json) String getDataUrl;

    private JSONObject jsonObject;
    private students student;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        requestQueue = Volley.newRequestQueue(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Fetching");
        progressDialog.setMessage("Wait while its fetching..");
        progressDialog.show();

        studentParseData = new ArrayList<>();
        loadDataToRecyclerView();
    }

    private void loadDataToRecyclerView() {
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, getDataUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("students");

                    for(int i=0; i< jsonArray.length(); i++){
                        jsonObject = jsonArray.getJSONObject(i);

                        String imageUrl = jsonObject.getString("image");
                        String name = jsonObject.getString("firstname") + " " + jsonObject.getString("lastname");
                        int age = jsonObject.getInt("age");
                        String course = jsonObject.getString("course");
                        int rollNo = jsonObject.getInt("rollno");

                        student = new students(imageUrl, name, age, rollNo, course);

                        studentParseData.add(student);
                    }

                    progressDialog.dismiss();

                    setRecyclerView(studentParseData);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.e("err", error.getMessage());
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void setRecyclerView(List<students> studentParseData) {
        StudentAdapter studentAdapter = new StudentAdapter(this, studentParseData);
        studentAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(studentAdapter);
    }
}
