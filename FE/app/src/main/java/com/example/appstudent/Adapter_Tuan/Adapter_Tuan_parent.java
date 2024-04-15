package com.example.appstudent.Adapter_Tuan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appstudent.Model.model_Tuan_Parent;
import com.example.appstudent.R;

import java.util.ArrayList;

public class Adapter_Tuan_parent extends RecyclerView.Adapter<Adapter_Tuan_parent.parentHolder>{
    ArrayList<model_Tuan_Parent> parent;
    Context m_Context;
    public Adapter_Tuan_parent(Context m_Context) {
        this.m_Context = m_Context;
    }
    public void setData(ArrayList<model_Tuan_Parent> parent){
        this.parent = parent;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public parentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_tuan_parent,parent,false);
        return new parentHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull parentHolder holder, int position) {
        holder.Thu.setText(parent.get(position).getThu());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(m_Context,RecyclerView.VERTICAL,false);
        holder.recyclerView.setLayoutManager(linearLayoutManager);

        Adapter_child adapter_child = new Adapter_child(parent.get(position).getParent());
        holder.recyclerView.setAdapter(adapter_child);
    }
    @Override
    public int getItemCount() {
        if(parent != null){
            return parent.size();
        }
        return 0;
    }
    public class parentHolder extends RecyclerView.ViewHolder{
        private TextView Thu;
        private RecyclerView recyclerView;

        public parentHolder(@NonNull View itemView) {
            super(itemView);
            Thu = itemView.findViewById(R.id.tuan_buoi);
            recyclerView = itemView.findViewById(R.id.reycle_tuan_parent);
        }
    }
}
