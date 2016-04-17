package com.example.m14x.aynctasklistview_itunes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.m14x.aynctasklistview_itunes.Controller.DownloadTask;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listView);
        try {
            getData();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void getData() throws MalformedURLException {
        URL url = new URL("http://itunes.apple.com/search?term=rock");
        DownloadTask task = new DownloadTask(listView,this);
        task.execute(url);
    }
}
