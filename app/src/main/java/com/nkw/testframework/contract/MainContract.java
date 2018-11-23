package com.nkw.testframework.contract;

import com.base.basecodelib.base.BaseContract;

public interface MainContract {

    interface View extends BaseContract.View{
        void showMainText(String text);
    }

    interface Presenter extends BaseContract.Presenter<MainContract.View> {
    }
}
