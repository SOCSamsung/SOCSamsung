package soc.samsung.dto;

import com.google.gson.Gson;

public class Behavior {
    private String behavior;

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }

    public String getBehavior() {
        return behavior;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
