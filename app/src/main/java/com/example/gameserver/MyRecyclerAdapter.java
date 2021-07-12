package com.example.gameserver;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Rank> mList;
    private int i = 1;

    public MyRecyclerAdapter(List<Rank> ranks){ //rank list받음.
        mList = ranks;
    }
    public MyRecyclerAdapter(){ //rank list받음.

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
        i+=1;
    }
    public void setList(List<Rank> list){
        this.mList = list;
        i = 1;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        //Log.d("NickName", mList.size() + " 개수 ");
        return  mList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_score, tv_name , tv_rank;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_rank = (TextView)itemView.findViewById(R.id.tv_rank);
            tv_name = (TextView)itemView.findViewById(R.id.tv_nick);
            tv_score = (TextView)itemView.findViewById(R.id.tv_score);
        }
        void onBind(Rank item){
            tv_rank.setText(i+"위");
            tv_score.setText(item.getScore().trim()+"점");
            tv_name.setText(item.getNickName().trim());
        }
    }

}