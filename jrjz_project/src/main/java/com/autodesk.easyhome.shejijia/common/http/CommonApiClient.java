package com.autodesk.easyhome.shejijia.common.http;

import android.app.Activity;

import com.autodesk.easyhome.shejijia.campaign.entity.CampaignResult;
import com.autodesk.easyhome.shejijia.campaign.entity.ZfbTopUpEntity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.entity.BaseEntity;
import com.autodesk.easyhome.shejijia.home.dto.AddAddressDTO;
import com.autodesk.easyhome.shejijia.home.dto.AppointmentDTO;
import com.autodesk.easyhome.shejijia.home.dto.DeleteAddressDTO;
import com.autodesk.easyhome.shejijia.home.dto.HomeServiceDTO;
import com.autodesk.easyhome.shejijia.home.dto.LookNewsDTO;
import com.autodesk.easyhome.shejijia.home.dto.ModifyAddressDTO;
import com.autodesk.easyhome.shejijia.home.dto.ServieceFreeDTO;
import com.autodesk.easyhome.shejijia.home.dto.WxDTO;
import com.autodesk.easyhome.shejijia.home.dto.ZfbDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.CarouselResult;
import com.autodesk.easyhome.shejijia.home.entity.ClassificationResult;
import com.autodesk.easyhome.shejijia.home.entity.DfaultResult;
import com.autodesk.easyhome.shejijia.home.entity.FullServiceResult;
import com.autodesk.easyhome.shejijia.home.entity.HomeServiceResult;
import com.autodesk.easyhome.shejijia.home.entity.NewsResult;
import com.autodesk.easyhome.shejijia.home.entity.SelectAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.ServiceResult;
import com.autodesk.easyhome.shejijia.home.entity.TimeResult;
import com.autodesk.easyhome.shejijia.home.entity.WxResult;
import com.autodesk.easyhome.shejijia.login.dto.LoginDTO;
import com.autodesk.easyhome.shejijia.login.dto.LoginForCodeDTO;
import com.autodesk.easyhome.shejijia.login.entity.LoginEntity;
import com.autodesk.easyhome.shejijia.mine.dto.ChangePhoneDTO;
import com.autodesk.easyhome.shejijia.mine.dto.FeedBackDTO;
import com.autodesk.easyhome.shejijia.mine.dto.MineCouponDTO;
import com.autodesk.easyhome.shejijia.mine.dto.zfbTopUpDTO;
import com.autodesk.easyhome.shejijia.mine.entity.MineCouponResult;
import com.autodesk.easyhome.shejijia.mine.entity.UserDetailResult;
import com.autodesk.easyhome.shejijia.order.dto.CancelOrderDTO;
import com.autodesk.easyhome.shejijia.order.dto.EvaluateDTO;
import com.autodesk.easyhome.shejijia.order.dto.NewPaymentDTO;
import com.autodesk.easyhome.shejijia.order.dto.OrderDTO;
import com.autodesk.easyhome.shejijia.order.dto.OrderDetailDTO;
import com.autodesk.easyhome.shejijia.order.dto.ServiceCouponDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderCancelResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderDetailResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderResult;
import com.autodesk.easyhome.shejijia.order.entity.ServiceCouponResult;
import com.autodesk.easyhome.shejijia.register.dto.ForgetPwdDTO;
import com.autodesk.easyhome.shejijia.register.dto.RegisterDTO;
import com.autodesk.easyhome.shejijia.register.entity.SmsVerifyEntity;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class CommonApiClient extends BaseApiClient{

    /**
     * 获取短信验证码
     * @param act
     * @param dto
     * @param callback
     */
    public static void verifyCode(Activity act, BaseDTO
            dto, CallBack<SmsVerifyEntity> callback) {
        AsyncCallBack<SmsVerifyEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, SmsVerifyEntity.class);
        post(getAbsoluteUrl("/user/getSmsVerifyCode"), dto,
                asyncCallBack);
    }

    /**
     * 注册
     * @param act
     * @param dto
     * @param callback
     */
    public static void register(Activity act, RegisterDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/user/userRegister"), dto,
                asyncCallBack);
    }

    /**
     * 用户名密码登录
     * @param act
     * @param dto
     * @param callback
     */
    public static void login(Activity act, LoginDTO
            dto, CallBack<LoginEntity> callback) {
        AsyncCallBack<LoginEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, LoginEntity.class);
        post(getAbsoluteUrl("/user/getAccessToken"), dto,
                asyncCallBack);
    }

    /**
     * 验证码登录
     * @param act
     * @param dto
     * @param callback
     */
    public static void loginForCode(Activity act, LoginForCodeDTO
            dto, CallBack<LoginEntity> callback) {
        AsyncCallBack<LoginEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, LoginEntity.class);
        post(getAbsoluteUrl("/user/smsVerifyCodeAuth"), dto,
                asyncCallBack);
    }

    /**
     * 忘记密码
     * @param act
     * @param dto
     * @param callback
     */
    public static void forgetPwd(Activity act, ForgetPwdDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/user/forgotPassword"), dto,
                asyncCallBack);
    }

    /**
     * 换绑手机
     * @param act
     * @param dto
     * @param callback
     */
    public static void changePhone(Activity act, ChangePhoneDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/user/changeMobile"), dto,
                asyncCallBack);
    }

    /**
     * 轮播图
     * @param act
     * @param dto
     * @param callback
     */
    public static void carousel(Activity act, BaseDTO
            dto, CallBack<CarouselResult> callback) {
        AsyncCallBack<CarouselResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, CarouselResult.class);
        get(getAbsoluteUrl("/banner/bannerlist"), dto,
                asyncCallBack);
    }

    /**
     * 分类
     * @param act
     * @param dto
     * @param callback
     */
    public static void classification(Activity act, BaseDTO
            dto, CallBack<ClassificationResult> callback) {
        AsyncCallBack<ClassificationResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, ClassificationResult.class);
        get(getAbsoluteUrl("/service/allServicesByClass"), dto,
                asyncCallBack);
    }

    /**
     * 新增地址
     * @param act
     * @param dto
     * @param callback
     */
    public static void addAddress(Activity act, AddAddressDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/user/address/add"), dto,
                asyncCallBack);
    }
    /**
     * 获取订单详情
     * @param act
     * @param dto
     * @param callback
     */
    public static void orderDetail(Activity act, OrderDetailDTO
            dto, CallBack<OrderDetailResult> callback) {
        AsyncCallBack<OrderDetailResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderDetailResult.class);
        get(getAbsoluteUrl("/custServiceOrder/detail/"), dto,
                asyncCallBack);
    }

    /**
     * 订单之钱包支付
     * @param act
     * @param dto
     * @param callback
     */
    public static void newPayment(Activity act, NewPaymentDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralResult.class);
        post(getAbsoluteUrl("/pay/inner/pay"), dto,
                asyncCallBack);
    }

    /**
     * 预约之支付宝预支付
     * @param act
     * @param dto
     * @param callback
     */
    public static void zfb(Activity act, ZfbDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralResult.class);
        post(getAbsoluteUrl("/pay/ali/prepay"), dto,
                asyncCallBack);
    }

    /**
     * 预约之微信预支付
     * @param act
     * @param dto
     * @param callback
     */
    public static void wx(Activity act, WxDTO
            dto, CallBack<WxResult> callback) {
        AsyncCallBack<WxResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, WxResult.class);
        post(getAbsoluteUrl("/pay/wx/prepay"), dto,
                asyncCallBack);
    }


    /**
     * 修改地址
     * @param act
     * @param dto
     * @param callback
     */
    public static void modifyAddress(Activity act, ModifyAddressDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/user/address/save"), dto,
                asyncCallBack);
    }

    /**
     * 常用地址
     * @param act
     * @param dto
     * @param callback
     */
    public static void selectdAddress(Activity act, BaseDTO
            dto, CallBack<SelectAddressResult> callback) {
        AsyncCallBack<SelectAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, SelectAddressResult.class);
        get(getAbsoluteUrl("/user/address/list/"), dto,
                asyncCallBack);
    }

    /**
     * 删除地址
     * @param act
     * @param dto
     * @param callback
     */
    public static void deleteAddress(Activity act, DeleteAddressDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/user/address/delete"), dto,
                asyncCallBack);
    }

    /**
     * 获取服务费
     * @param act
     * @param dto
     * @param callback
     */
    public static void serviceCharge(Activity act, ServieceFreeDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        getNew(getAbsoluteUrl("/service/homeVisitFee?"), dto,
                asyncCallBack);
    }

    /**
     * 获取默认地址
     * @param act
     * @param dto
     * @param callback
     */
    public static void dfault(Activity act, BaseDTO
            dto, CallBack<DfaultResult> callback) {
        AsyncCallBack<DfaultResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, DfaultResult.class);
        get(getAbsoluteUrl("/user/address/default/"), dto,
                asyncCallBack);
    }

    /**
     * 服务优惠券
     * @param act
     * @param dto
     * @param callback
     */
    public static void serviceCoupon(Activity act, ServiceCouponDTO
            dto, CallBack<ServiceCouponResult> callback) {
        AsyncCallBack<ServiceCouponResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, ServiceCouponResult.class);
        post(getAbsoluteUrl("/coupon/couponByPageForService"), dto,
                asyncCallBack);
    }

    /**
     * 家政服务
     * @param act
     * @param dto
     * @param callback
     */
    public static void homeService(Activity act, HomeServiceDTO
            dto, CallBack<HomeServiceResult> callback) {
        AsyncCallBack<HomeServiceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, HomeServiceResult.class);
        getNew(getAbsoluteUrl("/service/companySerivces?"), dto,
                asyncCallBack);
    }


    /**
     * 订单之积分
     * @param act
     * @param dto
     * @param callback
     */
    public static void integral(Activity act, BaseDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralResult.class);
        get(getAbsoluteUrl("/points/"), dto,
                asyncCallBack);
    }

    /**
     * 订单之积分规则
     * @param act
     * @param dto
     * @param callback
     */
    public static void rule(Activity act, BaseDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralResult.class);
        get(getAbsoluteUrl("/points/pointsRule"), dto,
                asyncCallBack);
    }

    /**
     * 评价
     * @param act
     * @param dto
     * @param callback
     */
    public static void evaluate(Activity act, EvaluateDTO
            dto, CallBack<IntegralResult> callback) {
        AsyncCallBack<IntegralResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, IntegralResult.class);
        post(getAbsoluteUrl("/commentinfo/addComment"), dto,
                asyncCallBack);
    }

    /**
     * 我的优惠券
     * @param act
     * @param dto
     * @param callback
     */
    public static void mineCoupon(Activity act, MineCouponDTO
            dto, CallBack<MineCouponResult> callback) {
        AsyncCallBack<MineCouponResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, MineCouponResult.class);
        post(getAbsoluteUrl("/coupon/couponByPage"), dto,
                asyncCallBack);
    }

    /**
     * 全部订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void whole(Activity act, OrderDTO
            dto, CallBack<OrderResult> callback) {
        AsyncCallBack<OrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderResult.class);
        post(getAbsoluteUrl("/custServiceOrder/all"), dto,
                asyncCallBack);
    }

    /**
     * 已完成订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void completed(Activity act, OrderDTO
            dto, CallBack<OrderResult> callback) {
        AsyncCallBack<OrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderResult.class);
        post(getAbsoluteUrl("/custServiceOrder/finished"), dto,
                asyncCallBack);
    }


    /**
     * 未完成订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void unfinished (Activity act, OrderDTO
            dto, CallBack<OrderResult> callback) {
        AsyncCallBack<OrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderResult.class);
        post(getAbsoluteUrl("/custServiceOrder/unFinished"), dto,
                asyncCallBack);
    }

    /**
     * 消息
     * @param act
     * @param dto
     * @param callback
     */
    public static void news (Activity act, OrderDTO
            dto, CallBack<NewsResult> callback) {
        AsyncCallBack<NewsResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, NewsResult.class);
        post(getAbsoluteUrl("/systemmessage/messageByPage"), dto,
                asyncCallBack);
    }
    /**
     * 查看消息
     * @param act
     * @param dto
     * @param callback
     */
    public static void lookNews (Activity act, LookNewsDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/systemmessage/readMessage"), dto,
                asyncCallBack);
    }

    /**
     * 待评价订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void tobeEvaluated(Activity act, OrderDTO
            dto, CallBack<OrderResult> callback) {
        AsyncCallBack<OrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderResult.class);
        post(getAbsoluteUrl("/custServiceOrder/waitComment"), dto,
                asyncCallBack);
    }

    /**
     * 待支付订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void tobePaid(Activity act, OrderDTO
            dto, CallBack<OrderResult> callback) {
        AsyncCallBack<OrderResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderResult.class);
        post(getAbsoluteUrl("/custServiceOrder/waitPay"), dto,
                asyncCallBack);
    }

    /**
     * 取消订单
     * @param act
     * @param dto
     * @param callback
     */
    public static void cancel (Activity act, CancelOrderDTO
            dto, CallBack<OrderCancelResult> callback) {
        AsyncCallBack<OrderCancelResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, OrderCancelResult.class);
        post(getAbsoluteUrl("/custServiceOrder/cancel"), dto,
                asyncCallBack);
    }

    /**
     * 预约
     * @param act
     * @param dto
     * @param callback
     */
    public static void appointment(Activity act, AppointmentDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/service/service/book"), dto,
                asyncCallBack);
    }

    /**
     * 服务时间
     * @param act
     * @param dto
     * @param callback
     */
    public static void time(Activity act, BaseDTO
            dto, CallBack<TimeResult> callback) {
        AsyncCallBack<TimeResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, TimeResult.class);
        get(getAbsoluteUrl("/service/serviceDates"), dto,
                asyncCallBack);
    }


    /**
     * 全部服务类别
     * @param act
     * @param dto
     * @param callback
     */
    public static void fullService(Activity act, BaseDTO
            dto, CallBack<FullServiceResult> callback) {
        AsyncCallBack<FullServiceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, FullServiceResult.class);
        get(getAbsoluteUrl("/service/allServiceClasses"), dto,
                asyncCallBack);
    }

    /**
     * 制定服务类
     * @param act
     * @param dto
     * @param callback
     */
    public static void service(Activity act, BaseDTO
            dto, CallBack<ServiceResult> callback) {
        AsyncCallBack<ServiceResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, ServiceResult.class);
        get(getAbsoluteUrl("/service/indexServices"), dto,
                asyncCallBack);
    }

    /**
     * 是否有未读消息
     * @param act
     * @param dto
     * @param callback
     */
    public static void whether(Activity act, BaseDTO
            dto, CallBack<AddAddressResult> callback) {
        AsyncCallBack<AddAddressResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, AddAddressResult.class);
        post(getAbsoluteUrl("/systemmessage/flagByUid"), dto,
                asyncCallBack);
    }

    /**
     * 意见反馈
     * @param act
     * @param dto
     * @param callback
     */
    public static void feedBack(Activity act, FeedBackDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/feedback/add"), dto,
                asyncCallBack);
    }


    /**
     * 用户登出
     * @param act
     * @param dto
     * @param callback
     */
    public static void logout(Activity act, BaseDTO
            dto, CallBack<BaseEntity> callback) {
        AsyncCallBack<BaseEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, BaseEntity.class);
        post(getAbsoluteUrl("/user/logout"), dto,
                asyncCallBack);
    }

    /**
     * 获取用户信息
     * @param act
     * @param dto
     * @param callback
     */
    public static void getUserDetail(Activity act, BaseDTO
            dto, CallBack<UserDetailResult> callback) {
        AsyncCallBack<UserDetailResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, UserDetailResult.class);
        getNew(getAbsoluteUrl("/user/userDetail?"), dto,
                asyncCallBack);
    }

    /**活动页面数据
     *
     * @param act
     * @param dto
     * @param callback
     */
    public static void campaign(Activity act, BaseDTO
            dto, CallBack<CampaignResult> callback) {
        AsyncCallBack<CampaignResult> asyncCallBack = new AsyncCallBack<>(
                act, callback, CampaignResult.class);
        post(getAbsoluteUrl("/activity/activityList"), dto,
                asyncCallBack);
    }


    /**支付宝充值
     *
     * @param act
     * @param dto
     * @param callback
     */
    public static void zfbTopUp(Activity act, zfbTopUpDTO
            dto, CallBack<ZfbTopUpEntity> callback) {
        AsyncCallBack<ZfbTopUpEntity> asyncCallBack = new AsyncCallBack<>(
                act, callback, ZfbTopUpEntity.class);
        post(getAbsoluteUrl("/account/recharge"), dto,
                asyncCallBack);
    }


}
