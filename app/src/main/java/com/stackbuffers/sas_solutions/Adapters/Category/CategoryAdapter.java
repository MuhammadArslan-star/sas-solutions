package com.stackbuffers.sas_solutions.Adapters.Category;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.stackbuffers.sas_solutions.Model.Categories.CategoryModel;
import com.stackbuffers.sas_solutions.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    ArrayList<CategoryModel> list;

    private Context context;
    OnClickLitener litener;
    String imageUrl;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> list, String imageUrl){
        this.list=list;
        this.context=context;
        this.imageUrl=imageUrl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_category,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel categoryModel=list.get(position);

        holder.tvCategoryName.setText(categoryModel.getName());
        holder.ivCategoryImage.setImageResource(Integer.parseInt(categoryModel.getImage()));

        holder.rlCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                litener.onClick(position,categoryModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategoryImage;
        TextView tvCategoryName;
        RelativeLayout rlCategory;
        public ViewHolder(@NonNull View view) {
            super(view);
            ivCategoryImage=itemView.findViewById(R.id.ivCategoryImage);
            tvCategoryName=itemView.findViewById(R.id.tvCategoryName);
            rlCategory=itemView.findViewById(R.id.rlCategory);
        }
    }



    public interface OnClickLitener{
        public void onClick(int position, CategoryModel categoryModel);
    }

    public void setOnClickListener(OnClickLitener listener){
        this.litener=listener;

    }


}
