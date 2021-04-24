package com.wdbyte.news;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author darcy
 * @date 2021/04/20th
 */
public class SinaNews {
    private static String url
        = "https://zhibo.sina.com.cn/api/zhibo/feed?zhibo_id=152&id=&tag_id=0&page=1&page_size=30&type=0";

    public static void main(String[] args) throws IOException {
        String content = HttpUtls.getHttpContent(url);
        JSONObject jsonObject = JSON.parseObject(content);
        JSONArray jsonArray = jsonObject
            .getJSONObject("result")
            .getJSONObject("data")
            .getJSONObject("feed")
            .getJSONArray("list");

        List<SinaNewsPojo> sinaNewsList = JSON.parseArray(jsonArray.toString(), SinaNewsPojo.class);
        for (SinaNewsPojo sinaNewsPojo : sinaNewsList) {
            sinaNewsPojo.setUpdateTime(sinaNewsPojo.getUpdateTime().substring(5));
        }
        String sinaNews = sinaNewsList.stream().map(SinaNewsPojo::toString).collect(Collectors.joining());
        String sinaHtml = new String(Files.readAllBytes(Paths.get("sina.html")), "utf-8");
        sinaHtml = sinaHtml.replace("${content}", sinaNews);

        Path indexPath = Paths.get("index.html");
        Files.deleteIfExists(indexPath);
        Files.createFile(indexPath);
        Files.write(indexPath, sinaHtml.getBytes("utf-8"));
    }
}
