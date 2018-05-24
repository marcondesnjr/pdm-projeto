package marcondesnjr.github.io.wfalert.entity;

import java.time.LocalDateTime;
import java.util.Calendar;

public class Alert {

    private int id;
    private Mission mission;
    private Reward reward;
    private int quantidade;
    private Calendar end;

    public Alert() {
    }

    public Alert(int id, Mission mission, Reward reward, int quantidade, Calendar end) {
        this.id = id;
        this.mission = mission;
        this.reward = reward;
        this.quantidade = quantidade;
        this.end = end;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
}
