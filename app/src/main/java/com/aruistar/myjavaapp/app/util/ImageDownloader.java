package com.aruistar.myjavaapp.app.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by liurui on 15/4/27.
 */
public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {

    ImageView bmImagetesttest;
    static HashMap imgMap = new HashMap<String, Bitmap>();

    public ImageDownloader(ImageView bmImagetesttest) {
        this.bmImagetesttest = bmImagetesttest;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];

        if (imgMap.containsKey(urldisplay))
            return (Bitmap) imgMap.get("urldisplay");

        Bitmap mIcon11 = null;
        try {
            InputStream input = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(input);
            imgMap.put("urldisplay", mIcon11);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImagetesttest.setImageBitmap(result);
    }
}
