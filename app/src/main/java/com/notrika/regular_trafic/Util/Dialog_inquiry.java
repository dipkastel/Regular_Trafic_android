package com.notrika.regular_trafic.Util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.squareup.picasso.Picasso;


public class Dialog_inquiry {
    private Dialog dialog;

    private View view;
    TextView txt_confirm;
    Button confirm;
    String txtconfirm;
    public Dialog_inquiry Config(Context context, String _txtconfirm) {

        if (dialog == null) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        txtconfirm=_txtconfirm;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_inquiry, null);
        init();
        fill();

        return this;
    }

    private void fill() {
        txt_confirm.setText(txtconfirm);
    }

    private void init() {
        txt_confirm = view.findViewById(R.id.txt_confirm);
        confirm = view.findViewById(R.id.confirm);
    }


    public Dialog_inquiry setOnClickListener(final OnDialogClickListener OnDialogClickListener) {

        confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                if (OnDialogClickListener != null)
                    OnDialogClickListener.click();

            }
        });


        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        return this ;
    }

    public void build() {
        if (dialog.isShowing())
            return;
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }

    public interface OnDialogClickListener {
        void click ( );
    }

}

