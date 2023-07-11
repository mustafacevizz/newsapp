# Kategoriye Göre Göster

Kategori butonlarımıza clickleri veriyoruz ve Apimizde belirlenen kategorileri düğmelere giriyoruz, girdiğimiz textler kategorilere atanıyor. dialog metodu ile bir mesaj gösteriyoruz.\
RequestManager ile istediğimiz kategorideki haber başlıklarını almamız için api çağrısı yapar ve getNewsHeadlines ile listener ve kategori parametreleriyle manager üzerinden ulaşır

```
@Override
public void onClick(View v) {
    Button button = (Button) v;
    String category= button.getText().toString();
    dialog.setTitle("Fetching news articles of"+category);
    dialog.show();
    RequestManager manager = new RequestManager(this);
    manager.getNewsHeadlines(listener,category,null);
}
```
