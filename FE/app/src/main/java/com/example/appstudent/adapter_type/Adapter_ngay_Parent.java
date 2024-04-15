package com.example.appstudent.adapter_type;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Controller.Controller;
import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnClickLongItem;
import com.example.appstudent.Model.model_ngay;
import com.example.appstudent.Model.model_ngay_Parent;
import com.example.appstudent.R;

import java.util.ArrayList;

public class Adapter_ngay_Parent extends RecyclerView.Adapter<Adapter_ngay_Parent.ParentHolder>{
    private ArrayList<model_ngay_Parent> list_parent;
    private Context m_conText;

    public Adapter_ngay_Parent(Context m_conText) {
        this.m_conText = m_conText;
    }
    public void setData(ArrayList<model_ngay_Parent> list_parent){
        this.list_parent = list_parent;
    }

    @NonNull
    @Override
    public ParentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ngay_parent,parent,false);
        return new ParentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentHolder holder, int position) {
        holder.buoihoc.setText(list_parent.get(position).getTitle());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m_conText,RecyclerView.VERTICAL,false);
        holder.recycle_parent.setLayoutManager(linearLayoutManager);
        Adapter_ngay adapter_ngay = new Adapter_ngay(list_parent.get(position).getList(), new OnClickLongItem() {
            @Override
            public void OnClickLongListener_Item(model_ngay model_ngay) {
                Controller.opendialog_sinhvien(m_conText, Gravity.CENTER,model_ngay);
            }
        });
        holder.recycle_parent.setAdapter(adapter_ngay);


    }

    @Override
    public int getItemCount() {
        if(list_parent != null){
            return list_parent.size();
        }
        return 0;
    }

    public class ParentHolder extends RecyclerView.ViewHolder{
        private TextView buoihoc;
        private RecyclerView recycle_parent;
        public ParentHolder(@NonNull View itemView) {
            super(itemView);
            this.buoihoc = itemView.findViewById(R.id.buoihoc);
            this.recycle_parent = itemView.findViewById(R.id.recycle_parent);
        }
    }
}
