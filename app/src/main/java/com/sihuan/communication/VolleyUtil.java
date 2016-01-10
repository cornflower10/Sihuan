package com.sihuan.communication;

/**
 * Created by admin on 2016/1/10.
 */
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import java.io.File;

public class VolleyUtil {
    private static final String DEFAULT_CACHE_DIR = "volley";

    public VolleyUtil() {
    }

    public static RequestQueue newRequestQueue(Context context, HttpStack stack, int maxDiskCacheBytes) {
        File cacheDir = new File(context.getCacheDir(), "volley");
        String userAgent = "volley/0";

        try {
            String network = context.getPackageName();
            PackageInfo queue = context.getPackageManager().getPackageInfo(network, 0);
            userAgent = network + "/" + queue.versionCode;
        } catch (NameNotFoundException var7) {
            ;
        }

        if(stack == null) {
            if(VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                //stack = new HttpClientStack(AndroidHttpClient.newInstance(userAgent));
                stack = null;
            }
        }

        MMMNetwork network1 = new MMMNetwork((HttpStack)stack);
        RequestQueue queue1;
        if(maxDiskCacheBytes <= -1) {
            queue1 = new RequestQueue(new DiskBasedCache(cacheDir), network1);
        } else {
            queue1 = new RequestQueue(new DiskBasedCache(cacheDir, maxDiskCacheBytes), network1);
        }

        queue1.start();
        return queue1;
    }

    public static RequestQueue newRequestQueue(Context context, int maxDiskCacheBytes) {
        return newRequestQueue(context, (HttpStack)null, maxDiskCacheBytes);
    }

    public static RequestQueue newRequestQueue(Context context, HttpStack stack) {
        return newRequestQueue(context, stack, -1);
    }

    public static RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, (HttpStack)null);
    }
}