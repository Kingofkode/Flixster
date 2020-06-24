package com.example.flixster.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie {

    String posterPath;
    String title;
    String overview;

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
    }
}
