package com.example.appstudent.Controller;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.appstudent.URL.url;
import com.example.appstudent.adapter_type.AdapterQuyChe_QuyDinh;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class QuyCheControll {
    public static void getQuyChe(Context context, RecyclerView view){
        RequestQueue queue = Volley.newRequestQueue(context);
        String path = url.path+"getQuyChe.php";
        StringRequest request = new StringRequest(
                path,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(context, response, Toast.LENGTH_LONG).show();
//                        Log.d(null,"SDEGEHSATHAT\nERWERWGFSDF$%^$%^$%\n#$%#$^#%^#$%\n\t"+response+"\n\n");
                        List<Pair<String,String>> list_model = new ArrayList<>();
                        try{
                            JSONArray json = new JSONArray(response);
                            for(int i = 0 ; i < json.length(); i ++) {
                                JSONObject obj = json.getJSONObject(i);
                                list_model.add(new Pair<>(obj.getString("tieuDe"), obj.getString("Link")  ));
                            }
                            AdapterQuyChe_QuyDinh quyDinh = new AdapterQuyChe_QuyDinh(list_model);
                            view.setAdapter(quyDinh);
                            Toast.makeText(context, String.valueOf(quyDinh.getItemCount()), Toast.LENGTH_SHORT).show();
                        }catch(Exception ex){
                            Log.d(null,"Lỗi \n@##%#@^#%^#@%$\n$^@#^$%^$@%6\n@^$@%^@$%\n\n\t\t\t\t"+ex.toString());
                            Toast.makeText(context, "Có lỗi try catch", Toast.LENGTH_SHORT).show();
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
        );
        queue.add(request);
    }
}
