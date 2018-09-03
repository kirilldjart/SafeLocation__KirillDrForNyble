package com.kirilldrob.h7fragments.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Url;

public interface UnsplashApi {
    @GET("collections/curated")
    @Headers("Authorization: Client-ID 06c783d32037a6b8a79942b316bef84ec45897a847059a4c44672b30a8c7bea4")
    Call<List<CuratedCollection>> getListCuratedCollection(); // there are 2 part of all collection - favorite and curated

    //@GET("collections/curated/:id")
   // @Headers("Authorization: Client-ID 06c783d32037a6b8a79942b316bef84ec45897a847059a4c44672b30a8c7bea4")
   // Call<CollectionIDResponse> filmPicture(@Url String url);
}
