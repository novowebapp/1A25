package com.ph.webview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.webkit.JavascriptInterface;

import command.sdk.Command;
import command.sdk.PrintPicture;
import command.sdk.PrinterCommand;


public class WebAppInterface {
    Context mContext;

    public  Bitmap decodedByte = null;
    public int nPaperWidth = 384;
    public String t="f";



    /** Instantiate the interface and set the context */
    WebAppInterface(Context c) {
        mContext = c;
    }

    /** Show a toast from the web page */
    @JavascriptInterface
    public void miniImpressora(String toast) {
        t="f";
        decodedByte=null;
        //Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
        byte[] decodedString = Base64.decode(toast, Base64.DEFAULT);
        decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        while (t.equals("f")) {
            //Toast.makeText(mContext,"while false",Toast.LENGTH_SHORT).show();
            if(!decodedByte.equals(null)){
                //Toast.makeText(mContext,"entrou no if",Toast.LENGTH_SHORT).show();
                t="v";
                Print_BMP();
        }
        }

    }




    private void Print_BMP(){

        //	byte[] buffer = PrinterCommand.POS_Set_PrtInit();
        int nMode = 0;
        if(decodedByte != null)
        {
            /**
             * Parameters:
             * mBitmap  要打印的图片
             * nWidth   打印宽度（58和80）
             * nMode    打印模式
             * Returns: byte[]
             */
            byte[] data = PrintPicture.POS_PrintBMP(decodedByte, nPaperWidth, nMode);
            //	SendDataByte(buffer);
            SendDataByte(Command.ESC_Init);
            SendDataByte(Command.LF);
            SendDataByte(data);
            SendDataByte(PrinterCommand.POS_Set_PrtAndFeedPaper(30));
            SendDataByte(PrinterCommand.POS_Set_Cut(1));
            SendDataByte(PrinterCommand.POS_Set_PrtInit());
        }
    }
    private void SendDataByte(byte[] data) {

        if (MainActivity.mService.getState() != BluetoothService.STATE_CONNECTED) {
            //Toast.makeText(this, R.string.not_connected, Toast.LENGTH_SHORT).show();
            return;
        }
        MainActivity.mService.write(data);
    }
}

