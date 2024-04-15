package com.example.appstudent.Adapter_Tuan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Model.model_Tuan_child;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;

import java.util.ArrayList;

public class Adapter_child extends RecyclerView.Adapter<Adapter_child.childHolder>{

    ArrayList<model_Tuan_child> child_list;

    public Adapter_child(ArrayList<model_Tuan_child> child_list) {
        this.child_list = child_list;
    }

    @NonNull
    @Override
    public childHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tuan_child,parent,false);
        return new childHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull childHolder holder, int position) {
        holder.tiet_firl_last.setText("Tiết học: "+child_list.get(position).getTietbatdau()+" - "+child_list.get(position).getTietketthuc());
        holder.sophong.setText(child_list.get(position).getSophong());
        holder.tenmon.setText(child_list.get(position).getTenmon());
        holder.tenlop.setText(child_list.get(position).getTenlop());
        if(TrangChu.current_sinhvien != null){
            holder.giangvien.setText(child_list.get(position).getTengiaovien());
        }


        holder.list_item_tuan.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if(child_list != null){
            return child_list.size();
        }
        return 0;
    }

    public class childHolder extends RecyclerView.ViewHolder{
        private LinearLayout list_item_tuan;
        private TextView tiet_firl_last,sophong,giangvien,tenmon,tenlop;
        public childHolder(@NonNull View itemView) {
            super(itemView);
            tiet_firl_last = itemView.findViewById(R.id.tietbd_kt);
            sophong = itemView.findViewById(R.id.sophong);
            giangvien = itemView.findViewById(R.id.giangvien);
            tenmon = itemView.findViewById(R.id.tenmon);
            tenlop = itemView.findViewById(R.id.tenlop);

            list_item_tuan = itemView.findViewById(R.id.list_item_tuan);
        }
    }
}
