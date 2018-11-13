package com.base.basecodelib.contract.activity;

public interface BaseContract {
    interface Model {
    }

    interface View {
        /**
         * 吐司
         *
         * @param text 显示的文本
         */
        void showToast(String text);

        /**
         * 显示加载对话框
         */
        void showLoadingDialog();

        /**
         * 隐藏加载对话框
         */
        void hideLoadingDialog();

        /**
         * 检查token无效
         */
        void checkTokenInvalid();

        /**
         * 关闭当前页面
         *
         * @param code 结果码
         */
        <T> void finishViewSetResutlCode(int code,T t);

        /**
         * 关闭当前页面
         */
        void finishView();
    }

    interface Presenter {
    }
}
