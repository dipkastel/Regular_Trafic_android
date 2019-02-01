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
import com.notrika.regular_trafic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Adapter_Grid_Violenc extends BaseAdapter {
    private Context mContext;
    ArrayList<Res_Violenc> selectedids=new ArrayList<>();

    List<Res_Violenc> items=new ArrayList<>();
    // Constructor
    public Adapter_Grid_Violenc(Context c, List<Res_Violenc> _items) {
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

        TextView txt;
        TextView txt_1;
        final LinearLayout linear_bg;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.item_gridview_violens, parent, false);
        imageView = view.findViewById(R.id.img);
        txt = view.findViewById(R.id.txt);
        linear_bg=view.findViewById(R.id.linear_bg);
        txt_1 = view.findViewById(R.id.txt_1);
        Picasso.get().load(items.get(position).getImage()).into(imageView);
        txt.setText(items.get(position).getName());
        txt_1.setText(items.get(position).getDescription());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(selectedids.contains(items.get(position))){

                    selectedids.remove(items.get(position));
                    linear_bg.setBackgroundResource(R.drawable.shapeitemgridborder);
                }else{
                    if(selectedids.size()>=3)return;
                    selectedids.add(items.get(position));
                    linear_bg.setBackgroundResource(R.drawable.shapeitemgridborderselected);
                }

            }
        });

        return view;
    }
    public ArrayList<Res_Violenc> getSelectedViolenc(){
        return selectedids;
    }

}