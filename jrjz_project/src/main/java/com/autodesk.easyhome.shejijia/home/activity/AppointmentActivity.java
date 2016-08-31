package com.autodesk.easyhome.shejijia.home.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.AppConfig;
import com.autodesk.easyhome.shejijia.AppContext;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.http.UploadFileTask;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.ImageLoaderUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhotoSystemUtils;
import com.autodesk.easyhome.shejijia.common.utils.TimeUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.dto.AppointmentDTO;
import com.autodesk.easyhome.shejijia.home.dto.DeleteAddressDTO;
import com.autodesk.easyhome.shejijia.home.entity.AddAddressResult;
import com.bumptech.glide.Glide;
import com.autodesk.easyhome.shejijia.R;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.OnClick;


/**
 * 预约页
 */
public class AppointmentActivity extends BaseTitleActivity {
    @Bind(R.id.add_tv01)
    TextView mAddTv01;
    @Bind(R.id.add_tv02)
    TextView mAddTv02;
    @Bind(R.id.add_tv03)
    TextView mAddTv03;
    @Bind(R.id.add_tv04)
    TextView mAddTv04;
    @Bind(R.id.lin_address)
    LinearLayout mLinAddress;
    @Bind(R.id.tv_project)
    TextView mTvProject;
    @Bind(R.id.lin_time)
    LinearLayout mLinTime;
    @Bind(R.id.et_describe)
    EditText mEtDescribe;
    @Bind(R.id.gridView)
    GridView mGv;
    @Bind(R.id.img_ljd)
    ImageView mImgLjd;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.apt_btn)
    Button mAptBtn;
    @Bind(R.id.tv_time)
    TextView mTime;

    PopupWindow popWindow;
    TextView mCamera,mPhoto,mExit;
    /* 请求识别码 */
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_PHOTO_REQUEST = 0xa2;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private static final int REQUEST_CAMERA_CODE = 10;
    private static final int REQUEST_PREVIEW_CODE = 20;
    private GridAdapter gridAdapter;
    private String mName,mId;
    private String mPImg1,mPImg2,mPImg3,mPImg4,mPImg5,mPImg6,mPImg7,mPImg8;

    private ArrayList<String> mPic = new ArrayList<>();


    @Override
    protected int getContentResId() {
        return R.layout.activity_appointment;
    }

    @Override
    public void initView() {
        setTitleText("预约");
        mName = getIntent().getBundleExtra("bundle").getString("mName");
        mId = getIntent().getBundleExtra("bundle").getString("mId");
        mTvProject.setText(mName);

        int cols = getResources().getDisplayMetrics().widthPixels / getResources().getDisplayMetrics().densityDpi;
        cols = cols < 3 ? 3 : cols;
        mGv.setNumColumns(cols);

        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String imgs = (String) parent.getItemAtPosition(position);
                LogUtils.e("imgs----", "" + imgs);
                if ("000000".equals(imgs)) {
                    showPicPop();
                } else {
                    return;
                }


            }
        });
    }

    @Override
    public void initData() {
        reqService();//服务费
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(imagePaths);
        mGv.setAdapter(gridAdapter);

    }

    private void reqService() {
        DeleteAddressDTO dto = new DeleteAddressDTO();
        dto.setId(mId);
        CommonApiClient.serviceCharge(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("获取服务费成功");
                    mTvMoney.setText(result.getData());
                }

            }
        });
    }


    @OnClick({R.id.lin_address, R.id.lin_time, R.id.img_ljd, R.id.tv_project,R.id.apt_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_address:
//                UIHelper.showFragment(this, SimplePage.SELECT_ADDRESS);//常用地址
                HomeUiGoto.gotoSelect(this);
                break;
            case R.id.lin_time:
                HomeUiGoto.gotoSt(this);
                break;
            case R.id.img_ljd:
                break;
            case R.id.tv_project:
                HomeUiGoto.gotoProjectDetails(this);
                break;
            case R.id.apt_btn:
                reqPic();//上传图片


//                if(mAddTv02.getText().toString().equals("")){
//                    DialogUtils.showPrompt(this, "提示","请选择地址", "知道了");
//                }
////                else if(mTime.getText().toString().equals("")||mTime.getText().toString().equals("请选择服务时间")){
////                    DialogUtils.showPrompt(this, "提示","请选择时间", "知道了");
////                }
//                else if(mEtDescribe.getText().toString().equals("")){
//                    DialogUtils.showPrompt(this, "提示","请填写问题", "知道了");
//                }
//                else if(mAddTv02.getText().toString().equals("")){
//                    DialogUtils.showPrompt(this, "提示","请上传图片", "知道了");
//                }
//                else {
//                    reqPic();//上传图片
//                    reqAppointment();//预约
//
//                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.btn_alter_pic_camera://拍照
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);

                break;

            case R.id.btn_alter_pic_photo://选择照片

                PhotoPickerIntent intent = new PhotoPickerIntent(AppointmentActivity.this);
                intent.setSelectModel(SelectModel.MULTI);
                intent.setShowCarema(true); // 是否显示拍照
                intent.setMaxTotal(8); // 最多选择照片数量，默认为6
                intent.setSelectedPaths(imagePaths); // 已选中的照片地址， 用于回显选中状态
                LogUtils.e("imagePaths---", "" + imagePaths);
                startActivityForResult(intent, REQUEST_CAMERA_CODE);

                break;
            case R.id.btn_alter_exit:
                backgroundAlpha(1f);
                popWindow.dismiss();
                break;
        }
    }


    private void reqPic() {
        if(mPic.size()>0){
            for(int i =0;i<mPic.size();i++){
                UploadFileTask uploadFileTask=new UploadFileTask(this);
                LogUtils.e("list.get(i)---",""+mPic.get(i));
                uploadFileTask.execute(mPic.get(i));
            }

        }

    }

    private void reqAppointment() {
        AppointmentDTO dto = new AppointmentDTO();
        String time = TimeUtils.getSignTime();
        String random = TimeUtils.genNonceStr();
        dto.setAccessToken(AppContext.get("accessToken",""));
        dto.setRandom(random);
        dto.setUid(AppContext.get("uid",""));
        dto.setTimestamp(time);
        dto.setSign(AppContext.get("uid","")+time+random);
        dto.setCustName(mAddTv01.getText().toString());
        dto.setPhone(mAddTv02.getText().toString());
        dto.setAddress(mAddTv04.getText().toString());
        dto.setServiceItemId(mId);
        dto.setServiceTime("12:00");
        dto.setDescr(mEtDescribe.getText().toString());
        dto.setHomeVisitFee(mTvMoney.getText().toString());
        CommonApiClient.appointment(this, dto, new CallBack<AddAddressResult>() {
            @Override
            public void onSuccess(AddAddressResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("预约成功");
                    HomeUiGoto.gotoOrder(AppointmentActivity.this);
                }

            }
        });
    }

    private void showPicPop() {
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.from(this).inflate(R.layout.popup_pic, null);
        popWindow = new PopupWindow(view, WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT,true);

        // 需要设置一下此参数，点击外边可消失
        popWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击窗口外边窗口消失
        popWindow.setOutsideTouchable(true);
        // 设置此参数获得焦点，否则无法点击
        popWindow.setFocusable(true);
        //防止虚拟软键盘被弹出菜单遮住
        popWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        backgroundAlpha(0.7f);

        mCamera = (TextView) view.findViewById(R.id.btn_alter_pic_camera);
        mPhoto = (TextView) view.findViewById(R.id.btn_alter_pic_photo);
        mExit = (TextView) view.findViewById(R.id.btn_alter_exit);
        mCamera.setOnClickListener(this);
        mPhoto.setOnClickListener(this);
        mExit.setOnClickListener(this);

        View parent = getWindow().getDecorView();//高度为手机实际的像素高度
        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //添加pop窗口关闭事件
        popWindow.setOnDismissListener(new poponDismissListener());
    }
    public class poponDismissListener implements PopupWindow.OnDismissListener{

        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
            popWindow.dismiss();
        }

    }
    /**
     * 设置添加屏幕的背景透明度
     * @param bgAlpha
     */
    public void backgroundAlpha(float bgAlpha)
    {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtils.e("data--------", "" + data);
        switch (requestCode) {

            //拍照
            case CODE_CAMERA_REQUEST:
                LogUtils.e("CODE_CAMERA_REQUEST----", "CODE_CAMERA_REQUEST");
                popWindow.dismiss();
                backgroundAlpha(1f);
                if (data == null||"".equals(data)) {
                    LogUtils.e("data----CODE_CAMERA_REQUEST", "" + data);
                    return;
                } else {
                    LogUtils.e("data----else", "else");
                    String sdStatus = Environment.getExternalStorageState();
                    if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                        LogUtils.e("TestFile", "SD card is not avaiable/writeable right now.");
                        return;
                    }
                    String name = new DateFormat().format("yyyyMMdd_hhmmss", Calendar.getInstance(Locale.CHINA)) + ".jpg";
                    LogUtils.e("name", "" + name);
                    Bundle bundle = data.getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
                    LogUtils.e("bitmap---", "" + bitmap);
                    Bitmap bm = PhotoSystemUtils.comp(bitmap);
                    LogUtils.e("bm---", "" + bm);

                    File file = new File("/sdcard/myImage/");
                    file.mkdirs();// 创建文件夹
                    String fileName = "/sdcard/myImage/" + name;
                    LogUtils.e("fileName", "" + fileName);

                    FileOutputStream b = null;
                    try {
                        b = new FileOutputStream(fileName);
                        LogUtils.e("b---",""+b);
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
                        LogUtils.e("bm.compress---",""+bm);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            b.flush();
                            b.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    LogUtils.e("imagePaths----if---", "" + imagePaths);


                    if (imagePaths.size()==1){
                        imagePaths.clear();
                        imagePaths.add(fileName);
                        imagePaths.add("000000");
                        LogUtils.e("imagePaths----1", "" + imagePaths);
                    }
                    if(imagePaths.size()>1){
                        imagePaths.set(imagePaths.size()-1, fileName);
                        imagePaths.add("000000");
                        LogUtils.e("imagePaths----set----", "   " + (imagePaths.size()-1) + "---"+imagePaths);
                    }
//                    else if (imagePaths.size()==2){
//                        imagePaths.add("000000");
//                        imagePaths.set(1, fileName);
//                        LogUtils.e("imagePaths----2", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==3){
//                        imagePaths.add("000000");
//                        imagePaths.set(2, fileName);
//                        LogUtils.e("imagePaths----3", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==4){
//                        imagePaths.add("000000");
//                        imagePaths.set(3, fileName);
//                        LogUtils.e("imagePaths----4", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==5){
//                        imagePaths.add("000000");
//                        imagePaths.set(4, fileName);
//                        LogUtils.e("imagePaths----5", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==6){
//                        imagePaths.add("000000");
//                        imagePaths.set(5, fileName);
//                        LogUtils.e("imagePaths----6", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==7){
//                        imagePaths.add("000000");
//                        imagePaths.set(6, fileName);
//                        LogUtils.e("imagePaths----7", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==8){
//                        imagePaths.add("000000");
//                        imagePaths.set(7, fileName);
//                        LogUtils.e("imagePaths----8", "" + imagePaths);
//                    }
//                    else if (imagePaths.size()==9){
//                        imagePaths.add("000000");
//                        imagePaths.set(8, fileName);
//                        LogUtils.e("imagePaths----9", "" + imagePaths);
//                    }


                    gridAdapter  = new GridAdapter(imagePaths);
                    mGv.setAdapter(gridAdapter);
                }
                break;

            // 选择照片
            case REQUEST_CAMERA_CODE:
                backgroundAlpha(1f);
                popWindow.dismiss();
                if(data == null){
                    return;
                }else
                {
                    ArrayList<String> list = data.getStringArrayListExtra(PhotoPickerActivity.EXTRA_RESULT);
                    LogUtils.e("list: ", "list = " + list + "--size = " + list.size());
                    if(null==list){
                        return;
                    }else {
                        loadAdpater(list);
                    }
                }

                break;

            case HomeUiGoto.SELECT_REQUEST:
                LogUtils.e("SELECT_REQUEST----","SELECT_REQUEST");
                if(resultCode==00001){
                    if(TextUtils.isEmpty(AppContext.get("name",""))){
                        return;
                    }else
                    {
                        mAddTv01.setText(AppContext.get("name",""));
                        mAddTv02.setText(AppContext.get("mobile",""));
                        mAddTv04.setText(AppContext.get("address",""));
                        AppContext.set("name","");
                        AppContext.set("mobile","");
                        AppContext.set("address","");

                    }
                }
                else if(resultCode==00002){

                }

                break;

            default:
                break;

        }

    }


    private void loadAdpater(ArrayList<String> paths){
        if (imagePaths!=null&& imagePaths.size()>0){
            imagePaths.clear();
        }
        if (paths.contains("000000")){
            paths.remove("000000");
        }
        paths.add("000000");
        LogUtils.e("paths----", "" + paths);
        imagePaths.addAll(paths);
        gridAdapter  = new GridAdapter(imagePaths);
        mGv.setAdapter(gridAdapter);

        try{
            JSONArray obj = new JSONArray(imagePaths);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    boolean flag = false;
    private class GridAdapter extends BaseAdapter {
        private ArrayList<String> listUrls;
        private LayoutInflater inflater;

        public GridAdapter(ArrayList<String> listUrls) {
            this.listUrls = listUrls;
            if(listUrls.size() == 9){
                LogUtils.e("listUrls---remove----1---",""+listUrls);
                listUrls.remove(listUrls.size()-1);
                flag =true;
                LogUtils.e("listUrls---remove----2---",""+listUrls);
            }
            inflater = LayoutInflater.from(AppointmentActivity.this);
        }

        public int getCount(){
            return  listUrls.size();
        }
        @Override
        public String getItem(int position) {
            return listUrls.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.item_comment_img, parent,false);
                holder.image = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }
            final String path=listUrls.get(position);
            LogUtils.e("listUrls---",""+listUrls);
            LogUtils.e("listUrls.size()---",""+listUrls.size());

            if(listUrls.size()>1){
                mPic.clear();
                for(int i =0;i<listUrls.size()-1;i++){
                    mPic.add(listUrls.get(i));
                }
                if(listUrls.size()==8&&flag==true){
                    mPic.add(listUrls.get(listUrls.size()-1));
                    LogUtils.e("mPic---flag---", "" + listUrls.size()+"-------"+mPic);
                }
                LogUtils.e("listUrls.get(i)----", "" + listUrls.size()+"-------"+mPic);
                LogUtils.e("flag---",""+flag);

            }


//            if(listUrls.size()==1){
//                LogUtils.e("listUrls.get(0)----1", "" + listUrls.get(0));
//            }
//            else if(listUrls.size()==2){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//
//                LogUtils.e("list----2", "" + mPic);
//            }
//            else if(listUrls.size()==3){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//
//                LogUtils.e("list----3", "" + mPic);
//            }
//
//            else if(listUrls.size()==4){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//
//                LogUtils.e("list----4", "" + mPic);
//            }
//
//            else if(listUrls.size()==5){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//                mPic.add(listUrls.get(3));
//
//                LogUtils.e("list----5", "" + mPic);
//            }
//
//            else if(listUrls.size()==6){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//                mPic.add(listUrls.get(3));
//                mPic.add(listUrls.get(4));
//
//                LogUtils.e("list----6", "" + mPic);
//            }
//
//            else if(listUrls.size()==7){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//                mPic.add(listUrls.get(3));
//                mPic.add(listUrls.get(4));
//                mPic.add(listUrls.get(5));
//
//                LogUtils.e("list----7", "" + mPic);
//            }
//
//            else if(listUrls.size()==8){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//                mPic.add(listUrls.get(3));
//                mPic.add(listUrls.get(4));
//                mPic.add(listUrls.get(5));
//                mPic.add(listUrls.get(6));
//                if(flag){
//                    mPic.add(listUrls.get(7));
//                    flag = false;
//                    LogUtils.e("list----8---++++", "" + mPic);
//                }
//                LogUtils.e("list----8", "" + mPic);
//            }

//            else if(listUrls.size()==9){
//                mPic.clear();
//                mPic.add(listUrls.get(0));
//                mPic.add(listUrls.get(1));
//                mPic.add(listUrls.get(2));
//                mPic.add(listUrls.get(3));
//                mPic.add(listUrls.get(4));
//                mPic.add(listUrls.get(5));
//                mPic.add(listUrls.get(6));
//
//                LogUtils.e("list----9", "" + mPic);
//            }


            if (path.equals("000000")){
                holder.image.setImageResource(R.drawable.tianjiatupianxdpi_03);
            }else {
                Glide.with(AppointmentActivity.this)
                        .load(path)
                        .placeholder(R.mipmap.default_error)
                        .error(R.mipmap.default_error)
                        .centerCrop()
                        .crossFade()
                        .into(holder.image);
            }
            return convertView;
        }
        class ViewHolder {
            ImageView image;
        }
    }






}
