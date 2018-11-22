package com.base.basecodelib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 * Fragment基类，封装了懒加载的实现
 * 1、Viewpager + Fragment情况下，fragment的生命周期因Viewpager的缓存机制而失去了具体意义
 * 该抽象类自定义新的回调方法，当fragment可见状态改变时会触发的回调方法，和 Fragment 第一次可见时会回调的方法
 *
 * @see #onFragmentVisibleChange(boolean)
 * @see #onFragmentFirstVisible()

 *注意事项

 *1.如果想要让 fragment 的布局复用成功，需要重写 viewpager 的适配器里的 destroyItem() 方法，将 super 去掉，也就是不销毁 view。

 *2.如果出现切换回来或不相邻的Tab切换时导致空白界面的问题，解决方法：在 onCreateView中复用布局 + ViewPager 的适配器中复写 destroyItem() 方法去掉
 * super。*/
public abstract class BaseFragment<T extends BaseContract.Presenter> extends Fragment implements BaseContract.View {
    private Unbinder     mUnbinder;
    public  BaseActivity mActivity;

    private   boolean isFragmentVisible;
    private   boolean isReuseView;
    private   boolean isFirstVisible;
    protected View    rootView;
    protected        T                  mPresenter;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (BaseActivity) context;

        //        LogUtils.d(this.getClass(), "onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
        //        LogUtils.d(this.getClass(), "onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view;
        if (rootView == null) {
            view = LayoutInflater.from(container.getContext()).inflate(getFragmentLayoutId(),
                    container, false);
        } else {
            view = rootView;
        }
        mUnbinder = ButterKnife.bind(this, view);
        //        LogUtils.d(this.getClass(), "onCreateView");
        return view;
    }

    /**
     * 获取布局ID
     *
     * @return Activity布局ID
     */
    protected abstract int getFragmentLayoutId();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        /*如果setUserVisibleHint()在rootView创建前调用时，那么
        就等到rootView创建完后才回调onFragmentVisibleChange(true)
        保证onFragmentVisibleChange()的回调发生在rootView创建完成之后，以便支持ui操作*/
        //        LogUtils.d(this.getClass(), "onViewCreated");
        if (rootView == null) {
            rootView = view;
            if (getUserVisibleHint()) {
                if (isFirstVisible) {
                    //                    LogUtils.d(this.getClass(), "getUserVisibleHint");
                    onFragmentFirstVisible();
                    isFirstVisible = false;
                }
                onFragmentVisibleChange(true);
                isFragmentVisible = true;
            }
        }
        super.onViewCreated(isReuseView ? rootView : view, savedInstanceState);
        initView(rootView);
        mPresenter = getPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mPresenter.init();
        }
    }


    @Override
    public void onDestroy() {
        initVariable();
        if (mPresenter != null) {
            mPresenter.destroy();
            mPresenter.detachView();
            mPresenter = null;
        }
        mUnbinder.unbind();
        super.onDestroy();
    }

    protected abstract T getPresenter();

    /**
     * 初始化变量
     */
    private void initVariable() {
        isFirstVisible = true;
        isFragmentVisible = false;
        rootView = null;
        isReuseView = true;
    }


    /*---------------------------------- -------------------------------------*/

    /**
     * setUserVisibleHint()只会在与ViewPager配合使用的时候才调用
     * setUserVisibleHint()在Fragment创建时会先被调用一次，传入isVisibleToUser = false
     * 如果当前Fragment可见，那么setUserVisibleHint()会再次被调用一次，传入isVisibleToUser = true
     * 如果Fragment从可见->不可见，那么setUserVisibleHint()也会被调用，传入isVisibleToUser = false
     * 总结：setUserVisibleHint()除了Fragment的可见状态发生变化时会被回调外，在new Fragment()时也会被回调
     * 如果我们需要在 Fragment 可见与不可见时干点事，用这个的话就会有多余的回调了，那么就需要重新封装一个
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //        LogUtils.d(this.getClass(), "setUserVisibleHint");
        //setUserVisibleHint()有可能在fragment的生命周期外被调用
        if (rootView == null) {
            return;
        }
        if (isFirstVisible && isVisibleToUser) {
            onFragmentFirstVisible();
            isFirstVisible = false;
        }
        if (isVisibleToUser) {
            onFragmentVisibleChange(true);
            isFragmentVisible = true;
            return;
        }
        if (isFragmentVisible) {
            isFragmentVisible = false;
            onFragmentVisibleChange(false);
        }
    }

    /**
     * 设置是否使用 view 的复用，默认开启
     * view 的复用是指，ViewPager 在销毁和重建 Fragment 时会不断调用 onCreateView() -> onDestroyView()
     * 之间的生命函数，这样可能会出现重复创建 view 的情况，导致界面上显示多个相同的 Fragment
     * view 的复用其实就是指保存第一次创建的 view，后面再 onCreateView() 时直接返回第一次创建的 view
     *
     * @param isReuse
     */
    protected void reuseView(boolean isReuse) {
        isReuseView = isReuse;
    }

    /**
     * 去除setUserVisibleHint()多余的回调场景，保证只有当fragment可见状态发生变化时才回调
     * 回调时机在view创建完后，所以支持ui操作，解决在setUserVisibleHint()里进行ui操作有可能报null异常的问题
     * 可在该回调方法里进行一些ui显示与隐藏，比如加载框的显示和隐藏
     *
     * @param isVisible true 不可见 -> 可见 * false 可见 -> 不可见
     */
    protected void onFragmentVisibleChange(boolean isVisible) {
    }

    /**
     * 在fragment首次可见时回调，可在这里进行加载数据，保证只在第一次打开Fragment时才会加载数据，
     * 这样就可以防止每次进入都重复加载数据
     * 该方法会在 onFragmentVisibleChange() 之前调用，所以第一次打开时，可以用一个全局变量表示数据下载状态，
     * 然后在该方法内将状态设置为下载状态，接着去执行下载的任务
     * 最后在 onFragmentVisibleChange() 里根据数据下载状态来控制下载进度ui控件的显示与隐藏
     */
    protected void onFragmentFirstVisible() {
    }

    protected boolean isFragmentVisible() {
        return isFragmentVisible;
    }


    /**
     * 初始化控件
     */
    protected abstract void initView(View view);

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
    public void finishView() {

    }
}