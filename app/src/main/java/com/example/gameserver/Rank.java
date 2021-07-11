package com.example.gameserver;

public class Rank {
    String rank;
    String ranknick;
    public Rank(String rank, String name){
        this.rank = rank;
        this.ranknick = name;
    }

    public String getRank() {
        return rank;
    }

    public String getRanknick() {
        return ranknick;
    }
}
