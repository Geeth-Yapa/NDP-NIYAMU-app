package com.greenarmy;


import android.app.Dialog;
import android.content.Context;


public class Util {

    public static Dialog showProgress(Context context){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.modal);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}
