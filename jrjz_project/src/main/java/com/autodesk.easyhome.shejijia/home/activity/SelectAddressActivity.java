package com.autodesk.easyhome.shejijia.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.dto.BaseDTO;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.dto.DeleteAddressDTO;
import com.autodesk.easyhome.shejijia.home.dto.ModifyAddressDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.autodesk.easyhome.shejijia.home.entity.SelectAddressEntity;
import com.autodesk.easyhome.shejijia.home.entity.SelectAddressResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 常用地址页
 */
public class SelectAddressActivity extends BaseTitleActivity {
    public static final String ACTION = "com.delete.address.broadcast";
    @Bind(R.id.list)
    ListView mList;
    @Bind(R.id.select_lin)
    LinearLayout mSelectLin;
    List<SelectAddressEntity> data;
    SelectAddressAdapter adapter;
    int option,def;

    @Override
    protected int getContentResId() {
        return R.layout.activity_select_address;
    }

    @Override
    public void initView() {
        setTitleText("常用地址");
        reqSelect();
    }

    private void reqSelect() {
        BaseDTO dto = new BaseDTO();
        dto.setUid( AppContext.get("uid", ""));
        CommonApiClient.selectdAddress(this, dto, new CallBack<SelectAddressResult>() {
            @Override
            public void onSuccess(SelectAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("常用地址成功");
                    if(null == result.getData()){
                        return;
                    }else {
                        setResult(result);
                    }


                }

            }
        });
    }

    private void setResult(SelectAddressResult result) {
        data = result.getData();
        adapter = new SelectAddressAdapter(this, data);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(AppContext.get("select_flag","").equals("0")){
                    AppContext.set("mSelName",data.get(position).getName());
                    AppContext.set("mSelPhone",data.get(position).getMobile());
                    AppContext.set("mSelAddress",data.get(position).getCity()+data.get(position).getArea()+data.get(position).getAddress());
                    String area = data.get(position).getArea();


                    Intent intent = SelectAddressActivity.this.getIntent();
                    Bundle bundle = intent.getExtras();
                    bundle.putString("yArea", area);//添加要返回给页面1的数据
                    intent.putExtras(bundle);

                    setResult(00001,intent);
                    finish();
                }

            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick(R.id.select_lin)
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_lin:
                HomeUiGoto.gotoAddress(this);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();

                break;

        }

    }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            LogUtils.e("onActivityResult---",""+requestCode);
            LogUtils.e("ADDRSS_REQUEST--1--",""+HomeUiGoto.ADDRSS_REQUEST);
            LogUtils.e("MODIFY_REQUEST--1--",""+HomeUiGoto.MODIFY_REQUEST);
            if(requestCode == HomeUiGoto.ADDRSS_REQUEST)
            {
                LogUtils.e("requestCode--1--",""+requestCode);
                reqSelect();
            }
            if(requestCode == HomeUiGoto.MODIFY_REQUEST)
            {
                LogUtils.e("requestCode--2--",""+requestCode);
                reqSelect();
            }



    }



    public class SelectAddressAdapter extends BaseAdapter {
        private final Context context;
        private final List<SelectAddressEntity> list;
        boolean flag =true;
        DialogInterface.OnClickListener listener;
        List<SelectAddressEntity> mList = new ArrayList<>();
        List<Boolean> bList = new ArrayList<>();
        LinearLayout lin;
        public SelectAddressAdapter(Context context, List<SelectAddressEntity> list) {
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            public TextView mTv;
            public LinearLayout mLinSz, mLinSc, mLinBj;
            public TextView mTvCk;
            public CheckBox mCb;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.item_select_address, null);
                holder.mTv = (TextView) convertView.findViewById(R.id.add_tv);
                holder.mTvCk = (TextView) convertView.findViewById(R.id.tv_address);
                holder.mCb = (CheckBox) convertView.findViewById(R.id.box);
                holder.mLinSz = (LinearLayout) convertView.findViewById(R.id.lin_shezhi);
                holder.mLinSc = (LinearLayout) convertView.findViewById(R.id.lin_sc);
                holder.mLinBj = (LinearLayout) convertView.findViewById(R.id.lin_bj);
                lin = holder.mLinSz;
                convertView.setTag(holder);

            } else {

                holder = (ViewHolder) convertView.getTag();
            }
            holder.mTv.setText(list.get(position).getCity()+list.get(position).getDistrict()+list.get(position).getArea()+list.get(position).getAddress());
            mList.add(position, list.get(position));
            LogUtils.e("flag---",""+flag);

            if(flag){
                if(list.get(position).getDefaultAddress().equals("true")){
                    holder.mTvCk.setTextColor(context.getResources().getColor(R.color.navi));
                    holder.mCb.setBackground(getResources().getDrawable(R.drawable.morenhxdpi_03));
                    bList.add(position, false);
                }else {
                    holder.mTvCk.setTextColor(context.getResources().getColor(R.color.color_01));
                    holder.mCb.setBackground(getResources().getDrawable(R.drawable.morenqxdpi_03));
                    bList.add(position, true);
                }
            }
            else {
                if(bList.get(position)==true){
                    holder.mLinSz.setEnabled(true);
                    holder.mTvCk.setTextColor(context.getResources().getColor(R.color.color_01));
                    holder.mCb.setBackground(getResources().getDrawable(R.drawable.morenqxdpi_03));
                }else {
                    holder.mLinSz.setEnabled(false);
                    bList.set(def,false);
                    holder.mTvCk.setTextColor(context.getResources().getColor(R.color.navi));
                    holder.mCb.setBackground(getResources().getDrawable(R.drawable.morenhxdpi_03));
                }
            }
            LogUtils.e("bList---",""+bList);


            LogUtils.e("notifyDataSetChanged---","notifyDataSetChanged");


            final ViewHolder finalHolder = holder;
            lin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    def = position;
                    LinearLayout ll = (LinearLayout) v;
                    if (bList.get(def)) {
                        flag = false;
                        for(int i =0;i<bList.size();i++){
                            if(bList.get(i)){

                            }else {
                                bList.set(i,true);
                            }
                        }
                        bList.set(def,false);
                        LogUtils.e("bList---click---",""+bList);
                        adapter.notifyDataSetChanged();
                        reqSetUp(def);//设置默认地址
                    }
                }
            });

            holder.mLinSc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    option = position;
                    DialogUtils.confirm(context, "是否删除地址", listener);
                }
            });

            listener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    LogUtils.e("position---1---",""+option);
                    reqDelete(option);//删除地址
                }
            };


            holder.mLinBj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("position---2----",""+position);
                    Bundle b = new Bundle();
                    b.putSerializable("SelectAddressEntity", mList.get(position));
                    HomeUiGoto.gotoModify((Activity) context, b);//修改地址
                }
            });

            return convertView;
        }

        private void reqSetUp(int def) {
            ModifyAddressDTO dto = new ModifyAddressDTO();
            long time = TimeUtils.getSignTime();
            String random = TimeUtils.genNonceStr();
            dto.setAccessToken(AppContext.get("accessToken", ""));
            dto.setUid(AppContext.get("uid", ""));
            dto.setTimestamp(time);
            dto.setRandom(random);
            dto.setSign(AppContext.get("uid", "") + time + random);
            dto.setName(list.get(def).getName());
            dto.setMobile(list.get(def).getMobile());
            dto.setCity(list.get(def).getCity());
            dto.setDistrict(list.get(def).getDistrict());
            dto.setArea(list.get(def).getArea());
            dto.setAddress(list.get(def).getAddress());
            dto.setDefaultAddress("true");//默认为是，是/true ,否/false
            dto.setId(mList.get(def).getId());
            CommonApiClient.modifyAddress(SelectAddressActivity.this, dto, new CallBack<AddAddressResult>() {
                @Override
                public void onSuccess(AddAddressResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("设置默认地址成功");

                    }

                }
            });
        }

        private void reqDelete(final int option) {
            DeleteAddressDTO dto = new DeleteAddressDTO();
            long time = TimeUtils.getSignTime();
            String random = TimeUtils.genNonceStr();
            dto.setUid(AppContext.get("uid", ""));
            dto.setRandom(random);
            dto.setTimestamp(time);
            dto.setAccessToken(AppContext.get("accessToken", ""));
            dto.setSign(AppContext.get("uid", "") + time + random);
            LogUtils.e("id----",""+mList.get(option));
            dto.setId(mList.get(option).getId());
            CommonApiClient.deleteAddress((Activity) context, dto, new CallBack<AddAddressResult>() {
                @Override
                public void onSuccess(AddAddressResult result) {
                    if (AppConfig.SUCCESS.equals(result.getCode())) {
                        LogUtils.e("删除地址成功");
                        LogUtils.e("list----1---",""+list);
                        list.remove(option);
                        LogUtils.e("list----2---",""+list);
                        adapter.notifyDataSetChanged();

                        //发送广播刷新预约页数据
                        sendBroadcast(new Intent(ACTION));

                    }

                }
            });
        }

    }
}
