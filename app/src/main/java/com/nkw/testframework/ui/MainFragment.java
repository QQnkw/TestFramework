package com.nkw.testframework.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.basecodelib.base.BaseFragment;
import com.nkw.testframework.R;
import com.nkw.testframework.contract.MainContract;
import com.nkw.testframework.presenter.MainPresenterImpl;

public class MainFragment extends BaseFragment<MainContract.Presenter> implements MainContract.View{

    private final String TAG = OneFragment.class.getSimpleName();
    //截取 Fragment.toString() 方法中的标识数字
    private final String ID = this.toString().substring(this.toString().indexOf("{") + 1, this.toString().length() - 1);

    /**
     * Fragment 关联到 Activity 时回调
     * 此时 Activity 已经与 Fragment 关联，通过 Context 向下转型，就可以与 Activity 通信
     * 当然也可以使用 getActivity(),前提是这个 fragment 已经和宿主的 activity 关联，并且没有脱离
     * onAttach 只调用一次。
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        //由于 onCreate 是在 onAttach 后执行，故此时 mTitle 为空
        Log.e(TAG, "Fragment id = " + ID + ", is onAttach.");
        super.onAttach(context);
    }

    /**
     * 系统创建 Fragment 的时候回调，介于 onAttach() 和 onCreateView() 之间
     * 一般用于初始化一些数据
     * 值得注意的是，此时 Activity 还在创建中，因此不能在执行一些跟 Activity UI 相关的操作
     * 否则，会出现一些难以预料的问题，比如：NullPointException
     * 如果要对 Activity 上的 UI 进行操作，建议在 onActivityCreated() 中操作
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment id = " + ID + ",is onCreate.");
        super.onCreate(savedInstanceState);
    }

    /**
     * 创建 Fragment 需要显示的 View，默认返回 null。
     * 当返回的 View 不为 null 时，View 被销毁时会调用 onDestroyView()
     * 此处应该只进行布局的初始化，而不应该执行耗时操作，如网络请求、数据库读取
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment id = " + ID + ",is onCreateView.");
        return super.onCreateView(inflater,container,savedInstanceState);
    }

    /**
     * 该方法在 onCreateView() 之后会被立即执行
     * 此时可以对 View 对象进行赋值，当然在 onCreateView() 中也可以执行
     *
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment id = " + ID + ",is onViewCreated.");
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * 当 Activity 执行完 onCreate() 方法后调用
     * 此时可以执行与 Activity 相关的 UI 操作
     *
     */
   /* @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.e(TAG, "Fragment id = " + ID + ",is onActivityCreated.");
        super.onActivityCreated(savedInstanceState);
    }
    @Override
    public void onStart() {
        Log.e(TAG, "Fragment id = " + ID + ",is onStart.");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.e(TAG, "Fragment id = " + ID + ",is onResume.");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.e(TAG, "Fragment id = " + ID + ",is onPause.");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.e(TAG, "Fragment id = " + ID + ",is onStop.");
        super.onStop();
    }*/

    /**
     * 表示销毁 Fragment 相关联的 UI 布局，清除所有跟视图相关的资源。
     * 不一定在 Activity 的 onDestroy() 方法中调用
     * 如：Fragment 与 ViewPager 结合使用时
     *
     */
    @Override
    public void onDestroyView() {
        Log.e(TAG, "Fragment id = " + ID + ",is onDestroyView.");
        super.onDestroyView();
    }

    /**
     * 销毁 Fragment 对象的时候调用，一般是调用 Activity 的 onDestroy() 的时候执行
     */
    @Override
    public void onDestroy() {
        //当调用 Activity 的 onDestroy() 时调用
        Log.e(TAG, "Fragment id = " + ID + ",is onDestroy.");
        super.onDestroy();
    }

    /**
     * 解除 Fragment 与 Activity 的关联
     */
    @Override
    public void onDetach() {
        Log.e(TAG, "Fragment id = " + ID + ",is onDetach.");
        super.onDetach();
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.main_fragment;
    }

    @Override
    protected MainContract.Presenter getPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onFragmentFirstVisible() {
        super.onFragmentFirstVisible();
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
    }

    @Override
    public void showMainText(String text) {

    }

}
