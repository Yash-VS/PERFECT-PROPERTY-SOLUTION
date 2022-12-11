package com.example.ppt_application.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppt_application.Details_Flats;
import com.example.ppt_application.R;
import com.example.ppt_application.model.TemplateModel;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class template_adapter extends RecyclerView.Adapter<template_adapter.MyViewHolder> {

    List<TemplateModel> myTemplateList;
    Activity context;

    public template_adapter(List<TemplateModel> template_list, Activity context){
        this.myTemplateList = template_list;
        this.context = context;
    }

    @NonNull
    public template_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_product_layout, parent, false);

        return new template_adapter.MyViewHolder(itemView);
    }


    public void onBindViewHolder(@NonNull template_adapter.MyViewHolder holder, int position) {

        TemplateModel product= myTemplateList.get(position);
        holder.productImg.setVisibility(View.INVISIBLE);
        //holder.productImg.setImageResource(myTemplateList.get(position).getImageUrl());

        //int imgg = myTemplateList.get(position).getImageUrl();
        holder.layout.setBackground(ContextCompat.getDrawable(context, myTemplateList.get(position).getImageUrl()));
        //holder.layout.setBackground(myTemplateList.get(position).getDrawable());
        //holder.layout.setBackground(myTemplateList.get(position).getImageUrl());
        holder.name.setText(product.getName());
    }

    public int getItemCount() {
        return myTemplateList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ConstraintLayout layout;
        ImageView productImg;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            productImg = itemView.findViewById(R.id.category_image);
            name = itemView.findViewById(R.id.product_brand_name);
        }

        @Override
        public void onClick(View view) {
            //clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }
    }
}
