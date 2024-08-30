package com.oms.kuberstarline.Uitls;

import android.content.Context;
import android.provider.Settings;
import android.text.InputType;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.ImageView;

import com.oms.kuberstarline.R;

public class UtilClass {

    public static boolean isValidEmail(String email) {
        // Check if the email is not null and matches the pattern
        return email != null && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }


    public static String getDeviceId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static  boolean showPassword(EditText editText, ImageView imageView) {
        editText.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        imageView.setImageResource(R.drawable.baseline_visibility_off_24);
        return true;
    }


    public static  boolean hidePassword(EditText editText, ImageView imageView) {
        editText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        imageView.setImageResource(R.drawable.baseline_remove_red_eye_24);
        return false;
    }

}


