package com.base.basecodelib.base;

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
        void showLoading();

        /**
         * 隐藏加载对话框
         */
        void hideLoading();

        /**
         * 检查token无效
         */
        void checkTokenInvalid();

        /**
         * 关闭当前页面
         *
         * @param code 结果码
         */
        <T> void finishViewSetResutlCode(int code, T t);

        /**
         * 关闭当前页面
         */
        void finishView();
    }

    interface Presenter<T extends BaseContract.View> {

        /**
         * 绑定view到presenter
         * @param t
         */
        void attachView(T t);

        /**
         * 和view同步销毁
         */
        void detachView();

        /**
         * 初始化
         */
        void init();

        /**
         * 销毁对象
         */
        void destroy();
    }
}