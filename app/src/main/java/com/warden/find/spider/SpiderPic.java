package com.warden.find.spider;



import com.warden.find.db.Picture1024;
import com.warden.find.model.ImageModel;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpiderPic {
    public static List<ImageModel> doGet(String url) {
        List<ImageModel> imageModelList = new ArrayList<>();
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
        Document doc = null;
        try {
            doc = conn.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //System.out.println(doc.toString());

        // 获取tbody元素下的所有tr元素
        if (doc == null){
            return null;
        }
        Elements elements = doc.select("[class=tpc_content]");
        System.out.printf(elements.toString());
        Elements elements1 = elements.select("img");
        for(Element element : elements1) {
            System.out.println("dssssssssss"+element.toString());
            Elements Picture = element.select("img");
            String url1 = Picture.get(0).attr("src");
            String[] queryStringSplit = url1.split("/");
            String idString =queryStringSplit[queryStringSplit.length-1];
            String Id = idString.split(".j")[0];
            ImageModel imageModel = new ImageModel();
            imageModel.img_id = Id;
            imageModel.imgsrc = url1;
            imageModel.article_id = url;
            List<ImageModel> imageModels = DataSupport.where("img_id = ?",Id).find(ImageModel.class);
            if (imageModels.size() == 0){
                imageModel.save();
            }
            imageModelList.add(imageModel);
            System.out.println("Url:" + url1);
            System.out.println("ID:" + Id);
            System.out.println("---------------------------------");





            }


        return imageModelList;
    }
}


