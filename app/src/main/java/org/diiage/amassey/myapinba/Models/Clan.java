package org.diiage.amassey.myapinba.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Clan {

    public static class ClanBuilder {
        public static Clan build(JSONObject jsonObject) throws JSONException {
            Clan clan = new Clan();
            clan.setTag(jsonObject.getString("tag"));
            clan.setName(jsonObject.getString("name"));
            clan.setScore(jsonObject.getInt("score"));
            clan.setMemberCount(jsonObject.getInt("memberCount"));
            clan.setRank(jsonObject.getInt("rank"));
            clan.setPreviousRank(jsonObject.getInt("previousRank"));

            JSONObject myJSONObjectBadge = jsonObject.getJSONObject("badge");
            Badge badge = new Badge();
            badge.setImage(myJSONObjectBadge.getString("image"));
            badge.setCategory(myJSONObjectBadge.getString("category"));
            badge.setId(myJSONObjectBadge.getInt("id"));
            badge.setName(myJSONObjectBadge.getString("name"));
            clan.setBadge(badge);

            JSONObject myJSONObjectLocation = jsonObject.getJSONObject("location");
            Location location = new Location();
            location.setCode(myJSONObjectLocation.getString("code"));
            location.setCountry(myJSONObjectLocation.getBoolean("isCountry"));
            location.setName(myJSONObjectLocation.getString("name"));
            clan.setLocation(location);

            JSONObject myJSONObjectTracking = jsonObject.getJSONObject("tracking");
            Tracking tracking = new Tracking();
            tracking.setSnapshotCount(myJSONObjectTracking.getInt("snapshotCount"));
            tracking.setAvailable(myJSONObjectTracking.getBoolean("snapshotCount"));
            tracking.setActive(myJSONObjectTracking.getBoolean("active"));
            clan.setTracking(tracking);

            return clan;
        }
    }

    private String tag;
    private String name;
    private Integer score;
    private Integer memberCount;
    private Integer rank;
    private Integer previousRank;
    private Badge badge;
    private Location location;
    private Tracking tracking;

    public Clan() {
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(Integer memberCount) {
        this.memberCount = memberCount;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getPreviousRank() {
        return previousRank;
    }

    public void setPreviousRank(Integer previousRank) {
        this.previousRank = previousRank;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Tracking getTracking() {
        return tracking;
    }

    public void setTracking(Tracking tracking) {
        this.tracking = tracking;
    }
}
