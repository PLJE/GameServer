package com.example.gameserver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private ArrayList<Rank> mList;

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

    public void setList(ArrayList<Rank> list){
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
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
            tv_rank.setText(item.getRank() + "위");
            tv_name.setText(item.getRanknick());
        }
    }
}