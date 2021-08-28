package com.campuscircle.app.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.campuscircle.app.R;

/**
 * @ClassName TakePhoteDialog
 * @Description TODO
 * @Author SeanLim
 * @Date 2021-8-25 17:28
 * @E-mail linlin.1016@qq.com
 * @Version 1.0
 */
public class TakePhoteDialog extends Dialog {
    private Context context;
    private View.OnClickListener onClickListener;
    public ImageView iv_close, iv_album, iv_takephoto;

    public TakePhoteDialog(Context context) {
        super(context);
    }

    public TakePhoteDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_photo_dialog);
        initView();
    }

    public void initView() {
        iv_close = (ImageView) findViewById(R.id.iv_close);
        iv_album = (ImageView) findViewById(R.id.iv_album);
        iv_takephoto = (ImageView) findViewById(R.id.iv_takephoto);

        iv_close.setOnClickListener(onClickListener);
        iv_album.setOnClickListener(onClickListener);
        iv_takephoto.setOnClickListener(onClickListener);
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
