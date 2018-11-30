package com.nkw.testframework.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.base.basecodelib.base.BaseActivity;
import com.nkw.testframework.R;
import com.nkw.testframework.contract.MainContract;
import com.nkw.testframework.presenter.MainPresenterImpl;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {
    @BindView(R.id.fl)
    FrameLayout mFl;
    @BindView(R.id.btn)
    Button      mBtn;
    private MainFragment mMainFragment;

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void showMainText(String text) {

    }

    @OnClick({R.id.btn, R.id.btn_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn:
                if (mMainFragment.isHidden()) {

                    getSupportFragmentManager().beginTransaction().show(mMainFragment).commitAllowingStateLoss();
                } else {

                    getSupportFragmentManager().beginTransaction().hide(mMainFragment).commitAllowingStateLoss();
                }
                break;
            case R.id.btn_send:
                startActivity(new Intent(this,SecondActivity.class));
                break;
        }

    }

    @Override
    protected void initView() {
        super.initView();
        mMainFragment = MainFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.fl, mMainFragment).commitAllowingStateLoss();
    }
}
