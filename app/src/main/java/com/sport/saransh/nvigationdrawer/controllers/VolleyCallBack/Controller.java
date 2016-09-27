package com.sport.saransh.nvigationdrawer.controllers.VolleyCallBack;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by SARANSH on 9/3/2016.
 */

public class Controller {
    public Typeface font;
    public Typeface Fontello;
    public AssetManager asset;

    public Controller(android.content.res.AssetManager Assets) {
        this.asset = Assets;
        font = Typeface.createFromAsset(asset, "fontawesome-webfont.ttf");
        Fontello = Typeface.createFromAsset(asset, "fontello.ttf");
    }

    public Button SetIconButton(Button button, String Text, String IconName) {
        Button btn = button;
        btn.setTypeface(font, Typeface.NORMAL);
        btn.setText(IconName + "" + Text, TextView.BufferType.NORMAL);
        return btn;
    }

    public TextView SetIconTextView(TextView txt, String Text, String IconName) {
        TextView txt1 = txt;
        txt1.setTypeface(font, Typeface.NORMAL);
        txt1.setText(Text + IconName, TextView.BufferType.NORMAL);
        return txt1;
    }
    public TextView SetIconTextView(TextView txt,String IconName) {
        TextView txt1 = txt;
        txt1.setTypeface(font, Typeface.NORMAL);
        txt1.setText(IconName, TextView.BufferType.NORMAL);
        return txt1;
    }

    public TextView SetIconTextView(TextView txt, String Text, String txt2, String IconName) {
        TextView txt1 = txt;
        txt1.setTypeface(font, Typeface.NORMAL);
        txt1.setText(Text + "" + IconName + " " + txt2, TextView.BufferType.NORMAL);
        txt1.setTextColor(Color.WHITE);
        return txt1;
    }

    public static class FontIcons {
        public static String degreeicon = "\u00B0";
        public static String github= "\uf113";
        public static String googleicon="\uf1a0";
        public static String linkedin ="\uf0e1";
        public static String phone ="\uf095";
        public static String facebook="\uf09a";


    }
}
