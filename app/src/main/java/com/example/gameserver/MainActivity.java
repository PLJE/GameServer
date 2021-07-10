package com.example.gameserver;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ISessionCallback mSessionCallback;
    private String BASE_URL = "http://192.249.18.168:80";
    //private RetrofitInterface retrofitInterface;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //retrofitInterface = retrofit.create(RetrofitInterface.class);

        mSessionCallback = new ISessionCallback()
        {
            @Override
            public void onSessionOpened()
            {
                // 로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback()
                {
                    @Override
                    public void onFailure(ErrorResult errorResult)
                    {
                        // 로그인 실패
                        Toast.makeText(MainActivity.this, "로그인 도중에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult)
                    {
                        // 세션이 닫힘..
                        Toast.makeText(MainActivity.this, "세션이 닫혔습니다.. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result)
                    {
                        //----------------------------------------------------------------------------------
//                        HashMap<String,String> map = new HashMap<>();
//                        Call <Login> call = retrofitInterface.executeLogin(map);
//
//                        map.put("nickName" , result.getKakaoAccount().getProfile().getNickname());
//                        map.put("email" , result.getKakaoAccount().getEmail());
//
//                        call.enqueue(new Callback<Login>() {
//                            @Override
//                            public void onResponse(Call<Login> call, Response<Login> response) {
//
//                            }
//
//                            @Override
//                            public void onFailure(Call<Login> call, Throwable t) {
//                                //Toast.makeText(MainActivity.this, "wrong credentials", Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        //-----------------------------------------------------------------------------------
                        // 로그인 성공
                        Intent intent = new Intent(MainActivity.this, SubActivity.class);
                        intent.putExtra("name", result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra("profileImg", result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra("email", result.getKakaoAccount().getEmail());


                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(intent);
                    }
                });
            }
            @Override
            public void onSessionOpenFailed(KakaoException exception)
            {
                Toast.makeText(MainActivity.this, "onSessionOpenFailed", Toast.LENGTH_SHORT).show();
            }
        };
        Session.getCurrentSession().addCallback(mSessionCallback);
        Session.getCurrentSession().checkAndImplicitOpen();
//        getAppKeyHash();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}