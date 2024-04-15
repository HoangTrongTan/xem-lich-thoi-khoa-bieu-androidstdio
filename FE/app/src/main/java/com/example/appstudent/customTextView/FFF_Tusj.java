package com.example.appstudent.customTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class FFF_Tusj extends AppCompatTextView {
    public FFF_Tusj(@NonNull Context context) {
        super(context);
        setFontsTextView();
    }

    public FFF_Tusj(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextView();
    }

    public FFF_Tusj(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextView();
    }
    private void setFontsTextView(){
        Typeface typeface = Utils.getFFF_Tusj(getContext());
        setTypeface(typeface);
    }
}
