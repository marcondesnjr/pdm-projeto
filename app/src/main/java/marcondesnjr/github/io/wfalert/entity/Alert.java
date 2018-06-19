package marcondesnjr.github.io.wfalert.entity;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class Alert {
    private String id;
    private int minLevel;
    private int maxLevel;
    private Mission mission;
    private List<Reward> rewards;
    private Calendar expiry;

    public Alert() {
    }


    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }


    public List<Reward> getRewards() {
        return rewards;
    }

    public void setRewards(List<Reward> rewards) {
        this.rewards = rewards;
    }

    public Calendar getExpiry() {
        return expiry;
    }

    public void setExpiry(Calendar expiry) {
        this.expiry = expiry;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
//</editor-fold>


    @Override
    public String toString() {
        return "Alert{" +
                "id='" + id + '\'' +
                ", minLevel=" + minLevel +
                ", maxLevel=" + maxLevel +
                ", mission=" + mission +
                ", rewards=" + rewards +
                ", expiry=" + expiry +
                '}';
    }

}