package com.example.ppt_application.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ppt_application.R;
import com.example.ppt_application.model.Land_Model;
import com.example.ppt_application.model.TemplateModel;

import java.util.List;

public class Land_Adapter extends RecyclerView.Adapter<Land_Adapter.MyViewHolder>{

    List<Land_Model> myTemplateList;
    Activity context;

    public Land_Adapter(List<Land_Model> template_list, Activity context){
        this.myTemplateList = template_list;
        this.context = context;
    }

    @NonNull
    @Override
    public Land_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items, parent, false);
        return new Land_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Land_Adapter.MyViewHolder holder, int position) {
        Land_Model product= myTemplateList.get(position);
        //holder.productImg.setImageResource(myTemplateList.get(position).getImageUrl());

        //int imgg = myTemplateList.get(position).getImageUrl();
        //holder.layout.setBackground(myTemplateList.get(position).getDrawable());
        //holder.layout.setBackground(myTemplateList.get(position).getImageUrl());
        holder.name.setText(product.getName());

    }

    public int getItemCount() {
        return myTemplateList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ConstraintLayout layout;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.layout);
            name = itemView.findViewById(R.id.product_brand_name);
        }

        @Override
        public void onClick(View view) {
            //clickListener.onClick(view, getPosition()); // call the onClick in the OnItemClickListener
        }
    }
}
