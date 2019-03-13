package com.notrika.regular_trafic.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notrika.regular_trafic.Entitie.Res_InQuery;
import com.notrika.regular_trafic.R;

import java.util.List;

public class Adapter_Recycle_List_Inquiry extends RecyclerView.Adapter<Adapter_Recycle_List_Inquiry.ViewHolder> {

        private List<Res_InQuery> mData;
        private LayoutInflater mInflater;
        private ItemClickListener mClickListener;

        public Adapter_Recycle_List_Inquiry(Context context, List<Res_InQuery> data) {
            this.mInflater = LayoutInflater.from(context);
            this.mData = data;
        }

        // inflates the row layout from xml when needed
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.item_recycle_list_inquiry, parent, false);
            return new ViewHolder(view);
        }

        // binds the data to the TextView in each row
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Res_InQuery viol = mData.get(position);
            holder.id_vio.setText(viol.getViolenceDesc());
        }

        // total number of rows
        @Override
        public int getItemCount() {
            return mData.size();
        }


        // stores and recycles views as they are scrolled off screen
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView id_vio;
            TextView discribtion_vio;

            ViewHolder(View itemView) {
                super(itemView);
                id_vio = itemView.findViewById(R.id.id_vio);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            }
        }


        // allows clicks events to be caught
        public void setClickListener(ItemClickListener itemClickListener) {
            this.mClickListener = itemClickListener;
        }

        // parent activity will implement this method to respond to click events
        public interface ItemClickListener {
            void onItemClick(View view, int position);
        }
    }


