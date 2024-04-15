package com.example.appstudent.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appstudent.I_OnclickLongItem_of_RecycleView.ClickThongBao;
import com.example.appstudent.Model.ThongBaoChung_model;
import com.example.appstudent.Tablayout_Fragment.bottom_fragment_notifi1;
import com.example.appstudent.URL.url;
import com.example.appstudent.adapter_type.AdapterThongBao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThongBaoChung_Control {

    public static void  getThongBao(Context context, String LoaiThongBao, RecyclerView recyclerView, bottom_fragment_notifi1.IClickListener iClickListener){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"getThongBao.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d(null,"SDEGEHSATHAT\nERWERWGFSDF$%^$%^$%\n#$%#$^#%^#$%\n\t"+response+"\n\n");
                        List<ThongBaoChung_model> list_model = new ArrayList<>();
                        try{
                            JSONArray json = new JSONArray(response);
                            for(int i = 0 ; i < json.length(); i ++){
                                JSONObject obj = json.getJSONObject(i);
                                list_model.add(new ThongBaoChung_model(obj.getString("tieude"),obj.getString("thoigian"),obj.getString("noidung"), Integer.parseInt(obj.getString("loaianh") ) ));
                            }
                            AdapterThongBao adapterThongBao = new AdapterThongBao(list_model, new ClickThongBao() {
                                @Override
                                public void OnclickItem(ThongBaoChung_model model) {
                                    iClickListener.passNotifi(model);
                                }
                            });
                            recyclerView.setAdapter(adapterThongBao);
                        }catch (Exception ex){
                            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
                            Log.d(null,ex.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d(null,error.toString());
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("loaithongbao",LoaiThongBao);
                return data;
            }
        };
        queue.add(request);
    }
}
