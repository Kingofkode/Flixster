package com.example.flixster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.example.flixster.databinding.ActivityMovieDetailsBinding;
import com.example.flixster.models.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String TAG = "MovieDetailsActivity";

    // Movie to display
    Movie movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Details");

        // Unwrap the movie passed via intent, using its simple name as a the key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d(TAG, String.format("Showing details for '%s", movie.getTitle()));

        // Set title and overview
        binding.tvTitle.setText(movie.getTitle());
        binding.tvOverview.setText(movie.getOverview());
        // Vote average is 0 - 10, converted to 0 - 5 by dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        binding.rbVoteAverage.setRating(voteAverage > 0 ? voteAverage / 2.0f : voteAverage);
        loadVideoForMovie();
    }

    public void onTrailerClick(View view) {
        Intent movieTrailerIntent = new Intent(this, MovieTrailerActivity.class);
        this.startActivity(movieTrailerIntent);
    }

    private void loadVideoForMovie() {
        String videosUrl = "https://api.themoviedb.org/3/movie/" + movie.getId() + "videos" + "?api_key=" + getString(R.string.tmdb_api_key);

        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(videosUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                JSONObject responseObject = json.jsonObject;
                Log.d(TAG, "onSuccess");
                try {
                    JSONArray results = responseObject.getJSONArray("results");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }


}