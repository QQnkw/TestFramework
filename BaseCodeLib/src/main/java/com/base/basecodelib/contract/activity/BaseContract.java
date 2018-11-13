package com.base.basecodelib.contract.activity;

public interface BaseContract {
    interface Model {
    }

    interface View {
        void showToast(String text);
        void showLoadingDialog();
        void hideLoadingDialog();
    }

    interface Presenter {
    }
}
