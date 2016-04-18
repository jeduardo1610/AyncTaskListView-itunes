package com.example.m14x.aynctasklistview_itunes;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.m14x.aynctasklistview_itunes.Controller.CustomAdapter;
import com.example.m14x.aynctasklistview_itunes.Controller.DownloadTask;
import com.example.m14x.aynctasklistview_itunes.Model.Pojo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private DownloadTask task;
    private ArrayList<Pojo> content = new ArrayList<Pojo>();
    private ConnectivityManager connManager;
    private NetworkInfo activeNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        connManager = (ConnectivityManager) getSystemService(this.CONNECTIVITY_SERVICE);
        activeNetwork = connManager.getActiveNetworkInfo();
        if (activeNetwork != null) {
            try {
                task = new DownloadTask(listView, getApplicationContext());

                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }else {
            Toast.makeText(this,"Unable to load",Toast.LENGTH_SHORT).show();
        }
    }

    public void getData() throws MalformedURLException {
        URL url = new URL("http://itunes.apple.com/search?term=rock");
        task.execute(url);
    }

}
