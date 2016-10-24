package com.autodesk.easyhome.shejijia.order.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.autodesk.easyhome.shejijia.MainActivity;
import com.autodesk.easyhome.shejijia.R;
import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.common.http.CallBack;
import com.autodesk.easyhome.shejijia.common.http.CommonApiClient;
import com.autodesk.easyhome.shejijia.common.http.UploadFileTask;
import com.autodesk.easyhome.shejijia.common.utils.DialogUtils;
import com.autodesk.easyhome.shejijia.common.utils.LogUtils;
import com.autodesk.easyhome.shejijia.common.utils.PhotoSystemUtils;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.activity.AppointmentActivity;
import com.autodesk.easyhome.shejijia.order.dto.EvaluateDTO;
import com.autodesk.easyhome.shejijia.order.entity.IntegralResult;
import com.bumptech.glide.Glide;
import com.lidong.photopicker.PhotoPickerActivity;
import com.lidong.photopicker.SelectModel;
import com.lidong.photopicker.intent.PhotoPickerIntent;

import org.json.JSONArray;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 评价页
 */
public class EvaluateActivity extends BaseTitleActivity {
    @Bind(R.id.img01)
    ImageView mImg01;
    @Bind(R.id.img02)
    ImageView mImg02;
    @Bind(R.id.img03)
    ImageView mImg03;
    @Bind(R.id.img04)
    ImageView mImg04;
    @Bind(R.id.img05)
    ImageView mImg05;
    @Bind(R.id.img06)
    ImageView mImg06;
    @Bind(R.id.img07)
    ImageView mImg07;
    @Bind(R.id.img08)
    ImageView mImg08;
    @Bind(R.id.img09)
    ImageView mImg09;
    @Bind(R.id.img10)
    ImageView mImg10;
    @Bind(R.id.et)
    EditText mEt;
    @Bind(R.id.evaluate_btn)
    Button mEvaluateBtn;
    @Bind(R.id.lin01)
    LinearLayout lin01;
    @Bind(R.id.lin02)
    LinearLayout lin02;
    @Bind(R.id.lin03)
    LinearLayout lin03;
    @Bind(R.id.lin04)
    LinearLayout lin04;
    @Bind(R.id.lin05)
    LinearLayout lin05;
    @Bind(R.id.lin06)
    LinearLayout lin06;
    @Bind(R.id.lin07)
    LinearLayout lin07;
    @Bind(R.id.lin08)
    LinearLayout lin08;
    @Bind(R.id.lin09)
    LinearLayout lin09;
    @Bind(R.id.lin10)
    LinearLayout lin10;
    @Bind(R.id.gridView)
    GridView mGv;
    private String tv1, tv2, tv3, tv4, tv5;
    private String mId;
    private ArrayList<String> imagePaths = new ArrayList<>();
    private ArrayList<String> mPic = new ArrayList<>();
    /* 请求识别码 */
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_PHOTO_REQUEST = 0xa2;
    private static final int REQUEST_CAMERA_CODE = 10;
    private GridAdapter gridAdapter;
    PopupWindow popWindow;
    TextView mCamera,mPhoto,mExit;

    @Override
    protected int getContentResId() {
        return R.layout.activity_evaluate;
    }

    @Override
    public void initView() {
        if (Build.VERSION.SDK_INT >= 23) {
            int readSDPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (readSDPermission != PackageManager.PERMISSION_GRANTED) {
                LogUtils.e("readSDPermission",""+readSDPermission);
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        123);
            }
        }
        setTitleText("评价");
        mId = getIntent().getBundleExtra("bundle").getString("mId");
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

