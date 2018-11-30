package com.nkw.testframework.ui;

import com.base.basecodelib.base.BaseContract;
import com.base.basecodelib.base.BaseFragment;
import com.nkw.testframework.R;
public class SecondFragment extends BaseFragment {

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_second;
    }

    @Override
    protected BaseContract.Presenter getPresenter() {
        return null;
    }

}
