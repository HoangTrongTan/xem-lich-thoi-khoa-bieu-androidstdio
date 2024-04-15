package com.example.appstudent.customTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class Pacifico extends AppCompatTextView{
    public Pacifico(@NonNull Context context) {
        super(context);
        setFontTextView();
    }

    public Pacifico(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontTextView();
    }

    public Pacifico(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontTextView();
    }
    private void setFontTextView(){
        Typeface typeface = Utils.getPacifico(getContext());
        setTypeface(typeface);
    }
}
