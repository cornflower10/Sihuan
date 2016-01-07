package com.sihuan.util;

import android.support.annotation.StringRes;
import android.widget.Toast;

import com.sihuan.application.ApplicationVariable;

public class ToastUtil {

    private ToastUtil() {
    }

    public static void show(CharSequence text) {
        if (text.length() < 10) {
            Toast.makeText(ApplicationVariable.getInstance(), text, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(ApplicationVariable.getInstance(), text, Toast.LENGTH_LONG).show();
        }
    }

    public static void show(@StringRes int resId) {
        show(ApplicationVariable.getInstance().getString(resId));
    }

}