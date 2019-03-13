package com.notrika.regular_trafic.Util;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.notrika.regular_trafic.R;

import java.util.List;

public class Popup extends Dialog implements
        android.view.View.OnClickListener {

    public Activity c;
    public Dialog d;
    public Button btn_popup;
    public TextView txt_popup;
    public ImageView img_popup;

    public Popup(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);
        btn_popup = (Button) findViewById(R.id.btn_popup);
        btn_popup.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        btn_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}



