package com.example.gameserver;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Rank> mList;

    public MyRecyclerAdapter(List<Rank> ranks){ //rank list받음.
        mList = ranks;
    }
    @NonNull
    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list , parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int position) {
        holder.onBind(mList.get(position));
    }
    public void setList(List<Rank> list){
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        Log.d("NickName", mList.size() + " 개수 ");
        return  mList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_rank, tv_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_name = (TextView)itemView.findViewById(R.id.tv_rank_nick);
            tv_rank = (TextView)itemView.findViewById(R.id.tv_rank);
        }
        void onBind(Rank item){
            tv_rank.setText(item.getScore());
            tv_name.setText(item.getNickName());
        }
    }
}