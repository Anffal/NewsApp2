package com.example.anfal.newsapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anfal on 8/26/17.
 */

public class Query {


    public static List<News> extractNews(String json) {

        List<News> news = new ArrayList<>();

        try {
            JSONObject jsonResponse = new JSONObject(json);
            JSONObject jsonResults = jsonResponse.getJSONObject("response");
            JSONArray resultsArray = jsonResults.getJSONArray("results");

            for (int i = 0; i < resultsArray.length(); i++) {
                JSONObject oneResult = resultsArray.getJSONObject(i);
                String title = oneResult.getString("webTitle");
                String date = oneResult.getString("webPublicationDate");
                // Substring the date only (without time)
                date = date.substring(0, 10);
                String section = oneResult.getString("sectionName");
                String webUrl = oneResult.getString("webUrl");
                news.add(new News(title, date, section, webUrl));
            }

        } catch (JSONException e) {
            Log.e("Queryutils", "Error parsing JSON response", e);
        }

        return news;
    }

}