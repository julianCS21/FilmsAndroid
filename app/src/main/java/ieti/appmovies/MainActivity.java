package ieti.appmovies;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import ieti.appmovies.databinding.ActivityMainBinding;
import ieti.appmovies.network.model.Movie;
import ieti.appmovies.network.model.MoviesResponse;
import ieti.appmovies.network.service.OmdbApi;
import ieti.appmovies.network.RetrofitClient;
import ieti.appmovies.ui.MoviesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.searchButton.setOnClickListener(v -> {
            String searchQuery = binding.searchQueryText.getText().toString();
            searchMovie(searchQuery);
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(new MoviesAdapter(new ArrayList<>()));
    }

    private void searchMovie(String searchQuery) {
        OmdbApi service = RetrofitClient.getRetrofitInstance().create(OmdbApi.class);
        Call<MoviesResponse> call = service.getMovies(searchQuery, "a2935151");

        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if (response.isSuccessful()) {
                    MoviesResponse movieDto = response.body();

                    List<Movie> movies = response.body().getSearch();
                    if (movies != null) {
                        MoviesAdapter adapter = new MoviesAdapter(movies);
                        binding.recyclerView.setAdapter(adapter);
                        Log.d("MainActivity", "Movie: " + movieDto);
                    }

                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.e("MainActivity", "Error: " + t.getMessage(), t);
            }
        });

    }
}