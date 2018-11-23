package com.base.basecodelib.base;

public interface BaseContract {
    interface Model {
    }

    interface View {

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

    }

    interface Presenter <T>{

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
