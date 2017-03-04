package devfigas.com.mvpsample.ui.movies;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devfigas.com.mvpsample.App;
import devfigas.com.mvpsample.R;
import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.provider.MoviesProvider;
import devfigas.com.mvpsample.rest.EndPoints;
import devfigas.com.mvpsample.ui.movies.item.MoviesAdapter;

public class MoviesActivity extends AppCompatActivity implements MoviesView {

    private MoviesAdapter mMoviesAdapter;

    @BindView(R.id.movies_list)RecyclerView rvMoviesList;
    @BindView(R.id.movies_swipe)SwipeRefreshLayout srMoviesSwipe;

    private MoviesPresenter mMoviesPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        EndPoints endPoints = App.with(this).getEndPoints();


        srMoviesSwipe.setOnRefreshListener(mMoviesPresenter);
        mMoviesAdapter =  new MoviesAdapter(endPoints);
        rvMoviesList.setLayoutManager(new LinearLayoutManager(this));
        rvMoviesList.setAdapter(mMoviesAdapter);

        MoviesProvider moviesProvider = new MoviesProvider(this, endPoints);
        mMoviesPresenter =  new MoviesPresenter(this, moviesProvider);
    }

    /**
     * turn on refreshing apparence
     */
    @Override
    public void showProgress() {
        srMoviesSwipe.setRefreshing(true);
    }

    /**
     * turn off refreshing apparence
     */
    @Override
    public void hideProgress() {
        srMoviesSwipe.setRefreshing(false);
    }

    /**
     * refresh list
     * @param movies to show on the list
     */
    @Override
    public void refreshList(List<Movie> movies) {
        mMoviesAdapter.refresh(movies);
    }

}
