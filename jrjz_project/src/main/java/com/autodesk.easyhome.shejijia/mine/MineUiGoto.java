package com.autodesk.easyhome.shejijia.mine;

import android.app.Activity;
import android.content.Intent;

import com.autodesk.easyhome.shejijia.mine.activity.ChangePhoneActivity;
import com.autodesk.easyhome.shejijia.mine.activity.MoreSettingActivity;

/**
 * Created by John_Libo on 2016/8/27.
 */
public class MineUiGoto {

    /**
     * 跳转到设置页面(执行退出操作)
     * @param
     */

    public static final int SETTING_REQUEST = 0x1101;

    public static void gotoSetting(Activity act) {
        Intent intent = new Intent(act, MoreSettingActivity.class);
        act.startActivityForResult(intent, SETTING_REQUEST);
    }

    public static final int CHANGEPHONE_REQUEST = 0x1100;
    public static void gotoChangePhone(Activity act) {
        Intent intent = new Intent(act, ChangePhoneActivity.class);
        act.startActivityForResult(intent, CHANGEPHONE_REQUEST);

    }
}
