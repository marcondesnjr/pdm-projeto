package marcondesnjr.github.io.wfalert.entity;

public class Alert {

    private int id;
    private String planeta;

    public Alert(int id, String planeta) {
        this.id = id;
        this.planeta = planeta;
    }

    public Alert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaneta() {
        return planeta;
    }

    public void setPlaneta(String planeta) {
        this.planeta = planeta;
    }
}
