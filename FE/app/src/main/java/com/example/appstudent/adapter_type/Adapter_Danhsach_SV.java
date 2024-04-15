package com.example.appstudent.adapter_type;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.R;

import java.util.List;

public class Adapter_Danhsach_SV extends RecyclerView.Adapter<Adapter_Danhsach_SV.ViewHolder> {
    List<SinhVien> arr;

    public Adapter_Danhsach_SV(List<SinhVien> arr) {
        this.arr = arr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_sinhvien_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SoThuTu.setText(String.valueOf(position));
        holder.HoTenSinhVien.setText(arr.get(position).getHoTen());
        holder.MaSinhVien.setText(arr.get(position).getMa());
        holder.Lop.setText(arr.get(position).getLop());

        Controller.setText_Colors(holder.HoTenSinhVien,new int[]{
                Color.parseColor("#F60303"),
                Color.parseColor("#D106F3"),
                Color.parseColor("#0328F6"),
                Color.parseColor("#06F60F"),
                Color.parseColor("#F1D906"),
                Color.parseColor("#F39408")
        });
    }

    @Override
    public int getItemCount() {
        if(arr != null){
            return arr.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView SoThuTu,HoTenSinhVien,MaSinhVien,Lop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            SoThuTu = itemView.findViewById(R.id.SoThuTu);
            HoTenSinhVien = itemView.findViewById(R.id.HoTenSinhVien);
            MaSinhVien = itemView.findViewById(R.id.MaSinhVien);
            Lop = itemView.findViewById(R.id.Lop);
        }
    }
}
