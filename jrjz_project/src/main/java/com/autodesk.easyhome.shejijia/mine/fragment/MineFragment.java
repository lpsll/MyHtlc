package com.autodesk.easyhome.shejijia.mine.fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.campaign.activity.TopUpActivity;
import com.autodesk.easyhome.shejijia.common.base.BaseFragment;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.activity.SelectAddressActivity;
import com.autodesk.easyhome.shejijia.mine.MineUiGoto;
import com.autodesk.easyhome.shejijia.mine.activity.FeedBackActivity;
import com.autodesk.easyhome.shejijia.mine.activity.H5Activity;
import com.autodesk.easyhome.shejijia.mine.activity.MineCouponActivity;
import com.autodesk.easyhome.shejijia.mine.activity.MineOrderActivity;
import com.autodesk.easyhome.shejijia.mine.entity.UserDetailResult;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * 我的页
 */
public class MineFragment extends BaseFragment {

    @Bind(R.id.rl_mine_changephone)
    RelativeLayout rlMineChangephone;
    @Bind(R.id.ll_mine_chongzhi)
    LinearLayout llMineChongzhi;
    @Bind(R.id.ll_mine_more_setting)
    LinearLayout llMineMoreSetting;
    @Bind(R.id.ll_mine_address)
    LinearLayout llMineAddress;
    @Bind(R.id.ll_mine_myorder)
    LinearLayout llMineMyorder;
    @Bind(R.id.ll_mine_coupon)
    LinearLayout llMineCoupon;
    @Bind(R.id.ll_mine_feedback)
    LinearLayout llMineFeedback;
    @Bind(R.id.tv_mine_phone)
    TextView tvMinePhone;
    @Bind(R.id.tv_mine_point)
    TextView tvMinePoint;
    @Bind(R.id.tv_mine_balance)
    TextView tvMineBalance;
    @Bind(R.id.ll_mine_share)
    LinearLayout llMineShare;
    PopupWindow popWindow;
    @Bind(R.id.ll_about_juran)
    LinearLayout llAboutJuran;
    private LinearLayout weixin, friend, weibo, qq, qqZon;
    private TextView text;
    private String type;

    @Override
    public void initView(View view) {
        if (AppContext.get("IS_LOGIN", false)) {
            LogUtils.e("true---", "true");
            //登录状态时就设置为用户手机号
            tvMinePhone.setText(AppContext.get("uid", ""));
            tvMinePoint.setVisibility(View.VISIBLE);
            tvMineBalance.setVisibility(View.VISIBLE);
            getUserDetail();

        } else {
            LogUtils.e("false---", "false");
            tvMinePhone.setText("请登录");
            tvMinePoint.setVisibility(View.GONE);
            tvMineBalance.setVisibility(View.GONE);

        }

        map = new HashMap<String, Object>();

    }

