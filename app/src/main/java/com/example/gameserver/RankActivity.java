package com.example.gameserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    MyRecyclerAdapter myRecyclerAdapter;
    ArrayList<Rank> test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        myRecyclerAdapter = new MyRecyclerAdapter();
        mRecyclerView.setAdapter(myRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        test = new ArrayList<>();
        test.add(new Rank("1","이주은"));
        test.add(new Rank("2","김영경"));
        test.add(new Rank("3","최정재"));
        myRecyclerAdapter.setList(test);
    }
}