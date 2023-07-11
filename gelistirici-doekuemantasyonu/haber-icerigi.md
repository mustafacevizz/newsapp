# Haber İçeriği

Haberlerimize MainActivitymizdeki OnNewsClicked metoduyla giriş yapıp detaylarına ulaşıyoruz. Haber detaylarımızdaki tanımlamaları yaptığımız DetailsActivityde de aşağıda görüldüğü gibi tanımlamaları yapıyoruz ve picasso eklentisini bağlıyoruz. En alttaki kodumuz ise Recyclerview'daki öğelere atamalar yapıp tıklandığında çağırma işlemini yapar. Haber başlığında tıklandığında ise onclick metodu işler ve haber detayları görüntülenir.

```
public void OnNewsClicked(NewsHeadlines headlines) {
    startActivity(new Intent(MainActivity.this,DetailsActivity.class)
            .putExtra("data",headlines));
}
```

```
NewsHeadlines headlines;
    TextView txt_title,txt_author,txt_time,txt_detail,txt_content;
    ImageView img_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        txt_title=findViewById(R.id.text_detail_title);
        txt_author=findViewById(R.id.text_detail_author);
        txt_time=findViewById(R.id.text_detail_time);
        txt_detail=findViewById(R.id.text_detail_detail);
        txt_content=findViewById(R.id.text_detail_content);
        img_news=findViewById(R.id.img_detail_news);
        headlines= (NewsHeadlines) getIntent().getSerializableExtra("data");
        txt_title.setText(headlines.getTitle());
        txt_author.setText(headlines.getAuthor());
        txt_time.setText(headlines.getPublishedAt());
        txt_detail.setText(headlines.getDescription());
        txt_content.setText(headlines.getContent());
        Picasso.get().load(headlines.getUrlToImage()).into(img_news);
    }
}
```

```
@Override
public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

    holder.text_title.setText(headlines.get(position).getTitle());
    holder.text_source.setText(headlines.get(position).getSource().getName());
    if(headlines.get(position).getUrlToImage()!=null){
        Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.img_headline);
    }

    holder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            listener.OnNewsClicked(headlines.get(position));
        }
    });
}
```
