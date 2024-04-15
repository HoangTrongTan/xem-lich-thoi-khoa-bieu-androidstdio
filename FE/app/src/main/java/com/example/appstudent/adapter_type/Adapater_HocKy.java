package com.example.appstudent.adapter_type;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnclickButton;
import com.example.appstudent.Model.HocKy;
import com.example.appstudent.R;

import java.util.ArrayList;

public class Adapater_HocKy extends RecyclerView.Adapter<Adapater_HocKy.HockY_Holder>{

    ArrayList<HocKy> list_hocky;
    OnclickButton button;

    public Adapater_HocKy(ArrayList<HocKy> list_hocky,OnclickButton button) {
        this.list_hocky = list_hocky;
        this.button = button;
    }

    @NonNull
    @Override
    public HockY_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_hocky,parent,false);

        return new HockY_Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HockY_Holder holder, int position) {
        holder.tenmon_hocky.setText(list_hocky.get(position).getMonhoc());
        holder.mamonhoc_hocky.setText(list_hocky.get(position).getMamonhoc());
        holder.giangvien.setText(list_hocky.get(position).getGiangvien());
        holder.tongsotiet_hocky.setText("Tổng số tiết: "+list_hocky.get(position).getTongsotiet());
        holder.thoigianhoc_hocky.setText("Thời gian: "+list_hocky.get(position).getThoigiannhoc());

        int i = position;
        holder.danhsachsinhvien_hocky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.OnClickButton(list_hocky.get(i).getMonhoc());
            }
        });

        boolean isExpandble = list_hocky.get(position).isExxpandble();
        holder.expandble.setVisibility(isExpandble ? View.VISIBLE : View.GONE);
        int vitri = position;
        holder.linear_hocky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HocKy hocKy = list_hocky.get(vitri);
                hocKy.setExxpandble(!hocKy.isExxpandble());
                notifyItemChanged(vitri);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list_hocky != null){
            return list_hocky.size();
        }
        return 0;
    }

    public class HockY_Holder extends RecyclerView.ViewHolder{
        private TextView tenmon_hocky,mamonhoc_hocky,giangvien,tongsotiet_hocky,thoigianhoc_hocky;
        private LinearLayout linear_hocky,expandble;
        private Button danhsachsinhvien_hocky;
        public HockY_Holder(@NonNull View itemView) {
            super(itemView);
            tenmon_hocky = itemView.findViewById(R.id.tenmon_hocky);
            mamonhoc_hocky = itemView.findViewById(R.id.mamonhoc_hocky);
            giangvien = itemView.findViewById(R.id.giangvien);
            tongsotiet_hocky = itemView.findViewById(R.id.tongsotiet_hocky);
            thoigianhoc_hocky = itemView.findViewById(R.id.thoigianhoc_hocky);
            danhsachsinhvien_hocky = itemView.findViewById(R.id.danhsachsinhvien_hocky);

            linear_hocky = itemView.findViewById(R.id.linear_hocky);
            expandble = itemView.findViewById(R.id.expandble);
        }
    }
}
