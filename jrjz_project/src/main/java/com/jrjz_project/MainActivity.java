package com.jrjz_project;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.htlc.jrjz.jrjz_project.R;
import com.jrjz_project.home.fragment.HomeFragment;
import com.jrjz_project.mine.fragment.MineFragment;
import com.jrjz_project.order.fragment.OrderFragment;
import com.jrjz_project.campaign.fragment.CampaignFragment;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends FragmentActivity {
    private RadioGroup radioGroup1;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private HomeFragment homeFragment;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        radioGroup1=(RadioGroup)this.findViewById(R.id.ra1);
//
        //获取FragmentManager管理器
        fragmentManager=getSupportFragmentManager();
        transaction=fragmentManager.beginTransaction();
        //实例化具体HomeFragment，并且加以替换
        homeFragment=new HomeFragment();
        transaction.replace(R.id.fragment_content,homeFragment);
        transaction.commit();

        radioButton1=(RadioButton) findViewById(R.id.radio1);
        radioButton2=(RadioButton) findViewById(R.id.radio2);
        radioButton3=(RadioButton) findViewById(R.id.radio3);
        radioButton4=(RadioButton) findViewById(R.id.radio4);
        //沉浸式状态栏（改变状态栏的颜色使其与APP风格一体化 ）
        initSystemBar(this);

        //初始化底部导航栏的状态
        radioGroup1.check(R.id.radio1);
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        //首页
                        HomeFragment homeFragment=new HomeFragment();
                        transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_content,homeFragment);
                        transaction.commit();
                        break;
                    case R.id.radio2:
                        //订单
                        OrderFragment orderFragment=new OrderFragment();
                        transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_content,orderFragment);
                        transaction.commit();
                        break;
                    case R.id.radio3:
                        //活动
                        CampaignFragment privilegeFragment=new CampaignFragment();
                        transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_content,privilegeFragment);
                        transaction.commit();

                        break;
                    case R.id.radio4:
                        //个人中心
                        MineFragment mineFragment=new MineFragment();
                        transaction=fragmentManager.beginTransaction();
                        transaction.replace(R.id.fragment_content,mineFragment);
                        transaction.commit();

                        break;

                }
            }
        });
    }

    /**
     * 设置顶部沉浸式状态的颜色
     * @param activity
     */
    public static void initSystemBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(activity, true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        // 使用颜色资源
        tintManager.setStatusBarTintColor(R.color.status_color);
    }


    @TargetApi(19)
    private static void setTranslucentStatus(Activity activity, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}
