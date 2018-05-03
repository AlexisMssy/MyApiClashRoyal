package org.diiage.amassey.myapinba.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class Tracking {
    public static class TrackingBuilder {
        public static Tracking build(JSONObject jsonObject) throws JSONException {
            Tracking tracking = new Tracking();
            tracking.setActive(jsonObject.getBoolean("active"));
            tracking.setAvailable(jsonObject.getBoolean("available"));
            tracking.setSnapshotCount(jsonObject.getInt("snapshotCount"));
            return tracking;
        }
    }

    private Boolean active;
    private Boolean available;
    private Integer snapshotCount;

    public Tracking() {
    }

    public Tracking(Boolean active, Boolean available, Integer snapshotCount) {
        this.active = active;
        this.available = available;
        this.snapshotCount = snapshotCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getSnapshotCount() {
        return snapshotCount;
    }

    public void setSnapshotCount(Integer snapshotCount) {
        this.snapshotCount = snapshotCount;
    }
}
