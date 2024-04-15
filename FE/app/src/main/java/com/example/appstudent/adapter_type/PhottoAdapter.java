package com.example.appstudent.adapter_type;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appstudent.Model.Photo;
import com.example.appstudent.R;

import java.util.List;

public class PhottoAdapter extends RecyclerView.Adapter<PhottoAdapter.PhoToviewHolder>{

    private final List<Photo> list_photo;

    public PhottoAdapter(List<Photo> list_photo) {
        this.list_photo = list_photo;
    }

    @NonNull
    @Override
    public PhoToviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PhoToviewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.itemphoto, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull PhoToviewHolder holder, int position) {
        holder.photoimage.setImageResource(list_photo.get(position).getResuorceId());
    }

    @Override
    public int getItemCount() {
        if(list_photo != null){
            return list_photo.size();
        }
        return 0;
    }

    public static class PhoToviewHolder extends RecyclerView.ViewHolder{
        private final ImageView photoimage;
        public PhoToviewHolder(@NonNull View itemView) {
            super(itemView);
            photoimage = itemView.findViewById(R.id.photoimage);
        }
    }
}
