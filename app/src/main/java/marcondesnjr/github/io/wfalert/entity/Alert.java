package marcondesnjr.github.io.wfalert.entity;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public class Alert {

    private int id;
    private Mission mission;
    private List<Reward> reward;
    private int quantidade;
    private Calendar end;

    public Alert() {
    }

    public Alert(int id, Mission mission, List<Reward> reward, int quantidade, Calendar end) {
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

    public List<Reward> getReward() {
        return reward;
    }

    public void setReward(List<Reward> reward) {
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
