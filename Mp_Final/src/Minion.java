import java.io.Serializable;

public class Minion implements Serializable {

    private String name;
    private int hitPoints;
    private TMinion tipominion;
    public Minion(){}
    public Minion(String name, int hitPoints, TMinion tipominion){
        this.name = name;
        this.hitPoints = hitPoints;
        this.tipominion = tipominion;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public TMinion getTipominion() {
        return tipominion;
    }

    public TMinion setTipominion(TMinion tipominion) {
        this.tipominion = tipominion;
        return tipominion;
    }
}
