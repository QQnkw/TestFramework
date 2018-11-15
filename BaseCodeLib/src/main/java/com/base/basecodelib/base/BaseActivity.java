package com.base.basecodelib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.base.basecodelib.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseActivity<T extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View {
    public static List<BaseActivity> sBaseActivityList = new ArrayList<>();
    protected        BaseActivity       mBaseActivity;
    private       T                  mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sBaseActivityList.add(this);
        mBaseActivity = this;
        setLayoutViewPre(savedInstanceState);
        setContentView(R.layout.activity_base);
        LayoutInflater.from(this).inflate(getLayoutId(), (FrameLayout) findViewById(R.id.fl_root), true);
        ButterKnife.bind(this);
        initView();
        mBasePresenter = getPresenter();
        if (mBasePresenter != null) {
            mBasePresenter.attachView(this);
            mBasePresenter.init();
        }
    }

    protected abstract T getPresenter();

    /**
     * 初始化控件
     */
    protected void initView() {

    }

    protected abstract int getLayoutId();

    private void setLayoutViewPre(Bundle savedInstanceState) {

    }

    @Override
    public void showToast(String text) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

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

    @Override
    protected void onDestroy() {
        sBaseActivityList.remove(this);
        if (mBasePresenter != null) {
            mBasePresenter.destroy();
            mBasePresenter.detachView();
            mBasePresenter = null;
        }
        super.onDestroy();
    }
}
