package com.example.appstudent.Controller;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
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
import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;
import com.example.appstudent.URL.url;
import com.example.appstudent.adapter_type.AdapterThongTinCaNhan;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ThongTinCaNhanControll {
    public static void setThognTinChung(Context context, RecyclerView viewThongtinchung, RecyclerView CaNhan, String idsinhvien){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"GetthongTincaNhan.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Log.d(null,"SDEGEHSATHAT\nERWERWGFSDF$%^$%^$%\n#$%#$^#%^#$%\n\t"+response+"\n\n");
                        List<Pair<String,String> > listThongtinchung = new ArrayList<>();
                        List<Pair<String,String> > listCaNhan = new ArrayList<>();
                            if(!response.equals("0")){
                                try{
                                    JSONArray json = new JSONArray(response);
                                    for(int i = 0 ; i < json.length(); i ++){
                                        JSONObject obj = json.getJSONObject(i);
                                        listThongtinchung.add(new Pair<String,String>("Lớp",obj.getString("tenlop")));
                                        listThongtinchung.add(new Pair<String,String>("Chuyên Ngành",obj.getString("TenChuyenNganh")));
                                        listThongtinchung.add(new Pair<String,String>("Khoa",obj.getString("tenkhoa")));
                                        listThongtinchung.add(new Pair<String,String>("Hệ Đào Tạo",obj.getString("SoNam")));
                                        listCaNhan.add(new Pair<String,String>("Ngày Sinh",obj.getString("NgaySinh")));
                                        listCaNhan.add(new Pair<String,String>("Điện Thoai",obj.getString("Dienthoai")));
                                        listCaNhan.add(new Pair<String,String>("Email",obj.getString("Email")));
                                        listCaNhan.add(new Pair<String,String>("Giới tính",Integer.parseInt(obj.getString("GioiTinh")) == 1?"Nam":"Nữ") );
                                    }
                                }catch (Exception ex){
                                    Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d(null,ex.toString());
                                }
                            }

                        viewThongtinchung.setAdapter(new AdapterThongTinCaNhan(listThongtinchung));
                        CaNhan.setAdapter(new AdapterThongTinCaNhan(listCaNhan));
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
                data.put("idsinhvien",idsinhvien);
                return data;
            }
        };
        queue.add(request);
    }
    public static void CapNhatNgaysinhDienThoaiEmailGioiTinh(Context context,String[] str){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"capnhatThongTinSv.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("1")){
                            CapNhat_toast(context,R.drawable.correctly,"Cập nhật thành công !!");
                            TrangChu.current_sinhvien.setNgaySinh(str[0]);
                            TrangChu.current_sinhvien.setDienthoai(str[1]);
                            TrangChu.current_sinhvien.setEmail(str[2]);
                            TrangChu.current_sinhvien.setGioiTinh(Integer.parseInt(str[3]));
                            return;
                        }else{
                            CapNhat_toast(context,R.drawable.error,"Cập nhật thất bại !!");
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
                data.put("NgaySinh",str[0]);
                data.put("DienThoai",str[1]);
                data.put("Email",str[2]);
                data.put("GioiTinh",str[3]);
                data.put("idsinhvien",str[4]);
                return data;
            }
        };
        queue.add(request);
    }
    public static void CapNhat_toast(Context context,int id,String txt){
        Toast toast = new Toast(context);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.capnhatthongtincanhan,null);
        CircleImageView image = view.findViewById(R.id.image);
        TextView text = view.findViewById(R.id.text);
        image.setImageResource(id);
        text.setText(txt);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER,0,90);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
