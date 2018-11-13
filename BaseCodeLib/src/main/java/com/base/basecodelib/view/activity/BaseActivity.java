package com.base.basecodelib.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.base.basecodelib.R;
import com.base.basecodelib.contract.activity.BaseContract;

public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View {

    private BaseActivity mBaseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBaseActivity = this;
        setContentViewPre(savedInstanceState);
        setContentView(R.layout.activity_base);
        LayoutInflater.from(this).inflate(getLayoutId(),(FrameLayout)findViewById(R.id.fl_root),true);
    }

    protected abstract int getLayoutId();

    private void setContentViewPre(Bundle savedInstanceState) {

    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void hideLoadingDialog() {

    }

    @Override
    public void checkTokenInvalid() {

    }

    @Override
    public <T> void finishViewSetResutlCode(int code, T t) {

    }

    @Override
    public void finishView() {
        finish();
    }
}
