package com.example.appstudent.adapter_type;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.R;

import java.util.List;

public class AdapterThongTinCaNhan extends RecyclerView.Adapter<AdapterThongTinCaNhan.ThongTinCaNhanHolder>{

    private List<Pair<String,String>> list_sv;

    public AdapterThongTinCaNhan(List<Pair<String,String>> list_sv) {
        this.list_sv = list_sv;
    }

    @NonNull
    @Override
    public ThongTinCaNhanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ThongTinCaNhanHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_thongtincanhan,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ThongTinCaNhanHolder holder, int position) {
        holder.txt1.setText(list_sv.get(position).first);
        holder.txt2.setText(list_sv.get(position).second);
    }

    @Override
    public int getItemCount() {
        if(list_sv != null){
            return list_sv.size();
        }
        return 0;
    }

    public class ThongTinCaNhanHolder extends RecyclerView.ViewHolder{
        private TextView txt1,txt2;
        public ThongTinCaNhanHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
        }
    }
}
