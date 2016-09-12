package com.autodesk.easyhome.shejijia.common.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.autodesk.easyhome.shejijia.common.base.SimpleActivity;
import com.autodesk.easyhome.shejijia.common.base.SimplePage;

/**
 * Created by John_Libo on 2016/8/18.
 */
public class UIHelper {

    public static void showFragment(Context context, SimplePage page) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        context.startActivity(intent);
    }
    public static final int WHETHER_REQUEST = 010;
    public static void showFragmentFor(Activity act, SimplePage page) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        act.startActivityForResult(intent,WHETHER_REQUEST);
    }

    public static void showBundleFragment(Context context, SimplePage page,
                                    Bundle args) {
        Intent intent = new Intent(context, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        context.startActivity(intent);
    }

    public static final int SEND_REQUEST = 0x100;//订单之优惠券
    public static void showFragmentResult(Activity act, SimplePage page,
                                          Bundle args) {
        Intent intent = new Intent(act, SimpleActivity.class);
        intent.putExtra(SimpleActivity.BUNDLE_KEY_PAGE, page.getValue());
        intent.putExtra(SimpleActivity.BUNDLE_KEY_ARGS, args);
        act.startActivityForResult(intent,SEND_REQUEST);
    }
}
