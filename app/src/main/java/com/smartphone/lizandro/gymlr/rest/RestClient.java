package com.smartphone.lizandro.gymlr.rest;

import com.smartphone.lizandro.gymlr.ApplicationClass;
import com.smartphone.lizandro.gymlr.GymActivity;
//import com.smartphone.lizandro.gymlr.mvp.presenter.DashboardPresenter;
import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

import javax.inject.Singleton;


@Module (
        library = true
)
public class RestClient {

    private static RestClient restClient;
    public static MarcadoresInterface marcadoresInterface;

    public RestClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.marcadoresonline.com")
                .build();

        marcadoresInterface = restAdapter.create(MarcadoresInterface.class);
    }

    @Provides
    @Singleton
    public  MarcadoresInterface getInstance() {
        if (restClient == null)
            restClient = new RestClient();
        return marcadoresInterface;
    }



}
