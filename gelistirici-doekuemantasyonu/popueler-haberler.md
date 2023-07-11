---
description: Trendler
---

# Popüler Haberler

Buradaki kodlarımızda apimizin dökümantasyonundan yararlanıyoruz.\
Önce apimizi uygulamamıza ekledik. BaseUrl kısmına apimizin kök kısmını yazıyoruz\
getNewsHeadlines metodunda Apinin country kısmına hangi ülkenin haberlerini istiyorsak onun kısaltmasını ve ardından da api keyimizi ekliyoruz. Ardından tekrar dökümantasyonun bize belirttiği gibi haberleri @Get fonksiyonuyla belirtilen şekilde verileri çekiyoruz. Main activitymizdeki trendClick metodunda listener parametresi ile haber başlıklarını apiden alıyoruz.

```
public void trendClick(View v){
    RequestManager manager = new RequestManager(this);
    manager.getNewsHeadlines(listener,null,null);
}
```

```
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
```

```
@GET ("top-headlines")
Call<NewsApiResponse> callHeadlines(
        @Query("country") String country,
        @Query("category") String category,
        @Query("apiKey") String api_key);
```
