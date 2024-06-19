package com.trinhnguyenvyna.d0_4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.trinhnguyenvyna.d0_4.SQLTest;
import com.trinhnguyenvyna.d0_4.R;
import com.trinhnguyenvyna.d0_4.models.Books;

import java.util.List;

public class Adapterthis extends BaseAdapter {
    SQLTest context;
    int item_layout;
    List<Books> book;

    public Adapterthis(Context context, int item_layout, List<Books> book) {
        this.context = (SQLTest) context;
        this.item_layout = item_layout;
        this.book = book;
    }

    @Override
    public int getCount() {
        return book.size();
    }

    @Override
    public Object getItem(int position) {
        return book.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(item_layout, null);

            // Linking view
            holder.txtInfo = convertView.findViewById(R.id.textInfo);
            /*holder.imgEdit = convertView.findViewById(R.id.imgEdit);
            holder.imgDel = convertView.findViewById(R.id.imgDel);*/

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // binding data
        Books p = book.get(position);
        holder.txtInfo.setText(String.format("Mã sách: %s \n" +
                "Tên sách:  %s \n" +
                "Giá sách:  %s \n", p.getbookCode(), p.getbookName(), String.format("%.0f đ", p.getbookPrice())));
        /*holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
              *//*  context.openEditDialog(p);*//*
            }
        });
        holder.imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                *//*context.openDeleteConfirmDialog(p);*//*
            }
        });*/

        return convertView;
    }

    public static class ViewHolder {
        TextView txtInfo;
      /*  ImageView imgEdit, imgDel;*/
    }
}
