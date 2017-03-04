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

import devfigas.com.mvpsample.model.domain.Movie;
import devfigas.com.mvpsample.model.domain.Synopsis;
import devfigas.com.mvpsample.rest.EndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SynopsisProvider {

    private EndPoints mEndPoints;
    private SynopsisProviderCallback mResumeMovieProviderCallback;

    public SynopsisProvider(EndPoints endPoints){
        mEndPoints = endPoints;
    }

    public void setResumeMovieProviderCallback(SynopsisProviderCallback resumeMovieProviderCallback) {
        mResumeMovieProviderCallback = resumeMovieProviderCallback;
    }

    /**
     * read synopsis from api
     */
    public void loadSynopsis(Movie movie){
        mEndPoints.getSynopsis(movie.getTitle()).enqueue(new Callback<Synopsis>() {
            @Override
            public void onResponse(Call<Synopsis> call, Response<Synopsis> response) {
                Synopsis resumeMovie = null;
                if(response.isSuccessful()){
                    resumeMovie = response.body();
                }
                if(resumeMovie == null){
                    mResumeMovieProviderCallback.onFailureReadSynopsis();
                }else{
                    mResumeMovieProviderCallback.onReadSynopsis(resumeMovie);
                }
            }

            @Override
            public void onFailure(Call<Synopsis> call, Throwable t) {
                mResumeMovieProviderCallback.onFailureReadSynopsis();
            }
        });
    }
}
