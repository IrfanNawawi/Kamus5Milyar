package com.example.irfannawawi.kamus5milyar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.irfannawawi.kamus5milyar.R;
import com.example.irfannawawi.kamus5milyar.model.KamusItem;
import com.example.irfannawawi.kamus5milyar.view.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Irfan Nawawi on 20/01/2019.
 */

public class KamusAdapter extends RecyclerView.Adapter<KamusAdapter.MyViewHolder> {
    private List<KamusItem> kamusItems;
    private Context context;
    private LayoutInflater mInflater;

    public KamusAdapter(Context context) {
        this.context = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_kamus, parent, false);
        return new MyViewHolder(view);
    }

    public void addItem(ArrayList<KamusItem> kamusItems) {
        this.kamusItems = kamusItems;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.kata.setText(kamusItems.get(position).getKata());
        holder.arti.setText(kamusItems.get(position).getArti());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent data = new Intent(holder.itemView.getContext(), DetailActivity.class);
                data.putExtra(DetailActivity.DATA_KATA, kamusItems.get(position).getKata());
                data.putExtra(DetailActivity.DATA_ARTI, kamusItems.get(position).getArti());
                holder.itemView.getContext().startActivity(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return kamusItems.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView kata, arti;

        public MyViewHolder(View view) {
            super(view);
            kata = view.findViewById(R.id.tv_kata);
            arti = view.findViewById(R.id.tv_arti);
        }
    }
}
