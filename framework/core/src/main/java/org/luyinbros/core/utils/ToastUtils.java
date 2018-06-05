package org.luyinbros.core.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.lang.ref.SoftReference;

public class ToastUtils {
    private static SoftReference<Toast> toastReference;

    public static void show(@Nullable Context context, int res) {
        if (context != null) {
            Toast toast = getToast(context);
            toast.setText(res);
            toast.show();
        }

    }

    public static void show(@Nullable Context context, CharSequence text) {
        if (context != null) {
            Toast toast = getToast(context);
            toast.setText(text);
            toast.show();
        }
    }

    private static Toast getToast(@NonNull Context context) {
        if (toastReference == null || toastReference.get() == null) {
            toastReference = new SoftReference<>(Toast.makeText(context.getApplicationContext(), "1", Toast.LENGTH_SHORT));
        }
        return toastReference.get();
    }
}
