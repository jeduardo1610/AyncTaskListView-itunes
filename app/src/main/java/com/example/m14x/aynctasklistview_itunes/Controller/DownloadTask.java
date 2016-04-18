package com.example.m14x.aynctasklistview_itunes.Controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.m14x.aynctasklistview_itunes.MainActivity;
import com.example.m14x.aynctasklistview_itunes.Model.Pojo;
import com.example.m14x.aynctasklistview_itunes.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m14x on 04/14/2016.
 */
public class DownloadTask extends AsyncTask<URL,Void,String> {

    private ArrayList<Pojo> pojoList = new ArrayList<Pojo>();
    private Context context;
    private ListView listView;

    public DownloadTask(ListView listView,Context context){
       this.context = context;
        this.listView = listView;
    }
    @Override
    protected String doInBackground(URL... params) {

        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;
        try{
            url = params[0];
            urlConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            int data = reader.read();

            while(data != -1){

                char current = (char) data;
                result += current;
                data = reader.read();

            }
            return result;


        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

  try {
            JSONObject jsonObject = new JSONObject(s);

            String info = jsonObject.getString("results");
            JSONArray jsonArray = new JSONArray(info);

            for (int i = 0; i<jsonArray.length(); i++){
                Pojo pojo = new Pojo();
                JSONObject jsonPart = jsonArray.getJSONObject(i);
                pojo.setCollectionName(jsonPart.optString("collectionName","Unknown"));
                pojo.setTrackName(jsonPart.optString("trackName","Unknown"));
                pojo.setArtistName(jsonPart.optString("artistName","Unknown"));
                pojoList.add(pojo);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        fill();

    }

    public void fill(){
        CustomAdapter adapter = new CustomAdapter(context, R.layout.item, pojoList);
        listView.setAdapter(adapter);
    }
}
