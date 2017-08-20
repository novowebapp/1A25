package com.ph.webview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.widget.Toast;


public class WebAppInterface {
    Context mContext;

    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void miniImpressora(String toast) {
        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();

        byte[] decodedString = Base64.decode(toast, Base64.DEFAULT);
        MainActivity.decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }
}

