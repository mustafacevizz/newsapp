# Arama

Kullanıcı metin arama sorgusunda setOnQueryTextListener metodu çağırılır. Kullanıcıya mesaj gösterilir. RequestManager sınıfından örnek oluşturulur ve apiye çağrı yapılır. getNewsHeadlines metodunda listener ve general parametreleriyle istek gönderir.

```
searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
    @Override
    public boolean onQueryTextSubmit(String query) {
        dialog.setTitle("Fetching news articles of "+query);
        dialog.show();
        RequestManager manager = new RequestManager(MainActivity.this);
        manager.getNewsHeadlines(listener,"general",query);
        return true;
    }
```
