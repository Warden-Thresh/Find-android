package com.warden.find.spider;
import com.warden.find.model.NewsModel;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Spider1024 {
    public static List<NewsModel> doGet(String url) {
        List<NewsModel> articalList = new ArrayList<>();
        // write your code here
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
        Document doc = null;
        try {
            doc = conn.get();
            //System.out.println(doc.toString());

            // 获取tbody元素下的所有tr元素
            Elements elements = doc.select("[class=tr3 t_one]");
            //System.out.printf(elements.toString());
            Elements elements1 = elements.select("h3");
            for (Element element : elements1) {
                Elements Article = element.select("a");
                String Title = Article.text();
                String articleUrl = "http://1024.luj8le.rocks/pw/"+Article.get(0).attr("href");
                String[] queryStringSplit = articleUrl.split("/");
                String idString = queryStringSplit[queryStringSplit.length - 1];
                String Id = idString.split(".h")[0];

                //String time = element.select("td.text-left cxxt-holdtime").first().text();
                if (!"read.".equals(Id)) {
                    String address = element.getElementsByClass("text-success company").text();


                    NewsModel article = new NewsModel();
                    article.setDocid(Id);
                    article.setTitle(Title);
                    article.setUrl(articleUrl);
                    //article.imgextra = SpiderPic.doGet(articleUrl );
                    article.photosetID = Id;
                    article.setType("1024");
                    article.save();
                    articalList.add(article);

                    System.out.println("标题：" + Title);
                    System.out.println("Url:" + articleUrl);
                    System.out.println("ID:" + Id);
                    System.out.println("---------------------------------");





                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return articalList;
    }
}


