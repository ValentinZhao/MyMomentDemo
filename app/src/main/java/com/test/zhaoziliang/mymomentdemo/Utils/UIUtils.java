package com.test.zhaoziliang.mymomentdemo.Utils;

import android.content.Context;

/**
 * Created by zhaoziliang on 16/10/26.
 */

public class UIUtils {
    public static int dip2Px(Context context, float dip){
        return (int) (dip * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
