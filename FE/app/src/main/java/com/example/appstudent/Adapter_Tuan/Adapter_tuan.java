package com.example.appstudent.Adapter_Tuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Model.model_Tuan;
import com.example.appstudent.R;

import java.util.ArrayList;

public class Adapter_tuan extends RecyclerView.Adapter<Adapter_tuan.tuanHolder>{

    Context m_Context;
    ArrayList<model_Tuan> tuan_list;

    public Adapter_tuan(ArrayList<model_Tuan> tuan_list) {
        this.tuan_list = tuan_list;
    }

    @NonNull
    @Override
    public tuanHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tuan,parent,false);
        return new tuanHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull tuanHolder holder, int position) {
        holder.Thu.setText(tuan_list.get(position).getThu());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m_Context,RecyclerView.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        Adapter_Tuan_parent adapter = new Adapter_Tuan_parent(m_Context);
        adapter.setData(tuan_list.get(position).getTuan());
        holder.recyclerView.setAdapter(adapter);

        boolean isExpanble = tuan_list.get(position).isExpandbel();
        holder.relativeLayout.setVisibility(isExpanble ? View.VISIBLE : View.GONE);


        int vitri = position;
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model_Tuan model_tuan = tuan_list.get(vitri);
                model_tuan.setExpandbel(!model_tuan.isExpandbel());
                notifyItemChanged(vitri);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(tuan_list != null){
            return tuan_list.size();
        }
        return 0;
    }

    public class tuanHolder extends RecyclerView.ViewHolder{
        private TextView Thu;
        private RecyclerView recyclerView;
        private LinearLayout linearLayout;
        private RelativeLayout relativeLayout;
        public tuanHolder(@NonNull View itemView) {
            super(itemView);
            Thu = itemView.findViewById(R.id.Tuan_thu);
            recyclerView = itemView.findViewById(R.id.recycle_parent);

            linearLayout = itemView.findViewById(R.id.linearlayout);
            relativeLayout = itemView.findViewById(R.id.expandble_Relative);


        }
    }
}
