package marcondesnjr.github.io.wfalert.entity;

import java.util.Random;

public class Mission {

    private String id;
    private String nome;
    private String tipo;
    private String faction;

    public Mission() {
    }

    public Mission(String id, String nome, String tipo, String faction) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.faction = faction;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    @Deprecated
    public static Mission random(){

        String[] nodes = {"Epimetheus (Saturn)","Perdita (Uranus)","Nix (Pluto)","Elion (Mercury)", "Ultor (Mars)"};
        String[] types = {"Mobile Defense", "Extermination", "Survival"};
        String[] faction = {"Corpus", "Grineer", "Infested"};
        Random r = new Random();


        return new Mission("Node",nodes[r.nextInt(5)],types[r.nextInt(3)],faction[r.nextInt(3)]);

    }

}
