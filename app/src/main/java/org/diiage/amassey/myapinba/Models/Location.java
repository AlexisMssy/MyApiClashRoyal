package org.diiage.amassey.myapinba.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Location {
    public static class LocationBuilder {
        public static Location build(JSONObject jsonObject) throws JSONException {
            Location location = new Location();
            location.setName(jsonObject.getString("name"));
            location.setCountry(jsonObject.getBoolean("isCountry"));
            location.setCode(jsonObject.getString("code"));
            return location;
        }
    }

    private String name;
    private Boolean isCountry;
    private String code;

    public Location() {
    }

    public Location(String name, Boolean isCountry, String code) {
        this.name = name;
        this.isCountry = isCountry;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCountry() {
        return isCountry;
    }

    public void setCountry(Boolean country) {
        isCountry = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