    /**
     * 获取用户信息
     * 用户详细信息请求接口
     * <p/>
     * accessToken:访问授权码
     * <p/>
     * uid:用户ID，默认为手机号码
     * <p/>
     * timestamp:当前时间戳
     * <p/>
     * random:随机数
     * <p/>
     * sign:签名【生成规则uid+timestamp+random 后md5加密串】
     */
    private void getUserDetail() {

        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();

        BaseDTO baseDTO = new BaseDTO();
        baseDTO.setAccessToken(AppContext.get("accessToken", ""));
        baseDTO.setUid(AppContext.get("uid", ""));
        baseDTO.setTimestamp(time);
        baseDTO.setRandom(random);
        baseDTO.setSign(AppContext.get("uid", "") + time + random);

        CommonApiClient.getUserDetail(getActivity(), baseDTO, new CallBack<UserDetailResult>() {
            @Override
            public void onSuccess(UserDetailResult result) {
                LogUtils.e("获取用户信息========" + result.getMsg());
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取用户信息成功=====" + result.getData().toString());

                    double points = result.getData().getPoints();
                    double balance = result.getData().getBalance();

                    tvMinePoint.setText(points + "分");
                    tvMineBalance.setText(balance + "元");
                    LogUtils.d("用户积分=======" + points);
                    LogUtils.d("用户余额=======" + balance);
                    AppContext.set("balance", String.valueOf(balance));

                }
            }
        });


    }

    @Override
    public void initData() {

    }


    @Override
    protected void retry() {

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_mine;
    }


    public static final int TOPUP_REQUEST = 0x1108;

    @OnClick({R.id.ll_about_juran, R.id.rl_mine_changephone, R.id.ll_mine_chongzhi, R.id.ll_mine_more_setting, R.id.ll_mine_share, R.id.ll_mine_myorder, R.id.ll_mine_address, R.id.ll_mine_coupon, R.id.ll_mine_feedback})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_changephone:
                //跳转到切换手机号页面
                if (AppContext.get("IS_LOGIN", false)) {
                    MineUiGoto.gotoChangePhone(getActivity());
//                    getContext().startActivity(new Intent(getContext(), ChangePhoneActivity.class));
                } else {
                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }
                break;
            case R.id.ll_mine_chongzhi:
                //跳转到充值页
                if (AppContext.get("IS_LOGIN", false)) {
                    Intent intent = new Intent(new Intent(getContext(), TopUpActivity.class));
                    intent.putExtra("TypeForTopUp", "WriteForUser");
                    getActivity().startActivityForResult(intent, TOPUP_REQUEST);
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();
                }
                break;
            case R.id.ll_mine_more_setting:
                //跳转到更多设置页
                if (AppContext.get("IS_LOGIN", false)) {
                    MineUiGoto.gotoSetting(getActivity());
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();
                }
                break;
            case R.id.ll_mine_address:
                if (AppContext.get("IS_LOGIN", false)) {
                    getContext().startActivity(new Intent(getContext(), SelectAddressActivity.class));
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();

                }
                break;
            case R.id.ll_mine_myorder:
                if (AppContext.get("IS_LOGIN", false)) {
                    getContext().startActivity(new Intent(getContext(), MineOrderActivity.class));
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();

                    HomeUiGoto.gotoLoginForPwd(getActivity());
                }

                break;
            case R.id.ll_mine_coupon:
                //跳转到我的优惠券页面
                if (AppContext.get("IS_LOGIN", false)) {
                    getContext().startActivity(new Intent(getContext(), MineCouponActivity.class));
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();
                }

                break;
            case R.id.ll_mine_feedback:
                if (AppContext.get("IS_LOGIN", false)) {
                    getContext().startActivity(new Intent(getContext(), FeedBackActivity.class));
                } else {
                    new AlertDialog.Builder(getContext()).setTitle("温馨提示").setMessage("您尚未登录，是否去登录？").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            HomeUiGoto.gotoLoginForPwd(getActivity());
                        }
                    }).setNegativeButton("取消", null).show();
                }
                break;

            case R.id.ll_about_juran:
                //跳转到关于居然h5
                Intent intent = new Intent(getContext(), H5Activity.class);
                intent.putExtra("url", AppConfig.ABOUT_JURAN);
                intent.putExtra("title", "关于居然家政");
                getContext().startActivity(intent);

                break;

            case R.id.ll_mine_share:
//                showPopShare();
                showShare();
                break;
