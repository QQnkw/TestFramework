package com.nkw.testframework.presenter;

import android.os.Handler;

import com.base.basecodelib.base.BasePresenterImpl;
import com.nkw.testframework.contract.MainContract;


public class MainPresenterImpl extends BasePresenterImpl<MainContract.View> implements MainContract.Presenter {

    @Override
    public void init() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mView.showMainText("adjfkhsdjkfhdsdjkhfjksddhffkjdfss");
            }
        },3000);
    }

    @Override
    public void destroy() {

    }
}
