package org.diiage.amassey.myapinba.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Badge {
    public static class BadgeBuilder {
        public static Badge build(JSONObject jsonObject) throws JSONException {
            Badge badge = new Badge();
            badge.setName(jsonObject.getString("name"));
            badge.setCategory(jsonObject.getString("category"));
            badge.setId(jsonObject.getInt("id"));
            badge.setImage(jsonObject.getString("image"));
            return badge;
        }
    }

    private String name;
    private String category;
    private Integer id;
    private String image;

    public Badge() {
    }

    public Badge(String name, String category, Integer id, String image) {
        this.name = name;
        this.category = category;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
