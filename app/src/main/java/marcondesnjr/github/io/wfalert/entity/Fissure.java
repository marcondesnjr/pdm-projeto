package marcondesnjr.github.io.wfalert.entity;

public class Fissure {

    private int id;
    private String node;
    private String tier;
    private  String type;
    private String faction;
    private Mission mission;

    public Fissure(int id, String node, String tier, String type, String faction) {
        this.id = id;
        this.node = node;
        this.tier = tier;
        this.type = type;
        this.faction = faction;
    }

    public Fissure() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public Mission getMission() {
        return mission;
    }

    public void setMission(Mission mission) {
        this.mission = mission;
    }
}
