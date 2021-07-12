package com.example.gameserver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class  GameActivity extends AppCompatActivity {

    private GameView gameView;

//    private String BASE_URL = "http://192.249.18.168:80";
//    private RetrofitInterface retrofitInterface;
//    private Retrofit retrofit; //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        gameView = new GameView(this, point.x, point.y);
        setContentView(gameView);

        //-----------------------------------------------------------
//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        retrofitInterface = retrofit.create(RetrofitInterface.class);
//
//        HashMap<String, String> map = new HashMap<>();
//        map.put("score" , String.valueOf(gameView.returnscore()));
//        map.put("nickName" , ((SubActivity)SubActivity.mContext).getNick());
//
//        Call<Void> call = retrofitInterface.executeSendScore(map);
//        call.enqueue(new Callback<Void>() {
//            @Override
//            public void onResponse(Call<Void> call, Response<Void> response) {
//
//            }
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//
//            }
//        });

        //---------------------------------------------------------
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}