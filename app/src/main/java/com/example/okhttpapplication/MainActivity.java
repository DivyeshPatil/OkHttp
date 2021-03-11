package com.example.okhttpapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RecyclerView recyclerview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mTextViewResult = findViewById(R.id.text_view_result);

        recyclerview = findViewById(R.id.recyclerview);


        OkHttpClient client = new OkHttpClient();
        //String url = "https://jsonplaceholder.typicode.com/users/1/todos";
        String url = "https://breaking-bad-quotes.herokuapp.com/v1/quotes/5";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mTextViewResult.setText(myResponse);
//                            Toast.makeText(MainActivity.this, myResponse, Toast.LENGTH_SHORT).show();
                            Gson gson = new Gson();
                            ArrayList<Pojo> pojo = gson.fromJson(myResponse, new TypeToken<ArrayList<Pojo>>(){}.getType()); //getlist
                            recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));
                            CustomAdapter customAdapter = new CustomAdapter(pojo);
                            recyclerview.setAdapter(customAdapter);
                        }
                    });
                }
            }
        });
    }
}