package devfigas.com.mvpsample.ui.movies.item;

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

import android.view.View;

import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.model.domain.Synopsis;
import devfigas.com.mvpsample.provider.SynopsisProviderCallback;
import devfigas.com.mvpsample.provider.SynopsisProvider;

public class MovieItemPresenter implements SynopsisProviderCallback {

    private SynopsisProvider mSynopsisProvider;//provide connection with the net or database
    private MovieItemView mMovieItemView;//user interface

    public MovieItemPresenter(SynopsisProvider synopsisProvider, MovieItemView movieItemView) {
        mSynopsisProvider = synopsisProvider;
        mMovieItemView = movieItemView;
        mSynopsisProvider.setResumeMovieProviderCallback(this);

    }

    /**
     * Request on the provider
     * @param movie serched
     */
    public void loadSynopsis(final Movie movie){
        mSynopsisProvider.loadSynopsis(movie);
    }

    /**
     * Create custom listener using a Movie
     * @param movie clicked
     * @return custom listener instance
     */
    public Item onClick(Movie movie){
        return new Item(movie);
    }

    /**
     *send synopsis response from provider and send to
     * @param resumeMovie is the movie returned from provider
     */
    @Override
    public void onReadSynopsis(Synopsis resumeMovie) {
        mMovieItemView.refreshSynopsis(resumeMovie);
    }

    /**
     * failure on the recupere provide response
     */
    @Override
    public void onFailureReadSynopsis() {

    }

    /**
     * Custom Listner
     */
    public class Item implements View.OnClickListener{

        private Movie mMovie;
        public Item(Movie movie) {
            mMovie = movie;
        }

        @Override
        public void onClick(View v) {
            loadSynopsis(mMovie);
        }
    }
}
