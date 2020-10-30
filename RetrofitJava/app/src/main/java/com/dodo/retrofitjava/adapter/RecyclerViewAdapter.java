package com.dodo.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dodo.retrofitjava.R;
import com.dodo.retrofitjava.model.CyrptoModel;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.rowHolder> {

    private ArrayList<CyrptoModel> cyrptoList;
    private  String[] colors = {"#c7a1ba","#0be7c9","#a896b6","#e7c90b","#c6cee3","#c6cee3","#2685c4","#c7a1ba"};

    public RecyclerViewAdapter(ArrayList<CyrptoModel> cyrptoList) {
        this.cyrptoList = cyrptoList;
    }

    @NonNull
    @Override
    public rowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_layout,parent,false);
        return new rowHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull rowHolder holder, int position) {

        holder.bind(cyrptoList.get(position),colors,position);
    }

    @Override
    public int getItemCount() {
        return cyrptoList.size();
    }

    public class rowHolder extends RecyclerView.ViewHolder {

        TextView textName;
        TextView textPrice;


        public rowHolder(@NonNull View itemView) {
            super(itemView);


        }

        public void bind(CyrptoModel cyrptoModel,String[] colors,Integer position){

            itemView.setBackgroundColor(Color.parseColor(colors[position % 8]));
            textName = itemView.findViewById(R.id.textView2);
            textPrice = itemView.findViewById(R.id.textView3);

            textName.setText(cyrptoModel.currency);
            textPrice.setText(cyrptoModel.price);


        }
    }
}
