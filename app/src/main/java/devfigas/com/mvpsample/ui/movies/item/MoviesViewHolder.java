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

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import devfigas.com.mvpsample.R;
import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.model.domain.Synopsis;
import devfigas.com.mvpsample.provider.SynopsisProvider;
import devfigas.com.mvpsample.rest.EndPoints;

public class MoviesViewHolder extends RecyclerView.ViewHolder implements MovieItemView{

    @BindView(R.id.item_movie_title) TextView tvMovieTitle;
    @BindView(R.id.item_movie_category) TextView tvMovieCategory;
    @BindView(R.id.item_movie_progress) View vwProgress;
    @BindView(R.id.item_movie_synopsis) TextView tvSynopsis;

    private MovieItemPresenter mMovieItemPresenter;
    private SynopsisProvider mSynopsisProvider;

    public MoviesViewHolder(View itemView, EndPoints endPoints) {
        super(itemView);
        ButterKnife.bind(this,itemView);
        mSynopsisProvider =  new SynopsisProvider(endPoints);
        mMovieItemPresenter =  new MovieItemPresenter(mSynopsisProvider,this);
    }

    /**
     * refresh layout and listener on create item
     * @param movie to receive
     */
    public void bind(Movie movie){
        tvMovieTitle.setText(movie.getTitle());
        tvMovieCategory.setText(movie.getCategory());
        tvSynopsis.setOnClickListener(mMovieItemPresenter.onClick(movie));
    }

    /**
     * refresh layout on update
     * @param synopsis is the updated
     */
    @Override
    public void refreshSynopsis(Synopsis synopsis) {
        tvSynopsis.setText(synopsis.getSynopsis());
    }

    /**
     * turn on refreshing apparence
     */
    @Override
    public void showProgress() {
        tvSynopsis.setText("");
        vwProgress.setVisibility(View.VISIBLE);
    }
    /**
     * turn off refreshing apparence
     */
    @Override
    public void hideProgress() {
        vwProgress.setVisibility(View.GONE);
    }

}
