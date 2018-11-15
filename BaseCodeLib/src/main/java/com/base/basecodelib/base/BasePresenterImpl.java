package com.base.basecodelib.base;

public abstract class BasePresenterImpl<T extends BaseContract.View> implements BaseContract.Presenter<T> {
    protected T mBaseView;

    @Override
    public void attachView(T t) {
        mBaseView = t;
    }

    @Override
    public void detachView() {
        if (mBaseView != null) {
            mBaseView = null;
        }
    }
}
