package devfigas.com.mvpsample;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import devfigas.com.mvpsample.rest.EndPoints;
import devfigas.com.mvpsample.rest.RestClient;

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

public class App extends Application{

    //provide singleton intance
    private RestClient mRestClient;

    @Override
    public void onCreate() {
        super.onCreate();
        mRestClient = new RestClient();
    }

    /**
     * static creator
     * @param appCompatActivity where application is nstantiated
     * @return Application on  custom App format
     */
    public static App with(AppCompatActivity appCompatActivity){
        return (App) appCompatActivity.getApplication();
    }
    /**
     * static creator
     * @param appCompatActivity where application is nstantiated
     * @return Application on  custom App format
     */
    public static App with(Fragment appCompatActivity){
        return (App) appCompatActivity.getActivity().getApplication();
    }

    public EndPoints getEndPoints(){
        return mRestClient.getEndPoins();
    }
}
