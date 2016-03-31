package com.aruistar.myjavaapp.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.aruistar.myjavaapp.app.util.Consts;
import com.aruistar.myjavaapp.app.util.ImageDownloader;

import java.util.HashMap;

/**
 * Created by liurui on 15/4/24.
 */
public class CardActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        HashMap data = null;
        if (getIntent() != null
                && getIntent().getExtras() != null
                && getIntent().getExtras().get("hello") != null) {
            data = (HashMap) getIntent().getExtras().get("hello");
        }

        if (data != null) {
            ((TextView) findViewById(R.id.text1)).setText(data.get("name").toString());
            ((TextView) findViewById(R.id.text2)).setText(data.get("detail").toString());

            new ImageDownloader((ImageView) findViewById(R.id.imageView)).execute(Consts.url + data.get("imgUrl"));
        }

    }
}
