package com.example.androidjsonbody;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String URL_TO_HIT = "https://jsonparsingdemo-cec5b.firebaseapp.com/jsonData/moviesData.txt";
    private HttpHandler httpHandler;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        httpHandler = new HttpHandler();

        new JsonTask().execute(URL_TO_HIT);
    }

    class JsonTask extends AsyncTask<String,String, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
        @Override
        protected List<Movie> doInBackground(String... strings) {
            String finalJsonObj = httpHandler.makeServiceCall(strings[0]);
            Log.d("TAG",finalJsonObj);


            try {

                List<Movie> movieList = new ArrayList<>();

                List<Movie.Cast> castList = new ArrayList<>();
                JSONObject parentObj = new JSONObject(finalJsonObj);
                JSONArray parentArray = parentObj.getJSONArray("movies");

                for (int i=0;i<parentArray.length();i++){

                    JSONObject movieObject = parentArray.getJSONObject(i);
                    JSONArray castArray = movieObject.getJSONArray("cast");

                    for (int j=0;j<castArray.length();j++){

                        JSONObject object = castArray.getJSONObject(j);
                        Movie.Cast cast =new Movie.Cast();
                        cast.setName(object.getString("name"));
                        castList.add(cast);
                    }

                    String movie = movieObject.getString("movie");
                    int year = movieObject.getInt("year");
                    float rating = movieObject.getLong("rating");
                    String duration = movieObject.getString("duration");
                    String director = movieObject.getString("director");
                    String tagline = movieObject.getString("tagline");
                    String image = movieObject.getString("image");
                    String story = movieObject.getString("story");

                    Movie movie1 = new Movie();
                    movie1.setMovie(movie);
                    movie1.setYear(year);
                    movie1.setRating(rating);
                    movie1.setDuration(duration);
                    movie1.setDirector(director);
                    movie1.setTagline(tagline);
                    movie1.setImage(image);
                    movie1.setStory(story);
                    movie1.setCast(castList);

                    movieList.add(movie1);

                }return movieList;
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Movie> movies) {
            super.onPostExecute(movies);

            MovieAdapter adapter = new MovieAdapter(MainActivity.this,movies);
           recyclerView.setAdapter(adapter);
        }
    }
}
