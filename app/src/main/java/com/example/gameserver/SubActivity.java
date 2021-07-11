package com.example.gameserver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SubActivity extends AppCompatActivity {

    private Button game, rank, mypage;
    private String nick, image, email;

    public SubActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        nick = intent.getStringExtra("name");
        image = intent.getStringExtra("profileImg");
        email = intent.getStringExtra("email");

        findViewById(R.id.bt_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, GameMainActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_rank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, RankActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.bt_mypage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, MyActivity.class);
                intent.putExtra("name", nick);
                intent.putExtra("profileImg", image);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
    }
}