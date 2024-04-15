package com.example.appstudent.Controller;

import static java.time.LocalDate.now;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextPaint;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appstudent.Adapter_Tuan.Adapter_tuan;
import com.example.appstudent.I_OnclickLongItem_of_RecycleView.OnclickButton;
import com.example.appstudent.LietKe_sv;
import com.example.appstudent.Model.HocKy;
import com.example.appstudent.Model.SinhVien;
import com.example.appstudent.Model.model_Tuan;
import com.example.appstudent.Model.model_Tuan_Parent;
import com.example.appstudent.Model.model_Tuan_child;
import com.example.appstudent.Model.model_ngay;
import com.example.appstudent.Model.model_ngay_Parent;
import com.example.appstudent.R;
import com.example.appstudent.TabMain;
import com.example.appstudent.TrangChu;
import com.example.appstudent.URL.url;
import com.example.appstudent.adapter_type.Adapater_HocKy;
import com.example.appstudent.adapter_type.Adapter_ngay_Parent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Controller {
    public static String lop = "";
    public static int[] date_month_present = new int[2];
    public static String d_m_y_present = null;
    public static String gg = "";
    public static String get_Month_Year_present(){
        String str = "";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDate today = LocalDate.now();
            DayOfWeek dayOfWeek = today.getDayOfWeek();
            str = "Tháng "+today.getMonthValue()+" "+today.getYear();
            date_month_present[0] = dayOfWeek.getValue()+1;
            date_month_present[1] = today.getDayOfMonth();
            d_m_y_present = today.getYear()+"-"+today.getMonthValue()+"-"+today.getDayOfMonth();
        }
        return str;
    }
    //Tắt chứng chỉ SSl
    public static void turn_off_SSL(){
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //Lấy object from php
        public static void getJsonArray_ngay(Context context,RecyclerView view ,String lop,String magv){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"get_date_present.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ArrayList<model_ngay_Parent> model_ngay_parent = new ArrayList<>();
                        ArrayList<model_ngay> model_ngayList = new ArrayList<>();
                        try{
                            JSONArray array = new JSONArray(response);
                            for(int i = 0 ; i < array.length();i++){
                                JSONObject obj = array.getJSONObject(i);
                                model_ngayList.add(new model_ngay(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                            }
                            ArrayList<model_ngay> sang = new ArrayList<>();
                            ArrayList<model_ngay> chieu = new ArrayList<>();
                            ArrayList<model_ngay> toi = new ArrayList<>();
                            for(model_ngay item:model_ngayList){
                                if(Integer.parseInt(item.getTietbatdau()) < 5){
                                    sang.add(item);
                                }
                                else if(Integer.parseInt(item.getTietketthuc()) > 5 && Integer.parseInt(item.getTietbatdau()) < 10){
                                    chieu.add(item);
                                }
                                else if(Integer.parseInt(item.getTietketthuc()) > 10 && Integer.parseInt(item.getTietbatdau()) < 12){
                                    toi.add(item);
                                }
                            }
                            model_ngay_parent.add(new model_ngay_Parent("Buổi sáng",sang));
                            model_ngay_parent.add(new model_ngay_Parent("Buổi chiều",chieu));
                            model_ngay_parent.add(new model_ngay_Parent("Buổi tối",toi));
                            Adapter_ngay_Parent adapterNgayParent = new Adapter_ngay_Parent(context);
                            adapterNgayParent.setData(model_ngay_parent);
                            view.setAdapter(adapterNgayParent);

                        }catch(Exception ex){
                            Toast.makeText(context, "không có dữ liệu", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(!lop.isEmpty()){
                    data.put("lop",lop);
                    data.put("d_m_y",d_m_y_present);
                }else{
                    data.put("magv",magv);
                    data.put("d_m_y",d_m_y_present);
                }
                return data;
            }
        };
        queue.add(request);
    }
    public static void get_date_of_choose(Context context, String date, RecyclerView recyclerView, String lop,String magv){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"get_date_present.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(TrangChu.curGiaoVien == null){
                            Log.d(null,"@#$%^&@#$%\nwertyert\n234567\nGiaos viên bị null");
                        }else{
                            Log.d(null,"!@$@#@^$%\n@!%#&@$*65\n21364@&$^#&*%\nSinh viên bị null");
                        }
                        Log.d(null,"$$$$$$$$$$$$$$$\n$$$#@###############@@@@@\n@@@@@@@@@@@@!!!!!!!!!!!!!!!!!!!!!!!\n\n\t"+response+" -date-"+date+"--lop: "+lop);
                        ArrayList<model_ngay> arr = new ArrayList<>();
                        try{
                            JSONArray js = new JSONArray(response);
                            for(int i = 0 ;i < js.length(); i ++){
                                JSONObject obj = js.getJSONObject(i);
                                arr.add(new model_ngay(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                            }
                        }catch(Exception ex){
                            ex.printStackTrace();
                        }
                        ArrayList<model_ngay_Parent> parents = new ArrayList<>();

                        ArrayList<model_ngay> sang = new ArrayList<>();
                        ArrayList<model_ngay> chieu = new ArrayList<>();
                        ArrayList<model_ngay> toi = new ArrayList<>();
                        for(model_ngay item:arr){
                            if(Integer.parseInt(item.getTietbatdau()) < 5){
                                sang.add(item);
                            }
                            else if(Integer.parseInt(item.getTietketthuc()) > 5 && Integer.parseInt(item.getTietbatdau()) < 10){
                                chieu.add(item);
                            }
                            else if(Integer.parseInt(item.getTietketthuc()) > 10 && Integer.parseInt(item.getTietbatdau()) < 12){
                                toi.add(item);
                            }
                        }
                        parents.add(new model_ngay_Parent("Buổi sáng",sang));
                        parents.add(new model_ngay_Parent("Buổi chiều",chieu));
                        parents.add(new model_ngay_Parent("Buổi tối",toi));

                        Adapter_ngay_Parent adapterNgayParent = new Adapter_ngay_Parent(context);
                        adapterNgayParent.setData(parents);
                        recyclerView.setAdapter(adapterNgayParent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(!magv.isEmpty()){
                    data.put("d_m_y",date);
                    data.put("magv",magv);
                    return data;
                }
                data.put("d_m_y",date);
                data.put("lop",lop);
                return data;
            }
        };
        queue.add(request);
    }


    public static void  opendialog_sinhvien(Context context,int Gravity,model_ngay model){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_danhsach_sv);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams atribuites = window.getAttributes();
        atribuites.gravity = Gravity;
        window.setAttributes(atribuites);

        TextView tenmon,tietbatdau,tietketthuc,sophong;
        Button danhsachsinhvien;

        tenmon = dialog.findViewById(R.id.tenmon);
        tietbatdau = dialog.findViewById(R.id.tietbatdau);
        tietketthuc = dialog.findViewById(R.id.tietketthuc);
        sophong = dialog.findViewById(R.id.phong);
        danhsachsinhvien = dialog.findViewById(R.id.danhsachsinhvien);

        tenmon.setText(model.getTenmon());
        tietbatdau.setText("Tiết bắt đầu: "+model.getTietbatdau());
        tietketthuc.setText("Tiết kết thúc: "+model.getTietketthuc());
        sophong.setText("Số phòng: "+model.getSophong());

        danhsachsinhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, LietKe_sv.class);
                intent.putExtra("class",model.getTenlop());
                context.startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public static void setText_Colors(TextView textView,int...color)
    {
        TextPaint paint = textView.getPaint();
        float width = paint.measureText(textView.getText().toString());

        Shader shader = new LinearGradient(0,0,width,textView.getTextSize(),color,null,Shader.TileMode.CLAMP);
        textView.getPaint().setShader(shader);
        textView.setTextColor(color[0]);
    }
    public static void setText_Btn_Colors(Button button,int...color)
    {
        TextPaint paint = button.getPaint();
        float width = paint.measureText(button.getText().toString());

        Shader shader = new LinearGradient(0,0,width,button.getTextSize(),color,null,Shader.TileMode.CLAMP);
        button.getPaint().setShader(shader);
        button.setTextColor(color[0]);
    }
    public static void startAnimaton(Context context,TextView textView){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim);
        textView.startAnimation(animation);
    }
    public static void startAnimaton(Context context,RecyclerView recyclerView){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.anim);
        recyclerView.startAnimation(animation);
    }
    public static void startAnimaton_circle(Context context,RecyclerView recyclerView){
        Animation animation = AnimationUtils.loadAnimation(context,R.anim.ani_cirrcle);
        recyclerView.startAnimation(animation);
    }
    //Nhập thông tin sinh viên Dialog
    public static void  opendialog_NhapMaSinhVien(Context context,int Gravity){
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_nhapmasv);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams atribuites = window.getAttributes();
        atribuites.gravity = Gravity;
        window.setAttributes(atribuites);

        EditText nhapmasv = dialog.findViewById(R.id.nhapmasv);
        Button huybo = dialog.findViewById(R.id.huybo);
        Button vaotrang = dialog.findViewById(R.id.vaotrang);
        huybo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        vaotrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login_Sinhvien(context,nhapmasv.getText().toString());
            }
        });

        dialog.show();
    }

        public static void Login_Sinhvien(Context context,String masinhvien){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"check_masv.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("")){

                            Toast toast = new Toast(context);

                            LayoutInflater inflater = LayoutInflater.from(context);
                            View view = inflater.inflate(R.layout.custom_toast,null);
                            toast.setView(view);
                            toast.setGravity(Gravity.CENTER,0,90);
                            toast.setDuration(Toast.LENGTH_LONG);
                            toast.show();
                        }else{
                            String[] arr = response.split(",");
                            Intent intent = new Intent(context, TrangChu.class);
                            if(arr[0].equals("1")){
                                intent.putExtra("checked",arr[0]);
                                intent.putExtra("lop",arr[1]);
                                intent.putExtra("tensv",arr[2]);
                                intent.putExtra("masv",arr[3]);
                                intent.putExtra("NgaySinh",arr[4]);
                                intent.putExtra("Dienthoai",arr[5]);
                                intent.putExtra("Email",arr[6]);
                                intent.putExtra("GioiTinh",arr[7]);
                            }else {
                                intent.putExtra("checked",arr[0]);
                                intent.putExtra("tensv",arr[1]);
                                intent.putExtra("magv",arr[2]);
                                intent.putExtra("sdt",arr[3]);
                                intent.putExtra("tenkhoa",arr[4]);
                            }
                            context.startActivity(intent);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Có lỗi xảy ra !!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                data.put("masinhvien",masinhvien);
                return data;

            }
        };
        queue.add(request);
    }
