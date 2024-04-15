package com.example.appstudent.adapter_type;

import android.opengl.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstudent.R;
import java.util.List;
import android.util.Pair;

public class AdapterQuyChe_QuyDinh extends RecyclerView.Adapter<AdapterQuyChe_QuyDinh.QuyChe_QuyDinhHolder>{

    private List<Pair<String,String>> list_model;

    public AdapterQuyChe_QuyDinh(List<Pair<String, String>> list_model1) {
        this.list_model = list_model1;
        Log.d(null,"\n\n\n"+list_model.get(0).first+"\n\n\n");
    }

    @NonNull
    @Override
    public QuyChe_QuyDinhHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuyChe_QuyDinhHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_quyche_quydinh,parent,false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull QuyChe_QuyDinhHolder holder, int position) {
        holder.tieude.setText(list_model.get(position).first);
        holder.link.setText("Link: "+list_model.get(position).second);
        if (holder.link.getVisibility() == View.VISIBLE) {
            holder.link.setVisibility(View.GONE);
        } else {
            holder.link.setVisibility(View.VISIBLE);
        }
        holder.expan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.link.getVisibility() == View.VISIBLE) {
                    holder.link.setVisibility(View.GONE);
                } else {
                    holder.link.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(list_model != null)
        {
            return list_model.size();
        }
        return 0;
    }

    public class QuyChe_QuyDinhHolder extends RecyclerView.ViewHolder{
        private TextView tieude,link;
        private LinearLayout expan;
        public QuyChe_QuyDinhHolder(@NonNull View itemView) {
            super(itemView);
            tieude = itemView.findViewById(R.id.tieude);
            link = itemView.findViewById(R.id.link);
            expan = itemView.findViewById(R.id.expan);
        }
    }
}
