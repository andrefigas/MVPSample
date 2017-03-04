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

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import devfigas.com.mvpsample.R;
import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.rest.EndPoints;
import devfigas.com.mvpsample.ui.movies.MoviesView;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder>{

    private EndPoints mEndPoints;
    private ArrayList<Movie> mMovies =  new ArrayList<>();//objects to list


    public MoviesAdapter(EndPoints endPoints) {
        mEndPoints =  endPoints;
    }

    /**
     * refresh list
     * @param movies to the show
     */
    public void refresh(List<Movie> movies){
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        return new MoviesViewHolder(view,mEndPoints);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


}
