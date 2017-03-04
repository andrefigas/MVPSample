package devfigas.com.mvpsample.provider;

/*Copyright 2017 Andre Figas

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License. */

import android.content.Context;

import java.util.List;

import devfigas.com.mvpsample.data.dao.MovieDAO;
import devfigas.com.mvpsample.model.api.MoviesCollection;
import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.rest.EndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesProvider  {

    private EndPoints mEndPoints;
    private MovieDAO mMovieDAO;
    private MoviesProviderCallback mMoviesProviderCallback;

    public MoviesProvider(Context context, EndPoints endPoints){
        mMovieDAO = new MovieDAO(context);
        mEndPoints = endPoints;
    }

    public void setMoviesProviderCallback(MoviesProviderCallback moviesProviderCallback) {
        mMoviesProviderCallback = moviesProviderCallback;
    }

    /**
     * read movies from api, and case failure, try recupere from database
     */
    public void readMovies(){
        mEndPoints.getMovies().enqueue(new Callback<MoviesCollection>() {
            @Override
            public void onResponse(Call<MoviesCollection> call, Response<MoviesCollection> response) {
                List<Movie> movies = null;
                if(response.isSuccessful()){
                    movies = response.body().getMovies();
                    //database persist
                    mMovieDAO.clear();
                    for(Movie m : movies){
                        mMovieDAO.insert(m);
                    }

                }else{
                    movies = mMovieDAO.getAll();
                }
                if (movies == null) {
                    mMoviesProviderCallback.onFailureReadMovies();
                }else{
                    mMoviesProviderCallback.onReadMovies(movies);
                }

            }

            @Override
            public void onFailure(Call<MoviesCollection> call, Throwable t) {
                List<Movie> movies = mMovieDAO.getAll();
                if (movies == null) {
                    mMoviesProviderCallback.onFailureReadMovies();
                }else{
                    mMoviesProviderCallback.onReadMovies(movies);
                }
            }
        });
    }

}
