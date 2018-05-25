package marcondesnjr.github.io.wfalert.entity;

import java.util.Calendar;

public class Fissure {

    private int id;
    private Tier tier;
    private Mission mission;
    private Calendar end;

    public Fissure() {
    }

    public Fissure(int id, Tier tier, Mission mission, Calendar end) {
        this.id = id;
        this.tier = tier;
        this.mission = mission;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
