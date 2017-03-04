package devfigas.com.mvpsample.rest;


import devfigas.com.mvpsample.BuildConfig;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

//provide api connection
public class RestClient {

    private EndPoints mEndPoints;

    public RestClient() {
        mEndPoints = new Retrofit.Builder()
               .baseUrl(BuildConfig.API_HOST)//see on the gradle
               .addConverterFactory(GsonConverterFactory.create())
               .build().create(EndPoints.class);
    }

    public EndPoints getEndPoins() {
        return mEndPoints;
    }
}
