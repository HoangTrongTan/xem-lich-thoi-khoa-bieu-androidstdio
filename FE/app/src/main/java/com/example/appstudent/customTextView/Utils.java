package com.example.appstudent.customTextView;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {
    private static Typeface FFF_Tusj;
    private static Typeface GreatVibes_Regular;
    private static Typeface Pacifico;

    public static Typeface getFFF_Tusj(Context context) {
        if(FFF_Tusj == null){
            FFF_Tusj = Typeface.createFromAsset(context.getAssets(),"fonts/FFF_Tusj.ttf");
        }
        return FFF_Tusj;
    }

    public static Typeface getGreatVibes_Regular(Context context) {
        if(GreatVibes_Regular == null){
            GreatVibes_Regular = Typeface.createFromAsset(context.getAssets(),"fonts/GreatVibes-Regular.otf");
        }
        return GreatVibes_Regular;
    }

    public static Typeface getPacifico(Context context) {
        if(Pacifico == null){
            Pacifico = Typeface.createFromAsset(context.getAssets(),"fonts/Pacifico.ttf");
        }
        return Pacifico;
    }
}