        LogUtils.e("mPic.size()----",""+mPic.size());
    }

    @Override
    public void initData() {
        imagePaths.add("000000");
        gridAdapter = new GridAdapter(imagePaths);
        mGv.setAdapter(gridAdapter);
    }

    private void reqEvaluate() {
        EvaluateDTO dto = new EvaluateDTO();
        dto.setAccessToken(AppContext.get("accessToken", ""));
        dto.setOrderId(mId);
        dto.setChargeforIs(tv5);
        dto.setOpinion(mEt.getText().toString());
        dto.setProblemIs(tv3);
        dto.setStatementIs(tv4);
        dto.setTimeIs(tv1);
        dto.setServiceIs(tv2);
        CommonApiClient.evaluate(this, dto, new CallBack<IntegralResult>() {
            @Override
            public void onSuccess(IntegralResult result) {
                if (AppConfig.SUCCESS.equals(result.getCode())) {
                    LogUtils.e("评价成功");
                    DialogUtils.showPromptListen(EvaluateActivity.this, "提示","感谢您的评价，我们将继续努力！", "知道了",listener);

//                    Intent intent2 = new Intent(EvaluateActivity.this, MainActivity.class);
//                    intent2.putExtra("tag",1);
//                    EvaluateActivity.this.startActivity(intent2);

                }

            }
        });
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };


    @OnClick({R.id.lin01, R.id.lin02, R.id.lin03, R.id.lin04, R.id.lin05, R.id.lin06, R.id.lin07, R.id.lin08, R.id.lin09, R.id.lin10, R.id.evaluate_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin01:
                tv1 = "ture";
                mImg01.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg02.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin02:
                tv1 = "false";
                mImg01.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg02.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin03:
                tv2 = "ture";
                mImg03.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg04.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin04:
                tv2 = "false";
                mImg03.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg04.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin05:
                tv3 = "ture";
                mImg05.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg06.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin06:
                tv3 = "false";
                mImg05.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg06.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin07:
                tv4 = "ture";
                mImg07.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg08.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin08:
                tv4 = "false";
                mImg07.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg08.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.lin09:
                tv5 = "ture";
                mImg09.setBackground(getResources().getDrawable(R.drawable.shi_03));
                mImg10.setBackground(getResources().getDrawable(R.drawable.fou_03));
                break;
            case R.id.lin10:
                tv5 = "false";
                mImg09.setBackground(getResources().getDrawable(R.drawable.fou_03));
                mImg10.setBackground(getResources().getDrawable(R.drawable.shi_03));
                break;
            case R.id.evaluate_btn:
                if(TextUtils.isEmpty(tv1)){
                    DialogUtils.showPrompt(this, "提示", "请选择是否准时！", "知道了");
                    return;
                }
                if(TextUtils.isEmpty(tv2)){
                    DialogUtils.showPrompt(this, "提示", "请选择服务态度是否良好！", "知道了");
                    return;
                }
                if(TextUtils.isEmpty(tv3)){
                    DialogUtils.showPrompt(this, "提示", "请选择是否解决问题！", "知道了");
                    return;
                }
                if(TextUtils.isEmpty(tv4)){
                    DialogUtils.showPrompt(this, "提示", "请选择是否说明事项！", "知道了");
                    return;
                }
                if(TextUtils.isEmpty(tv5)){
                    DialogUtils.showPrompt(this, "提示", "请选择是否私自乱收费！", "知道了");
                    return;
                }
                if (TextUtils.isEmpty(mEt.getText().toString())) {
                    DialogUtils.showPrompt(this, "提示", "请提交意见！", "知道了");
                    return;
                }
                else {
                    reqPic();//上传图片
                    reqEvaluate();//评价
                }

                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
            case R.id.btn_alter_pic_camera://拍照
                Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentFromCapture, CODE_CAMERA_REQUEST);

                break;

            case R.id.btn_alter_pic_photo://选择照片

                PhotoPickerIntent intent = new PhotoPickerIntent(EvaluateActivity.this);
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
                UploadFileTask uploadFileTask=new UploadFileTask(this,AppConfig.BASE_URL+"/commentinfo/addComment");
                LogUtils.e("list.get(i)---",""+mPic.get(i));
                uploadFileTask.execute(mPic.get(i));
            }

        }
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
        LogUtils.e("parent---",""+parent);
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
                    else if(imagePaths.size()>1){
                        imagePaths.set(imagePaths.size()-1, fileName);
                        imagePaths.add("000000");
                        LogUtils.e("imagePaths----set----", "   " + (imagePaths.size()-1) + "---"+imagePaths);
                    }

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
            inflater = LayoutInflater.from(EvaluateActivity.this);
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

            if (path.equals("000000")){
                holder.image.setImageResource(R.drawable.tianjiatupianxdpi_03);
            }else {
                Glide.with(EvaluateActivity.this)
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
