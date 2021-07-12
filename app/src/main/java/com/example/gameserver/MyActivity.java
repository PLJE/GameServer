package com.example.gameserver;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyActivity extends AppCompatActivity {

    private String nick, image, email , bestscore;
    private TextView nickname, tv_bestscore, rank;
    private ImageView Img, imageRank;
    private int rrank;

    private String BASE_URL = "http://192.249.18.168:80";
    private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;

    List<Rank> ranking = new ArrayList<Rank>(); //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        Intent intent = getIntent();
        nick = intent.getStringExtra("name");
        image = intent.getStringExtra("profileImg");
        email = intent.getStringExtra("email");

        //-----------------------------------------------------------------------
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitInterface = retrofit.create(RetrofitInterface.class);

        HashMap<String, String> map = new HashMap<>();

        map.put("nickName", nick);

        Call<BestScore> call = retrofitInterface.getBestScore(map);

        call.enqueue(new Callback<BestScore>() {
            @Override
            public void onResponse(Call<BestScore> call, Response<BestScore> response) {
                if (response.code() == 200) {
                    BestScore result = response.body();
                    bestscore = result.getScore();
                  
                    //Toast.makeText(MyActivity.this, "best" + bestscore, Toast.LENGTH_SHORT).show();
                    tv_bestscore = (TextView)findViewById(R.id.tv_bestscore);
                    tv_bestscore.setText("최고기록: " + bestscore + "점");

                } else if (response.code() == 404) { //로그인 실패

                }
            }
            @Override
            public void onFailure(Call<BestScore> call, Throwable t) {
            }
        });
        //---------------------------------------------------------------------------
        findViewById(R.id.bt_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback()
                {
                    @Override
                    public void onCompleteLogout()
                    {
                        Intent intent = new Intent(MyActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                        //Toast.makeText(MyActivity.this, "로그아웃합니다.", Toast.LENGTH_SHORT).show();
                    }
                });
//                Intent intent = new Intent(MyActivity.this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent); //
            }
        });

        imageRank = findViewById(R.id.imageRank);

        Call<List<Rank>> call2 = retrofitInterface.executerank();
        call2.enqueue(new Callback<List<Rank>>() {
            @Override
            public void onResponse(Call<List<Rank>> call2, Response<List<Rank>> response) {
                ranking = response.body(); // 데이터 받음 List<Rank> ranking

                MiniComparator comp = new MiniComparator();
                Collections.sort(ranking,comp);

                rank = (TextView)findViewById(R.id.myPageRank);
                rrank = getRank(nick)+1 ;
                rank.setText(rrank + "위");

                if (rrank == 1){
                    imageRank.setImageResource(R.drawable.gold);
                }
                else if (rrank == 2){
                    imageRank.setImageResource(R.drawable.silver);
                }
                else if (rrank == 3){
                    imageRank.setImageResource(R.drawable.bronze);
                }
                else if (rrank >= 4){
                    imageRank.setImageResource(R.drawable.crying);
                }
            }


            @Override
            public void onFailure(Call<List<Rank>> call2, Throwable t) {
                Toast.makeText(MyActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        nickname = (TextView)findViewById(R.id.tv_name);
        nickname.setText(nick);
        Img = findViewById(R.id.iv_image);


        if(image != null)
            Glide.with(this).load(image).into(Img);

    }

    public int getRank(String name){//
        for(int i=0;i<ranking.size();i++){
            if(name.equals(ranking.get(i).getNickName())){
                return i;
            }
        }
        return -1; //error
    }
}