package com.hfad.myaddressbook;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfad.myaddressbook.adapter.MyAdapter;
import com.hfad.myaddressbook.model.BaseModel;
import com.hfad.myaddressbook.model.Employees;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
//    EditText firstName, lastName, city, state;
//    EditText phone, email, picture;
//    Button insert, update, delete, view;
//    DatabaseHandler db;
    private Button getData;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        Methods methods = RetroClient.getRetrofit().create(Methods.class);
        Call<BaseModel> call = methods.getAllData();

        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                Log.e(TAG, "onResponse: code: " + response.code());
                ArrayList<Employees> employee = response.body().getEmployees();

                MyAdapter myAdapter = new MyAdapter(employee);
                recyclerView.setAdapter(myAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}