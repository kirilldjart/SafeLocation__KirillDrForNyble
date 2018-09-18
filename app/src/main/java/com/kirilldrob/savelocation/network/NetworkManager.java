package com.kirilldrob.savelocation.network;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private static final String BaseUrl = "https://api.unsplash.com/";
    // private static final String imageSearchUrl = "https://api.cognitive.microsoft.com/bing/v7.0/images/search?q=";
    private static volatile NetworkManager singleton = null;
    public final UnsplashApi unsplashApi;


    public NetworkManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        unsplashApi = retrofit.create(UnsplashApi.class);
    }

    public static NetworkManager getInstance() {
        if (singleton == null) {
            synchronized (NetworkManager.class) {
                if (singleton == null) {
                    singleton = new NetworkManager();
                }
            }
        }
        return singleton;
    }

    public void getData(final DataCallback dataCallback) {

        unsplashApi.getListCuratedCollection().enqueue(new Callback<List<CuratedCollection>>() {
                                                           @Override
                                                           public void onResponse(Call<List<CuratedCollection>> call, Response<List<CuratedCollection>> response) {
                                                               if (response.code() == 200) {
                                                                   CollectionsRepository.getInstance().collectionList = response.body();
                                                                   dataCallback.onDataReady();
                                                               } else dataCallback.onError(response.message());
                                                           }

                                                           @Override
                                                           public void onFailure(Call<List<CuratedCollection>> call, Throwable t) {
                                                               // showErrorMessage(context);
                                                               dataCallback.onError(t.getMessage());
                                                           }
                                                       }

        );


    }


}

 /* synchronous way
 callbackWeakReference = new WeakReference<>(callback);

        //never do that. summoning new thread is very expensive and can cause for a lot of error
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response<CuratedCollection> response = unsplashApi.listFilms().execute();
                    if (response.code() == 200) {
                        List<Film> results = response.body().getResults();
                        for (Film result : results) {
                            String url = imageSearchUrl + URLEncoder.encode(result.getTitle(), "UTF-8");
                            Response<ImageSearchResponse> imageSearchResponse = unsplashApi.filmPicture(url).execute();
                            ImageSearchResponse searchResponse = imageSearchResponse.body();
                            if (searchResponse != null) {
                                List<ImageValue> hits = searchResponse.getHits();
                                if (hits != null && hits.size() > 0) {
                                    result.setUrl(hits.get(0).getWebformatURL());
                                }
                            }
                        }
                        if (callbackWeakReference.get() != null) {
                            callbackWeakReference.get().onDataReady(results);
                        }
                    } else {
                        callbackWeakReference.get().onError(response.message());
                    }
                } catch (IOException e) {
                    callbackWeakReference.get().onError(e.getMessage());
                }
            }
        });
        thread.start();*/
