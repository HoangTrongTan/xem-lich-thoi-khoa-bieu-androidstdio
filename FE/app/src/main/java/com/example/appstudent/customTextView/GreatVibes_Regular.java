package com.example.appstudent.customTextView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class GreatVibes_Regular extends AppCompatTextView {
    public GreatVibes_Regular(@NonNull Context context) {
        super(context);
        setFontTextView();
    }

    public GreatVibes_Regular(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontTextView();
    }

    public GreatVibes_Regular(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontTextView();
    }
    private void setFontTextView(){
        Typeface typeface = Utils.getGreatVibes_Regular(getContext());
        setTypeface(typeface);
    }
}