//    làm do nha
    public static void get_Week_present(Context context, ArrayList<model_Tuan> list_tuan,Button button,String lop,int day,String magv){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"get_week_present.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        LocalDate today = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            today = LocalDate.now();
                        }
                        ArrayList<String> all_thu = get_Weeks(String.valueOf(today));

                        ArrayList<model_Tuan_Parent> second = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> tuesday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> wednesday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> thursday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> friday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> saturday = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu2 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu2 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu2 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu3 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu3 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu3 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu4 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu4 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu4 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu5 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu5 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu5 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu6 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu6 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu6 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu7 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu7 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu7 = new ArrayList<>();


                        try {
                            JSONArray json = new JSONArray(response);
                            for(int i = 0; i < json.length(); i ++){
                                JSONObject obj = json.getJSONObject(i);
                                //cái thứ 1
                                if(obj.getString("ngaybatdau").equals(all_thu.get(0))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }else{
                                        toi_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop")));
                                    }
                                }
                                //cái thứ 2
                                if(obj.getString("ngaybatdau").equals(all_thu.get(1))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }else{
                                        toi_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }
                                }
                                //cái thứ 3
                                if(obj.getString("ngaybatdau").equals(all_thu.get(2))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                 //cái thứ 4
                                if(obj.getString("ngaybatdau").equals(all_thu.get(3))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                //cái thứ 5
                                if(obj.getString("ngaybatdau").equals(all_thu.get(4))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                //cái thứ 6
                                if(obj.getString("ngaybatdau").equals(all_thu.get(5))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                            }
                            //End for

                                second.add(new model_Tuan_Parent("Buổi sáng",sang_thu2));
                                second.add(new model_Tuan_Parent("Buổi chiều",chieu_thu2));
                                second.add(new model_Tuan_Parent("Buổi tối",toi_thu2));

                                tuesday.add(new model_Tuan_Parent("Buổi sáng",sang_thu3));
                                tuesday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu3));
                                tuesday.add(new model_Tuan_Parent("Buổi tối",toi_thu3));

                                wednesday.add(new model_Tuan_Parent("Buổi sáng",sang_thu4));
                                wednesday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu4));
                                wednesday.add(new model_Tuan_Parent("Buổi tối",toi_thu4));

                                thursday.add(new model_Tuan_Parent("Buổi sáng",sang_thu5));
                                thursday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu5));
                                thursday.add(new model_Tuan_Parent("Buổi tối",toi_thu5));

                                friday.add(new model_Tuan_Parent("Buổi sáng",sang_thu6));
                                friday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu6));
                                friday.add(new model_Tuan_Parent("Buổi tối",toi_thu6));

                                saturday.add(new model_Tuan_Parent("Buổi sáng",sang_thu7));
                                saturday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu7));
                                saturday.add(new model_Tuan_Parent("Buổi tối",toi_thu7));

                            list_tuan.add(new model_Tuan("Thứ 2",second,false));
                            list_tuan.add(new model_Tuan("Thứ 3",tuesday,false));
                            list_tuan.add(new model_Tuan("Thứ 4",wednesday,false));
                            list_tuan.add(new model_Tuan("Thứ 5",thursday,false));
                            list_tuan.add(new model_Tuan("Thứ 6",friday,false));
                            list_tuan.add(new model_Tuan("Thứ 7",saturday,false));

                            button.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Có lỗi xảy ra !!", Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(!lop.isEmpty()){
                    data.put("lop",lop);
                    data.put("today", d_m_y_present);
                    return data;
                }
                data.put("magv",magv);
                data.put("today", d_m_y_present);
                return data;
            }
        }
        ;
        queue.add(request);
    }
    public static void get_Week_of_Choose(Context context,String ngay,String lop,RecyclerView recyclerView,String magv){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"get_week_present.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                        Log.d(null,"#\n$%%^&*()&^\n%$@@$^&\n\n\t\t"+response);
                        ArrayList<String> all_thu;
                        try{
                            all_thu = get_Weeks(String.valueOf(ngay));
                        }catch(Exception ex){
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                            LocalDate date = LocalDate.parse(ngay, formatter);
                            all_thu = get_Weeks(String.valueOf(date));
                        }


                        ArrayList<model_Tuan_Parent> second = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> tuesday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> wednesday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> thursday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> friday = new ArrayList<>();
                        ArrayList<model_Tuan_Parent> saturday = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu2 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu2 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu2 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu3 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu3 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu3 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu4 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu4 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu4 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu5 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu5 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu5 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu6 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu6 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu6 = new ArrayList<>();

                        ArrayList<model_Tuan_child> sang_thu7 = new ArrayList<>();
                        ArrayList<model_Tuan_child> chieu_thu7 = new ArrayList<>();
                        ArrayList<model_Tuan_child> toi_thu7 = new ArrayList<>();


                        try {
                            JSONArray json = new JSONArray(response);
                            for(int i = 0; i < json.length(); i ++){
                                JSONObject obj = json.getJSONObject(i);
                                //cái thứ 1
//                                Toast.makeText(context, obj.getString("ngaybatdau"), Toast.LENGTH_SHORT).show();
                                if(obj.getString("ngaybatdau").equals(all_thu.get(0))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }else{
                                        toi_thu2.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop")));
                                    }
                                }
                                //cái thứ 2
                                if(obj.getString("ngaybatdau").equals(all_thu.get(1))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }else{
                                        toi_thu3.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop") ));
                                    }
                                }
                                //cái thứ 3
                                if(obj.getString("ngaybatdau").equals(all_thu.get(2))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu4.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                //cái thứ 4
                                if(obj.getString("ngaybatdau").equals(all_thu.get(3))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu5.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                //cái thứ 5
                                if(obj.getString("ngaybatdau").equals(all_thu.get(4))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu6.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                                //cái thứ 6
                                if(obj.getString("ngaybatdau").equals(all_thu.get(5))){
                                    int tietbatdau = Integer.parseInt(obj.getString("tietbatdau"));
                                    if(tietbatdau < 5){
                                        sang_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));

                                    }else if(tietbatdau > 5 && tietbatdau < 10){
                                        chieu_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau") ));
                                    }else{
                                        toi_thu7.add(new model_Tuan_child(obj.getString("tietbatdau"),obj.getString("tietketthuc"),obj.getString("sophong"),obj.getString("tenmon"),obj.getString("hoten"),obj.getString("tenlop"),obj.getString("ngaybatdau")));
                                    }
                                }
                            }
                            //End for
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        second.add(new model_Tuan_Parent("Buổi sáng",sang_thu2));
                        second.add(new model_Tuan_Parent("Buổi chiều",chieu_thu2));
                        second.add(new model_Tuan_Parent("Buổi tối",toi_thu2));

                        tuesday.add(new model_Tuan_Parent("Buổi sáng",sang_thu3));
                        tuesday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu3));
                        tuesday.add(new model_Tuan_Parent("Buổi tối",toi_thu3));

                        wednesday.add(new model_Tuan_Parent("Buổi sáng",sang_thu4));
                        wednesday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu4));
                        wednesday.add(new model_Tuan_Parent("Buổi tối",toi_thu4));

                        thursday.add(new model_Tuan_Parent("Buổi sáng",sang_thu5));
                        thursday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu5));
                        thursday.add(new model_Tuan_Parent("Buổi tối",toi_thu5));

                        friday.add(new model_Tuan_Parent("Buổi sáng",sang_thu6));
                        friday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu6));
                        friday.add(new model_Tuan_Parent("Buổi tối",toi_thu6));

                        saturday.add(new model_Tuan_Parent("Buổi sáng",sang_thu7));
                        saturday.add(new model_Tuan_Parent("Buổi chiều",chieu_thu7));
                        saturday.add(new model_Tuan_Parent("Buổi tối",toi_thu7));

                        ArrayList<model_Tuan> list_tuan = new ArrayList<>();
                        list_tuan.add(new model_Tuan("Thứ 2",second,false));
                        list_tuan.add(new model_Tuan("Thứ 3",tuesday,false));
                        list_tuan.add(new model_Tuan("Thứ 4",wednesday,false));
                        list_tuan.add(new model_Tuan("Thứ 5",thursday,false));
                        list_tuan.add(new model_Tuan("Thứ 6",friday,false));
                        list_tuan.add(new model_Tuan("Thứ 7",saturday,false));


                        Adapter_tuan adapter = new Adapter_tuan(list_tuan);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setHasFixedSize(true);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Có lỗi xảy ra !!" + "["+lop+"]", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(!magv.isEmpty()){
                    data.put("today",ngay);
                    data.put("magv",magv);
                    return data;
                }
                data.put("today",ngay);
                data.put("lop",lop);
                return data;

            }
        };
        queue.add(request);
    }
    public static ArrayList<String> get_Weeks(String ngay){
        ArrayList<String> arr = new ArrayList<>();
        LocalDate today = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            today = LocalDate.parse(ngay, DateTimeFormatter.ofPattern("yyyy-M-dd"));
            int dayOfWeek = today.getDayOfWeek().getValue(); // Lấy thứ của ngày hiện tại (1: Thứ 2, 2: Thứ 3, ..., 7: Chủ nhật)
            for (int i = 1; i <= 7; i++) { // Lặp qua từ thứ 2 đến chủ nhật
                int diff = i - dayOfWeek;
                LocalDate date = null; // Tính ngày của thứ i
                date = today.plusDays(diff);
                arr.add(String.valueOf(date));
            }
        }
        return arr;
    }
