package com.example.anfal.newsapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private List<News> news;
    private NewsAdapter newsAdapter;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsAdapter = new NewsAdapter(this, new ArrayList<News>());
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(newsAdapter);
        LoaderManager loaderManager=getSupportLoaderManager();
        loaderManager.initLoader(1, null, this).forceLoad();
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(MainActivity.this);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        /** CHECK THE INTERNET CONNECTION */
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            newsAdapter.setNews(data);
        } else {
            /** Display warning message if there is no connection to Internet */
            connectivityMessage("NO INTERNET CONNECTION");
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        /** CHECK THE INTERNET CONNECTION */
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            newsAdapter.setNews(new ArrayList<News>());
        } else {
            /** Display warning message if there is no connection to Internet */
            connectivityMessage("NO INTERNET CONNECTION");
        }
    }

    public void connectivityMessage(String message) {
        Context context = getApplicationContext();
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setText(message);
        toast.show();
    }

}