---
description: Explore
---

# Keşfet

getExploreHeadlines metodunda apideki tüm haberleri getirmek için domains=a dedik ve api keyimizi girdik burada içinde a harfi olan tüm haberleri karşımıza getiriyor. Hatalı isteklerde de hata mesajı veriyoruz. Main activityde de en aşağıda gözüktüğü gibi çağırıyoruz. showNews metoduyla haber başlıklarını recyclerView'a atıyoruz. CustomAdapter adında bir adapter oluşturuyoruz ve 3 adet parametre veriyoruz. İlk parametre bulunduğumuz aktiviteyi, 2.parametreyi haber başlıklarını list olarak göstereceğimiz için list, 3. parametreyi ise haber başlıklarına tıklanma olarak this kullanılır.

```
private void showNews(List<NewsHeadlines> list) {
    recyclerView = findViewById(R.id.recycler_main);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    adapter=new CustomAdapter(this,list,this);
    recyclerView.setAdapter(adapter);
}
```

```
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
```

```
public void exploreClick(View v){
    getExploreNews();
}
public void getExploreNews(){
    RequestManager manager = new RequestManager(this);
    manager.getExploreHeadlines(listener);
}
```
