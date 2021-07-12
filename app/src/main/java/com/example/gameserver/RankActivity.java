package com.example.gameserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class RankActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
//    ArrayList<Rank> test;
    List<Rank> ranking = new ArrayList<Rank>();












    private String BASE_URL = "http://192.249.18.168:80";
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        //--------------------------------------------------------------- server로 데이터 요청
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);


        Call<List<Rank>> call = retrofitInterface.executerank();
        call.enqueue(new Callback<List<Rank>>() {
            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>> response) {
                ranking = response.body(); // 데이터 받음 List<Rank> ranking
                myRecyclerAdapter.setList(ranking);
                //Log.d("NickName", ranking.get(0).getScore());
//                for (int i=0 ; i <5 ; i++){
//                    Toast.makeText(RankActivity.this, ranking.get(i).getScore(), Toast.LENGTH_SHORT).show();
//                }
//                mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
//                myRecyclerAdapter = new MyRecyclerAdapter(ranking);
//                mRecyclerView.setAdapter(myRecyclerAdapter);
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
                MiniComparator comp = new MiniComparator();
                Collections.sort(ranking,comp);
            }
            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Toast.makeText(RankActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //-------------------------------------------------------------------
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        myRecyclerAdapter = new MyRecyclerAdapter(ranking);
        mRecyclerView.setAdapter(myRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));
    }
}