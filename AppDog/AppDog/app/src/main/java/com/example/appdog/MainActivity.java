package com.example.appdog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final RecyclerView rvDog = findViewById(R.id.rv_dog);
        rvDog.setLayoutManager(new LinearLayoutManager(this));
        OkHttpClient client = new OkHttpClient();
        Moshi moshi = new Moshi.Builder().build();
        Type dogType = Types.newParameterizedType(List.class,DogBreed.class);
        final JsonAdapter<List<DogBreed>> jsonAdapter = moshi.adapter(dogType);
        Request request = new Request.Builder()
                .url("https://raw.githubusercontent.com/DevTides/DogsApi/master/dogs.json?fbclid=IwAR1KlzuSDhY3HiTv_s59-SIFCOU4H9LS7m-T8rQXJCy-nFgLP251JdnggeE")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull IOException e) {
                Log.e("Error","Network error");

            }

            @Override
            public void onResponse(@androidx.annotation.NonNull Call call, @androidx.annotation.NonNull Response response) throws IOException {
                String json = response.body().string();
                 List<DogBreed> dogBreeds = jsonAdapter.fromJson(json);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        rvDog.setAdapter(new DogAdapter(dogBreeds, MainActivity.this));
                    }
                });
            }
        });

    }
}