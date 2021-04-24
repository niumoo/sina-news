package com.wdbyte.news;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @author darcy
 * @date 2021/04/21
 */
public class SinaNewsPojo {

    @JSONField(name = "rich_text")
    private String richText;
    @JSONField(name = "update_time")
    private String updateTime;

    public String getRichText() {
        return richText;
    }

    public void setRichText(String richText) {
        this.richText = richText;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "<div class=\"w3-container w3-justify\"><p>" + updateTime + " " + richText.trim() + "</p></div>";
    }
}
