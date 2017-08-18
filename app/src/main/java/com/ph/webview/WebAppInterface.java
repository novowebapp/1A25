package com.ph.webview;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by aepedro.rodrigues on 18/08/2017.
 */

public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void miniImpressora(String toast) {
       // Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}

