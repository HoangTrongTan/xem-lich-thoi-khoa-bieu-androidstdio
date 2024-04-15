package com.example.appstudent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appstudent.Controller.Controller;
import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.URL.url;
import com.example.appstudent.adapter_type.Adapter_Danhsach_SV;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LietKe_sv extends AppCompatActivity {

    private RecyclerView recycle_sinhvien;
    private Adapter_Danhsach_SV adapter;
    private ArrayList<SinhVien> list = new ArrayList<>();
    private String lop = "",Monhoc = null,magv = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liet_ke_sv2);

        Controller.turn_off_SSL();

        lop = getIntent().getStringExtra("class");
//        Toast.makeText(this, lop, Toast.LENGTH_LONG).show();
        if(TrangChu.curGiaoVien != null){
            Monhoc = getIntent().getStringExtra("monhoc");
            magv = getIntent().getStringExtra("magv");
        }


        recycle_sinhvien = findViewById(R.id.list_danhsach_sinhvien);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recycle_sinhvien.setLayoutManager(linearLayoutManager);
        adapter = new Adapter_Danhsach_SV(list);
        recycle_sinhvien.setAdapter(adapter);
        get_All_sinhvien(lop,list,adapter,Monhoc,magv);
    }
    private void get_All_sinhvien(String str,ArrayList<SinhVien> list,Adapter_Danhsach_SV adapter,String monhoc,String magv){
        RequestQueue queue = Volley.newRequestQueue(this);
        String path = url.path+"get_all_sinhvien.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(LietKe_sv.this, response, Toast.LENGTH_SHORT).show();
                        try{
                            JSONArray arr = new JSONArray(response);
                            for(int i = 0 ; i < arr.length(); i++){
                                JSONObject obj = arr.getJSONObject(i);
                                list.add(new SinhVien(obj.getString("hoten"),obj.getString("idsinhvien"),obj.getString("tenlop")));
                            }
                        }catch(Exception ex){
                            Toast.makeText(LietKe_sv.this, "Có lỗi xảy ra", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LietKe_sv.this, "Dữ liệu chưa được cập nhật lên", Toast.LENGTH_SHORT).show();
                        Log.d(null,error.toString());
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(magv != "" && monhoc != null){
                    data.put("magv",magv);
                    data.put("monhoc",monhoc);
                    return data;
                }
                data.put("class",str);
                return data;
            }
        };
        queue.add(request);
    }
}