//    ....
    public static void getHocKy_Json(Context context, ArrayList<HocKy> list_hocky,String hocky,String lop,RecyclerView recyclerView,String magv){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"gethocky.php";
        StringRequest request = new StringRequest(
                Request.Method.POST,
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONArray json = new JSONArray(response);
                            for(int i = 0 ; i < json.length(); i ++){
                                JSONObject obj = json.getJSONObject(i);
                                list_hocky.add(new HocKy(obj.getString("tenmon"),obj.getString("idmonhoc"),obj.getString("hoten"),obj.getString("tongsotiet"),obj.getString("thoigian"),false));
                            }
                            Adapater_HocKy hocKy = new Adapater_HocKy(list_hocky, new OnclickButton() {
                                @Override
                                public void OnClickButton(String tenmon) {
//                                    Toast.makeText(context, "@#$@$%#$\n#^%$^&$%&\n"+tenmon+"\n\nĐây là tên  monn", Toast.LENGTH_LONG).show();
//                                    Log.d(null,"@#$@$%#$\n#^%$^&$%&\n"+tenmon+"\n\nĐây là tên  monn");
                                    Intent intent = new Intent(context,LietKe_sv.class);
                                    intent.putExtra("class",lop);
                                    intent.putExtra("monhoc",tenmon);
                                    intent.putExtra("magv",magv);
                                    context.startActivity(intent);
                                }
                            });
                            recyclerView.setAdapter(hocKy);
                        }catch (Exception ex){
                            Toast.makeText(context, ex.toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> data = new HashMap<>();
                if(!magv.isEmpty()){
                    data.put("hocky",hocky);
                    data.put("magv",magv);
                    return data;
                }
                data.put("hocky",hocky);
                data.put("lop",lop);
                return data;
            }
        }
        ;
        queue.add(request);
    }

}
