package com.mcvz.apinewsapp;

import android.content.Context;
import android.widget.Toast;

import com.mcvz.apinewsapp.Models.NewsApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public void getNewsHeadlines(OnFetchDataListener listener, String category, String query){
        CallNewsApi callNewsApi= retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call=callNewsApi.callHeadlines("us",category,"98420c8fd2f44bd48b4ce13bf33b638c");
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()){
                        Toast.makeText(context, "Hata!!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(),response.message());
                }
                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("İstek Hatalı");

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void getExploreHeadlines(OnFetchDataListener listener) {
        CallNewsApi callNewsApi = retrofit.create(CallNewsApi.class);
        Call<NewsApiResponse> call = callNewsApi.callExploreNews("a", "98420c8fd2f44bd48b4ce13bf33b638c");
        try {
            call.enqueue(new Callback<NewsApiResponse>() {
                @Override
                public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(context, "Hata!!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                    listener.onError("İstek Hatalı");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public RequestManager(Context context) {
        this.context = context;
    }
    public interface CallNewsApi{
        @GET ("top-headlines")
        Call<NewsApiResponse> callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                //@Query("q") String query,
                @Query("apiKey") String api_key);
        @GET ("everything")
        Call<NewsApiResponse> callExploreNews(
                @Query("q") String domains,
                //@Query("country") String country,
                //@Query("category") String category,
                //@Query("q") String query,
                @Query("apiKey") String api_key);






    }
}
