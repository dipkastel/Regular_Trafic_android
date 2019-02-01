package com.notrika.regular_trafic;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;

import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.adapters.Adapter_Grid_Violenc;

import java.util.ArrayList;
import java.util.List;

public class DialogReport {
    private Dialog dialog;
    private View view;
    private Button submit;
    private GridView gridview;
    static DialogReport errorDialog;
    public Adapter_Grid_Violenc imageAdapter;

    public static DialogReport getInstace() {
        if (errorDialog == null)
            errorDialog = new DialogReport();
        return errorDialog;

    }


    public DialogReport Config(Context context, List<Res_Violenc> items) {

        if (dialog == null) {
            dialog = new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.dialog_report, null);
        init();
        imageAdapter=new Adapter_Grid_Violenc(context, items);
        gridview.setAdapter(imageAdapter);
        return errorDialog;
    }

    private void init() {
        submit = view.findViewById(R.id.submit);
        gridview = view.findViewById(R.id.gridview);

    }


    public DialogReport setOnClickListener(final OnDialogClickListener OnDialogClickListener) {

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
                if (OnDialogClickListener != null)
                    OnDialogClickListener.positiveButtonClicked(imageAdapter.getSelectedViolenc());
                dissmissDialog();
            }
        });


        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
            }
        });
        return errorDialog;
    }

    public void build() {
        if (errorDialog.dialog.isShowing())
            return;
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        try {
            dialog.show();
        } catch (Exception e) {
        }
    }

    public DialogReport setPositiveTitle(int Title) {
        submit.setText(Title);
        return errorDialog;
    }

    public interface OnDialogClickListener {
        void positiveButtonClicked(ArrayList<Res_Violenc> list);
    }

    public void dissmissDialog() {

        dialog = null;
        view = null;
        submit = null;
        errorDialog = null;
        gridview = null;

    }

}
