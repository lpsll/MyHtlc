package com.autodesk.easyhome.shejijia.order.fragment;


import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;

import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.widget.SlidingTabLayout;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.order.adapter.OrderTabListAdapter;
import com.autodesk.easyhome.shejijia.order.entity.OrderEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 订单页
 */
public class OrderFragment extends BaseFragment {
    public static final int TAB_A = 1;
    public static final int TAB_B = 2;
    public static final int TAB_C = 3;
    public static final int TAB_D = 4;
    public static final int TAB_E = 5;
    @Bind(R.id.order_tab)
    SlidingTabLayout mOrderTab;
    @Bind(R.id.order_content)
    ViewPager mOrderContent;
    private OrderTabListAdapter mAdapter;
    private List<Fragment> fragmentList;
    boolean login,tlt;
    String[] titles;
    private String tl;

    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {

        return R.layout.fragment_order;
    }


    //定义一个接口
    public interface OnOKClickListener  {
        public void onOKClick();
    }

    @Override
    public void initView(View view) {
        AppContext.set("inFlag",true);
//        LogUtils.e("onOKClickListener---initView---",""+onOKClickListener);
        OrderInsideFragment insideFragmentA = OrderInsideFragment.newInstance(TAB_A);
        OrderInsideFragment insideFragmentB = OrderInsideFragment.newInstance(TAB_B);
        OrderInsideFragment insideFragmentC = OrderInsideFragment.newInstance(TAB_C);
        OrderInsideFragment insideFragmentD = OrderInsideFragment.newInstance(TAB_D);
        OrderInsideFragment insideFragmentE = OrderInsideFragment.newInstance(TAB_E);

        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(insideFragmentA);
        fragmentList.add(insideFragmentB);
        fragmentList.add(insideFragmentC);
        fragmentList.add(insideFragmentD);
        fragmentList.add(insideFragmentE);
//        insideFragmentA.setInterface(new OnOKClickListener() {
//            @Override
//            public void onOKClick() {
//                LogUtils.e("onOKClick---getData----",""+AppContext.get("getData",""));
//                tl = AppContext.get("getData","");
//                if(tl.equals("1")){
//                    titles= getResources().getStringArray(R.array.order_tab_false);
//                    bindAdapter(titles);
//                }
//                else {
//                    titles= getResources().getStringArray(R.array.order_tab_true);
//                    bindAdapter(titles);
//                }
//            }
//        });
//        insideFragmentB.setInterface(new OnOKClickListener() {
//            @Override
//            public void onOKClick() {
//                LogUtils.e("onOKClick---getData----",""+AppContext.get("getData",""));
//                tl = AppContext.get("getData","");
//                if(tl.equals("1")){
////                    titles= getResources().getStringArray(R.array.order_tab_false);
////                    bindAdapter(titles);
//                }
//                else {
//                    AppContext.set("inFlag",false);
//                    titles= getResources().getStringArray(R.array.order_tab_true);
//                    bindAdapter(titles);
//                }
//            }
//        });
//        insideFragmentC.setInterface(new OnOKClickListener() {
//            @Override
//            public void onOKClick() {
//                LogUtils.e("onOKClick---getData----",""+AppContext.get("getData",""));
//                tl = AppContext.get("getData","");
//                if(tl.equals("1")){
//                    titles= getResources().getStringArray(R.array.order_tab_false);
//                    bindAdapter(titles);
//                }
//                else {
//                    titles= getResources().getStringArray(R.array.order_tab_true);
//                    bindAdapter(titles);
//                }
//            }
//        });
//        insideFragmentD.setInterface(new OnOKClickListener() {
//            @Override
//            public void onOKClick() {
//                LogUtils.e("onOKClick---getData----",""+AppContext.get("getData",""));
//                tl = AppContext.get("getData","");
//                if(tl.equals("1")){
//                    titles= getResources().getStringArray(R.array.order_tab_false);
//                    bindAdapter(titles);
//                }
//                else {
//                    titles= getResources().getStringArray(R.array.order_tab_true);
//                    bindAdapter(titles);
//                }
//            }
//        });
//        insideFragmentE.setInterface(new OnOKClickListener() {
//            @Override
//            public void onOKClick() {
//                LogUtils.e("onOKClick---getData----",""+AppContext.get("getData",""));
//                tl = AppContext.get("getData","");
//                if(tl.equals("1")){
//                    titles= getResources().getStringArray(R.array.order_tab_false);
//                    bindAdapter(titles);
//                }
//                else {
//                    titles= getResources().getStringArray(R.array.order_tab_true);
//                    bindAdapter(titles);
//                }
//            }
//        });

//        fragmentList = new ArrayList<Fragment>();
//        fragmentList.add(OrderInsideFragment.newInstance(TAB_A));
//        fragmentList.add(OrderInsideFragment.newInstance(TAB_B));
//        fragmentList.add(OrderInsideFragment.newInstance(TAB_C));
//        fragmentList.add(OrderInsideFragment.newInstance(TAB_D));
//        fragmentList.add(OrderInsideFragment.newInstance(TAB_E));



        LogUtils.e("getData---",""+AppContext.get("getData",""));
        titles= getResources().getStringArray(R.array.order_tab_false);
        bindAdapter(titles);


    }

    private void bindAdapter(String[] titles) {

        mAdapter = new OrderTabListAdapter(getChildFragmentManager(), this, titles, fragmentList);

        mOrderContent.setAdapter(mAdapter);
        mOrderContent.setOffscreenPageLimit(fragmentList.size());
        mOrderTab.setSelectedIndicatorColors(getResources().getColor(R.color.navi));
        mOrderTab.setDistributeEvenly(true);
        mOrderTab.setViewPager(mOrderContent);
        login = AppContext.get("IS_LOGIN",false);
        if(login){
        }else {
            DialogUtils.confirm(getActivity(), "您尚未登录，是否去登录？", listener);

        }
    }

    DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            HomeUiGoto.gotoLogin(getActivity());
        }
    };

    @Override
    public void initData() {
    }


    public void onEventMainThread(OrderEvent event) {
        String msg = event.getMsg();
        LogUtils.e("msg---", "" + msg);
        if (TextUtils.isEmpty(msg)) {

        } else {
            if (msg.equals("1")) {
                LogUtils.e("1----", "1");
//                titles = getResources().getStringArray(R.array.order_tab_false);
//                bindAdapter(titles);

            }
            else if(msg.equals("支付成功")){
                DialogUtils.showPrompt(getActivity(), "提示","支付成功！", "知道了");
            }
            else {
                AppContext.set("inFlag",false);
                LogUtils.e("fei--1----", "fei--1");
                titles = getResources().getStringArray(R.array.order_tab_true);
                bindAdapter(titles);
            }
        }
    }
}
