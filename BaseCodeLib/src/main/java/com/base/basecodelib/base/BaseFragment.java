package com.base.basecodelib.base;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment implements BaseContract.View {
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

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
