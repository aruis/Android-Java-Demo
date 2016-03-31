package com.aruistar.myjavaapp.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.aruistar.myjavaapp.app.util.Consts;
import com.aruistar.myjavaapp.app.util.Tool;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    private static Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initListData();
    }

    private void initListData() {
        final ListView listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap data = (HashMap) adapterView.getAdapter().getItem(i);

                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CardActivity.class);
                intent.putExtra("hello", data);
                startActivityForResult(intent, 9527);
            }
        });


        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Consts.url + "task/list",
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.v("yseap", response.length() + "");
                        List dataList = null;
                        try {
                            dataList = Tool.toList(response);
                            final MyAdapter asdp = new MyAdapter(getApplicationContext(),
                                    dataList,
                                    R.layout.list_item, //行布局文件
                                    new String[]{"name", "detail", "imgUrl"},
                                    new int[]{R.id.mainTW, R.id.subTW, R.id.itemImageView}
                            );

                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    listView.setAdapter(asdp);
                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(jsonArrayRequest);

    }
}
