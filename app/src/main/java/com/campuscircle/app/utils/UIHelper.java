package com.campuscircle.app.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.campuscircle.app.R;

public class UIHelper {
    private static Toast mToast;

    private static Handler mHandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
            mToast = null;//toast隐藏后，将其置为null
        }
    };

    /**
     * 弹出Toast消息
     */
    public static void toastMessage(Context context, String msg) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_toast, null);//自定义布局
        TextView text = view.findViewById(R.id.tv_toast);//显示的提示文字
        text.setText(msg);
        mHandler.removeCallbacks(r);
        if (mToast == null) {//只有mToast==null时才重新创建，否则只需更改提示文字
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.BOTTOM, 0, 150);
            mToast.setView(view);
        }
        mHandler.postDelayed(r, 1000);//延迟1秒隐藏toast
        mToast.show();


    }



    public static Toast makeImageText(Context context, CharSequence text) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.view_image_toast, null);
        TextView textView = layout.findViewById(R.id.message);
        textView.setText(text);
        mHandler.removeCallbacks(r);
        if (mToast == null) {
            mToast = new Toast(context);
            mToast.setDuration(Toast.LENGTH_SHORT);
            mToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
            mToast.setView(layout);
        }
        mHandler.postDelayed(r, 1000);

        return mToast;
    }

    public static void showImageToast(Context context, String content) {
        mToast = UIHelper.makeImageText(context,  content);
        mToast.show();
    }



    /**
     * 正常启动Activity
     */
    public static void startActivity(Activity activity, Class<? extends Activity> clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
    }

    /**
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }

    /**
     * 携带数据启动Activity
     */
    public static void startActivity(Activity activity, Class<? extends Activity> clazz, Bundle bundle) {
        Intent intent = new Intent(activity, clazz);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 启动Activity，获取数据
     */
    public static void startActivityForResult(Activity activity, Class<? extends Activity> clazz, int requestCode, Bundle bundle) {
        try {
            Intent intent = new Intent(activity, clazz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            activity.startActivityForResult(intent, requestCode);
        } catch (Exception ex) {

        }
    }

    /**
     * @param activity
     * @param intent
     * @param requestCode
     * @param bundle
     */
    public static void startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle bundle) {
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 弹出拨打电话Activity
     *
     * @param context
     * @param phone
     */
    public static void showTel(Activity context, String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(intent);
    }
}
