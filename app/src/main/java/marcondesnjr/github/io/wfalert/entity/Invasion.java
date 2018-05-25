package marcondesnjr.github.io.wfalert.entity;

public class Invasion {

    private int id;
    private int progress;
    private  Mission mission;
    private  Reward attReward;
    private int attRewardQt;
    private Reward defReward;
    private int defRewardQt;

    public Invasion() {
    }

    public Invasion(int id, int progress, Mission mission, Reward attReward, int attRewardQt, Reward defReward, int defRewardQt) {
        this.id = id;
        this.progress = progress;
        this.mission = mission;
        this.attReward = attReward;
        this.attRewardQt = attRewardQt;
        this.defReward = defReward;
        this.defRewardQt = defRewardQt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }

    public Reward getAttReward() {
        return attReward;
    }

    public void setAttReward(Reward attReward) {
        this.attReward = attReward;
    }

    public int getAttRewardQt() {
        return attRewardQt;
    }

    public void setAttRewardQt(int attRewardQt) {
        this.attRewardQt = attRewardQt;
    }

    public Reward getDefReward() {
        return defReward;
    }

    public void setDefReward(Reward defReward) {
        this.defReward = defReward;
    }

    public int getDefRewardQt() {
        return defRewardQt;
    }

    public void setDefRewardQt(int defRewardQt) {
        this.defRewardQt = defRewardQt;
    }
}
