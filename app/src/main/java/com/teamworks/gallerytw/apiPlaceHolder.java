package com.teamworks.gallerytw;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface apiPlaceHolder {

    @GET("photos")
    Call<List<apiItem>> getApiItems();
}
