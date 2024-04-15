package com.example.appstudent.adapter_type;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.I_OnclickLongItem_of_RecycleView.ClickThongBao;
import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.R;

import java.util.List;

public class AdapterThongBao extends RecyclerView.Adapter<AdapterThongBao.ThongBaoHolder>{

    private List<ThongBaoChung_model> list;
    private ClickThongBao thongBao;

    public AdapterThongBao(List<ThongBaoChung_model> list,ClickThongBao thongbao) {
        this.list = list;
        this.thongBao = thongbao;
    }

    @NonNull
    @Override
    public ThongBaoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongBaoHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thongbaochung,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoHolder holder, int position) {
        holder.tieude.setText(list.get(position).getTitle());
        holder.thoigiangieuluc.setText(list.get(position).getThoigianhieuluc());
        holder.image_thongbaochung.setImageResource(list.get(position).getCheckTypeNotifi());

        int vitri = position;
        holder.itemThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thongBao.OnclickItem(list.get(vitri));
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list != null){
            return list.size();
        }
        return 0;
    }

    public class ThongBaoHolder extends RecyclerView.ViewHolder{
        private RelativeLayout itemThongBao;
        private TextView tieude,thoigiangieuluc;
        private ImageView image_thongbaochung;
        public ThongBaoHolder(@NonNull View itemView) {
            super(itemView);
            tieude = itemView.findViewById(R.id.tieude);
            thoigiangieuluc = itemView.findViewById(R.id.thoigiangieuluc);
            image_thongbaochung = itemView.findViewById(R.id.image_thongbaochung);
            itemThongBao = itemView.findViewById(R.id.itemThongBao);
        }
    }
}
