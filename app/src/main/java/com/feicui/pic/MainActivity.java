package com.feicui.pic;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private       RecyclerView            rv;
    private       SwipeRefreshLayout      swl;
    private       List<Jeson.ResultsBean> mList;
    public static String                  url;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.rv);
//        rv.setLayoutManager(new GridLayoutManager(this, 2));
        rv.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        rv.setItemAnimator(new DefaultItemAnimator());
        new MyAsTask().execute();
        Log.d(TAG, "onCreate: 1111111");
        swl = (SwipeRefreshLayout) findViewById(R.id.srl);
        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swl.setRefreshing(false);
                    }

                }, 3000);
                Toast.makeText(MainActivity.this, "刷新完成", Toast.LENGTH_SHORT).show();

            }

        });

    }

    class MyAsTask extends AsyncTask<String, Integer, String> {


        @Override
        protected String doInBackground(String... strings) {
            HttpOnline httpOnline = new HttpOnline();
            String     results    = httpOnline.getData();
            Log.d(TAG, "doInBackground: "+results);
            return results;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Parsegson(s);
            MyAdapter adapter=new MyAdapter(MainActivity.this,mList);
            rv.setAdapter(adapter);
        }

        private List<Jeson.ResultsBean> Parsegson(String jeson) {
            mList = new ArrayList<>();
            Gson gson=new Gson();
            Jeson jeson1 = gson.fromJson(jeson, Jeson.class);
            List<Jeson.ResultsBean> data=jeson1.getResults();
            for (int i = 0; i < data.size(); i++) {
                mList.add(data.get(i));
            }
            return mList;
        }
    }
}
