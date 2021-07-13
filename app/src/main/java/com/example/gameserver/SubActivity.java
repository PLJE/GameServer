package com.example.gameserver;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SubActivity extends AppCompatActivity {

    private Button game, rank, mypage;
    private String nick, image, email;

    public static Context mContext;

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

        mContext = this;

        findViewById(R.id.bt_game).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, GameMainActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.bt_rank).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubActivity.this, RankActivity.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
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
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });
        findViewById(R.id.bt_chat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View view = getLayoutInflater().inflate(R.layout.enter_chat, null); //로그인 다이얼로그 뷰로 띄우기

                AlertDialog.Builder builder = new AlertDialog.Builder(SubActivity.this);

                builder.setView(view).show();
                final EditText nickname = view.findViewById(R.id.nickname);
                view.findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SubActivity.this, ChatActivity.class);
                        intent.putExtra("name", nickname.getText().toString());
                        startActivity(intent);
                    }
                });
            }
        });
    }
    public String getNick() {
        return nick;
    }
}