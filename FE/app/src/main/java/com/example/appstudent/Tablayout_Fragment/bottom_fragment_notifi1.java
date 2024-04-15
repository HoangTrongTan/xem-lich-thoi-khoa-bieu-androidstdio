package com.example.appstudent.Tablayout_Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appstudent.Controller.ThongBaoChung_Control;
import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.R;
public class bottom_fragment_notifi1 extends Fragment{

    private RecyclerView recycle_thongbao;
    private IClickListener iClickListener;
    public interface IClickListener{
        void passNotifi(ThongBaoChung_model model);
    }
    public bottom_fragment_notifi1() {

    }
    public void setFragmentContainer(int n){

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        iClickListener = (IClickListener) getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_notifi1, container, false);
        recycle_thongbao = view.findViewById(R.id.recycle_thongbao);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recycle_thongbao.setLayoutManager(linearLayoutManager);

        ThongBaoChung_Control.getThongBao(getContext(),"0",recycle_thongbao,iClickListener);
        return view;
    }
}