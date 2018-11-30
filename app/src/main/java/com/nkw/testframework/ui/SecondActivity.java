package com.nkw.testframework.ui;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.base.basecodelib.base.BaseActivity;
import com.base.basecodelib.base.BaseContract;
import com.nkw.testframework.R;

import java.util.Random;

import butterknife.BindView;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.vp)
    ViewPager mVp;
    @BindView(R.id.btn)
    Button    mBtn;

    @Override
    protected BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        super.initView();
        MyFragmentAdapter myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager());
        mVp.setAdapter(myFragmentAdapter);
        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVp.setCurrentItem(new Random().nextInt(5));
            }
        });
    }

    private static class MyFragmentAdapter extends FragmentPagerAdapter {

        public MyFragmentAdapter(FragmentManager supportFragmentManager) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int i) {
            /*if (i == 0) {
                return OneFragment.newInstance(i);
            } else {
                return SecondFragment.newInstance();
            }*/
            return OneFragment.newInstance(i);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }
    }
}
