package com.stackbuffers.sas_solutions.Adapters.Tecniations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.stackbuffers.sas_solutions.Model.Categories.CategoryModel;
import com.stackbuffers.sas_solutions.Model.Tecnication.TecnitionModel;
import com.stackbuffers.sas_solutions.R;

import java.util.ArrayList;

public class TecnicationsAdapter extends RecyclerView.Adapter<TecnicationsAdapter.ViewHolder> {
    ArrayList<TecnitionModel> list;

    private Context context;
    OnClickLitener litener;
    String imageUrl;

    public TecnicationsAdapter(Context context/*, ArrayList<TecnitionModel> list*/){
        this.list=list;
//        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tecnications,parent,false);
        return new TecnicationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        CategoryModel categoryModel=list.get(position);

        holder.tvBookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TecnitionModel tecnitionModel=new TecnitionModel();
                tecnitionModel.setName(holder.tvName.getText().toString());
                litener.onClick(position,tecnitionModel);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvName,tvSkill,tvBookNow,tvRating;
        RatingBar ratingBar;
        CardView cvTecnication;
        public ViewHolder(@NonNull View view) {
            super(view);
            ivImage=itemView.findViewById(R.id.ivImage);
            tvName=itemView.findViewById(R.id.tvName);
            tvSkill=itemView.findViewById(R.id.tvSkill);
            tvBookNow=itemView.findViewById(R.id.tvBookNow);
            tvRating=itemView.findViewById(R.id.tvRating);
            ratingBar=itemView.findViewById(R.id.ratingBar);
            cvTecnication=itemView.findViewById(R.id.cvTecnication);
        }
    }



    public interface OnClickLitener{
        public void onClick(int position, TecnitionModel tecnitionModel);
    }

    public void setOnClickListener(OnClickLitener listener){
        this.litener=listener;

    }


}
