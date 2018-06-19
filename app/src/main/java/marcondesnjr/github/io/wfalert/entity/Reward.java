package marcondesnjr.github.io.wfalert.entity;

public class Reward {
    private String name;
    private int quant;
    private String imgRef;

    public Reward() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public String getImgRef() {
        return imgRef;
    }

    public void setImgRef(String imgRef) {
        this.imgRef = imgRef;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "name='" + name + '\'' +
                ", quant=" + quant +
                ", imgRef='" + imgRef + '\'' +
                '}';
    }
}
