package com.base.basecodelib.base;

public abstract class BasePresenterImpl<T extends BaseContract.View> implements BaseContract.Presenter<T> {
    protected T mView;

    @Override
    public void attachView(T t) {
        mView = t;
    }

    @Override
    public void detachView() {
        if (mView != null) {
            mView = null;
        }
    }
}