//            case R.id.share_weixin:
//                type = "1";
//                showShare();
//                break;
//            case R.id.share_friend:
//                type = "2";
//                showShare();
//                break;
//            case R.id.share_weibo:
//                type = "3";
//                showShare();
//                break;
//            case R.id.share_qq:
//                type = "4";
//                showShare();
//                break;
//            case R.id.share_qzone:
//                type = "5";
//                showShare();
//                break;
//            case R.id.pop_share_text:
//                backgroundAlpha(1f);
//                popWindow.dismiss();
//                break;
        }
    }

    private void showShare() {
        ShareSDK.initSDK(getContext());
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("居然之家，一切只为提高您的生活品质");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(getContext());
    }

    private void showPopShare() {
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(getActivity()).inflate(R.layout.pop_share, null);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        backgroundAlpha(0.7f);

        weixin = (LinearLayout) view
                .findViewById(R.id.share_weixin);
        friend = (LinearLayout) view
                .findViewById(R.id.share_friend);
        weibo = (LinearLayout) view
                .findViewById(R.id.share_weibo);
        qq = (LinearLayout) view
                .findViewById(R.id.share_qq);
        qqZon = (LinearLayout) view
                .findViewById(R.id.share_qzone);

        text = (TextView) view
                .findViewById(R.id.pop_share_text);
        weixin.setOnClickListener(this);
        friend.setOnClickListener(this);
        weibo.setOnClickListener(this);
        qq.setOnClickListener(this);
        qqZon.setOnClickListener(this);
        text.setOnClickListener(this);

        View parent = getActivity().getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //添加pop窗口关闭事件
        popWindow.setOnDismissListener(new poponDismissListener());

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public class poponDismissListener implements PopupWindow.OnDismissListener {

        @Override
        public void onDismiss() {
            LogUtils.e("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
            popWindow.dismiss();
            LogUtils.e("List_noteTypeActivity:", "dismiss");
        }

    }

    private HashMap<String, Object> map;

    /**
     * 设置添加屏幕的背景透明度
     *
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }


//    private void showShare() {
//        ShareSDK.initSDK(getContext());
//
//
//        if (type.equals("1")) {
//            WechatMoments.ShareParams sp = new WechatMoments.ShareParams();
//            Platform wm = ShareSDK.getPlatform(WechatMoments.NAME);
////            sp.setShareType(Platform.SHARE_WEBPAGE);
////            sp.setTitle("居然之家");
////            // text是分享文本，所有平台都需要这个字段
////            sp.setText("我是分享文本");
////            // url仅在微信（包括好友和朋友圈）中使用
//
////
////            wm.setPlatformActionListener(paListener);
////            wm.share(sp);
//
//            OnekeyShare oks = new OnekeyShare();
//            //关闭sso授权
//            oks.disableSSOWhenAuthorize();
////            final OnekeyShare oks = new OnekeyShare();
//            //不同平台的分享参数，请看文档
//            //http://wiki.mob.com/Android_%E4%B8%8D%E5%90%8C%E5%B9%B3%E5%8F%B0%E5%88%86%E4%BA%AB%E5%86%85%E5%AE%B9%E7%9A%84%E8%AF%A6%E7%BB%86%E8%AF%B4%E6%98%8E
//            sp.setTitle("share title");
//            sp.setText("weixin");
//            sp.setUrl("http://baidu.com");
//            //oks.setSilent(silent);
//            // 去自定义不同平台的字段内容
//            // http://wiki.mob.com/Android_%E5%BF%AB%E6%8D%B7%E5%88%86%E4%BA%AB#.E4.B8.BA.E4.B8.8D.E5.90.8C.E5.B9.B3.E5.8F.B0.E5.AE.9A.E4.B9.89.E5.B7.AE.E5.88.AB.E5.8C.96.E5.88.86.E4.BA.AB.E5.86.85.E5.AE.B9
////            sp.setShareContentCustomizeCallback(new ShareContentCustomizeDemo());
////            sp.show(getContext());
//
//            wm.setPlatformActionListener(paListener);
//            wm.share(sp);
//
//
////            HashMap<String, Object> map = new HashMap<String, Object>();
////
////            Platform circle = ShareSDK.getPlatform(getContext(), Wechat.NAME);
////            if (!circle.isValid()) {
////
////                return;
////            }
////            map.put("AppId", "4b2ca6bfe38721ea4513485eca281099");
////            map.put("Enable", true);
////            map.put("BypassApproval", false);
////
////            ShareSDK.setPlatformDevInfo(Wechat.NAME, map);
////
////            Platform.ShareParams sp = new Platform.ShareParams();
////            sp.setShareType(Platform.SHARE_WEBPAGE);// 一定要设置分享属性
////            sp.setTitle("11111");
////            sp.setText("111111");
////            sp.setImageData(null);
////            sp.setImageUrl("  ");
////            sp.setImagePath("");
////            sp.setUrl("");
//
////            circle.setPlatformActionListener(getActivity()); // 设置分享事件回调
////            // 执行图文分享
////            circle.share(sp);
//
//
//        } else if (type.equals("2")) {
////            Wechat.ShareParams sp = new Wechat.ShareParams();
////            Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
////            sp.setTitle("居然之家");
////            // text是分享文本，所有平台都需要这个字段
////            sp.setText("我是分享文本");
////            // url仅在微信（包括好友和朋友圈）中使用
////            sp.setUrl("http://baidu.com");
////            wechat.setPlatformActionListener(paListener);
////            wechat.share(sp);
//
//            Platform circle = ShareSDK.getPlatform(getContext(), Wechat.NAME);
//            if (!circle.isValid()) {
//
//                ToastUtils.showShort(getContext(),"分享失败，请先安装微信");
//                return;
//            }
//            map.put("AppId", "");
//            map.put("Enable", ShareConfig.ENABLE_WXFRIEND);
//            map.put("BypassApproval", ShareConfig.BYPASSAPPROVAL_WXFRIEND);
//
//            ShareSDK.setPlatformDevInfo(Wechat.NAME, map);
//            ShareSDK.initSDK(this, ShareConfig.APPKEY);
//
//            Platform.ShareParams sp = new Platform.ShareParams();
//            sp.setShareType(Platform.SHARE_WEBPAGE);// 一定要设置分享属性
//            sp.setTitle(share_title);
//            sp.setText(share_text);
//            sp.setImageData(null);
//            sp.setImageUrl(share_image);
//            sp.setImagePath("");
//            sp.setUrl(share_url);
//
//            circle.setPlatformActionListener(this); // 设置分享事件回调
//            // 执行图文分享
//            circle.share(sp);
//        } else if (type.equals("3")) {
//            SinaWeibo.ShareParams sp = new SinaWeibo.ShareParams();
//            Platform sn = ShareSDK.getPlatform(SinaWeibo.NAME);
//            sp.setTitle("居然之家");
//            // text是分享文本，所有平台都需要这个字段
//            sp.setText("我是分享文本");
//
//            sn.setPlatformActionListener(paListener);
//            sn.share(sp);
//        } else if (type.equals("4")) {
//            QQ.ShareParams sp = new QQ.ShareParams();
//            Platform qq = ShareSDK.getPlatform(QQ.NAME);
//            sp.setTitle("居然之家");
//            // text是分享文本，所有平台都需要这个字段
//            sp.setText("我是分享文本");
//            // titleUrl是标题的网络链接，QQ和QQ空间等使用
//            sp.setTitleUrl("http://baidu.com");
//
//            qq.setPlatformActionListener(paListener);
//            qq.share(sp);
//        } else if (type.equals("5")) {
//            QZone.ShareParams sp = new QZone.ShareParams();
//            Platform qzone = ShareSDK.getPlatform(QZone.NAME);
//            sp.setTitle("居然之家");
//            // text是分享文本，所有平台都需要这个字段
//            sp.setText("我是分享文本");
//            // titleUrl是标题的网络链接，QQ和QQ空间等使用
//            sp.setTitleUrl("http://baidu.com");
//            // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//            sp.setComment("我是测试评论文本");
//            // site是分享此内容的网站名称，仅在QQ空间使用
//            sp.setSite(getString(R.string.app_name));
//            // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//            sp.setSiteUrl("http://baidu.com");
//
//            qzone.setPlatformActionListener(paListener);
//            qzone.share(sp);
//        }
//
//    }
//
//
//    PlatformActionListener paListener = new PlatformActionListener() {
//        @Override
//        public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
//            //操作成功，在这里可以做后续的步骤
//            //这里需要说明的一个参数就是HashMap<String, Object> arg2
//            //这个参数在你进行登录操作的时候里面会保存有用户的数据，例如用户名之类的。
//        }
//
//        @Override
//        public void onError(Platform platform, int i, Throwable throwable) {
//            //操作失败啦，打印提供的错误，方便调试
//
//        }
//
//        @Override
//        public void onCancel(Platform platform, int i) {
//            //用户取消操作会调用这里
//        }
//    };


}
