package com.notrika.regular_trafic.Util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.notrika.regular_trafic.DialogReport;
import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.notrika.regular_trafic.adapters.Adapter_Grid_Violenc;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Dialog_Product {

        private Dialog dialog;
        private View view;
        private Button btnSale;
        TextView name;
        TextView description;
        TextView score;
        ImageView img_product;
        Res_product res_product;

        public Dialog_Product Config(Context context, Res_product _res_product) {

            if (dialog == null) {
                dialog = new Dialog(context);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            }
            res_product=_res_product;
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dialog_product_select, null);
            init();
            fill();

            return this;
        }

    private void fill() {
        Picasso.get().load(res_product.getImage()).into(img_product);
        name.setText(res_product.getName());
        description.setText(res_product.getDescription());
        score.setText(res_product.getScoreNeed()+"");

    }

    private void init() {
            btnSale = view.findViewById(R.id.btnSale);
            name = view.findViewById(R.id.name);
            description = view.findViewById(R.id.description);
            img_product = view.findViewById(R.id.img_product);
            score=view.findViewById(R.id.score);


        }


        public Dialog_Product setOnClickListener(final Dialog_Product.OnDialogClickListener OnDialogClickListener) {

            btnSale.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {
                    dialog.dismiss();
                    if (OnDialogClickListener != null)
                        OnDialogClickListener.click (res_product);

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
            void click (Res_product item);
        }


    }
