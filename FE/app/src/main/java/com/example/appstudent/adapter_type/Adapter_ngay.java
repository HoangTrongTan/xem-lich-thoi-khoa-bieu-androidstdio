package com.example.appstudent.adapter_type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnClickLongItem;
import com.example.appstudent.Model.model_ngay;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;


import java.util.List;

public class Adapter_ngay extends RecyclerView.Adapter<Adapter_ngay.Holder_ngay>{

    List<model_ngay> list_model_ngay;
    OnClickLongItem ClicklongItem;

    public Adapter_ngay(List<model_ngay> list_model_ngay, OnClickLongItem clicklongItem) {
        this.list_model_ngay = list_model_ngay;
        ClicklongItem = clicklongItem;
    }

    @NonNull
    @Override
    public Holder_ngay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);

        return new Holder_ngay(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder_ngay holder, int position){
        holder.tiet_firl_last.setText("Tiết học: "+list_model_ngay.get(position).getTietbatdau()+" - "+list_model_ngay.get(position).getTietketthuc());
        holder.sophong.setText(list_model_ngay.get(position).getSophong());
        holder.tenmon.setText(list_model_ngay.get(position).getTenmon());
        holder.tenlop.setText(list_model_ngay.get(position).getTenlop());
        if(TrangChu.curGiaoVien == null){
            holder.giangvien.setText(list_model_ngay.get(position).getHoten());
        }
//
        int vitri = position;
        holder.list_item_ngay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClicklongItem.OnClickLongListener_Item(list_model_ngay.get(vitri));
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list_model_ngay != null){
            return list_model_ngay.size();
        }
        return 0;
    }

    public class Holder_ngay extends RecyclerView.ViewHolder{
        private LinearLayout list_item_ngay;
        private TextView tiet_firl_last,sophong,giangvien,tenmon,tenlop;
        public Holder_ngay(@NonNull View itemView) {
            super(itemView);
            list_item_ngay = itemView.findViewById(R.id.list_item_ngay);
            tiet_firl_last = itemView.findViewById(R.id.tiet_firl_last);
            sophong = itemView.findViewById(R.id.sophong);
            tenmon = itemView.findViewById(R.id.tenmon);
            tenlop = itemView.findViewById(R.id.tenlop);
            giangvien = itemView.findViewById(R.id.giangvien);
        }
    }
}
