package devfigas.com.mvpsample.ui.movies;

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

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.provider.MoviesProvider;
import devfigas.com.mvpsample.provider.MoviesProviderCallback;

public class MoviesPresenter implements SwipeRefreshLayout.OnRefreshListener, MoviesProviderCallback {

    private MoviesView mMoviesView;
    private MoviesProvider mMoviesProvider;

    public MoviesPresenter(MoviesView moviesView, MoviesProvider moviesProvider) {
        mMoviesView = moviesView;
        mMoviesProvider =  moviesProvider;
        moviesProvider.setMoviesProviderCallback(this);
        refreshMovies();//refresh on the start
    }

    /**
     * request movies from provider
     */
    public void refreshMovies(){
        mMoviesProvider.readMovies();
    }

    /**
     * Pull to refresh
     * Implemented by SwipeRefreshLayout.
     */
    @Override
    public void onRefresh() {
        refreshMovies();
    }

    /**
     * Receive response from provider and send to view
     * @param movies received
     */
    @Override
    public void onReadMovies(List<Movie> movies) {
        mMoviesView.hideProgress();
        mMoviesView.refreshList(movies);
    }

    /**
     * failure on the recupere provide response
     */
    @Override
    public void onFailureReadMovies() {
        mMoviesView.hideProgress();
    }
}
