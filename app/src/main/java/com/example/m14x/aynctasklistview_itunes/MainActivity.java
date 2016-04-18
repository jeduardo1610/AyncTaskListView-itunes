package com.example.m14x.aynctasklistview_itunes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        try {
            task = new DownloadTask(listView,getApplicationContext());
            getData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getData() throws MalformedURLException {
        URL url = new URL("http://itunes.apple.com/search?term=rock");
        task.execute(url);
    }

}
