package com.dukang.wanandroid;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dukang.wanandroid.retrofit.HttpCallBack;
import com.dukang.wanandroid.retrofit.RequestManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements HttpCallBack {

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = findViewById(R.id.btnLogin);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        retrofitRequest();
    }

    private void retrofitRequest() {

        LoginService loginServiceImp = RequestManager.creatImpl(LoginService.class);
        Call<ResponseBody> call = loginServiceImp.login("wangdukang22", "123456");
        RequestManager.requestEnque(call, this);

    }

    @Override
    public void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response) {
        Log.e("json_body:", response.toString());
        Log.e("json_body:", response.code() + "");
        try {
            Log.e("json_body:", response.body().string() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(Call<ResponseBody> call, Throwable t) {
        Log.e("failed", t.getMessage());
    }


}

