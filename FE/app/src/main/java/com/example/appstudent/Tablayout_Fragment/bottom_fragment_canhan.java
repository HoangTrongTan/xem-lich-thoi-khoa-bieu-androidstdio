package com.example.appstudent.Tablayout_Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appstudent.Controller.ThongTinCaNhanControll;
import com.example.appstudent.MainActivity;
import com.example.appstudent.R;
import com.example.appstudent.TrangChu;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class bottom_fragment_canhan extends Fragment {
    private BottomSheetDialog bottomSheetDialog;
    private TextView caNhan,tensv,masv,btnsua;
    private CircleImageView anh;
    private RecyclerView Recyclethongtinchung,recycleCanhan;
    private Button btn_dangxuat;
    public bottom_fragment_canhan() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_canhan, container, false);

            caNhan = view.findViewById(R.id.caNhan);
            tensv = view.findViewById(R.id.tensv);tensv.setText(TrangChu.current_sinhvien.getHoTen());
            masv = view.findViewById(R.id.masv);masv.setText(TrangChu.current_sinhvien.getMa());
            anh = view.findViewById(R.id.anh);
            btnsua = view.findViewById(R.id.btnsua);
            btn_dangxuat = view.findViewById(R.id.btn_dangxuat);
            btn_dangxuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TrangChu.current_sinhvien = null;
                    startActivity(new Intent(getContext(), MainActivity.class));
                }
            });
            Recyclethongtinchung = view.findViewById(R.id.Recyclethongtinchung);
            recycleCanhan = view.findViewById(R.id.recycleCanhan);
            Recyclethongtinchung.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            recycleCanhan.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));
            if(TrangChu.current_sinhvien.getGioiTinh() == 1){
                anh.setImageResource(R.drawable.namgiaumat);
            }else{
                anh.setImageResource(R.drawable.nugiaumat);
            }
        ThongTinCaNhanControll.setThognTinChung(getContext(),Recyclethongtinchung,recycleCanhan, TrangChu.current_sinhvien.getMa());
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.BottomSheet);

                View view = LayoutInflater.from(getContext()).inflate(R.layout.bottom_sheet_thongtincanhan,
                        null);
                TextInputLayout txt_input_Email = view.findViewById(R.id.txt_input_Email),
                        txt_input_phone = view.findViewById(R.id.txt_input_phone);
                TextInputEditText txtEmail = view.findViewById(R.id.txtEmail),txtphone = view.findViewById(R.id.txtphone);
                DatePicker ngaysinh = view.findViewById(R.id.ngaysinh);
                RadioButton rdNam = view.findViewById(R.id.rdNam),
                        rdNu = view.findViewById(R.id.rdNu);
                txtEmail.setText(TrangChu.current_sinhvien.getEmail());
                txtphone.setText(TrangChu.current_sinhvien.getDienthoai());
                if (TrangChu.current_sinhvien.getGioiTinh() == 1) {
                    rdNam.setChecked(true);
                } else {
                    rdNu.setChecked(true);
                }
                int[] date = getYearMonthDay(TrangChu.current_sinhvien.getNgaySinh());
                ngaysinh.init(date[0], date[1], date[2], null);
                Button capnhat = view.findViewById(R.id.capnhat);

                capnhat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(validateEmail(txt_input_Email) && validatePhone(txt_input_phone)){
                            ThongTinCaNhanControll.CapNhatNgaysinhDienThoaiEmailGioiTinh(getContext(),new String[]{
                                    (ngaysinh.getYear()+"-"+ngaysinh.getDayOfMonth() +"-"+ngaysinh.getMonth()),
                                    txtphone.getText().toString().trim(),
                                    txtEmail.getText().toString().trim(),
                                    rdNam.isChecked()?"1":"0",
                                    TrangChu.current_sinhvien.getMa()
                            });
                            bottomSheetDialog.dismiss();
                        }else{
                            ThongTinCaNhanControll.CapNhat_toast(getContext(),R.drawable.bird,"Điền thông tin phù hợp để thay đổi !!");
                        }

                    }
                });

                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();
            }
        });

        return view;
    }


    private boolean validateEmail(TextInputLayout TXTL_signup_Email){
        String val = TXTL_signup_Email.getEditText().getText().toString().trim();
        String checkspaces = "[a-zA-Z0-9._-]+@[a-z]+.+[a-z]+";

        if(val.isEmpty()){
            TXTL_signup_Email.setError("Email không được để trống");
            return false;
        }else if(!val.matches(checkspaces)){
            TXTL_signup_Email.setError("Email không hợp lệ!");
            return false;
        }
        else {
            TXTL_signup_Email.setError(null);
            TXTL_signup_Email.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validatePhone(TextInputLayout TXTL_signup_SDT){
        String val = TXTL_signup_SDT.getEditText().getText().toString().trim();


        if(val.isEmpty()){
            TXTL_signup_SDT.setError("Điện thoại không được để trống");
            return false;
        }else if(val.length() != 10){
            TXTL_signup_SDT.setError("Số điện thoại không hợp lệ!");
            return false;
        }
        else {
            TXTL_signup_SDT.setError(null);
            TXTL_signup_SDT.setErrorEnabled(false);
            return true;
        }
    }
      private int[] getYearMonthDay(String datestr){
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
          Date date;
          try {
              date = sdf.parse(datestr);
          } catch (Exception e) {
              e.printStackTrace();
              return new int[]{0,0,0};
          }
          Calendar calendar = Calendar.getInstance();
          calendar.setTime(date);
          int year = calendar.get(Calendar.YEAR);
          int month = calendar.get(Calendar.MONTH);
          int day = calendar.get(Calendar.DAY_OF_MONTH);

          return new int[]{year,month,day};
      }
}