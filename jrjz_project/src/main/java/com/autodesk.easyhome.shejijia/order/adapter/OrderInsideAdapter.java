package com.autodesk.easyhome.shejijia.order.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.StringUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.order.OrderUiGoto;
import com.autodesk.easyhome.shejijia.order.dto.CancelOrderDTO;
import com.autodesk.easyhome.shejijia.order.entity.OrderCancelResult;
import com.autodesk.easyhome.shejijia.order.entity.OrderEntity;
import com.qluxstory.ptrrecyclerview.BaseRecyclerViewHolder;
import com.qluxstory.ptrrecyclerview.BaseSimpleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John_Libo on 2016/8/15.
 */
public class OrderInsideAdapter extends BaseSimpleRecyclerAdapter<OrderEntity> {
    private final Context context;
    private final int type;
    TextView tv05,tv06,tv07;
    Button btn;
    LinearLayout lin05,lin06,lin07;
    List<OrderEntity> list = new ArrayList<>();
    private String status;

    public OrderInsideAdapter(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewLayoutId() {
        return R.layout.item_orderinsider;
    }

    @Override
    public void bindData(BaseRecyclerViewHolder holder, OrderEntity orderEntity, final int position) {
        LogUtils.e("bindData---type---",""+type);
        list.add(position,orderEntity);
        status = orderEntity.getStatus();

        String serviceTime= orderEntity.getServiceTime();
        LogUtils.e("serviceTime---",""+serviceTime);
        List<String> dateAndTime = StringUtils.getDateAndTime(serviceTime);

        holder.setText(R.id.order_tv01,orderEntity.getServiceName());
        holder.setText(R.id.order_tv02,orderEntity.getOrderNo());
        holder.setText(R.id.order_tv03,dateAndTime.get(0));
        holder.setText(R.id.order_tv04,orderEntity.getAddress());
        holder.setText(R.id.order_tv03_time,dateAndTime.get(1));

        tv05 = holder.getView(R.id.order_tv05);
        lin05 = holder.getView(R.id.order_lin05);
        tv06 = holder.getView(R.id.order_tv06);
        lin06 = holder.getView(R.id.order_lin06);
        tv07 = holder.getView(R.id.order_tv07);
        btn = holder.getView(R.id.order_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            TextView tv = tv07;
            Button bt = btn;
            @Override
            public void onClick(View v) {

                Button mBtn = (Button) v;
                if(mBtn.getText().toString().equals("去评价")){
                    Bundle bundle = new Bundle();
                    bundle.putString("mId",list.get(position).getOrderId());
//                    OrderUiGoto.gotoEvaluate(context,bundle);
                    OrderUiGoto.gotoEvaluateStrat((Activity) context,bundle);
                }
                if(mBtn.getText().toString().equals("去支付")){
                    AppContext.set("statu", false);
                    Bundle bundle = new Bundle();
                    LogUtils.e("id----",list.get(position).getOrderId());
                    bundle.putString("id",list.get(position).getOrderId());
                    OrderUiGoto.gotoOrderNew(context,bundle);
                }
                if(mBtn.getText().toString().equals("取消订单")){
                    DialogUtils.confirm(context, "如果取消订单，优惠券和积分不予退回！", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            CancelOrderDTO dto = new CancelOrderDTO();
                            String time = TimeUtils.getSignTime();
                            String random = TimeUtils.genNonceStr();
                            dto.setAccessToken(AppContext.get("accessToken",""));
                            dto.setRandom(random);
                            dto.setUid(AppContext.get("uid",""));
                            dto.setTimestamp(time);
                            dto.setSign(AppContext.get("uid","")+time+random);
                            dto.setOrderId(list.get(position).getOrderId());
                            CommonApiClient.cancel((Activity) context, dto, new CallBack<OrderCancelResult>() {
                                @Override
                                public void onSuccess(OrderCancelResult result) {
                                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                                        LogUtils.e("取消订单成功");
                                        tv.setText("已取消");
                                        bt.setText("已取消");
                                        DialogUtils.showPrompt(context, "提示","订单已取消！", "知道了");
                                    }

                                }
                            });

                        }
                    });


                }
            }
        });
        if(status.equals("1")){
            //未完成订单
            lin05.setVisibility(View.GONE);
            lin06.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            tv07.setText("未完成");
            btn.setText("取消订单");
        }
        else if(status.equals("3")){
            //待支付订单
            lin05.setVisibility(View.VISIBLE);
            lin06.setVisibility(View.VISIBLE);
            btn.setVisibility(View.VISIBLE);
            tv05.setText(orderEntity.getEmpName());
            tv06.setText(orderEntity.getServiceFee()+"元");
            tv07.setText("待支付");
            btn.setText("去支付");
        }
        else if(status.equals("4")){
            //待评价订单
            lin05.setVisibility(View.VISIBLE);
            lin06.setVisibility(View.GONE);
            btn.setVisibility(View.VISIBLE);
            tv05.setText(orderEntity.getEmpName());
            tv07.setText("已支付");
            btn.setText("去评价");
        }
        else if(status.equals("5")){
            //已完成订单
            lin05.setVisibility(View.VISIBLE);
            lin06.setVisibility(View.VISIBLE);
            tv05.setText(orderEntity.getEmpName());
            tv06.setText(orderEntity.getServiceFee()+"元");
            tv07.setText("已完成");
            btn.setVisibility(View.GONE);
        }

    }

}
