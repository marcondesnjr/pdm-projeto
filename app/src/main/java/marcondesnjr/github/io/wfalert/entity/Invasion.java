package marcondesnjr.github.io.wfalert.entity;

public class Invasion {

    private int id;
    private String node;
    private String type;
    private int progress;

    public Invasion() {
    }

    public Invasion(int id, String node, String type, int progress) {
        this.id = id;
        this.node = node;
        this.type = type;
        this.progress = progress;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
