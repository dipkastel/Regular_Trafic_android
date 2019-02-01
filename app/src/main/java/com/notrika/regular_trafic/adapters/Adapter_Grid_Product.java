package com.notrika.regular_trafic.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.notrika.regular_trafic.Entitie.Res_Violenc;
import com.notrika.regular_trafic.Entitie.Res_product;
import com.notrika.regular_trafic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Grid_Product extends BaseAdapter {
    private Context mContext;

    List<Res_product> items = new ArrayList<>();
    onclicklistner onclicklistner;
    // Constructor
    public Adapter_Grid_Product(Context c, List<Res_product> _items) {
        mContext = c;
        this.items = _items;
    }


    public int getCount() {
        return items.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        final ImageView imageView;
        TextView name;
        TextView txtscore;


        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.item_gridview_product, parent, false);


        imageView = view.findViewById(R.id.img);
        name = view.findViewById(R.id.name);
        txtscore = view.findViewById(R.id.txtscore);



        Picasso.get().load(items.get(position).getImage()).into(imageView);
        name.setText(items.get(position).getName());
        txtscore.setText(items.get(position).getScoreNeed()+"");


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if(onclicklistner!=null)
                onclicklistner.onclick(items.get(position));
            }
        });

        return view;
    }
    public interface onclicklistner {

        void onclick(Res_product item);


    }
    public  void setOnclicklistner (onclicklistner onclicklistner){

        this.onclicklistner=onclicklistner;
    }

}