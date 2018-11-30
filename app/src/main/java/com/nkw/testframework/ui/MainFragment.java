package com.nkw.testframework.ui;

import android.widget.TextView;

import com.base.basecodelib.base.BaseFragment;
import com.nkw.testframework.R;
import com.nkw.testframework.contract.MainContract;
import com.nkw.testframework.presenter.MainPresenterImpl;

import butterknife.BindView;

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View {

    @BindView(R.id.tv)
    TextView mTv;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    public void showMainText(String text) {
        mTv.setText(text);
    }
}
