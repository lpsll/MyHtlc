package com.autodesk.easyhome.shejijia.home.activity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.autodesk.easyhome.shejijia.common.base.BaseTitleActivity;
import com.autodesk.easyhome.shejijia.home.HomeUiGoto;
import com.autodesk.easyhome.shejijia.home.view.BottomPopupWindows;
import com.htlc.jrjz.jrjz_project.R;

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
    @Bind(R.id.apt_img)
    ImageView mAptImg;
    @Bind(R.id.img_ljd)
    ImageView mImgLjd;
    @Bind(R.id.tv_money)
    TextView mTvMoney;
    @Bind(R.id.apt_btn)
    Button mAptBtn;

    private BottomPopupWindows popupWindow;

    private ImageView photo;
    private static final int RESULT = 1;

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果

    @Override
    protected int getContentResId() {
        return R.layout.activity_appointment;
    }

    @Override
    public void initView() {
        setTitleText("预约");


        if (popupWindow == null) {
            popupWindow = new BottomPopupWindows(this, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popupWindow.dismiss();
                    switch (v.getId()) {
                        case R.id.popupwindow_Button_from_photos:
                            //打开相册
                            openPhoto();

                            break;
                        case R.id.popupwindow_Button_open_camera:
                            //打开相机

                            break;
                    }
                }
            });
        }
    }

    /**
     * 打开相册选择图片并显示
     */
    private void openPhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            // 从相册返回的数据
            if (data != null) {
                // 得到图片的全路径
                Uri uri = data.getData();
                Log.i("TAG",uri.toString());
                mAptImg.setImageURI(uri);

            }

//        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
//            // 从相机返回的数据
//            if (hasSdcard()) {
//                crop(Uri.fromFile(tempFile));
//            } else {
//                Toast.makeText(MainActivity.this, "未找到存储卡，无法存储照片！", 0).show();
//            }
//
//        } else if (requestCode == PHOTO_REQUEST_CUT) {
//            // 从剪切图片返回的数据
//            if (data != null) {
//                Bitmap bitmap = data.getParcelableExtra("data");
//                this.iv_image.setImageBitmap(bitmap);
//            }
//            try {
//                // 将临时文件删除
//                tempFile.delete();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 显示图片
     *
     * @param
     */
//    private void showPhoto(ImageView photo) {
////        String picturePath = target.getInfo(Target.TargetPhotoPath);
//        if (picturePath.equals(""))
//            return;
//        // 缩放图片, width, height 按相同比例缩放图片
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        // options 设为true时，构造出的bitmap没有图片，只有一些长宽等配置信息，但比较快，设为false时，才有图片
//        options.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeFile(picturePath, options);
//        int scale = (int) (options.outWidth / (float) 300);
//        if (scale <= 0)
//            scale = 1;
//        options.inSampleSize = scale;
//        options.inJustDecodeBounds = false;
//        bitmap = BitmapFactory.decodeFile(picturePath, options);
//
//        photo.setImageBitmap(bitmap);
//        photo.setMaxHeight(350);
//    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.lin_address, R.id.lin_time, R.id.apt_img, R.id.img_ljd, R.id.apt_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lin_address:
//                UIHelper.showFragment(this, SimplePage.SELECT_ADDRESS);//常用地址
                HomeUiGoto.gotoSelect(this);
                break;
            case R.id.lin_time:
                HomeUiGoto.gotoSt(this);
                break;
            case R.id.apt_img:
                //弹出popuwidow（从相册选择，打开照相机，取消）
                openPopuwidow();
                break;
            case R.id.img_ljd:
                break;
            case R.id.apt_btn:
                HomeUiGoto.gotoOrder(this);
                break;
            case R.id.base_titlebar_back:
                baseGoBack();
                break;
        }
    }

    /**
     * 从底部弹出弹出popuwidow（从相册选择，打开照相机，取消）
     */
    private void openPopuwidow() {
        popupWindow.showAtLocation(this.findViewById(R.id.main),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }


}
